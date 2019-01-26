package com.bjpowernode.seckill.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.seckill.activemq.MessageService;
import com.bjpowernode.seckill.commons.CommonsConstants;
import com.bjpowernode.seckill.commons.CommonsReturnObject;
import com.bjpowernode.seckill.mapper.GoodsMapper;
import com.bjpowernode.seckill.mapper.OrdersMapper;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.model.Orders;
import com.bjpowernode.seckill.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName:GoodsServiceImpl
 * package:com.bjpowernode.seckill.service.impl
 * Descrption:
 *
 * @Date:2018/8/7 16:31
 * @Author:724班
 */
@Component
@Service(timeout = 10000, interfaceClass = GoodsService.class) //dubbo注解
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * RedisTemplate
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MessageService messageService;

    @Override
    public List<Goods> getAllSeckillGoods() {
        return goodsMapper.selectAllSeckillGoods();
    }

    @Override
    public Goods getSeckillGoodsById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 执行秒杀处理
     *
     * @param id
     * @param random
     * @return
     */
    @Override
    public CommonsReturnObject execSeckill(Integer uid, Integer id, String random) {

        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();

        //1、验证用户器请求的秒杀标志是否正确
        Goods goods = goodsMapper.selectSeckillGoods(id, random);
        if (null == goods) {
            //请求参数不合法
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("请求参数有误");
            return commonsReturnObject;
        }

        //2、验证秒杀是否开始（因为前面已经验证过，所以者一步是可有可无的）
        //秒杀开始时间
        long startTime = goods.getStarttime().getTime();
        //秒杀结束时间
        long endTime = goods.getEndtime().getTime();
        //当前时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime) {
            //秒杀未开始, 不能返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀未开始");
            return commonsReturnObject;

        } else if (nowTime > endTime) {
            //秒杀已结束，不能返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀已结束");
            return commonsReturnObject;

        } else {
            //秒杀真正开始了

            //3、验证一下用户是否秒杀过该商品(需求：一个用户只能参与秒杀一次，只能秒杀一件商品)
            //思路：用redis实现，用户秒杀后把redis中设置一个标志，我们基于该标志判断用户是否秒杀过
            String hold = redisTemplate.opsForValue().get(CommonsConstants.REDIS_HOLD + id + ":" + uid);
            if (StringUtils.isNotEmpty(hold)) {
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("您已经秒杀过该商品，不能再秒杀了~");
                return commonsReturnObject;
            }

            //4、验证一下商品是否已经被抢光了 (库存>0，可以秒杀，否则不能秒杀)
            Integer store = StringUtils.isEmpty(redisTemplate.opsForValue().get(CommonsConstants.REDIS_STORE + id)) ? 0 : Integer.parseInt(redisTemplate.opsForValue().get(CommonsConstants.REDIS_STORE + id));
            if (store <= 0) {
                //没有库存，商品卖光了
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("来晚了，商品已经抢光了~");
                return commonsReturnObject;
            }

            //5、限流（限制访问流量）
            //思路：我们设定一个访问流量的最大值，如果超过该值，就拒绝后续的访问，给用户提示一个诙谐幽默的语句
            long maxLimt = 100000; // store * 1000;
            long currentLimit = redisTemplate.opsForList().size(CommonsConstants.REDIS_LIMIT + id);
            if (currentLimit >= maxLimt) {
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("服务器太挤了，没有挤进去~");
                return commonsReturnObject;
            } else {
                System.out.println("放入限流List..............................");
                redisTemplate.opsForList().leftPush(CommonsConstants.REDIS_LIMIT + id, String.valueOf(uid));
            }

            //6、可以正式秒杀
            //1、减库存  2、下单 （需要在数据库中操作，由于秒杀是高并发场景，瞬间流量很大，不要把请求直接落入到数据库）

            //redis中减库存 （库存超卖的问题：锁（数据库的锁，性能不行）（单台部署的，线程锁 syn，性能不行）（分布式锁）、队列 的思路解决）

            //redis是单线程的，你操作redis的时候会排队的，10个同时减库存，那么也是一个一个去减，有先后顺序
            long left = redisTemplate.opsForValue().increment(CommonsConstants.REDIS_STORE + id, -1);

            if (left >= 0) {

                //用户没有买过该商品，到这一步就直接在redis放标志，表示他已经买过了
                redisTemplate.opsForValue().set(CommonsConstants.REDIS_HOLD + id + ":" + uid, String.valueOf(uid));

                //可以下订单
                //MQ，流量削峰、异步处理

                //发送订单消息
                Orders orders = new Orders();
                orders.setBuynum(1);
                orders.setBuyprice(goods.getPrice());
                orders.setCreatetime(new Date());
                orders.setGoodsid(id);
                orders.setOrdermoney(goods.getPrice().multiply(new BigDecimal(1)));
                orders.setStatus(1);//待支付
                orders.setUid(uid);

                String ordersJSON = JSONObject.toJSONString(orders);
                messageService.sendMessage(ordersJSON);

                //此时返回秒杀结果，不是最终结果，是一个中间结果
                commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
                commonsReturnObject.setErrorMessage("秒杀请求成功，正在处理......");
                return commonsReturnObject;

            } else {
                //不能下订单，这个时候库存被减成负数了
                //redis的库存变成负数了，但是这个是不影响正常业务逻辑，只是数据不好看，也是可以处理一下
                redisTemplate.opsForValue().increment(CommonsConstants.REDIS_STORE + id, 1);

                //此时返回秒杀结果，不是最终结果，是一个中间结果
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("来晚了，商品已经抢光了~");
                return commonsReturnObject;
            }
        }
    }

    /**
     * 下订单
     *
     * @param orders
     * @return
     */
    @Transactional
    @Override
    public int createOrders(Orders orders) {

        int insert = ordersMapper.insertSelective(orders);

        if (insert > 0) {
            //下单成功
            //秒杀流程执行完了，那么将限流List里面弹出一个元素，以便于后面的用户可以再进来一个
            redisTemplate.opsForList().rightPop(CommonsConstants.REDIS_LIMIT + orders.getGoodsid());

            //整个秒杀流程执行完了，应该让前台页面知道最终的秒杀结果，服务端向客户端推的技术：websocket,
            //通过前台查询后台结果（前台轮询后台，3秒查一次，页面的js定时任务）

            //把最终的秒杀结果放入到redis中，以便于前台可以轮询该秒杀结果
            CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
            commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
            commonsReturnObject.setErrorMessage("秒杀成功");
            commonsReturnObject.setData(orders);
            String seckillResult = JSONObject.toJSONString(commonsReturnObject);
            redisTemplate.opsForValue().set(CommonsConstants.REDIS_RESULT + orders.getGoodsid() + ":" + orders.getUid(), seckillResult);

        } else {
            //下单失败
            throw new RuntimeException("秒杀下单失败");
        }
        return insert;
    }

    /**
     * 对异常的处理
     *
     * @param orders
     */
    @Override
    public void processException (Orders orders) {

        //要把redis库存加回去
        redisTemplate.opsForValue().increment(CommonsConstants.REDIS_STORE + orders.getGoodsid(), 1);

        //把已经抢购过的标志删除，否则用户没法再买了
        redisTemplate.delete(CommonsConstants.REDIS_HOLD + orders.getGoodsid() + ":" + orders.getUid());

        //秒杀流程执行完了，那么将限流List里面弹出一个元素，以便于后面的用户可以再进来一个
        redisTemplate.opsForList().rightPop(CommonsConstants.REDIS_LIMIT + orders.getGoodsid());

        //把最终的秒杀结果放入到redis中，以便于前台可以轮询该秒杀结果
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        commonsReturnObject.setErrorCode(CommonsConstants.ONE);
        commonsReturnObject.setErrorMessage("秒杀失败");
        String seckillResult = JSONObject.toJSONString(commonsReturnObject);
        redisTemplate.opsForValue().set(CommonsConstants.REDIS_RESULT + orders.getGoodsid() + ":" + orders.getUid(), seckillResult);
    }

    /**
     * 查询秒杀最终结果
     *
     * @param uid
     * @param id
     * @return
     */
    @Override
    public CommonsReturnObject querySeckillResult(Integer uid, Integer id) {

        String result = redisTemplate.opsForValue().get(CommonsConstants.REDIS_RESULT + id + ":" + uid);

        CommonsReturnObject commonsReturnObject = StringUtils.isEmpty(result) ? new CommonsReturnObject() : JSONObject.parseObject(result, CommonsReturnObject.class);

        return commonsReturnObject;
    }

    /**
     * 同步redis库存到数据库
     *
     * @return
     */
    @Override
    public int updateStore(Integer goodId, Integer store) {
        return goodsMapper.updateStore(goodId, store);
    }
}
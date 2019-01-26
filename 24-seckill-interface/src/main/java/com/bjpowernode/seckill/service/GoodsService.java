package com.bjpowernode.seckill.service;

import com.bjpowernode.seckill.commons.CommonsReturnObject;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.model.Orders;

import java.util.List;

/**
 * ClassName:GoodsService
 * package:com.bjpowernode.seckill.service
 * Descrption:
 *
 * @Date:2018/8/7 16:28
 * @Author:724班
 */
public interface GoodsService {

    /**
     * 查询所有的秒杀商品
     *
     * @return
     */
    public List<Goods> getAllSeckillGoods();

    /**
     * 根据商品id查询某个秒杀商品
     *
     * @param id
     * @return
     */
    public Goods getSeckillGoodsById(Integer id);

    /**
     * 执行秒杀处理
     *
     * @param id
     * @param random
     * @return
     */
    public CommonsReturnObject execSeckill(Integer uid, Integer id, String random);

    /**
     * 下订单
     *
     * @param orders
     * @return
     */
    public int createOrders(Orders orders);

    /**
     * 下单的异常处理
     *
     * @param orders
     */
    public void processException (Orders orders);

    /**
     * 查询秒杀最终结果
     *
     * @param uid
     * @param id
     * @return
     */
    public CommonsReturnObject querySeckillResult(Integer uid, Integer id);

    /**
     * 同步redis库存到数据库
     *
     * @return
     */
    int updateStore(Integer goodId, Integer store);
}
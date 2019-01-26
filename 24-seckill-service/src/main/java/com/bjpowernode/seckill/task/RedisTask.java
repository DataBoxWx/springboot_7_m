package com.bjpowernode.seckill.task;

import com.bjpowernode.seckill.commons.CommonsConstants;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ClassName:RedisTask
 * package:com.bjpowernode.seckill.task
 * Descrption:
 *
 * @Date:2018/8/9 16:41
 * @Author:724班
 */
@Configuration
@EnableScheduling //开启定时任务支持
public class RedisTask {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 每5秒执行一次，初始化redis的库存
     *
     * 10点开始秒杀，那么我可以9:30把redis库存初始化一下  （0 30 9 * * *）
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void initStore () {

        List<Goods> goodsList = goodsService.getAllSeckillGoods();

        for (Goods goods : goodsList) {
            Integer store = goods.getStore();

            //放入redis，只初始化一遍
            redisTemplate.opsForValue().setIfAbsent(CommonsConstants.REDIS_STORE + goods.getId(), String.valueOf(store));
        }
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void syncStore2DB () {

        //所有的redis库存key拿到
        Set<String> keySet =  redisTemplate.keys(CommonsConstants.REDIS_STORE + "*");

        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()) {

            //redis的key
            String key = iterator.next();

            //通过redis的key拿到对应的值
            Integer store = Integer.parseInt(redisTemplate.opsForValue().get(key));

            //商品id
            String goodId = key.split(":")[2];

            //把redis的库存值同步到mysql数据库
            goodsService.updateStore(Integer.parseInt(goodId), store);

        }
    }
}

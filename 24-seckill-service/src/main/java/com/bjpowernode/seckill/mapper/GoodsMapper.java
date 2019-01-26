package com.bjpowernode.seckill.mapper;

import com.bjpowernode.seckill.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectAllSeckillGoods();

    /**
     * mybatis传多个参数
     *
     * 1、用户Map传
     * 2、用对象传
     * 3、用@Param注解传
     *
     * @param id
     * @param random
     * @return
     */
    Goods selectSeckillGoods(@Param("id") Integer id, @Param("random") String random);

    /**
     * 更新数据库库存
     *
     * @param goodId
     * @param store
     * @return
     */
    int updateStore(@Param("goodId") Integer goodId, @Param("store") Integer store);
}
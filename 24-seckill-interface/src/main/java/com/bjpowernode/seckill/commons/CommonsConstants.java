package com.bjpowernode.seckill.commons;

/**
 * ClassName:CommonsConstants
 * <p>company:www.bjpowernode.com</p>
 *
 * @Date 2018/4/3 11:24
 * @Author 724班
 */
public class CommonsConstants {

    //表示成功状态
    public static final String ZERO = "0";

    //表示失败状态
    public static final String ONE = "1";

    public static final String SESSION_USER = "user";

    /**
     * 定义redis的秒杀标志key（有没有秒杀过） redis:hold:1:128
     */
    public static final String REDIS_HOLD = "redis:hold:";

    /**
     * 定义redis的库存key --> redis:store:1
     */
    public static final String REDIS_STORE = "redis:store:";

    /**
     * 定义redis的限流key --> redis:store:1
     */
    public static final String REDIS_LIMIT = "redis:limit:";

    /**
     * 定义redis的秒杀结果key --> redis:store:1
     */
    public static final String REDIS_RESULT = "redis:result:";
}
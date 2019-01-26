package com.bjpowernode.multidb.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 实现一个动态数据源，在程序运行的时候，决定采用哪个数据源
 *
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/6 17:45
 * @Author:724班
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DB3307 = "db3307";
    public static final String DB3308 = "db3308";
    public static final String DB3309 = "db3309";
    public static final String DB3310 = "db3310";

    /**
     * 设置好数据源的 key(字符串) -- value(数据源)
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalHolder.getDataSourceKey();
    }
}

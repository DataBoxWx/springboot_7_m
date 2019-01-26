package com.bjpowernode.multidb.datasource;

/**
 * ClassName:ThreadLocalHolder
 * package:com.bjpowernode.multidb.datasource
 * Descrption:
 *
 * @Date:2018/8/6 17:53
 * @Author:724班
 */
public class ThreadLocalHolder {

    public static ThreadLocal<String> holder = new ThreadLocal<String>();

    public static String getDataSourceKey() {
        return holder.get();
    }

    public static void setDataSourceKey(String dataSourceKey) {
        holder.set(dataSourceKey);
    }
}

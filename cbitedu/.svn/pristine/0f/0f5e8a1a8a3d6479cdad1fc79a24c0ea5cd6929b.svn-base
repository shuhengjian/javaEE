package com.creatorblue.cbitedu.core.multidatasource;

/**
 * 切换数据源(设置或者获得上下文)的工具类
 * User: zhujw
 * Date: 2015
 * Time: 下午3:22
 */
public class ContextHolder {

    private static final ThreadLocal<DBType> holder = new ThreadLocal<DBType>();

    public static void setDbType(DBType dbType) {
        holder.set(dbType);
    }

    public static DBType getDbType() {
        return (DBType) holder.get();
    }

    public static void clearDbType() {
        holder.remove();
    }

}

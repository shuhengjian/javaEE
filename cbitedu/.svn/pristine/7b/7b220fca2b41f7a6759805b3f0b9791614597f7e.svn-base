package com.creatorblue.cbitedu.core.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA.
 * User: zhujw
 * Date: 15-4-26
 * Time: 下午3:23
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        DBType key = ContextHolder.getDbType();//获得当前数据源标识符
        logger.info("当前数据源 :" + key);
        return key;
    }

}

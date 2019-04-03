package com.creatorblue.cbitedu.core.interceptor;

import java.io.StringReader;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.log4j.Logger;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;
import com.creatorblue.cbitedu.core.utils.AopTargetUtils;
import com.creatorblue.cbitedu.core.utils.ReflectHelper;


/**
 * @ClassName: ACInterceptor
 * @Description: 数据权限拦截
 * @author tbw
 * @date 2014-4-28 下午4:21:43
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args =  {
        Connection.class}
    )
})
public class ACInterceptor implements Interceptor {
    private final static Logger log = Logger.getLogger(ACInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        target = AopTargetUtils.getProxyTarget(target); //得到代理的原始Plugin对象

        if (!(target instanceof RoutingStatementHandler)) { //搜索Plugin里的target对象

            if (target instanceof Plugin) { //搜索Plugin里的target对象
                target = ReflectHelper.getValueByFieldName(target, "target");
            } else {
                return invocation.proceed();
            }
        }

        RoutingStatementHandler statementHandler = (RoutingStatementHandler) target;
        BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
                "delegate");
        MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
                "mappedStatement");

        if (mappedStatement.getSqlCommandType() != SqlCommandType.SELECT) { //tbw 判断是不是查询

            return invocation.proceed();
        }

        // 获得BoundSql对象
        BoundSql boundSql = delegate.getBoundSql();
        String originalSql = boundSql.getSql().trim();

        Object parameterObject = boundSql.getParameterObject();

        if (parameterObject instanceof ParamMap) {
            Map map = (Map) parameterObject;
        }

        if ((boundSql == null) || (boundSql.getSql() == null) ||
                "".equals(boundSql.getSql())) {
            return null;
        }

        AConfig[] aConfig = null;

        if (parameterObject instanceof BaseDomain) {
            List<AConfig> aConfigs = ((BaseDomain) parameterObject).getAConfigs();

            if (aConfigs != null) {
                aConfig = aConfigs.toArray(new AConfig[0]);
            }
        } else if (parameterObject instanceof Map) {
            Map map = (Map) parameterObject;

            for (Object object : map.values()) {
                if (object instanceof AConfig[]) { //找到权限配置的参数
                    aConfig = (AConfig[]) object;
                }
            }

            if (aConfig == null) {
                for (Object object : map.values()) {
                    if (object instanceof AConfig) { //找到权限配置的参数
                        aConfig = new AConfig[] { (AConfig) object };
                    }
                }
            }
        }

        if ((aConfig == null) || (aConfig.length == 0)) {
            return invocation.proceed();
        }

        CCJSqlParserManager parserManager = new CCJSqlParserManager();

        try {
            Select select = (Select) parserManager.parse(new StringReader(
                        originalSql)); //解析SQL语句
            SelectBody body = select.getSelectBody();
            body.accept(new SelectVisitorImpl(aConfig)); //访问SQL并根据SQL中涉及的表来增强SQL

            ExpressionDeParser expressionDeParser = new ExpressionDeParser();
            StringBuilder stringBuffer = new StringBuilder();
            SelectDeParser deParser = new OracleSelectDeParser(expressionDeParser,
                    stringBuffer); //针对ORACLE的SQL生成
            expressionDeParser.setSelectVisitor(deParser);
            expressionDeParser.setBuffer(stringBuffer);

            body.accept(deParser);
            log.info("权限SQL：" + stringBuffer.toString());

            ReflectHelper.setValueByFieldName(boundSql, "sql",
                stringBuffer.toString()); //tbw 将sql语句反射回BoundSql.
        } catch (JSQLParserException e) {
        }

        return invocation.proceed();
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties arg0) {
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
        SqlSource newSqlSource) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());

        String[] s = ms.getKeyProperties();

        if (s == null) {
            builder.keyProperty(null);
        } else {
            builder.keyProperty(s[0]);
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());

        MappedStatement newMs = builder.build();

        return newMs;
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}

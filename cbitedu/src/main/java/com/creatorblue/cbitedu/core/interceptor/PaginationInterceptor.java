package com.creatorblue.cbitedu.core.interceptor;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import org.apache.log4j.Logger;

import com.creatorblue.cbitedu.core.page.Dialect;
import com.creatorblue.cbitedu.core.page.HSQLDialect;
import com.creatorblue.cbitedu.core.page.MySql5Dialect;
import com.creatorblue.cbitedu.core.page.OracleDialect;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.page.PostgreSQLDialect;
import com.creatorblue.cbitedu.core.page.SQLServer2005Dialect;
import com.creatorblue.cbitedu.core.page.SybaseDialect;
import com.creatorblue.cbitedu.core.utils.AopTargetUtils;
import com.creatorblue.cbitedu.core.utils.ReflectHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Properties;


//只拦截select部分查询分页拦截器，用户拦截SQL，并加上分页的参数和高级查询条件 StatementHandler和Executor区分
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args =  {
        Connection.class}
    )
})
public class PaginationInterceptor implements Interceptor {
    private final static Logger log = Logger.getLogger(PaginationInterceptor.class);
    private String dbtype;
    private Dialect dialect;

    public void initDialect() {
        // 分页查询 本地化对象修改数据库注意修改实现
        //String[] dbtypes=StringUtils.tokenizeToStringArray(dbtype,",");
        if ((dbtype != null) && dbtype.equals("mysql")) {
            dialect = new MySql5Dialect();
        } else if ((this.dbtype != null) && dbtype.equals("oracle")) {
            dialect = new OracleDialect();
        } else if ((this.dbtype != null) && dbtype.equals("postgresSQL")) {
            dialect = new PostgreSQLDialect();
        } else if ((this.dbtype != null) && dbtype.equals("hsql")) {
            dialect = new HSQLDialect();
        } else if ((this.dbtype != null) && dbtype.equals("SQLServer")) {
            dialect = new SQLServer2005Dialect();
        } else if ((this.dbtype != null) && dbtype.equals("Sybase")) {
            dialect = new SybaseDialect();
        } else {
            throw new RuntimeException(
                "the value of the dialect property in applicationContext-hihsoft-framework.xml is not defined : ");
        }
    }

    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        target = AopTargetUtils.getProxyTarget(target); //得到代理的原始Plugin对象

        if (!(target instanceof RoutingStatementHandler)) { //搜索Plugin里的target对象

            if (target instanceof Plugin) {
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

        if ((boundSql == null) || (boundSql.getSql() == null) ||
                "".equals(boundSql.getSql())) {
            return null;
        }

        // 分页参数--上下文传参：解析参数，获取高级查询参数信息
        Page page = null;

        // map传参和baseDomain传参
        if (parameterObject != null) {
            if (parameterObject instanceof ParamMap) { //多个mybatis入参

                ParamMap paramMap = (ParamMap) parameterObject;

                for (Object ob : paramMap.values()) {
                    page = (Page) ReflectHelper.isPage(ob, "page");

                    if (page != null) { //以第一个找到的page对象为准

                        break;
                    }
                }
            } else {
                page = (Page) ReflectHelper.isPage(parameterObject, "page");
            }
        }

        if (page == null)  {
            return invocation.proceed();
        }

        if ((page != null) && (page.isPagination() == true)) {
            int totpage = page.getTotalRows();

            // 得到总记录数
            StringBuffer countSql = new StringBuffer(originalSql.length() +
                    100);
            countSql.append("select count(1) from (").append(originalSql)
                    .append(") t");

            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countStmt = null;
            ResultSet rs = null;

            try {
                countStmt = connection.prepareStatement(countSql.toString());

                //tbw 这里，使用boundSql来设置countBS的参数，目的是boundSql里面包含了扩展参数，而countBS里面没有，会导致foreach这样的处理赋值失败
                setParameters(countStmt, mappedStatement, boundSql,
                    parameterObject);

                rs = countStmt.executeQuery();

                if (rs.next()) {
                    totpage = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (countStmt != null) {
                    countStmt.close();
                }
            }

            // 分页计算
            page.init(totpage, page.getPageSize(), page.getCurrentPage());

            String pagesql = dialect.getLimitString(originalSql,
                    page.getPageSize() * (page.getCurrentPage() - 1),
                    page.getPageSize());

            ReflectHelper.setValueByFieldName(boundSql, "sql", pagesql); //tbw 将分页sql语句反射回BoundSql.
        }

        return invocation.proceed();
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties arg0) {
    }

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps,
        MappedStatement mappedStatement, BoundSql boundSql,
        Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters")
                    .object(mappedStatement.getParameterMap().getId());

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = (parameterObject == null) ? null
                                                              : configuration.newMetaObject(parameterObject);

            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);

                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);

                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(
                                parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(
                                ForEachSqlNode.ITEM_PREFIX) &&
                            boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());

                        if (value != null) {
                            value = configuration.newMetaObject(value)
                                                 .getValue(propertyName.substring(
                                        prop.getName().length()));
                        }
                    } else {
                        value = (metaObject == null) ? null
                                                     : metaObject.getValue(propertyName);
                    }

                    TypeHandler typeHandler = parameterMapping.getTypeHandler();

                    if (typeHandler == null) {
                        throw new ExecutorException(
                            "There was no TypeHandler found for parameter " +
                            propertyName + " of statement " +
                            mappedStatement.getId());
                    }

                    typeHandler.setParameter(ps, i + 1, value,
                        parameterMapping.getJdbcType());
                }
            }
        }
    }
}

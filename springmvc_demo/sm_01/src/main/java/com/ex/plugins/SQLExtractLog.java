package com.ex.plugins;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.List;

@Intercepts({@Signature(type = StatementHandler.class,method = "query",args = {Statement.class, ResultHandler.class})}

)
public class SQLExtractLog implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(SQLExtractLog.class);
    @Override
    public  Object  intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler rsh = (RoutingStatementHandler) invocation.getTarget();
        BoundSql boundSql = rsh.getBoundSql();
        Object o = boundSql.getParameterObject();
        String message = buildSqlStr(boundSql, o);
        logger.info(message);
        return invocation.proceed();
    }
    public String buildSqlStr(BoundSql boundSql,Object parameterObject){
        MapperMethod.ParamMap paramMap = parameterObject instanceof MapperMethod.ParamMap ?
                (MapperMethod.ParamMap) parameterObject: null;
        StringBuilder logStr = new StringBuilder();
        logStr.append(boundSql.getSql().replaceAll("\n|\\s+"," "));
        StringBuilder sqlRecord ;
        if(null!=paramMap){
            sqlRecord = getParameterStr(boundSql, logStr, paramMap);
        }else{
            sqlRecord = logStr;
        }
        return sqlRecord.toString();
    }
    public StringBuilder getParameterStr(BoundSql boundSql,StringBuilder sb,MapperMethod.ParamMap paramMap){
        sb.append("\n").append("parameters:");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        int i = 0;
        for (ParameterMapping parameterMapping : parameterMappings) {
            String property = parameterMapping.getProperty();
            if((i++)==parameterMappings.size()-1){
                sb.append(paramMap.get(property)).append(",");
            }else{
                sb.append(paramMap.get(property));
            }
        }
        return sb;
    }
}

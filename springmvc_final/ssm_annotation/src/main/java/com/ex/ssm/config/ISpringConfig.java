package com.ex.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ex.ssm.plugins.SQLExtractLog;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(value = "com.ex" ,excludeFilters = {@ComponentScan.Filter({Controller.class})})
@PropertySource("classpath:druid.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ISpringConfig {
    @Bean("druidDataSource")
    public DataSource getDruidDataSource(DruidDataSourceConfig d){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(d.getDriverClassName());
        dataSource.setUrl(d.getUrl());
        dataSource.setUsername(d.getUsername());
        dataSource.setPassword(d.getPassword());
        dataSource.setMaxActive(d.getMaxActive());
        dataSource.setInitialSize(d.getInitialSize());
        dataSource.setMaxWait(d.getMaxWait());
        return dataSource;
    }
    @Bean("sqlSessionFactory")
    public MybatisSqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean msfb = new MybatisSqlSessionFactoryBean();
        msfb.setDataSource(dataSource);
        msfb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*Mapper.xml"));
        msfb.setTypeAliasesPackage("com.ex.ssm.entity");
        msfb.setPlugins(new SQLExtractLog());
        return msfb;
    }
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.ex.ssm.mapper");
        return msc;
    }
    @Bean
    public TransactionManager getTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public FastJsonHttpMessageConverter createFJHMConverter(){
        return new FastJsonHttpMessageConverter();
    }

    @Bean
    public DataSourceTransactionManager createTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}


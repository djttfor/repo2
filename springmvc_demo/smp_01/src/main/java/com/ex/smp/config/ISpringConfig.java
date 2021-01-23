package com.ex.smp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(value = "com.ex")
@EnableTransactionManagement
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
    @Bean("sqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource druidDataSource) throws IOException {
        MybatisSqlSessionFactoryBean mssf
                = new MybatisSqlSessionFactoryBean();
        mssf.setDataSource(druidDataSource);
        //ssfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        //mssf.setPlugins(new SQLExtractLog());//插件
        return mssf;
    }
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.ex.smp.mapper");
        return msc;
    }
    @Bean
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}


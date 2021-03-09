# Mybatis-Plus

## 1.启动配置（ssm注解方式）

### 1.1 maven依赖

详见mybatis-plus-maven依赖

### 1.2 spring配置

```java
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

        //mybatisplus自带的插件
        PaginationInnerInterceptor p =new PaginationInnerInterceptor(DbType.MYSQL);
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        mpi.addInnerInterceptor(p);

        msfb.setPlugins(new SQLExtractLog(),mpi);
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
}
```

### 1.3 使用mybatis-plus写xml

xml配置

```xml
<select id="queryAccountByMybatisplus" resultMap="byUid">
    select account_name ,password ,balance from account ${ew.customSqlSegment}
</select>
```

mapper配置

```java
Account queryAccountByMybatisplus(@Param(Constants.WRAPPER)Wrapper<Account> ew);
```

### 1.4 使用mybatis plus自带的分页插件

```java
    //mybatisplus自带的插件
        PaginationInnerInterceptor p =new PaginationInnerInterceptor(DbType.MYSQL);
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        mpi.addInnerInterceptor(p);
        msfb.setPlugins(mpi);
```

```java
Page<User> page = new Page<>(1,5);//当前页，每页记录数
Page<User> userPage = userMapper.selectPage(page, Wrappers.lambdaQuery());
log.info("查询到的记录:{}",userPage.getRecords());
log.info("当前页:{}",userPage.getCurrent());
log.info("每页显示条数:{}",userPage.getSize());
log.info("总条数:{}",userPage.getTotal());
```

### 1.5 使用pagehelper 进行分页

```xml
<dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>5.1.2</version>
            </dependency>
```

```java
PageHelper.startPage(1,5);//必须放在最前面，两个参数分别是当前页码，页面记录数，放在ThreadLocal上面。
com.github.pagehelper.Page<User> page = (com.github.pagehelper.Page<User>) userService.list();
log.info("当前页:{}",page.getPageNum());
log.info("每页显示条数:{}",page.getPageSize());
log.info("总条数:{}",page.getTotal());

for (User user : page.getResult()) {
    log.info("查询结果:{}",user);
}
```
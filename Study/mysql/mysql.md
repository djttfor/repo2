# mysql

## 杂项

### 1.主键自增(auto_increment)的问题

指定自增的列必须有有索引,一张表最多有一列自增

查看自增量

```mysql
show create table t2;-- 查看表创建,最后面接有最新的自增值,也就是插入下一行的自增值,注意插入失败自增量也会增加
show table status; -- 查看数据库中所有表的状态,里面有一行是自增值
alter table t2 engine = 'innodb';-- 修改存储引擎
alter table t2 charset = 'gbk';-- 修改表字符集
```

#### 查看自增量相关 : show VARIABLES like '%auto_increment%'.

| Variable_name            | Value |
| ------------------------ | ----- |
| auto_increment_increment | 1     |
| auto_increment_offset    | 1     |

两个值的含义：

auto_increment_increment：自增值的自增量 (每次增加多少)

auto_increment_offset： 自增值的偏移量 (初始值)

设置了两个值之后，每次自增的量：

**auto_increment_offset + auto_increment_increment * N**的值，其中**N>=0**(N可以看做是第几行),上限受字段类型限制

**如何变更下一次的自增值**

比如下一次的自增值为90,你要把自增值改为80,那先要把>=80的列全部删除,然后执行下面语句

```mysql
alter table t2 auto_increment = 80;
```


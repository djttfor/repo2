package com.qingcheng.entity;

import com.qingcheng.pojo.goods.Brand;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果集类
 * @param <T>
 */
public class PageResult<T> implements Serializable {

    Long total;
    List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

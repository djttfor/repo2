package com.qingcheng.pojo.goods;


import com.qingcheng.util.IdWorker;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_goods_log")
public class GoodsLog implements Serializable {
    @Id
    private String id;
    private String spuId;
    private String message;
    private Date time;

    public GoodsLog() {
    }
    public GoodsLog(String spuId,String message){
        IdWorker idWorker = new IdWorker(1, 1);
        this.id = idWorker.nextId()+"";
        this.spuId = spuId;
        this.message = message;
        this.time = new Date();
    }

    public GoodsLog(String id, String spuId, String message, Date time) {
        this.id = id;
        this.spuId = spuId;
        this.message = message;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

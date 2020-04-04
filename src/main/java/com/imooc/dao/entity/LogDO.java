package com.imooc.dao.entity;


import java.util.Date;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
public class LogDO {
    private Long userId;
    private String detail;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

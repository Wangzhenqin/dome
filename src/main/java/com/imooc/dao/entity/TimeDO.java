package com.imooc.dao.entity;


import java.util.Date;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
public class TimeDO {
     private Long userId;
     private Long startTime;
     private Long endTime;
     private String event;
     private Integer isDelete;
     private Date createTime;
     private Date updateTime;

     public Long getUserId() {
          return userId;
     }

     public void setUserId(Long userId) {
          this.userId = userId;
     }

     public Long getStartTime() {
          return startTime;
     }

     public void setStartTime(Long startTime) {
          this.startTime = startTime;
     }

     public Long getEndTime() {
          return endTime;
     }

     public void setEndTime(Long endTime) {
          this.endTime = endTime;
     }

     public String getEvent() {
          return event;
     }

     public void setEvent(String event) {
          this.event = event;
     }

     public Integer getIsDelete() {
          return isDelete;
     }

     public void setIsDelete(Integer isDelete) {
          this.isDelete = isDelete;
     }

     public Date getCreateTime() {
          return createTime;
     }

     public void setCreateTime(Date createTime) {
          this.createTime = createTime;
     }

     public Date getUpdateTime() {
          return updateTime;
     }

     public void setUpdateTime(Date updateTime) {
          this.updateTime = updateTime;
     }
}

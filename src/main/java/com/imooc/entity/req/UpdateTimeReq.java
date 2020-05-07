package com.imooc.entity.req;

import com.imooc.entity.Enum.JudgeEnum;

public class UpdateTimeReq {
   private Long userId;
   private String event;
   private Long startTime;
   private Long endTime;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getEvent() {
      return event;
   }

   public void setEvent(String event) {
      this.event = event;
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
}

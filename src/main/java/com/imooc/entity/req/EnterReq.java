package com.imooc.entity.req;

import com.imooc.entity.Page;

public class EnterReq {
   private Long userId;
   private String code;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }
}

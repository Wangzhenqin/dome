package com.imooc.entity.req;

public class UpdateCodeReq {
   private Long userId;
   private String oldCode;
   private String newCode;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getOldCode() {
      return oldCode;
   }

   public void setOldCode(String oldCode) {
      this.oldCode = oldCode;
   }

   public String getNewCode() {
      return newCode;
   }

   public void setNewCode(String newCode) {
      this.newCode = newCode;
   }
}

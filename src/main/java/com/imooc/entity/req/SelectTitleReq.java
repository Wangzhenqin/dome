package com.imooc.entity.req;

import com.imooc.entity.Page;

public class SelectTitleReq {
   private Long userId;
   private Long titleId;


   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getTitleId() {
      return titleId;
   }

   public void setTitleId(Long titleId) {
      this.titleId = titleId;
   }
}

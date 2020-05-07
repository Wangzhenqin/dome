package com.imooc.entity.req;

import com.imooc.entity.Page;

public class GetTeacherTitleReq {
   private Long userId;
   private Page page;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Page getPage() {
      return page;
   }

   public void setPage(Page page) {
      this.page = page;
   }
}

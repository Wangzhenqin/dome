package com.imooc.entity.req;

import com.imooc.entity.Page;
import com.imooc.entity.dto.UserDTO;

public class GetUserReq {
   private Long userId;
   private Integer userType;
   private String name;
   private Long facultyId;
   private Page page;

   public Page getPage() {
      return page;
   }

   public void setPage(Page page) {
      this.page = page;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Integer getUserType() {
      return userType;
   }

   public void setUserType(Integer userType) {
      this.userType = userType;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getFacultyId() {
      return facultyId;
   }

   public void setFacultyId(Long facultyId) {
      this.facultyId = facultyId;
   }
}

package com.imooc.entity.req;

import com.imooc.entity.Enum.StudentFileEnum;
import com.imooc.entity.dto.UserDTO;

public class UploadStudentFileReq {
   private Long titleId;
   private StudentFileEnum studentFileEnum;
   private String url;

   public Long getTitleId() {
      return titleId;
   }

   public void setTitleId(Long titleId) {
      this.titleId = titleId;
   }

   public StudentFileEnum getStudentFileEnum() {
      return studentFileEnum;
   }

   public void setStudentFileEnum(StudentFileEnum studentFileEnum) {
      this.studentFileEnum = studentFileEnum;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
}

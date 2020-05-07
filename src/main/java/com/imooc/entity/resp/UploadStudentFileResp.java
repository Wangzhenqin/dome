package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.UserDTO;

public class UploadStudentFileResp {
   private String url;
   private SuccessEnum ret;

   @Override
   public String toString() {
      return "UploadStudentFileResp{" +
              "url='" + url + '\'' +
              ", ret=" + ret +
              '}';
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }


}

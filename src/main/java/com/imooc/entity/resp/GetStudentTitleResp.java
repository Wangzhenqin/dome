package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.TitleDTO;

import java.util.List;

public class GetStudentTitleResp {
   private List<TitleDTO> title;
   private SuccessEnum ret;

   @Override
   public String toString() {
      return "GetStudentTitleResp{" +
              "title=" + title +
              ", ret=" + ret +
              '}';
   }

   public List<TitleDTO> getTitle() {
      return title;
   }

   public void setTitle(List<TitleDTO> title) {
      this.title = title;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }


}

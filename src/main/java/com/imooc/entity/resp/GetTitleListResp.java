package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.TitleDTO;


import java.util.List;

public class GetTitleListResp {
   private List<TitleDTO> title;
   private SuccessEnum ret;
   private int total;

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
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

package com.imooc.entity.req;

import com.imooc.entity.Page;
import com.imooc.entity.dto.TitleDTO;

public class GetTitleByDTOReq {
   private TitleDTO TitleDTO;
   private Page page;

   public Page getPage() {
      return page;
   }

   public void setPage(Page page) {
      this.page = page;
   }

   public com.imooc.entity.dto.TitleDTO getTitleDTO() {
      return TitleDTO;
   }

   public void setTitleDTO(com.imooc.entity.dto.TitleDTO titleDTO) {
      TitleDTO = titleDTO;
   }
}

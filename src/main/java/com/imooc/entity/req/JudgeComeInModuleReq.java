package com.imooc.entity.req;

import com.imooc.entity.Enum.JudgeEnum;

public class JudgeComeInModuleReq {
   private Long userId;
   private JudgeEnum JudgeEnum;

   public com.imooc.entity.Enum.JudgeEnum getJudgeEnum() {
      return JudgeEnum;
   }

   public void setJudgeEnum(com.imooc.entity.Enum.JudgeEnum judgeEnum) {
      JudgeEnum = judgeEnum;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }
}

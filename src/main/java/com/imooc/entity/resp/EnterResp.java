package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.UserDTO;

public class EnterResp {
   private UserDTO userDTO;
   private SuccessEnum ret;

   public UserDTO getUserDTO() {
      return userDTO;
   }

   @Override
   public String toString() {
      return "EnterResp{" +
              "userDTO=" + userDTO +
              ", ret=" + ret +
              '}';
   }

   public void setUserDTO(UserDTO userDTO) {
      this.userDTO = userDTO;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }


}

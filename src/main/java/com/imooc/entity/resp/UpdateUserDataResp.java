package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.UserDTO;

public class UpdateUserDataResp {
   @Override
   public String toString() {
      return "UpdateUserDataResp{" +
              "userDTO=" + userDTO +
              ", ret=" + ret +
              '}';
   }

   private UserDTO userDTO;
   private SuccessEnum ret;

   public UserDTO getUserDTO() {
      return userDTO;
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

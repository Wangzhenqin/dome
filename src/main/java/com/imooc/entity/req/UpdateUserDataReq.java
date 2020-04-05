package com.imooc.entity.req;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.UserDTO;

public class UpdateUserDataReq {
   private UserDTO userDTO;
   private String oldCode;
   private String newCode;

   public UserDTO getUserDTO() {
      return userDTO;
   }

   public void setUserDTO(UserDTO userDTO) {
      this.userDTO = userDTO;
   }

   public String getOldCode() {
      return oldCode;
   }

   public void setOldCode(String oldCode) {
      this.oldCode = oldCode;
   }

   public String getNewCode() {
      return newCode;
   }

   public void setNewCode(String newCode) {
      this.newCode = newCode;
   }
}

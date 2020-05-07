package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.UserDTO;

import java.util.List;

public class GetUserResp {
   private List<UserDTO> userDTO;
   private SuccessEnum ret;
   private int total;

   @Override
   public String toString() {
      return "SelectUserResp{" +
              "userDTO=" + userDTO +
              ", ret=" + ret +
              ", total=" + total +
              '}';
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public List<UserDTO> getUserDTO() {
      return userDTO;
   }

   public void setUserDTO(List<UserDTO> userDTO) {
      this.userDTO = userDTO;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }


}

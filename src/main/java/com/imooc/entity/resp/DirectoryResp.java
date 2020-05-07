package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.DirectoryDTO;
import com.imooc.entity.dto.UserDTO;

import java.util.List;

public class DirectoryResp {
   private List<DirectoryDTO> directory;
   private SuccessEnum ret;

   @Override
   public String toString() {
      return "DirectoryResp{" +
              "Directory=" + directory +
              ", ret=" + ret +
              '}';
   }

   public List<DirectoryDTO> getDirectory() {
      return directory;
   }

   public void setDirectory(List<DirectoryDTO> directory) {
      this.directory = directory;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }
}

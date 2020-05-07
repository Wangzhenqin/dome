package com.imooc.entity.resp;

import com.imooc.dao.entity.FacultyDO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.FacultyDTO;
import com.imooc.entity.dto.UserDTO;

import java.util.List;

public class GetFacultyResp {
   private List<FacultyDTO> facultyDTOS;
   private SuccessEnum ret;

   @Override
   public String toString() {
      return "getFacultyResp{" +
              "facultyDTOS=" + facultyDTOS +
              ", ret=" + ret +
              '}';
   }

   public List<FacultyDTO> getFacultyDTOS() {
      return facultyDTOS;
   }

   public void setFacultyDTOS(List<FacultyDTO> facultyDTOS) {
      this.facultyDTOS = facultyDTOS;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }
}

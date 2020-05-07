package com.imooc.entity.resp;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.TimeDTO;
import com.imooc.entity.dto.TitleDTO;

import java.util.List;

public class GetTimeListResp {
   private List<TimeDTO> timeDTOS;
   private SuccessEnum ret;
   private int total;

   @Override
   public String toString() {
      return "GetTimeListResp{" +
              "timeDTOS=" + timeDTOS +
              ", ret=" + ret +
              ", total=" + total +
              '}';
   }

   public List<TimeDTO> getTimeDTOS() {
      return timeDTOS;
   }

   public void setTimeDTOS(List<TimeDTO> timeDTOS) {
      this.timeDTOS = timeDTOS;
   }

   public SuccessEnum getRet() {
      return ret;
   }

   public void setRet(SuccessEnum ret) {
      this.ret = ret;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }
}

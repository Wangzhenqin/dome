package com.imooc.manager;

import com.imooc.common.ListUtil;
import com.imooc.dao.entity.TimeDO;
import com.imooc.dao.entity.TitleDO;
import com.imooc.dao.entity.UserDO;
import com.imooc.dao.mybatis.TimeDAO;
import com.imooc.dao.mybatis.TitleDAO;
import com.imooc.dao.mybatis.UserDAO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.Enum.TitleStatusEnum;
import com.imooc.entity.Page;
import com.imooc.entity.dto.TimeDTO;
import com.imooc.entity.dto.TitleDTO;
import com.imooc.entity.req.*;
import com.imooc.entity.resp.GetStudentTitleResp;
import com.imooc.entity.resp.GetTeacherTitleResp;
import com.imooc.entity.resp.GetTimeListResp;
import com.imooc.entity.resp.GetTitleListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@Service
public class TimeServiceManager {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TitleDAO titleDAO;
    @Autowired
    TimeDAO timeDAO;

    public GetTimeListResp getimeList(Page req) {
        GetTimeListResp resp = new GetTimeListResp();
        resp.setRet(SuccessEnum.SUCCESS);
        List<TimeDO> timeDOS = timeDAO.getList(req.getPageNum()-1,req.getPageSize());
        List<TimeDTO> timeDTOS = new ArrayList<>();
        for (TimeDO timeDO : timeDOS) {
            TimeDTO timeDTO=new TimeDTO();
            timeDTO.setEvent(timeDO.getEvent());
            timeDTO.setUserId(timeDO.getUserId());
            timeDTO.setStartTime(timeDO.getStartTime());
            timeDTO.setEndTime(timeDO.getEndTime());
            timeDTOS.add(timeDTO);
        }
        resp.setTimeDTOS(timeDTOS);
        return resp;
    }

    public SuccessEnum updateTime(UpdateTimeReq req) {
        if (req.getStartTime()!=null&&req.getEndTime()!=null){
            TimeDO timeDO = timeDAO.getByEvent(req.getEvent());
            timeDO.setStartTime(req.getStartTime());
            timeDO.setEndTime(req.getEndTime());
            timeDO.setUserId(req.getUserId());
            timeDAO.updateByEvent(timeDO);
        }
        return SuccessEnum.SUCCESS;
    }
}

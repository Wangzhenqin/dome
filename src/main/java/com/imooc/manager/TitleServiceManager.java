package com.imooc.manager;

import com.imooc.common.ListUtil;
import com.imooc.dao.entity.TitleDO;
import com.imooc.dao.entity.UserDO;
import com.imooc.dao.mybatis.TitleDAO;
import com.imooc.dao.mybatis.UserDAO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.TitleDTO;
import com.imooc.entity.req.GetTitleListReq;
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
public class TitleServiceManager{
    @Autowired
    UserDAO userDAO;
    @Autowired
    TitleDAO titleDAO;

    public GetTitleListResp getTitleList(GetTitleListReq req) {
        GetTitleListResp getTitleListResp = new GetTitleListResp();
        getTitleListResp.setRet(SuccessEnum.SUCCESS);
        UserDO userDO = userDAO.getByUserId(req.getUserId());
        List<TitleDO> titleDOS = titleDAO.getByFacultyId(userDO.getFacultyId(),req.getPage().getPageNum(),req.getPage().getPageSize());
        int total = titleDAO.countByFacultyId(userDO.getFacultyId());
        getTitleListResp.setTotal(total);
        List<Long> teacherIds = titleDOS.stream().map(TitleDO::getTeacherId).collect(Collectors.toList());
        List<UserDO> userDOS = userDAO.getByUserIds(teacherIds);
        Map<Long, UserDO> teacherMap = ListUtil.makeMap(userDOS, UserDO::getUserId);
        List<TitleDTO> titleDTOS =new ArrayList<>();
        for (TitleDO titleDO : titleDOS) {
            TitleDTO titleDTO =new TitleDTO();
            titleDTO.setTitleId(titleDO.getTitleId());
            titleDTO.setTitleName(titleDO.getTitleName());
            titleDTO.setContent(titleDO.getContent());
            titleDTO.setTitleName(teacherMap.get(titleDO.getTeacherId()).getName());
            titleDTOS.add(titleDTO);
        }
        getTitleListResp.setTitle(titleDTOS);
        return getTitleListResp;
    }
}

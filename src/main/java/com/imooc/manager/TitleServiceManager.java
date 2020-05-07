package com.imooc.manager;

import com.imooc.common.ListUtil;
import com.imooc.dao.entity.FacultyDO;
import com.imooc.dao.entity.TitleDO;
import com.imooc.dao.entity.UserDO;
import com.imooc.dao.mybatis.FacultyDAO;
import com.imooc.dao.mybatis.TitleDAO;
import com.imooc.dao.mybatis.UserDAO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.Enum.TitleStatusEnum;
import com.imooc.entity.dto.FacultyDTO;
import com.imooc.entity.dto.TitleDTO;
import com.imooc.entity.req.*;
import com.imooc.entity.resp.GetFacultyResp;
import com.imooc.entity.resp.GetStudentTitleResp;
import com.imooc.entity.resp.GetTeacherTitleResp;
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
    @Autowired
    FacultyDAO facultyDAO;

    public GetTitleListResp getTitleList(GetTitleListReq req) {
        GetTitleListResp getTitleListResp = new GetTitleListResp();
        getTitleListResp.setRet(SuccessEnum.SUCCESS);
        UserDO userDO=new UserDO();
        if (req.getUserId()!=null){
            userDO = userDAO.getByUserId(req.getUserId());
        }
        List<TitleDO> titleDOS = titleDAO.getByFacultyId(userDO.getFacultyId(),req.getPage().getPageNum()-1,req.getPage().getPageSize());
        int total = titleDAO.countByFacultyId(userDO.getFacultyId());
        getTitleListResp.setTotal(total);
        if (titleDOS.size()==0){
            return getTitleListResp;
        }
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(titleDOS.stream().map(TitleDO::getTeacherId).collect(Collectors.toList()));
        userIds.addAll(titleDOS.stream().map(TitleDO::getStudentId).collect(Collectors.toList()));
        List<UserDO> userDOS = userDAO.getByUserIds(userIds);
        Map<Long, UserDO> userDOMap = ListUtil.makeMap(userDOS, UserDO::getUserId);
        List<TitleDTO> titleDTOS =new ArrayList<>();
        for (TitleDO titleDO : titleDOS) {
            TitleDTO titleDTO =new TitleDTO();
            titleDTO.setTitleId(titleDO.getTitleId());
            titleDTO.setTitleName(titleDO.getTitleName());
            titleDTO.setContent(titleDO.getContent());
            titleDTO.setStatus(titleDO.getStatus());
            titleDTO.setTeacherName(userDOMap.get(titleDO.getTeacherId()).getName());
            titleDTO.setFacultyId(userDO.getFacultyId());
            titleDTOS.add(titleDTO);
        }
        getTitleListResp.setTitle(titleDTOS);
        return getTitleListResp;
    }

    public GetTeacherTitleResp getTeacherTitle(GetTeacherTitleReq req) {
        GetTeacherTitleResp resp = new GetTeacherTitleResp();
        resp.setRet(SuccessEnum.SUCCESS);
        List<TitleDO> titleDOS = titleDAO.getByTeacherId(req.getUserId(),req.getPage().getPageNum()-1,req.getPage().getPageSize());
        int total = titleDAO.countByTeacherId(req.getUserId());
        resp.setTotal(total);
        if (titleDOS.size()==0){
            return resp;
        }
        List<Long> userIds = titleDOS.stream().map(TitleDO::getTeacherId).collect(Collectors.toList());
        List<Long> studentIds = titleDOS.stream().map(TitleDO::getStudentId).collect(Collectors.toList());
        userIds.addAll(studentIds);
        List<UserDO> userDOS = userDAO.getByUserIds(userIds);
        Map<Long, UserDO> userMap = ListUtil.makeMap(userDOS, UserDO::getUserId);
        List<TitleDTO> titleDTOS =new ArrayList<>();
        for (TitleDO titleDO : titleDOS) {
            TitleDTO titleDTO =new TitleDTO();
            titleDTO.setTitleId(titleDO.getTitleId());
            titleDTO.setTitleName(titleDO.getTitleName());
            titleDTO.setContent(titleDO.getContent());
            titleDTO.setStatus(titleDO.getStatus());
            titleDTO.setTeacherId(titleDO.getTeacherId());
            titleDTO.setTeacherName(userMap.get(titleDO.getTeacherId()).getName());
            titleDTO.setStudentId(titleDO.getStudentId());
            if (userMap.containsKey(titleDO.getStudentId())){
                titleDTO.setStudentName(userMap.get(titleDO.getStudentId()).getName());
            }else {
                titleDTO.setStudentName("/");
            }
            titleDTO.setFacultyId(titleDO.getFacultyId());
            titleDTOS.add(titleDTO);
        }
        resp.setTitle(titleDTOS);
        return resp;
    }

    public SuccessEnum updateTitleData(UpdateTitleDataReq req) {
        if (req.getTitleDTO()==null){
            return SuccessEnum.SUCCESS;
        }
        TitleDO titleDO = titleDAO.getByTitleId(req.getTitleDTO().getTitleId());
        titleDO.setContent(req.getTitleDTO().getContent());
        titleDO.setTitleName(req.getTitleDTO().getTitleName());
        titleDO.setFacultyId(req.getTitleDTO().getFacultyId());
        if (req.getTitleDTO().getTeacherId()!=null){
            titleDO.setTeacherId(req.getTitleDTO().getTeacherId());
        }
        if (req.getTitleDTO().getStudentId()!=null){
            titleDO.setStudentId(req.getTitleDTO().getStudentId());
        }
        titleDO.setStatus(req.getTitleDTO().getStatus());
        titleDAO.updateTitleByTitleId(titleDO);
        return SuccessEnum.SUCCESS;
    }

    public SuccessEnum delectTitle(Long titleId) {
        titleDAO.deleteTitleByTitleId(titleId);
        return SuccessEnum.SUCCESS;
    }

    public SuccessEnum selectTitle(SelectTitleReq req) {
        List<TitleDO> titleDOS = titleDAO.getByStudentId(req.getUserId());
        if (titleDOS.size()>=3){
            return SuccessEnum.STUDENT_SELECT_TITLE_MAX;
        }
        for (TitleDO titleDO : titleDOS) {
            if (titleDO.getStatus()==TitleStatusEnum.CONFIRM.getCode()){
                return SuccessEnum.TITLE_STATUS_ERROR;
            }
        }
        TitleDO titleDO = titleDAO.getByTitleId(req.getTitleId());
        if (titleDO.getStatus()!= TitleStatusEnum.NOT_SELECT.getCode()){
            return SuccessEnum.TITLE_ALREADY_SELECT;
        }
        titleDO.setStatus(TitleStatusEnum.SELECT.getCode());
        titleDO.setStudentId(req.getUserId());
        titleDAO.updateTitleByTitleId(titleDO);
        return SuccessEnum.SUCCESS;
    }

    public SuccessEnum cancelTitle(CancelTitleReq req) {
        TitleDO titleDO = titleDAO.getByTitleId(req.getTitleId());
        if (titleDO.getStatus()!= TitleStatusEnum.SELECT.getCode()){
            return SuccessEnum.TITLE_STATUS_ERROR;
        }
        titleDO.setStatus(TitleStatusEnum.NOT_SELECT.getCode());
        titleDO.setStudentId(0L);
        titleDAO.updateTitleByTitleId(titleDO);
        return SuccessEnum.SUCCESS;
    }

    public GetStudentTitleResp getStudentTitle(Long userId) {
        GetStudentTitleResp getStudentTitleResp = new GetStudentTitleResp();
        getStudentTitleResp.setRet(SuccessEnum.SUCCESS);
        List<TitleDO> titleDOS = titleDAO.getByStudentId(userId);
        if (titleDOS.size()==0){
            return getStudentTitleResp;
        }
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(titleDOS.stream().map(TitleDO::getTeacherId).collect(Collectors.toList()));
        userIds.addAll(titleDOS.stream().map(TitleDO::getStudentId).collect(Collectors.toList()));
        List<UserDO> userDOS = userDAO.getByUserIds(userIds);
        Map<Long, UserDO> userDOMap = ListUtil.makeMap(userDOS, UserDO::getUserId);
        TitleDO confirmTitledo = null;
        for (TitleDO titleDO : titleDOS) {
            if (titleDO.getStatus()==TitleStatusEnum.CONFIRM.getCode()){
                confirmTitledo=titleDO;
                break;
            }
        }
        List<TitleDTO> titleDTOS =new ArrayList<>();
        if (confirmTitledo!=null){
            TitleDTO titleDTO =new TitleDTO();
            titleDTO.setTitleId(confirmTitledo.getTitleId());
            titleDTO.setTitleName(confirmTitledo.getTitleName());
            titleDTO.setContent(confirmTitledo.getContent());
            titleDTO.setStatus(confirmTitledo.getStatus());
            titleDTO.setTeacherName(userDOMap.get(confirmTitledo.getTeacherId()).getName());
            titleDTO.setFacultyId(userDOMap.get(confirmTitledo.getStudentId()).getFacultyId());
            titleDTOS.add(titleDTO);
        }else {
            for (TitleDO titleDO : titleDOS) {
                TitleDTO titleDTO =new TitleDTO();
                titleDTO.setTitleId(titleDO.getTitleId());
                titleDTO.setTitleName(titleDO.getTitleName());
                titleDTO.setContent(titleDO.getContent());
                titleDTO.setStatus(titleDO.getStatus());
                titleDTO.setTeacherName(userDOMap.get(titleDO.getTeacherId()).getName());
                titleDTO.setFacultyId(userDOMap.get(titleDO.getStudentId()).getFacultyId());
                titleDTOS.add(titleDTO);
            }
        }
        getStudentTitleResp.setTitle(titleDTOS);
        return getStudentTitleResp;
    }

    public SuccessEnum confirmStudentTitle(Long titleId) {
        TitleDO titleDO = titleDAO.getByTitleId(titleId);
        int count=titleDAO.countComfirmTitleByStudentId(titleDO.getStudentId());
        if (count>0){
            return SuccessEnum.TITLE_STATUS_ERROR;

        }
        List<TitleDO> titleDOS = titleDAO.getByStudentId(titleDO.getStudentId());
        for (TitleDO title : titleDOS) {
            if (title.getTitleId().equals(titleId)){
                title.setStatus(TitleStatusEnum.CONFIRM.getCode());
            }else {
                title.setStatus(TitleStatusEnum.NOT_SELECT.getCode());
                title.setStudentId(0L);
            }
            titleDAO.updateTitleByTitleId(title);
        }
        return SuccessEnum.SUCCESS;
    }

    public SuccessEnum cancelStudentTitle(Long titleId) {
        TitleDO titleDO = titleDAO.getByTitleId(titleId);
        titleDO.setStatus(TitleStatusEnum.NOT_SELECT.getCode());
        titleDO.setStudentId(0L);
        titleDAO.updateTitleByTitleId(titleDO);
        return SuccessEnum.SUCCESS;
    }

    public GetTitleListResp getTitleByDTO(GetTitleByDTOReq req) {

        GetTitleListResp getTitleListResp = new GetTitleListResp();
        getTitleListResp.setRet(SuccessEnum.SUCCESS);
        if (req.getTitleDTO()==null){
            return getTitleListResp;
        }
        List<TitleDO> titleDOS = titleDAO.getByDTO(req.getTitleDTO(),req.getPage().getPageNum()-1,req.getPage().getPageSize());
        int total = titleDAO.countByDTO(req.getTitleDTO());
        getTitleListResp.setTotal(total);
        if (total==0){
            return getTitleListResp;
        }
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(titleDOS.stream().map(TitleDO::getTeacherId).collect(Collectors.toList()));
        userIds.addAll(titleDOS.stream().map(TitleDO::getStudentId).collect(Collectors.toList()));
        List<UserDO> userDOS = userDAO.getByUserIds(userIds);
        Map<Long, UserDO> userDOMap = ListUtil.makeMap(userDOS, UserDO::getUserId);
        List<TitleDTO> titleDTOS =new ArrayList<>();
        for (TitleDO titleDO : titleDOS) {
            TitleDTO titleDTO =new TitleDTO();
            titleDTO.setTitleId(titleDO.getTitleId());
            titleDTO.setTitleName(titleDO.getTitleName());
            titleDTO.setContent(titleDO.getContent());
            titleDTO.setStatus(titleDO.getStatus());
            titleDTO.setTeacherId(titleDO.getTeacherId());
            if (userDOMap.containsKey(titleDO.getTeacherId())){
                titleDTO.setTeacherName(userDOMap.get(titleDO.getTeacherId()).getName());
            }else {
                titleDTO.setTeacherName("/");
            }
            titleDTO.setStudentId(titleDO.getStudentId());
            if (userDOMap.containsKey(titleDO.getStudentId())){
                titleDTO.setStudentName(userDOMap.get(titleDO.getStudentId()).getName());
            }else {
                titleDTO.setStudentName("/");
            }
            titleDTO.setFacultyId(titleDO.getFacultyId());
            titleDTOS.add(titleDTO);
        }
        getTitleListResp.setTitle(titleDTOS);
        return getTitleListResp;
    }

    public GetFacultyResp getFaculty() {
        GetFacultyResp resp =new GetFacultyResp();
        resp.setRet(SuccessEnum.SUCCESS);
        List<FacultyDO> facultys = facultyDAO.getFacultys();
        List<FacultyDTO> facultyDTOS=new ArrayList<>();
        for (FacultyDO faculty : facultys) {
            FacultyDTO facultyDTO = new FacultyDTO();
            facultyDTO.setFacultyId(faculty.getFacultyId());
            facultyDTO.setFacultyName(faculty.getFacultyName());
            facultyDTOS.add(facultyDTO);
        }
        resp.setFacultyDTOS(facultyDTOS);
        return resp;
    }
}

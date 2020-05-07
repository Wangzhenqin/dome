package com.imooc.manager;

import com.imooc.dao.entity.DirectoryDO;
import com.imooc.dao.entity.TimeDO;
import com.imooc.dao.entity.UserDO;
import com.imooc.dao.mybatis.DirectoryDAO;
import com.imooc.dao.mybatis.TimeDAO;
import com.imooc.dao.mybatis.TitleDAO;
import com.imooc.dao.mybatis.UserDAO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.Enum.UserTypeEnum;
import com.imooc.entity.dto.DirectoryDTO;
import com.imooc.entity.dto.UserDTO;
import com.imooc.entity.req.EnterReq;
import com.imooc.entity.req.GetUserReq;
import com.imooc.entity.req.JudgeComeInModuleReq;
import com.imooc.entity.req.UpdateUserDataReq;
import com.imooc.entity.resp.DirectoryResp;
import com.imooc.entity.resp.EnterResp;
import com.imooc.entity.resp.GetUserResp;
import com.imooc.entity.resp.UpdateUserDataResp;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangzhenqin on 2019/6/13.
 */
@Service
public class UserServiceManager {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TitleDAO titleDAO;
    @Autowired
    TimeDAO timeDAO;
    @Autowired
    DirectoryDAO directoryDAO;


    public EnterResp enter(EnterReq req) {
        EnterResp enterResp=new EnterResp();
        enterResp.setRet(SuccessEnum.SUCCESS);
        UserDO userDO = userDAO.getByUserId(req.getUserId());
        if (req.getCode().equals(userDO.getCode())){
            UserDTO userDTO =new UserDTO();
            userDTO.setUserId(userDO.getUserId());
            userDTO.setFacultyId(userDO.getFacultyId());
            userDTO.setUserType(userDO.getUserType());
            userDTO.setMail(userDO.getMail());
            userDTO.setName(userDO.getName());
            userDTO.setPhone(userDO.getPhone());
            enterResp.setUserDTO(userDTO);
            return enterResp;
        }else {
            enterResp.setRet(SuccessEnum.CODE_ERROR);
            return enterResp;
        }
    }

    public UpdateUserDataResp updateUserData(UpdateUserDataReq req) {
        UpdateUserDataResp enterResp=new UpdateUserDataResp();
        enterResp.setRet(SuccessEnum.SUCCESS);
        UserDO userDO = userDAO.getByUserId(req.getUserDTO().getUserId());
        if (StringUtils.isNotEmpty(req.getOldCode())){
            if (req.getOldCode().equals(userDO.getCode())){
                if (StringUtils.isEmpty(req.getNewCode())){
                    enterResp.setRet(SuccessEnum.CODE_ERROR);
                    return enterResp;
                }
                userDAO.updateCodeByUserId(req.getUserDTO().getUserId(),req.getNewCode());
            }else {
                enterResp.setRet(SuccessEnum.CODE_ERROR);
                return enterResp;
            }
        }else {
            if (req.getUserDTO().getFacultyId()!=null){
                userDO.setFacultyId(req.getUserDTO().getFacultyId());
            }
            if (req.getUserDTO().getUserType()!=null){
                userDO.setUserType(req.getUserDTO().getUserType());
            }
            if (StringUtils.isNotEmpty(req.getUserDTO().getMail())){
                userDO.setMail(req.getUserDTO().getMail());
            }
            if (StringUtils.isNotEmpty(req.getUserDTO().getName())){
                userDO.setName(req.getUserDTO().getName());
            }
            if (req.getUserDTO().getPhone()!=null){
                userDO.setPhone(req.getUserDTO().getPhone());
            }
            if (StringUtils.isNotEmpty(req.getNewCode())){
                userDO.setCode(req.getNewCode());
            }
            userDAO.updateUserByUserId(userDO);
        }
        UserDTO userDTO =new UserDTO();
        userDTO.setUserId(userDO.getUserId());
        userDTO.setFacultyId(userDO.getFacultyId());
        userDTO.setUserType(userDO.getUserType());
        userDTO.setMail(userDO.getMail());
        userDTO.setName(userDO.getName());
        userDTO.setPhone(userDO.getPhone());
        enterResp.setUserDTO(userDTO);
        return enterResp;
    }

    public SuccessEnum delectUser(Long userId) {
        userDAO.delectUserByUserId(userId);
        return SuccessEnum.SUCCESS;
    }

    public GetUserResp selectUser(GetUserReq req) {
        UserDO userDO=new UserDO();
        if (req.getFacultyId()!=null){
            userDO.setFacultyId(req.getFacultyId());
        }
        if (req.getUserId()!=null){
            userDO.setUserId(req.getUserId());
        }
        if (req.getUserType()!=null){
            userDO.setUserType(req.getUserType());
        }
        if (StringUtils.isNotEmpty(req.getName())){
            userDO.setName(req.getName());
        }
        List<UserDO> userDOS = userDAO.selectList(userDO, req.getPage().getPageSize(), req.getPage().getPageNum()-1);
        int total=userDAO.count(userDO);
        GetUserResp getUserResp = new GetUserResp();
        getUserResp.setTotal(total);
        getUserResp.setRet(SuccessEnum.SUCCESS);
        List<UserDTO> userDTOS=new ArrayList<>();
        for (UserDO user : userDOS) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setFacultyId(user.getFacultyId());
            userDTO.setUserType(user.getUserType());
            userDTO.setMail(user.getMail());
            userDTO.setName(user.getName());
            userDTO.setPhone(user.getPhone());
            userDTOS.add(userDTO);
        }
        getUserResp.setUserDTO(userDTOS);
        return getUserResp;
    }

    public SuccessEnum judgeComeInModule(JudgeComeInModuleReq req) {
        UserDO userDO = userDAO.getByUserId(req.getUserId());
        if (userDO.getUserType()== UserTypeEnum.ADMINISTRATOR.getCode()||req.getJudgeEnum()==null){
            return SuccessEnum.SUCCESS;
        }
        TimeDO timeDO = timeDAO.getByEvent(req.getJudgeEnum().getDesc());
        if (timeDO==null){
            return SuccessEnum.TIME_ERROR;
        }
        Long time=System.currentTimeMillis();
        if (timeDO.getStartTime()<=time&&timeDO.getEndTime()>=time){
            return SuccessEnum.SUCCESS;
        }else {
            return SuccessEnum.TIME_ERROR;
        }
    }

    public DirectoryResp getDirectory(int userType) {
        DirectoryResp directoryResp = new DirectoryResp();
        directoryResp.setRet(SuccessEnum.SUCCESS);
        List<DirectoryDO> directoryDOS = directoryDAO.getByUserType("%"+userType+",%");
        List<DirectoryDTO> directoryDTOS= new ArrayList<>();
        for (DirectoryDO directoryDO : directoryDOS) {
            DirectoryDTO directoryDTO = new DirectoryDTO();
            directoryDTO.setName(directoryDO.getName());
            directoryDTO.setPath(directoryDO.getPath());
            directoryDTO.setManagement(directoryDO.getManagement());
            directoryDTOS.add(directoryDTO);
        }

        directoryResp.setDirectory(directoryDTOS);
        return directoryResp;
    }
}

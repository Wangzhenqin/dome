package com.imooc.manager;

import com.imooc.dao.entity.UserDO;
import com.imooc.dao.mybatis.TitleDAO;
import com.imooc.dao.mybatis.UserDAO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.dto.UserDTO;
import com.imooc.entity.req.EnterReq;
import com.imooc.entity.req.UpdateCodeReq;
import com.imooc.entity.req.UpdateUserDataReq;
import com.imooc.entity.resp.EnterResp;
import com.imooc.entity.resp.UpdateUserDataResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wangzhenqin on 2019/6/13.
 */
@Service
public class UserServiceManager {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TitleDAO titleDAO;


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
}

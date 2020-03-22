package com.imooc.dao.entity;

import lombok.Data;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Data
public class UserDO {
    Long userId;
    Integer userType;
    String name;
    String code;
    Long facultyId;
    Long phone;
    String mail;
    Integer isDelete;
}

package com.imooc.dao.mybatis;

import com.imooc.dao.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Mapper
public interface UserDAO {
    UserDO getByUserId(Long userId);

    List<UserDO> getByUserIds(@Param("list") List<Long> teacherIds);

    void updateCodeByUserId(Long userId, String newCode);

    void delectUserByUserId(Long userId);

    void insert(@Param("list") List<UserDO> userDOS);

}

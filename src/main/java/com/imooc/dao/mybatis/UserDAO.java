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

    List<UserDO> getByUserIds(@Param("list") List<Long> userIds);

    void updateCodeByUserId(Long userId, String newCode);

    void delectUserByUserId(Long userId);

    void insert(@Param("list") List<UserDO> userDOS);

    void updateUserByUserId(UserDO userDO);

    List<UserDO> selectList(@Param("userDO")UserDO userDO, int pageSize, int pageNum);

    int count(@Param("userDO")UserDO userDO);
}

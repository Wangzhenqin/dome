package com.imooc.dao.mybatis;

import com.imooc.dao.entity.FacultyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Mapper
public interface FacultyDAO {
    List<FacultyDO> getByNames(@Param("list") List<String> collect);
}

package com.imooc.dao.mybatis;

import com.imooc.dao.entity.TitleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Mapper
public interface TitleDAO {
    List<TitleDO> getByFacultyId(Long facultyId, int pageNum, int pageSize);

    int countByFacultyId(Long facultyId);
}

package com.imooc.dao.mybatis;

import com.imooc.dao.entity.TitleDO;
import com.imooc.entity.dto.TitleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Mapper
public interface TitleDAO {
    List<TitleDO> getByFacultyId(Long facultyId, int pageNum, int pageSize);

    int countByFacultyId(Long facultyId);

    void insert(@Param("list") List<TitleDO> titleDOS);

    List<TitleDO> getByTeacherId(Long teacherId, int pageNum, int pageSize);

    int countByTeacherId(@Param("teacherId") Long teacherId);

    TitleDO getByTitleId(Long titleId);

    void updateTitleByTitleId(TitleDO titleDO);

    void deleteTitleByTitleId(Long titleId);

    List<TitleDO> getByStudentId(Long studentId);

    int countComfirmTitleByStudentId(Long studentId);

    List<TitleDO> getByDTO(@Param("titleDTO")TitleDTO titleDTO, int pageNum, int pageSize);

    int countByDTO(@Param("titleDTO")TitleDTO titleDTO);
}

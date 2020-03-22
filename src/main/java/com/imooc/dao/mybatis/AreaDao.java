package com.imooc.dao.mybatis;

import com.imooc.dao.entity.Area;

import java.util.List;

/**
 * Created by wangzhenqin on 2019/6/12.
 */

public interface AreaDao {
    //列出区域信息
    List<Area> queryArea();
    //根据id查找
    Area queryAreaById(int areaId);
    //插入区域信息
    int insertArea(Area area);
    //更新区域信息
    int updataArea(Area area);
    //删除区域信息
    int deleteArea(int areaId);
}

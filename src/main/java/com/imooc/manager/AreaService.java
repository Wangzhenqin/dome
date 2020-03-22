package com.imooc.manager;

import com.imooc.dao.entity.Area;

import java.util.List;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
public interface AreaService {
    //列出区域信息
    List<Area> getAreaList();
    //根据id查找
    Area getAreaById(int areaId);
    //插入区域信息
    boolean addArea(Area area);
    //更新区域信息
    boolean modifyArea(Area area);
    //删除区域信息
    boolean deleteArea(int areaId);
}

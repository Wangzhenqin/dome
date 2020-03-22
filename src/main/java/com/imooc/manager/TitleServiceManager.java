package com.imooc.manager;

import com.imooc.dao.entity.Area;
import com.imooc.dao.mybatis.AreaDao;
import com.imooc.manager.AreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@Service
public class TitleServiceManager implements AreaService{
    private AreaDao areaDao;
    public TitleServiceManager(AreaDao areaDao){
        this.areaDao=areaDao;
    }
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }
    @Transactional
    @Override
    public boolean addArea(Area area) {
        if (area.getAreaName()!=null && !"".equals(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try{
                int effectedNum=areaDao.insertArea(area);
                if (effectedNum>0)
                    return true;
                else
                    throw new RuntimeException("插入失败");
            }catch (Exception e){
                throw new RuntimeException("插入失败："+e.getMessage());
            }
        }else
            throw new RuntimeException("不能为空");
    }
    @Transactional
    @Override
    public boolean modifyArea(Area area) {
        if (area.getAreaId()!=null && area.getAreaId()>0){
            area.setLastEditTime(new Date());
            try{
                int effectedNum=areaDao.updataArea(area);
                if (effectedNum>0)
                    return true;
                else
                    throw new RuntimeException("更新失败");
            }catch (Exception e){
                throw new RuntimeException("更新失败："+e.toString());
            }
        }else
            throw new RuntimeException("不能为空");
    }
    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if (areaId>0){

            try{
                int effectedNum=areaDao.deleteArea(areaId);
                if (effectedNum>0)
                    return true;
                else
                    throw new RuntimeException("删除失败");
            }catch (Exception e){
                throw new RuntimeException("删除失败："+e.getMessage());
            }
        }else
            throw new RuntimeException("不能为空");
    }
}

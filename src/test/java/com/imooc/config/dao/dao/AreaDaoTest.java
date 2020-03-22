package com.imooc.config.dao.dao;

import com.imooc.dao.mybatis.AreaDao;
import com.imooc.dao.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    @Ignore
    public void queryArea() throws Exception {
        List<Area> areaList=areaDao.queryArea();
        assertEquals(2,areaList.size());
    }

    @Test

    public void queryAreaById() throws Exception {
        Area area=areaDao.queryAreaById(1);
        assertEquals("东",area.getAreaName());
    }

    @Test
    @Ignore
    public void insertArea() throws Exception {
        Area area=new Area();
        area.setAreaName("南");
        area.setPriority(1);
        int effectedNum=areaDao.insertArea(area);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void updataArea() throws Exception {
        Area area=new Area();
        area.setAreaName("西");
        area.setAreaId(3);
        area.setLastEditTime(new Date());
        int effectedNum=areaDao.updataArea(area);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void deleteArea() throws Exception {
        int effectedNum=areaDao.deleteArea(3);
        assertEquals(1,effectedNum);
    }

}
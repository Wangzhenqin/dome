package com.imooc.dao.mybatis;

import com.imooc.dao.entity.TimeDO;
import com.imooc.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Mapper
public interface TimeDAO {
    TimeDO getByEvent(String event);

    List<TimeDO> getList(Integer pageNum,Integer pageSize);

    void updateByEvent(TimeDO timeDO);
}

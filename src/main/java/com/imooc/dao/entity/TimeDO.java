package com.imooc.dao.entity;

import lombok.Data;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Data
public class TimeDO {
     Long studentId;
     Long titleId;
     String statusReplyTime;
     String paperTime;
     String gradeTime;
     Integer isDelete;
}

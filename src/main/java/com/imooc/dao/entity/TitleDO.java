package com.imooc.dao.entity;

import lombok.Data;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Data
public class TitleDO {
    Long titleId;
    String titleName;
    Long teacherId;
    Long studentId;
    Integer status;
    String task;
    String content;
    String statusReport;
    String paper;
    Integer isDelete;
}

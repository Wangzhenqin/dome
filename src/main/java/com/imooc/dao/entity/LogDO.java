package com.imooc.dao.entity;

import lombok.Data;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
@Data
public class LogDO {
    Long userId;
    String detail;
    Integer isDelete;
}

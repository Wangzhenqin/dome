package com.imooc.dao.entity;


import java.util.Date;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
public class FacultyDO {
    private Long facultyId;
    private String facultyName;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyNamee(String facultyName) {
        this.facultyName = facultyName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

package com.imooc.dao.entity;


import java.util.Date;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
public class FacultyDO {
    private Long facultyId;
    private String facultName;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultName() {
        return facultName;
    }

    public void setFacultName(String facultName) {
        this.facultName = facultName;
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

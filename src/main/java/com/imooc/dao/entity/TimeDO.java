package com.imooc.dao.entity;


import java.util.Date;

/**
 * Created by wangzhenqin on 2020/3/22.
 */
public class TimeDO {
     private Long studentId;
     private Long selectTitleTime;
     private Long statusReplyTime;
     private Long paperTime;
     private Long gradeTime;
     private Integer isDelete;
     private Date createTime;
     private Date updateTime;

     public Long getStudentId() {
          return studentId;
     }

     public void setStudentId(Long studentId) {
          this.studentId = studentId;
     }

     public Long getSelectTitleTime() {
          return selectTitleTime;
     }

     public void setSelectTitleTime(Long selectTitleTime) {
          this.selectTitleTime = selectTitleTime;
     }

     public Long getStatusReplyTime() {
          return statusReplyTime;
     }

     public void setStatusReplyTime(Long statusReplyTime) {
          this.statusReplyTime = statusReplyTime;
     }

     public Long getPaperTime() {
          return paperTime;
     }

     public void setPaperTime(Long paperTime) {
          this.paperTime = paperTime;
     }

     public Long getGradeTime() {
          return gradeTime;
     }

     public void setGradeTime(Long gradeTime) {
          this.gradeTime = gradeTime;
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

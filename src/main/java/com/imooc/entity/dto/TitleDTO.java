package com.imooc.entity.dto;


public class TitleDTO {
    private Long titleId;
    private String titleName;
    private String content;
    private Long teacherName;

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(Long teacherName) {
        this.teacherName = teacherName;
    }
}

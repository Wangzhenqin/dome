package com.imooc.entity.dto;


/**
 * Created by wangzhenqin on 2020/3/22.
 */
public class TitleExcelDTO {
    private Long teacherId;
    private String titleName;
    private String content;

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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}

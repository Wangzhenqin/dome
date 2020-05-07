package com.imooc.entity.Enum;

import java.util.HashMap;
import java.util.Map;

public enum StudentFileEnum {
    STATUS_REPLY(1,"开题报告提交"),
    PAPER(2,"论文提交");

    StudentFileEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
    private static Map<Integer, StudentFileEnum> codeMap = new HashMap<>(values().length);

    static {
        for (StudentFileEnum e : values()) {
            codeMap.put(e.getCode(), e);
        }
    }

    public static StudentFileEnum convert(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static StudentFileEnum getByCode(Integer code) {
        if(null == code) {
            return null;
        }
        for (StudentFileEnum successEnum:values()) {
            if(successEnum.getCode().equals(code)) {
                return successEnum;
            }
        }
        return null;
    }

}

package com.imooc.entity.Enum;

import java.util.HashMap;
import java.util.Map;

public enum JudgeEnum {
    SELECT_TITLE(1, "选题"),
    STATUS_REPLY_TIME(2,"开题报告提交"),
    PAPER_TIME(3,"论文提交"),
    GRADE_TIME(4,"分数提交");

    JudgeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
    private static Map<Integer, JudgeEnum> codeMap = new HashMap<>(values().length);

    static {
        for (JudgeEnum e : values()) {
            codeMap.put(e.getCode(), e);
        }
    }

    public static JudgeEnum convert(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static JudgeEnum getByCode(Integer code) {
        if(null == code) {
            return null;
        }
        for (JudgeEnum successEnum:values()) {
            if(successEnum.getCode().equals(code)) {
                return successEnum;
            }
        }
        return null;
    }

}

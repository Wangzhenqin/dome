package com.imooc.entity.Enum;

import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {
    ADMINISTRATOR(0, "管理员"),
    STUDENT(1,"学生"),
    TEACHER(2,"老师");

    UserTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
    private static Map<Integer, UserTypeEnum> codeMap = new HashMap<>(values().length);

    static {
        for (UserTypeEnum e : values()) {
            codeMap.put(e.getCode(), e);
        }
    }

    public static UserTypeEnum convert(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static UserTypeEnum getByCode(Integer code) {
        if(null == code) {
            return null;
        }
        for (UserTypeEnum successEnum:values()) {
            if(successEnum.getCode().equals(code)) {
                return successEnum;
            }
        }
        return null;
    }

    public static UserTypeEnum getByDesc(String desc) {
        if(null == desc) {
            return null;
        }
        for (UserTypeEnum successEnum:values()) {
            if(successEnum.getDesc().equals(desc)) {
                return successEnum;
            }
        }
        return null;
    }

}

package com.imooc.entity.Enum;

import java.util.HashMap;
import java.util.Map;

public enum TitleStatusEnum {
    NOT_SELECT(0, "未选择"),
    SELECT(1,"已选择"),
    CONFIRM(2,"已确认");

    TitleStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
    private static Map<Integer, TitleStatusEnum> codeMap = new HashMap<>(values().length);

    static {
        for (TitleStatusEnum e : values()) {
            codeMap.put(e.getCode(), e);
        }
    }

    public static TitleStatusEnum convert(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static TitleStatusEnum getByCode(Integer code) {
        if(null == code) {
            return null;
        }
        for (TitleStatusEnum successEnum:values()) {
            if(successEnum.getCode().equals(code)) {
                return successEnum;
            }
        }
        return null;
    }

    public static TitleStatusEnum getByDesc(String desc) {
        if(null == desc) {
            return null;
        }
        for (TitleStatusEnum successEnum:values()) {
            if(successEnum.getDesc().equals(desc)) {
                return successEnum;
            }
        }
        return null;
    }

}

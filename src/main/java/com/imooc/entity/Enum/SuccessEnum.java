package com.imooc.entity.Enum;

import java.util.HashMap;
import java.util.Map;

public enum SuccessEnum {
    SUCCESS(1, "成功"),
    CODE_ERROR(2,"密码错误"),
    FILE_NOT_EXIST(3,"文件不存在"),
    INNER_ERROR(-1, "内部错误");

    SuccessEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
    private static Map<Integer, SuccessEnum> codeMap = new HashMap<>(values().length);

    static {
        for (SuccessEnum e : values()) {
            codeMap.put(e.getCode(), e);
        }
    }

    public static SuccessEnum convert(Integer code) {
        return codeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SuccessEnum getByCode(Integer code) {
        if(null == code) {
            return null;
        }
        for (SuccessEnum successEnum:values()) {
            if(successEnum.getCode().equals(code)) {
                return successEnum;
            }
        }
        return null;
    }

}

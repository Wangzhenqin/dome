package com.imooc.manager;

import com.imooc.common.ExcelProperty;
import com.imooc.common.FileUtil;
import com.imooc.common.ImportUtils;
import com.imooc.common.ListUtil;
import com.imooc.dao.entity.FacultyDO;
import com.imooc.dao.entity.UserDO;
import com.imooc.dao.mybatis.FacultyDAO;
import com.imooc.dao.mybatis.UserDAO;
import com.imooc.entity.Enum.BooleanEnum;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.Enum.UserTypeEnum;
import com.imooc.entity.dto.UserExcelDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@Service
public class UploadOrDownloadServiceManager{
    @Autowired
    FacultyDAO facultyDAO;
    @Autowired
    UserDAO userDAO;

    public SuccessEnum uploadUser(String url) {
        String filePath = FileUtil.getFilePathFromUrl(url);
        File file = new File(filePath);
        if (!file.exists()) {
            return SuccessEnum.FILE_NOT_EXIST;
        }
        //读数据
        List<UserExcelDTO> result = this.parse(file, UserExcelDTO.class, true, 0);
        List<FacultyDO> facultyDOS = facultyDAO.getByNames(result.stream().map(UserExcelDTO::getFacultyName).collect(Collectors.toList()));
        Map<String, FacultyDO> FacultyDOMap = ListUtil.makeMap(facultyDOS, FacultyDO::getFacultName);
        List<UserDO> userDOS=new ArrayList<>();
        for (UserExcelDTO userExcelDTO : result) {
            UserDO userDO = new UserDO();
            if (userExcelDTO.getUserId()==null||StringUtils.isEmpty(userExcelDTO.getCode())||
                    StringUtils.isEmpty(userExcelDTO.getName())||
                    !FacultyDOMap.containsKey(userExcelDTO.getFacultyName())||
                    UserTypeEnum.getByDesc(userExcelDTO.getUserType())==null){
                continue;
            }
            userDO.setUserId(userExcelDTO.getUserId());
            userDO.setCode(userExcelDTO.getCode());
            userDO.setName(userExcelDTO.getName());
            userDO.setFacultyId(FacultyDOMap.get(userExcelDTO.getFacultyName()).getFacultyId());
            userDO.setUserType(UserTypeEnum.getByDesc(userExcelDTO.getUserType()).getCode());
            userDOS.add(userDO);
        }
        userDAO.insert(userDOS);
        return SuccessEnum.SUCCESS;
    }

    /**
     * 将 Excel 解析为 JavaBean 对象
     * @since  2019-04-23 16:07
     * @param file
     * @param clazz
     * @param beContainTitle 是否包含表头
     * @param rowIndex 从第几行开始解析，第一行为 0，依次类推
     * @return
     */
    public static <T> List<T> parse(File file, Class<T> clazz, boolean beContainTitle, int rowIndex) {
        List<List<String>> result = parse(file, beContainTitle, rowIndex);
        return result.stream()
                .map(x -> setField(clazz, x))
                .collect(Collectors.toList());
    }

    /**
     * 将 Excel 解析为 List 集合
     * @since  2019-04-23 16:11
     * @param file
     * @param beContainTitle 是否包含表头
     * @param rowIndex 从第几行开始解析，第一行为 0，依次类推
     * @return
     */
    public static List<List<String>> parse(File file, boolean beContainTitle, int rowIndex) {
        Sheet sheet = ImportUtils.parseFile(file);
        int rowCount = sheet.getPhysicalNumberOfRows(); // 总行数
        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells(); // 总列数
        int i = beContainTitle ? rowIndex + 1 : rowIndex; // 是否包含表头

        List<List<String>> result = new ArrayList<List<String>>();
        for (; i < rowCount; i++) {
            List<String> cellResult = new ArrayList<>();
            for (int j = 0; j < cellCount; j++) {
                cellResult.add(ImportUtils.getCellValue(sheet, i, j));
            }
            if (StringUtils.isBlank(cellResult.get(0))) {
                continue;
            }
            result.add(cellResult);
        }
        return result;
    }

    /**
     * 为对象的每一个属性赋值
     * @since  2019-04-23 16:10
     * @param <T>
     * @param clazz
     * @param rowDatas 当前行的数据
     * @return
     */
    private static <T> T setField(Class<T> clazz, List<String> rowDatas) {
        try {
            T obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                if (excelProperty == null) {
                    continue;
                }
                int index = excelProperty.index();
                String format = excelProperty.format();
                Object value = getFieldValue(field, rowDatas.get(index), format);
                field.setAccessible(true);
                field.set(obj, value);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 根据 Java 对象的属性的类型，将 Excel 中的值解析为相对应的类型
     * @since  2019-04-23 16:12
     * @param field 对象的属性
     * @param value Excel 中的值
     * @param pattern 如果 Java 对象的属性的类型为 Date，使用 pattern 模式将  Excel 中的值转换为 Date 类型
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Object getFieldValue(Field field, String value, String pattern) {
        Class typeClass = field.getType();
        Object val = null;
        if (typeClass == Integer.class) {
            val = Integer.valueOf(value);
        } else if(typeClass == Long.class) {
            val = Long.valueOf(value);
        } else if(typeClass == Float.class) {
            val = Float.valueOf(value);
        } else if(typeClass == Double.class) {
            val = Double.valueOf(value);
        } else if(typeClass == Date.class) {
            val = ImportUtils.getDate(value, pattern);
        } else if(typeClass == Short.class) {
            val = Short.valueOf(value);
        } else if(typeClass == Character.class) {
            val = Character.valueOf(value.charAt(0));
        } else if(typeClass == Boolean.class) {
            val = Arrays.asList(BooleanEnum.values()).stream()
                    .filter(x -> x.getName().equals(value.toUpperCase()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new)
                    .getValue();
        } else {
            val = value;
        }
        return val;
    }
}

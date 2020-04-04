package com.imooc.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportUtils {
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    /**
     * 解析导入模板
     * @param file
     * @return
     */
    public static Sheet parseFile(File file) {
        FileInputStream in = null;
        Workbook workBook = null;
        try {
            String suffix = file.getName().substring(file.getName().indexOf(".") + 1);
            in = new FileInputStream(file);
            workBook = suffix.equals("xlsx") ? new XSSFWorkbook(in) : new HSSFWorkbook(in);
            return workBook.getSheetAt(0);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
        }
    }

    /**
     * 返回指定单元格的数据
     * @since  2019-04-23 19:01
     * @param sheet
     * @param rowIndex 第几行，从 0 开始
     * @param cellIndex 第几列，从 0 开始
     * @return
     */
    public static String getCellValue(Sheet sheet, int rowIndex, int cellIndex) {
        return getCellValue(sheet.getRow(rowIndex).getCell(cellIndex));
    }

    /**
     * 格式化解析的数据
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数值类型
                    if(DateUtil.isCellDateFormatted(cell)) {
                        cellValue = getDateStr(cell.getDateCellValue(), pattern);
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cellValue = cell.getStringCellValue();
                    }
                    break;
                case Cell.CELL_TYPE_STRING: // 字符串类型
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA: // 公式类型
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK: // 空白类型
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

    public static Date getDate(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getDateStr(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
}

package mywebapp;

import com.alibaba.fastjson.JSON;
import com.shadow.domain.CellTemplate;
import com.shadow.util.ReadFileUtil;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.List;

import static org.apache.poi.ss.util.CellReference.convertNumToColString;

/**
 * Created by alice on 2016/12/11 23:36
 */
public class TestExcelDataValidation {
    public static final String outputFile = "d:/test.xls "; // 生成的文件
    private static final String HIDE_SHEET = "hideSheet";

    @SuppressWarnings("DMI_HARDCODED_ABSOLUTE_FILENAME")
    public static void main(String[] args) {
        List<CellTemplate> list = JSON.parseArray(ReadFileUtil.toString("/json/others/cellTemplate.json"), CellTemplate.class);
        Workbook wb = new HSSFWorkbook();
        Sheet firstSheet = wb.createSheet("firstSheet");
//        Sheet hideSheet = wb.createSheet(HIDE_SHEET);
        wb.setSheetHidden(wb.getSheetIndex(HIDE_SHEET), false);

        for (int i = 0; i < list.size(); i++) {
            CellTemplate cellTemplate = list.get(i);
            if ("SELECT".equals(cellTemplate.getType())) {
                String rule = cellTemplate.getRule();
//                cellTemplate.getName();
                String[] arr = null;
                if (rule.contains(";")) {

                } else if (rule.contains(",")) {
                    arr = rule.split(",");
                }
                firstSheet.addValidationData(getDataValidationByFormula(arr, 2, i, 2));
            }
        }

        //输出
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(outputFile);
            wb.write(fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * 关联下拉需要先写数据字典
     *
     * @param workbook
     * @param sheet
     * @param list
     * @param rowNum
     */
    private static void createHiddenSheet(Workbook workbook, Sheet sheet, List<String[]> list, int rowNum) {
        //先输出在sheet中
        int i = rowNum;
        for (String[] strArr : list) {
            Row row = sheet.createRow(i++);
            for (int j = 0; j < strArr.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(strArr[j]);
            }
        }
        //设置数据字典
        i = rowNum;
        for (String[] strArr : list) {
            int end = strArr.length;
            i++;
            Name name = workbook.createName();
            name.setNameName(strArr[0]);
            name.setRefersToFormula(MessageFormat.format(HIDE_SHEET + "!${0}${1}:${2}${3}",
                    convertNumToColString(1), i, convertNumToColString(end - 1), i));
        }
    }

    /**
     * 设置约束
     *
     * @param formulaString      下拉值
     * @param naturalRowIndex    行index
     * @param naturalColumnIndex 列index
     * @param flag               约束类型
     * @return
     */
    private static DataValidation getDataValidationByFormula(String[] formulaString, int naturalRowIndex, int naturalColumnIndex, int flag) {
        String msg = "请下拉选择合适的值！";
        //加载下拉列表内容
        DVConstraint constraint = null;
        if (flag == 1) {//序列
            constraint = DVConstraint.createFormulaListConstraint(formulaString[0]);
        } else if (flag == 2) {//约束范围
            constraint = DVConstraint.createExplicitListConstraint(formulaString);
        } else if (flag == 3) {//数值，范围内
            constraint = DVConstraint.createNumericConstraint(DataValidationConstraint.ValidationType.INTEGER,
                    DataValidationConstraint.OperatorType.BETWEEN, formulaString[0], formulaString[1]);
            msg = "数值在0-86之间！";
        } else if (flag == 4) {//小数，大于等于
            constraint = DVConstraint.createNumericConstraint(DataValidationConstraint.ValidationType.DECIMAL,
                    DataValidationConstraint.OperatorType.GREATER_OR_EQUAL, formulaString[0], null);
            msg = "请输入大于等于20！";
        }
        int i = naturalRowIndex - 1;
        int j = naturalColumnIndex - 1;
        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        DataValidation d = new HSSFDataValidation(new CellRangeAddressList(i, i, j, j), constraint);
        d.createErrorBox("提示", msg);
        return d;
    }


}

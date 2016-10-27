package mywebapp;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.util.CellReference.convertNumToColString;

/**
 * Created by alice on 2016/10/24.
 */
public class TestExcel {
    public static final String outputFile = "d:/test.xls "; // 生成的文件
    private static final String HIDE_SHEET = "hideSheet";


    public static void main(String[] args) {

        final String SEX= "性别";
        List<String[]> sexList = new ArrayList<>();
        String[] sex = {SEX, "男", "女"};
        String[] male = {"男", "鬼剑士", "格斗家", "神枪手", "魔法师", "圣职者", "黑暗武士", "魔枪士", "剑魂", "剑圣", "剑神", "散打", "武极", "极武皇", "漫游枪手", "枪神", "掠天之翼", "元素爆破师", "魔皇", "湮灭之瞳", "圣骑士", "天启者", "神思者", "决战者", "无双之魂", "圣武枪魂", "狂战士", "狱血魔神", "帝血弑天", "柔道家", "风林火山", "宗师", "弹药专家", "大将军", "战场 统治者", "冰结师", "冰冻之心", "刹那永恒", "蓝拳使者", "神之手", "正义仲裁者", "征战者", "战魂", "不灭战魂", "阿修罗", "大暗黑天", "天帝", "街霸", "千手罗汉", "暗街之王", "枪炮师", "狂暴者", "毁灭者", "驱魔师", "龙斗士", "真龙星君", "鬼泣", "弑魂 ", "黑暗君主", "气功师", "猛虎帝", "念皇", "机械师", "机械战神", "机械元首", "复仇者", "末日审判者", "永生者"};
        String[] female = {"女", "鬼剑士", "格斗家", "神枪手", "魔法师", "暗夜使者", "缔造者", "守护者", "驭剑士", "剑宗", "剑皇", "散打", "武圣", "极武圣", "漫游枪手", "沾血蔷薇", "绯红玫瑰", "元素师", "大魔导师", "元素圣灵", "刺客", "银月", "月影星劫", "精灵骑士", "星辰之光", "大地女神", "暗殿骑士", "暗帝", "复仇女神", "柔道家", "暴风眼", "风暴女皇", "弹药专家", "战争女神", "芙蕾雅", "召唤师", "月之女皇", "月蚀", "死灵术士", "灵魂收割者", "塔纳托斯", "混沌魔灵", "黑魔后", "黑曜神", "流浪武士", "剑豪", "剑帝", "街霸", "毒王", "毒神绝", "枪炮师", "重炮掌控者", "风暴骑兵", "魔道学者", "魔术师", "古灵精怪", "忍者", "毕方之炎", "不知火", "契魔者", "裁决女神", "弑神者", "气功师", "百花缭乱", "念帝", "机械师", "机械之心", "机械之灵", "战斗法师", "贝亚娜斗神", "伊斯塔战灵", "影舞者", "梦魇", "幽冥", "极武圣"};
        sexList.add(sex);
        sexList.add(male);
        sexList.add(female);


        final String PROVINCE= "省份";
        List<String[]> provList = new ArrayList<>();
        String[] pList = {PROVINCE, "江苏", "浙江", "山东"};
        String[] jsList = {"江苏", "南京", "苏州", "扬州"};
        String[] zjList = {"浙江", "杭州", "宁波", "温州"};
        String[] sdList = {"山东", "济南", "青岛", "烟台"};
        provList.add(pList);
        provList.add(jsList);
        provList.add(zjList);
        provList.add(sdList);


        Workbook workbook = new HSSFWorkbook();
        Sheet first = workbook.createSheet("帐号发布");
        Sheet sheet = workbook.createSheet(HIDE_SHEET);
        workbook.setSheetHidden(workbook.getSheetIndex(HIDE_SHEET), false);

        createHiddenSheet(workbook, sheet, sexList, 0);
        createHiddenSheet(workbook, sheet, provList, sexList.size());

        setDataValidation(workbook,SEX, 2, new int[]{1, 2});
        setDataValidation(workbook,PROVINCE, 2, new int[]{3, 4});


        //输出
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(outputFile);
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void setDataValidation(Workbook wb,String str, int startRow, int[] cols) {
        int sheetIndex = wb.getNumberOfSheets();
        String[] colNames = getColName(cols);
        if (sheetIndex > 0) {
            Sheet sheet = wb.getSheetAt(0);
            DataValidation d = null;
            for (int row = startRow; row < startRow + 10; row++) {
                d = getDataValidationByFormula(str, row, cols[0]);
                sheet.addValidationData(d);
                for (int i = 0; i < colNames.length - 1; i++) {
                    d = getDataValidationByFormula("INDIRECT($" + colNames[i] + "$" + row + ")", row, cols[i] + 1);
                    sheet.addValidationData(d);
                }
                //设置其他约束

            }
        }
    }

    private static String[] getColName(int[] colIndex) {
        String[] colName = new String[colIndex.length];
        for (int i = 0; i < colIndex.length; i++) {
            colName[i] = CellReference.convertNumToColString(colIndex[i] - 1);
        }
        return colName;
    }

    private static DataValidation getDataValidationByFormula(String formulaString, int naturalRowIndex, int naturalColumnIndex) {
        int i = naturalRowIndex - 1;
        int j = naturalColumnIndex - 1;
        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        DataValidation d = new HSSFDataValidation(new CellRangeAddressList(i, i, j, j), DVConstraint.createFormulaListConstraint(formulaString));
        d.createErrorBox("提示", "你输入的值不合法，请下拉选择合适的值！");
        return d;
    }

    private static String createHiddenSheet(Workbook workbook, Sheet sheet, List<String[]> list, int rowNum) {
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
            name.setRefersToFormula(MessageFormat.format(HIDE_SHEET + "!${0}${1}:${2}${3}", convertNumToColString(1), i, convertNumToColString(end - 1), i));
        }
        return  list.get(0)[0];
    }


}

package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author shadowyy
 * @version 2017/10/24 11:47
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Workbook workbook = new XSSFWorkbook(new File("C:\\documents\\机器学习申请风险模型_变量列表.xlsx"));
        Sheet sh = workbook.getSheetAt(1);//0代表第一个

        String tempString=null;
        ArrayList<String> base = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("c:/documents/风控-手机.txt")));
        while ((tempString = reader.readLine()) != null) {
            base.add(tempString);
        }

        int num=123;
        for(int i=1;i<197;i++){
            String search=sh.getRow(i).getCell(0).getStringCellValue();
            if(!base.contains(search)){
                System.out.println(search);
                Cell cell = sh.getRow(i).createCell(5);
                cell.setCellValue("ph_"+(num++)+"_"+search);
            }
            System.out.println();
        }


        FileOutputStream out = new FileOutputStream("c:\\documents\\test.xlsx");
        out.flush();
        workbook.write(out);
        out.close();
    }


}

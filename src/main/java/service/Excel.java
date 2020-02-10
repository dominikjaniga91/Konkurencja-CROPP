package service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Map;
import java.util.Set;

public class Excel {

    public static XSSFWorkbook createNewXlsxFile(String brandName) {

        System.out.println("Creating new XSSFWorkbook..");
        XSSFWorkbook xslxSpreadsheet = new XSSFWorkbook();
        xslxSpreadsheet.createSheet(brandName);

        return xslxSpreadsheet;
    }

    public static void writeToExcel(Map<Integer, Object[]> data, XSSFWorkbook xslxSpreadsheet) {

        XSSFSheet mySheet = xslxSpreadsheet.getSheetAt(0);
        Set<Integer> newRows = data.keySet();
        int rowNumber = mySheet.getLastRowNum() + 1;

        for (Integer key : newRows) {
            Row row = mySheet.createRow(rowNumber++);
            Object[] objAtt = data.get(key);
            int cellNumber = 0;
            for (Object obj : objAtt) {
                Cell cell = row.createCell(cellNumber++);
                cell.setCellValue((String) obj);
            }
        }
    }
}

package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static final String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/test-data/Testdata.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		Object[][] data = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void setCellData(String sheetName, int rownum, int colnum, String data) {
		try {
			fi = new FileInputStream(TESTDATA_SHEET_PATH);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rownum);
			cell = row.createCell(colnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(TESTDATA_SHEET_PATH);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

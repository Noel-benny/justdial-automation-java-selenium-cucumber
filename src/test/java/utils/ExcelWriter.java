package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter {
    private static final Logger logger = LogManager.getLogger(ExcelWriter.class);

    public static void writeToExcel(String browserName, List<carWashInfo> data) {
        String fileName = "C:\\Users\\2408723\\eclipse-workspace\\carwash_all_Capstone\\src\\test\\resources\\Excel_apache.xlsx";
        String sheetName = browserName.equalsIgnoreCase("chrome") ? "Chrome Results" : "Edge Results";
        logger.info("Writing results to Excel: Sheet = " + sheetName);

        try (Workbook workbook = getOrCreateWorkbook(fileName)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                int sheetIndex = workbook.getSheetIndex(sheet);
                workbook.removeSheetAt(sheetIndex);
                logger.info("Old sheet removed: " + sheetName);
            }

            sheet = workbook.createSheet(sheetName);
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            //header.createCell(1).setCellValue("Phone");
            header.createCell(2).setCellValue("Rating");

            int rowNum = 1;
            for (carWashInfo info : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(info.getName());
                //row.createCell(1).setCellValue(info.getPhone());
                row.createCell(2).setCellValue(info.getRating());
                logger.debug("Written: " + info.getName() + ", " + info.getPhone());
            }

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
                logger.info("Excel file saved: " + fileName);
            }

        } catch (Exception e) {
            logger.error("Error while writing Excel file", e);
        }
    }

    private static Workbook getOrCreateWorkbook(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                logger.info("Existing Excel file found. Loading workbook...");
                return new XSSFWorkbook(new FileInputStream(file));
            } else {
                logger.info("Excel file not found. Creating new workbook...");
                return new XSSFWorkbook();
            }
        } catch (Exception e) {
            logger.warn("Failed to load workbook. Creating new one.", e);
            return new XSSFWorkbook();
        }
    }
}

package com.sk.code.utility.service;

import com.sk.code.utility.model.FoodSales;
import com.sk.code.utility.repository.FoodSalesRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelDataLoader {

    public static final Logger LOG = LoggerFactory.getLogger(ExcelDataLoader.class);

    @Autowired
    FoodSalesRepository repository;
    public void verifyAndLoadData(String filePath) {
        List<FoodSales> dataList = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(filePath);
            XSSFSheet sheet = workbook.getSheetAt(1); // index start from 0, I need from next sheet

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                DataFormatter formatter = new DataFormatter();
                FoodSales foodSales = new FoodSales();
                foodSales.setProductId(formatter.formatCellValue(row.getCell(0)));
                foodSales.setProductDate(formatter.formatCellValue(row.getCell(1)));
                foodSales.setRegion(formatter.formatCellValue(row.getCell(2)));
                foodSales.setCity(formatter.formatCellValue(row.getCell(3)));
                foodSales.setCategory(formatter.formatCellValue(row.getCell(4)));
                foodSales.setProductName(formatter.formatCellValue(row.getCell(5)));
                foodSales.setQuantity(formatter.formatCellValue(row.getCell(6)));
                foodSales.setUnitPrice(formatter.formatCellValue(row.getCell(7)));
                foodSales.setTotalPrice(formatter.formatCellValue(row.getCell(8)));
                foodSales.setInsertDate(new Date());
                // Set other fields as needed
                dataList.add(foodSales);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repository.saveAll(dataList);
    }
}

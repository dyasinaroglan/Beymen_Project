package excelUtils;

import org.apache.poi.ss.usermodel.*;
import pages.Locators;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import static pages.Locators.*;

public class ExcelUtilities {

    public static Workbook workbook;
    private static FileInputStream input;

    public static List<String> getData(String file, String sayfa) throws IOException {

        List<String> listOfValues = new LinkedList<>(); //listenin listesi oluşturdum
        //excel dosyasının içeriğini okudum
        InputStream input = new FileInputStream(file);  // input ile okusun

        workbook = WorkbookFactory.create(input); //input ile geleni creat et. Apache'ye bunu Workbook olarak tanı demiş oluyoruz.
        //apache bizim için bu excel dosyasını okuyabiliyor

        Sheet sheet = workbook.getSheet(sayfa); // beymen.xlsx dosyasındaki sayfa1'i al
        int rowNum = sheet.getPhysicalNumberOfRows(); //satır sayısını al
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i); //satırları al
            int cellNum = row == null ? 0 : row.getPhysicalNumberOfCells(); //hücre sayısını al

            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j); // hücreleri al
                String cellValue = cell == null ? "" : cell.toString(); // null olduğunda boşluk yaz ve değerini al
                listOfValues.add(cellValue); //listeye ekle
            }

        }
        return listOfValues; //listeyi döndür
    }
    public static void WriteToExcel(String file) throws IOException {

        FileOutputStream output = new FileOutputStream(file);
        workbook =WorkbookFactory.create(input);
    }

    public static void writeTXT(String path,String str) throws IOException {

        File file = new File("dosya.txt");
        if(!file.exists()){
        file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(str);
        bufferedWriter.close();

    }

}

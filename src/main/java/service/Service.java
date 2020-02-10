package service;


import model.inditex.*;
import model.otherBrands.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import model.otherBrands.BrandsInterface;
import model.inditex.InditexInterface;

public class Service implements BrandsInterface, InditexInterface {

    XSSFWorkbook xslxSpreadsheet;

    public XSSFWorkbook startAnalyzeBrand(String providedBrand, List<Document> htmlCodeFromURLs) throws Exception {

        switch (providedBrand){

            case "bershka": {
                Bershka bershka = new Bershka();
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, bershka);
                break;
            }
            case "house": {
                House house = new House();
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, house);
                break;
            }
            case "reserved": {
                Reserved reserved = new Reserved();
                xslxSpreadsheet =  getDataFromHtml(htmlCodeFromURLs, reserved);
                break;
            }
            case "h&m": {
                Hm hm = new Hm();
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, hm);
                break;
            }
            case "pull&bear": {
                PullBear pullBear = new PullBear();
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, pullBear);
                break;
            }
            case "terranova": {
                Terranova terranova = new Terranova();
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, terranova);
                break;
            }
            case "befree": {
                Befree befree = new Befree();
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, befree);
                break;
            }
            default:{
                throw new Exception("Nie odnaleziono brandu: " + providedBrand);
            }
        }
        return xslxSpreadsheet;
    }

    public static List<Document> readingFile(FileItem item)  throws IOException {

        List<Document> documentsArray = new ArrayList<>();
        InputStream stream = item.getInputStream();
        BufferedReader readFile = new BufferedReader(new InputStreamReader(stream));
        String html;

        while ((html = readFile.readLine()) != null) {
            System.out.println(html);
            Document documentHtml = Jsoup.connect(html).get();
            documentsArray.add(documentHtml);
        }
        readFile.close();
        stream.close();
        return documentsArray;
    }
}

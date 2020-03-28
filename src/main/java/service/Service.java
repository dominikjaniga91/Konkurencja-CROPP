package service;

import model.inditex.*;
import model.otherBrands.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import java.util.List;
import model.otherBrands.OtherBrandsStrategy;
import model.inditex.InditexDataStrategy;

public class Service implements OtherBrandsStrategy, InditexDataStrategy {

    XSSFWorkbook xslxSpreadsheet;
    public XSSFWorkbook startAnalyzeBrand(String providedBrand, List<Document> htmlCodeFromURLs) throws Exception {

        switch (providedBrand){

            case "bershka": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, new Bershka());
                break;
            }
            case "house": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, new House());
                break;
            }
            case "reserved": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, new Reserved());
                break;
            }
            case "h&m": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, new Hm());
                break;
            }
            case "pull&bear": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs,  new PullBear());
                break;
            }
            case "terranova": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, new Terranova());
                break;
            }
            case "befree": {
                xslxSpreadsheet = getDataFromHtml(htmlCodeFromURLs, new Befree());
                break;
            }
            default:{
                throw new Exception("Nie odnaleziono brandu: " + providedBrand);
            }
        }
        return xslxSpreadsheet;
    }


}

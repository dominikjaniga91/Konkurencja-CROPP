package model.otherBrands;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import service.Excel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OtherBrandsStrategy {

    Map<Integer, Object[]> data = new HashMap<>();


    default XSSFWorkbook getDataFromHtml(List<Document> htmlCodeFromURLs, OtherBrands brand ) {

        XSSFWorkbook xslxSpreadsheet = Excel.createNewXlsxFile(brand.getBrandName());

        for (Document htmlCodeFromURL : htmlCodeFromURLs) {

            Elements productName = htmlCodeFromURL.getElementsByClass(brand.getProductClassName());
            Elements price = htmlCodeFromURL.getElementsByClass(brand.getPriceClassName());
            Elements modeloColor = htmlCodeFromURL.getElementsByAttribute(brand.getModeloColorClassName());
            Elements countryData = htmlCodeFromURL.getElementsByAttribute("lang");

            List<String> nameTable = productName.eachText();
            List<String> priceTable = price.eachText();
            List<String> modeloColorTable = modeloColor.eachAttr(brand.getModeloColorParam());
            String country = countryData.attr("lang");

            for (int j = 0; j < priceTable.size(); j++) {

                data.put(j, new Object[]{brand.getBrandName(), country, modeloColorTable.get(j), nameTable.get(j), priceTable.get(j)});
            }

            Excel.writeToExcel(data, xslxSpreadsheet);
            data.clear();
        }

        return xslxSpreadsheet;
    }



}

package model.inditex;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import service.Excel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface InditexInterface {

    Map<Integer, Object[]> data = new HashMap<>();

    default XSSFWorkbook getDataFromHtml(List<Document> htmlCodeFromURLs, AbstractInditex brand) {

        XSSFWorkbook xslxSpreadsheet = Excel.createNewXlsxFile(brand.getBrandName());

        for (Document htmlCodeFromURL : htmlCodeFromURLs) {

            Elements namesAndPrices = htmlCodeFromURL.getElementsByAttribute(brand.getProductAndPriceClassName());
            Elements modeloColor = htmlCodeFromURL.getElementsByAttribute(brand.getModeloColorClassName());
            Elements countryData = htmlCodeFromURL.getElementsByAttributeValue(brand.getLangClass(), brand.getLangClassParam());

            List<String> namesAndPricesTable = namesAndPrices.eachText();
            List<String> modeloColorTable = modeloColor.eachAttr(brand.getModeloColorAttribute());
            String country = countryData.attr(brand.getCountryAttribute());

            for (int j = 0; j < modeloColorTable.size(); j++) {
                if (!("null".equals(modeloColorTable.get(j)))) {
                    if (modeloColorTable.get(j).contains("photo")) {

                        data.put(j, new Object[]{ brand.getBrandName(),
                                                  country,
                                                  modeloColorTable.get(j).substring(brand.getCutFrom(), brand.getCutTo()),
                                                  namesAndPricesTable.get(j)});
                    }
                }
            }
            Excel.writeToExcel(data, xslxSpreadsheet);
            data.clear();
        }
        return xslxSpreadsheet;
    }
}

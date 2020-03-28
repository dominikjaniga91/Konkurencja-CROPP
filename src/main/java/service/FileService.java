package service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private Service service = new Service();

    public void downloadExcelFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        XSSFWorkbook xslxSpreadsheet = prepareFile(request, response);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + xslxSpreadsheet.getSheetName(0) + ".xlsx");
        OutputStream outStream = response.getOutputStream();
        xslxSpreadsheet.write(outStream);
        outStream.flush();
        outStream.close();
    }

    private XSSFWorkbook prepareFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook xslxSpreadsheet = null;
        List<FileItem> items = uploadFile(request);

        for (FileItem item : items) {
            if (item.getContentType().equals("text/plain")) {
                String brandName = item.getName().substring(0, item.getName().length() - 4).toLowerCase();
                List<Document> htmlCodeFromURLs = readingFile(item);
                xslxSpreadsheet = service.startAnalyzeBrand(brandName, htmlCodeFromURLs);

            } else {
                String theMessage = "Brak pliku lub niepoprawne rozszerzenie";
                accessDeniedMessage(request, response, theMessage);
            }
        }
        return xslxSpreadsheet;
    }



    private List<FileItem> uploadFile(HttpServletRequest request) throws Exception {

        List<FileItem> items = null;

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            items = upload.parseRequest(request);
        }
        return items;
    }

    public void  accessDeniedMessage(HttpServletRequest request, HttpServletResponse response, String theMessage) {

        request.setAttribute("information", theMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
        try{
            dispatcher.forward(request, response);
        }catch (Exception ex){
            ex.getMessage();
            ex.printStackTrace();
        }

    }

    private List<Document> readingFile(FileItem item)  throws IOException {

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

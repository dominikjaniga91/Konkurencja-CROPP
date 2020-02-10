package controller;

import service.Service;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * A Java servlet that handles file upload from client.
 *
 */

@WebServlet("/FileController")
public class FileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private Service service = new Service();

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            downloadExcelFile(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String theMessage = e.getMessage();
            accessDeniedMessage(request, response, theMessage);
        }
    }

    private List<FileItem> uploadFile(HttpServletRequest request) throws Exception {

        List<FileItem> items = null;

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            items = upload.parseRequest(request);
        }
        return items;
    }

    private XSSFWorkbook prepareFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook xslxSpreadsheet = null;
        List<FileItem> items = uploadFile(request);

        for (FileItem item : items) {
            if (item.getContentType().equals("text/plain")) {
                String brandName = item.getName().substring(0, item.getName().length() - 4).toLowerCase();
                List<Document> htmlCodeFromURLs = Service.readingFile(item);
                xslxSpreadsheet = service.startAnalyzeBrand(brandName, htmlCodeFromURLs);

            } else {
                String theMessage = "Brak pliku lub niepoprawne rozszerzenie";
                accessDeniedMessage(request, response, theMessage);
            }
        }
        return xslxSpreadsheet;
    }

    private void downloadExcelFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        XSSFWorkbook xslxSpreadsheet = prepareFile(request, response);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + xslxSpreadsheet.getSheetName(0) + ".xlsx");
        OutputStream outStream = response.getOutputStream();
        xslxSpreadsheet.write(outStream);
        outStream.flush();
        outStream.close();
    }

    private static void  accessDeniedMessage(HttpServletRequest request, HttpServletResponse response, String theMessage) throws ServletException, IOException {

        request.setAttribute("information", theMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
        dispatcher.forward(request, response);

    }
}

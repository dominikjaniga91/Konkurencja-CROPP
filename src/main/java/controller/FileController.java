package controller;

import service.FileService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FileController")
public class FileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FileService fileService = new FileService();

   protected void doPost(HttpServletRequest request, HttpServletResponse response)  {

        try {
            fileService.downloadExcelFile(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String theMessage = e.getMessage();
            fileService.accessDeniedMessage(request, response, theMessage);
        }
    }



}

package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "addCarServlet", urlPatterns = "/addCar")
public class addCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> companyNames = new ArrayList<String>();
        companyNames.add("Volvo");
        companyNames.add("BMW");
        companyNames.add("Ford");
        companyNames.add("Mazda");
        request.setAttribute("companyNames", companyNames);
        getServletContext().getRequestDispatcher("/addCar.jsp").forward(request, response);
    }
}

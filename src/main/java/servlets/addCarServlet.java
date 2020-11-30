package servlets;

import models.Car;
import models.User;
import services.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@WebServlet(name = "addCarServlet", urlPatterns = "/addCar")
public class addCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String company = request.getParameter("company");
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Car newCar = new Car(company, name, year, description, price, userId);

        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " insert into car (company, name, year, description, price, userId)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, newCar.getCompany());
            preparedStmt.setString (2, newCar.getName());
            preparedStmt.setInt   (3, newCar.getYear());
            preparedStmt.setString   (4, newCar.getDescription());
            preparedStmt.setDouble   (5, newCar.getPrice());
            preparedStmt.setInt    (6, newCar.getUserId());

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> companyNames = new ArrayList<String>();
        companyNames.add("Toyota");
        companyNames.add("Honda");
        companyNames.add("Dodge");
        companyNames.add("Mazda");
        request.setAttribute("companyNames", companyNames);
        getServletContext().getRequestDispatcher("/addCar.jsp").forward(request, response);
    }
}

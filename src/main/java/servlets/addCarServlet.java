package servlets;

import models.Car;
import models.User;
import services.DatabaseConnection;
import services.LoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "addCarServlet", urlPatterns = "/addCar")
public class addCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String company = request.getParameter("company");
        String name = request.getParameter("name");
        String colour = request.getParameter("colour");
        int year = Integer.parseInt(request.getParameter("year"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int userId = LoginUser.getLoginUser().getUserId();

        Car newCar = new Car(company, name, colour, year, description, price, userId);

        System.out.println("Company" + newCar.getCompany());
        System.out.println("name" + newCar.getName());
        System.out.println("Colour" + newCar.getColour());
        System.out.println("Year" + newCar.getYear());
        System.out.println("Desc" + newCar.getDescription());
        System.out.println("Price" + newCar.getPrice());


        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " insert into car (company, name, colour, year, description, price, userId)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, newCar.getCompany());
            preparedStmt.setString (2, newCar.getName());
            preparedStmt.setString (3, newCar.getColour());
            preparedStmt.setInt   (4, newCar.getYear());
            preparedStmt.setString   (5, newCar.getDescription());
            preparedStmt.setDouble   (6, newCar.getPrice());
            preparedStmt.setInt    (7, newCar.getUserId());

            // execute the preparedstatement
            //preparedStmt.RETURN_GENERATED_KEYS;
            preparedStmt.execute();

            //ResultSet generatedCarId = preparedStmt.getGeneratedKeys();

            //System.out.println("Generated car id: " + generatedCarId);

            conn.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> companyNames = new ArrayList<String>();
        companyNames.add("Toyota");
        companyNames.add("Honda");
        companyNames.add("Dodge");
        companyNames.add("Mazda");
        request.setAttribute("companyNames", companyNames);

        User loggedInUser = LoginUser.getLoginUser();
        if(loggedInUser != null){
            getServletContext().getRequestDispatcher("/addCar.jsp").forward(request, response);
        }
    }
}

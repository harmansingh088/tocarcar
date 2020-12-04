package servlets;

import models.Car;
import models.User;
import services.DatabaseConnection;
import services.LoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet(name = "addCarServlet", urlPatterns = "/addCar")
public class addCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String company = request.getParameter("company");
        String name = request.getParameter("name");
        String colour = request.getParameter("colour");
        int year = Integer.parseInt(request.getParameter("year"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int userId = LoginUser.getLoginUser().getUserId();

        Car newCar = new Car(company, name, colour, year, description, price, userId);

        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " insert into car (company, name, colour, year, description, price, userId)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, newCar.getCompany());
            preparedStmt.setString (2, newCar.getName());
            preparedStmt.setString (3, newCar.getColour());
            preparedStmt.setInt   (4, newCar.getYear());
            preparedStmt.setString   (5, newCar.getDescription());
            preparedStmt.setDouble   (6, newCar.getPrice());
            preparedStmt.setInt    (7, newCar.getUserId());

            // execute the preparedstatement
            //preparedStmt.RETURN_GENERATED_KEYS;
            int affectedRows = preparedStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int carId = generatedKeys.getInt(1);

                    InputStream inputStream = null;
                    List<Part> photos = request.getParts().stream().filter(part -> "photos".equals(part.getName())).collect(Collectors.toList());;

                    if (photos != null && photos.size() > 0) {
                        String queryPhotos = " insert into carPhoto (photo, carId)"+ " values (?, ?)";

                        PreparedStatement preparedStmtPhotos = conn.prepareStatement(queryPhotos, Statement.RETURN_GENERATED_KEYS);

                        for(int i = 0; i < photos.size(); i++){
                            Part filePart = photos.get(i);
                            inputStream = filePart.getInputStream();
                            if (inputStream != null) {
                                preparedStmtPhotos.setBlob(1, inputStream);
                            }
                            preparedStmtPhotos.setInt (2, carId);
                            preparedStmtPhotos.addBatch();
                        }

                        preparedStmtPhotos.executeBatch();
                    }

                    response.sendRedirect("/myCars");

                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            conn.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> companyNames = new ArrayList<String>();
        companyNames.add("Acura");
        companyNames.add("Audi");
        companyNames.add("BMW");
        companyNames.add("Chevrolet");
        companyNames.add("Chrysler");
        companyNames.add("Dodge");
        companyNames.add("Ford");
        companyNames.add("GMC");
        companyNames.add("Honda");
        companyNames.add("Jeep");
        companyNames.add("Lexus");
        companyNames.add("Mazda");
        companyNames.add("Mercedes");
        companyNames.add("Toyota");
        companyNames.add("Volvo");
        request.setAttribute("companyNames", companyNames);

        HttpSession session = request.getSession(false);
        User loggedInUser = LoginUser.getLoginUser();
        if(session == null || loggedInUser == null){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        else{
            getServletContext().getRequestDispatcher("/addCar.jsp").forward(request, response);
        }
    }
}

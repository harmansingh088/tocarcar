package servlets;

import models.Car;
import services.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "myCarsServlet", urlPatterns = "/myCars")
public class myCarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        String useridString = String.valueOf(session.getAttribute("loggedInUserId"));
        String userType = String.valueOf(session.getAttribute("loggedInUserType"));
        if(useridString == null || !userType.equalsIgnoreCase("Customer")){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        else{
            int loggedInUserId = Integer.valueOf(useridString);

            try{
                Connection conn = DatabaseConnection.getDatabaseConnection();

                String query = " select * from car where userId = ?";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt (1, loggedInUserId);

                ResultSet rs = preparedStmt.executeQuery();

                List<Car> userCars = new ArrayList<Car>();
                while (rs.next()) {
                    Car newCar = new Car(rs.getString("company"),
                            rs.getString("name"),
                            rs.getString("colour"),
                            rs.getInt("year"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("userId"));
                    newCar.setCarId(rs.getInt("carId"));
                    userCars.add(newCar);
                }

                request.setAttribute("userCars", userCars);

                rs.close();

                conn.close();

            }
            catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

            getServletContext().getRequestDispatcher("/myCars.jsp").forward(request, response);
        }

    }
}

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
import java.util.List;

@WebServlet(name = "myCarsServlet", urlPatterns = "/myCars")
public class myCarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser = LoginUser.getLoginUser();

        if(loggedInUser == null){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        else{
            int loggedInUserId = loggedInUser.getUserId();

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

package servlets;

import models.Car;
import models.CarPosting;
import models.CarPostingWrapper;
import models.User;
import services.DatabaseConnection;
import services.LoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "adminNewPostingsServlet", urlPatterns = "/adminNewPostings")
public class adminNewPostingsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedInUser = LoginUser.getLoginUser();
        if(session == null || loggedInUser == null || !loggedInUser.getUserType().equalsIgnoreCase("Admin")){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        else{
            int loggedInUserId = loggedInUser.getUserId();

            try{
                Connection conn = DatabaseConnection.getDatabaseConnection();

                String query = " select * " +
                        " from carPosting cp inner join car c " +
                        " on cp.carId = c.carId " +
                        " where cp.status = ? AND cp.postingDate >= ? " +
                        " order by cp.postingDate ";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, "Pending Approval");
                preparedStmt.setDate (2, new Date(System.currentTimeMillis()));

                ResultSet rs = preparedStmt.executeQuery();

                List<CarPostingWrapper> adminNewPostings = new ArrayList<CarPostingWrapper>();
                while (rs.next()) {
                    CarPosting carPosting = new CarPosting(
                            rs.getDate("cp.postingDate"),
                            rs.getString("cp.description"),
                            rs.getDouble("cp.price"),
                            rs.getInt("cp.carId"),
                            rs.getInt("cp.ownerId"),
                            rs.getString("cp.status")
                    );
                    carPosting.setCarId(rs.getInt("cp.carId"));

                    Car newCar = new Car(
                            rs.getString("c.company"),
                            rs.getString("c.name"),
                            rs.getString("c.colour"),
                            rs.getInt("c.year"),
                            rs.getString("c.description"),
                            rs.getDouble("c.price"),
                            rs.getInt("c.userId"));
                    newCar.setCarId(rs.getInt("c.carId"));


                    CarPostingWrapper carPostingWrapperObj = new CarPostingWrapper();
                    carPostingWrapperObj.setCarPosting(carPosting);
                    carPostingWrapperObj.setCar(newCar);
                    adminNewPostings.add(carPostingWrapperObj);
                }

                request.setAttribute("adminNewPostings", adminNewPostings);

                rs.close();

                conn.close();

            }
            catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

            getServletContext().getRequestDispatcher("/adminNewPostings.jsp").forward(request, response);
        }
    }
}

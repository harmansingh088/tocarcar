package servlets;

import models.Car;
import models.CarPosting;
import models.CarPostingWrapper;
import services.CarPostingWrapperProvider;
import services.DatabaseConnection;

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

@WebServlet(name = "myPostingsServlet", urlPatterns = "/myPostings")
public class myPostingsServlet extends HttpServlet {

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

                String query = " select * " +
                        " from carPosting cp inner join car c " +
                        " on cp.carId = c.carId " +
                        " inner join users u " +
                        " on c.userId = u.userId " +
                        " where cp.ownerId = ? AND cp.postingDate >= ? " +
                        " order by cp.postingDate ";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt (1, loggedInUserId);
                preparedStmt.setDate (2, new Date(System.currentTimeMillis()));

                ResultSet rs = preparedStmt.executeQuery();

                List<CarPostingWrapper> myPostings = new ArrayList<CarPostingWrapper>();
                while (rs.next()) {
                    CarPostingWrapper carPostingWrapperObj = CarPostingWrapperProvider.getCarPostingWrapperObj(rs);
                    myPostings.add(carPostingWrapperObj);
                }

                request.setAttribute("myPostings", myPostings);

                rs.close();

                conn.close();

            }
            catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

            getServletContext().getRequestDispatcher("/myPostings.jsp").forward(request, response);
        }
    }
}

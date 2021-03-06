package servlets;

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

@WebServlet(name = "viewPostingsServlet", urlPatterns = "/viewPostings")
public class viewPostingsServlet extends HttpServlet {
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
                        " where cp.status = ? AND cp.postingDate >= ? AND ownerId != ?" +
                        " order by cp.postingDate ";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, "Approved");
                preparedStmt.setDate (2, new Date(System.currentTimeMillis()));
                preparedStmt.setInt (3, loggedInUserId);


                ResultSet rs = preparedStmt.executeQuery();

                List<CarPostingWrapper> approvedPostings = new ArrayList<CarPostingWrapper>();
                while (rs.next()) {
                    approvedPostings.add(CarPostingWrapperProvider.getCarPostingWrapperObj(rs));
                }

                request.setAttribute("approvedPostings", approvedPostings);

                rs.close();

                conn.close();

            }
            catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

            getServletContext().getRequestDispatcher("/viewPostings.jsp").forward(request, response);
        }
    }
}

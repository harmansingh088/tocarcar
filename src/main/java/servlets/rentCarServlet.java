package servlets;

import models.Car;
import models.CarPosting;
import models.CarPostingWrapper;
import models.User;
import services.CarPhotosProvider;
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

@WebServlet(name = "rentCarServlet", urlPatterns = "/rentCar")
public class rentCarServlet extends HttpServlet {
    static int carPostingId;
    static CarPostingWrapper carPostingWrapperObj;
    static int loggedInUserId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("Cancel") != null){
            response.sendRedirect("/viewPostings");
        }
        else if(request.getParameter("Book") != null){
            Connection conn = DatabaseConnection.getDatabaseConnection();
            String query = " update carPosting " +
                    "set status = ?, renteeId = ? " +
                    "where carPostingId = ?";
            HttpSession session = request.getSession(false);
            try{
                String loggedInUserName = String.valueOf(session.getAttribute("loggedInUserName"));
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, "Booked by " + loggedInUserName);
                preparedStmt.setInt(2, loggedInUserId);
                preparedStmt.setInt (3, carPostingId);
                int count = preparedStmt.executeUpdate();
            }
            catch(Exception e){
                System.out.println(e.getLocalizedMessage());
            }
            response.sendRedirect("/myBookings");
        }
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
        try{
            carPostingId = Integer.valueOf(request.getParameter("carPostingId"));
            Connection conn = DatabaseConnection.getDatabaseConnection();

            loggedInUserId = Integer.valueOf(useridString);
            String query = " select * " +
                    " from carPosting cp inner join car c " +
                    " on cp.carId = c.carId " +
                    " inner join users u " +
                    " on c.userId = u.userId " +
                    " where cp.carPostingId = ?" +
                    " limit 1";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, carPostingId);

            ResultSet rs = preparedStmt.executeQuery();

            List<String> imageBase64StringList;
            if (rs.next()) {
                carPostingWrapperObj = CarPostingWrapperProvider.getCarPostingWrapperObj(rs);

                String queryPhotos = " select * from carPhoto where carId = ?";

                PreparedStatement preparedStmtPhotos = conn.prepareStatement(queryPhotos);
                preparedStmtPhotos.setInt(1, rs.getInt("cp.carId"));

                ResultSet rsPhotos = preparedStmtPhotos.executeQuery();
                imageBase64StringList = CarPhotosProvider.getCarPhotos(rsPhotos);

                carPostingWrapperObj.setCarPhotos(imageBase64StringList);

                request.setAttribute("carPostingWrapperObj", carPostingWrapperObj);
            }

            rs.close();

            conn.close();

            getServletContext().getRequestDispatcher("/rentCar.jsp").forward(request, response);
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}

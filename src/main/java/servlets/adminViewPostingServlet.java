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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "adminViewPostingServlet", urlPatterns = "/adminViewPosting")
public class adminViewPostingServlet extends HttpServlet {
    static int carPostingId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("Reject") != null){
            System.out.println("Rejected");
            setStatus("Rejected");
        }
        else if(request.getParameter("Approve") != null){
            System.out.println("Approved");
            setStatus("Approved");
        }
        response.sendRedirect("/adminNewPostings");
    }

    static void setStatus(String status){
        Connection conn = DatabaseConnection.getDatabaseConnection();
        String query = " update carPosting " +
                "set status = ? " +
                "where carPostingId = ?";

        try{
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, status);
            preparedStmt.setInt (2, carPostingId);
            preparedStmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        String useridString = String.valueOf(session.getAttribute("loggedInUserId"));
        String userType = String.valueOf(session.getAttribute("loggedInUserType"));
        if(useridString == null || !userType.equalsIgnoreCase("Admin")){
            getServletContext().getRequestDispatcher("/").forward(request, response);
        }
        try{
            carPostingId = Integer.valueOf(request.getParameter("carPostingId"));
            Connection conn = DatabaseConnection.getDatabaseConnection();

            String query = " select * " +
                    " from carPosting cp inner join car c " +
                    " on cp.carId = c.carId " +
                    " inner join user u " +
                    " on c.userId = u.userId " +
                    " where cp.carPostingId = ?" +
                    " limit 1";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, carPostingId);

            ResultSet rs = preparedStmt.executeQuery();

            List<String> imageBase64StringList;
            if (rs.next()) {
                CarPostingWrapper carPostingWrapperObj = CarPostingWrapperProvider.getCarPostingWrapperObj(rs);

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

            getServletContext().getRequestDispatcher("/adminViewPosting.jsp").forward(request, response);
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }
}

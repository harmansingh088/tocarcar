package servlets;

import models.CarPostingWrapper;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

@WebServlet(name = "viewMyBookingServlet", urlPatterns = "/viewMyBooking")
public class viewMyBookingServlet extends HttpServlet {
    static int carPostingId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("Back") != null){
            response.sendRedirect("/myBookings");
        }
        if(request.getParameter("Cancel") != null){
            Connection conn = DatabaseConnection.getDatabaseConnection();
            String query = " update carPosting " +
                    " set status = ? , renteeId = ? " +
                    " where carPostingId = ?";

            try{
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, "Approved");
                preparedStmt.setNull(2, Types.INTEGER);
                preparedStmt.setInt (3, carPostingId);
                preparedStmt.executeUpdate();
                response.sendRedirect("/myBookings?afterCancelBooking=true");
            }
            catch(Exception e){
                System.out.println(e.getLocalizedMessage());
            }
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
                CarPostingWrapper myBookingWrapperObj = CarPostingWrapperProvider.getCarPostingWrapperObj(rs);

                String queryPhotos = " select * from carPhoto where carId = ?";

                PreparedStatement preparedStmtPhotos = conn.prepareStatement(queryPhotos);
                preparedStmtPhotos.setInt(1, rs.getInt("cp.carId"));

                ResultSet rsPhotos = preparedStmtPhotos.executeQuery();
                imageBase64StringList = CarPhotosProvider.getCarPhotos(rsPhotos);

                myBookingWrapperObj.setCarPhotos(imageBase64StringList);

                request.setAttribute("carPostingWrapperObj", myBookingWrapperObj);
            }

            rs.close();

            conn.close();

            getServletContext().getRequestDispatcher("/viewMyBooking.jsp").forward(request, response);
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}

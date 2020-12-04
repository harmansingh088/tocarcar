package servlets;

import models.Car;
import models.CarPosting;
import models.User;
import services.DatabaseConnection;
import services.LoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "postAdServlet", urlPatterns = "/postAd")
public class postAdServlet extends HttpServlet {

    static Car carSelected;
    static  User loggedInUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Car Name "+ carSelected.getName());
        String postingDateString = request.getParameter("postingDate");
        Date postingDate = Date.valueOf(postingDateString);
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        CarPosting carPosting = new CarPosting(postingDate, description, price, carSelected.getCarId(), loggedInUser.getUserId(), "Pending Approval");
        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " insert into carPosting (postingDate, description, price, carId, ownerId, status)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setDate (1, carPosting.getPostingDate());
            preparedStmt.setString (2, carPosting.getDescription());
            preparedStmt.setDouble (3, carPosting.getPrice());
            preparedStmt.setInt   (4, carPosting.getCarId());
            preparedStmt.setInt    (5, carPosting.getOwnerId());
            preparedStmt.setString (6, carPosting.getStatus());

            int affectedRows = preparedStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating car posting failed, no rows affected.");
            }

            response.sendRedirect("/myPostings");

            conn.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = 0;

        try {
            loggedInUser = LoginUser.getLoginUser();
            carId = Integer.valueOf(request.getParameter("carId"));
            HttpSession session = request.getSession(false);
            if(session == null || loggedInUser == null){
                getServletContext().getRequestDispatcher("/").forward(request, response);
            }
            else if(carId == 0){
                getServletContext().getRequestDispatcher("/myCars").forward(request, response);
            }
            else {
                int loggedInUserId = loggedInUser.getUserId();

                Connection conn = DatabaseConnection.getDatabaseConnection();

                String query = " select * from car where userId = ? AND carId = ? LIMIT 1";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, loggedInUserId);
                preparedStmt.setInt(2, carId);

                ResultSet rs = preparedStmt.executeQuery();

                List<String> imageBase64StringList;
                if (rs.next()) {
                    carSelected = new Car(rs.getString("company"),
                            rs.getString("name"),
                            rs.getString("colour"),
                            rs.getInt("year"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("userId"));
                    carSelected.setCarId(rs.getInt("carId"));
                    request.setAttribute("carSelected", carSelected);

                    String queryPhotos = " select * from carPhoto where carId = ?";

                    PreparedStatement preparedStmtPhotos = conn.prepareStatement(queryPhotos);
                    preparedStmtPhotos.setInt(1, carId);

                    imageBase64StringList = new ArrayList<String>();
                    ResultSet rsPhotos = preparedStmtPhotos.executeQuery();
                    while(rsPhotos.next()){
                        Blob blob = rsPhotos.getBlob("photo");

                        InputStream inputStream = blob.getBinaryStream();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        byte[] imageBytes = outputStream.toByteArray();

                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                        imageBase64StringList.add(base64Image);

                        inputStream.close();
                        outputStream.close();
                    }
                    request.setAttribute("imageBase64StringList", imageBase64StringList);

                    getServletContext().getRequestDispatcher("/postAd.jsp").forward(request, response);

                }
                else{
                    getServletContext().getRequestDispatcher("/myCars").forward(request, response);
                }

                rs.close();

                conn.close();


            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

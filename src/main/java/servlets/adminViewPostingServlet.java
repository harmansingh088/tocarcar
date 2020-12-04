package servlets;

import models.Car;
import models.CarPosting;
import models.CarPostingWrapper;
import models.User;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        int carPostingId;
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
                CarPosting carPosting = new CarPosting(
                        rs.getDate("cp.postingDate"),
                        rs.getString("cp.description"),
                        rs.getDouble("cp.price"),
                        rs.getInt("cp.carId"),
                        rs.getInt("cp.ownerId"),
                        rs.getString("cp.status")
                );
                carPosting.setCarId(rs.getInt("cp.carId"));

                System.out.println("Car Posting Date: " + carPosting.getPostingDate());

                Car newCar = new Car(
                        rs.getString("c.company"),
                        rs.getString("c.name"),
                        rs.getString("c.colour"),
                        rs.getInt("c.year"),
                        rs.getString("c.description"),
                        rs.getDouble("c.price"),
                        rs.getInt("c.userId"));
                newCar.setCarId(rs.getInt("c.carId"));

                System.out.println("Car name: " + newCar.getName());

                User user = new User(
                        rs.getString("u.firstName"),
                        rs.getString("u.lastName"),
                        rs.getString("u.email"),
                        "",
                        rs.getString("u.userType"),
                        rs.getString("u.phoneNumber"),
                        rs.getInt("u.age")
                );

                System.out.println("User name: " + user.getFirstName());

                String queryPhotos = " select * from carPhoto where carId = ?";

                PreparedStatement preparedStmtPhotos = conn.prepareStatement(queryPhotos);
                preparedStmtPhotos.setInt(1, newCar.getCarId());

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

                    System.out.println("Photo: "+ base64Image);

                    inputStream.close();
                    outputStream.close();
                }

                CarPostingWrapper carPostingWrapperObj = new CarPostingWrapper();
                carPostingWrapperObj.setCarPosting(carPosting);
                carPostingWrapperObj.setCar(newCar);
                carPostingWrapperObj.setUser(user);
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

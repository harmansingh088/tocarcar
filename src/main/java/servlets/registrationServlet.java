package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import services.DatabaseConnection;
import services.LoginUser;

@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class registrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        String password = request.getParameter("password");
        String pswRepeat = request.getParameter("pswRepeat");

        String phoneNumber = request.getParameter("phoneNumber");

        int age = Integer.parseInt(request.getParameter("age"));

        User newUser = new User(firstName, lastName, email, password, "Customer", phoneNumber, age);

        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " insert into user (firstName, lastName, email, password, userType, phoneNumber, age)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString (1, newUser.getFirstName());
            preparedStmt.setString (2, newUser.getLastName());
            preparedStmt.setString   (3, newUser.getEmail());
            preparedStmt.setString   (4, newUser.getPassword());
            preparedStmt.setString   (5, newUser.getUserType());
            preparedStmt.setString   (6, newUser.getPhoneNumber());
            preparedStmt.setInt    (7, newUser.getAge());

            int affectedRows = preparedStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            else{
                try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        newUser.setUserId(userId);
                        LoginUser.setLoginUser(newUser);
                        response.sendRedirect("/myCars");
                    }
                }
                catch(Exception e){

                }
            }

            conn.close();
        }
        catch (Exception e){

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}

package servlets;

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

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " select * from user where email = ? and password = ? LIMIT 1";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString (2, password);


            // execute the preparedstatement
            ResultSet rs;
            rs = preparedStmt.executeQuery();

            while (rs.next()) {
                User loggedInUser = new User(rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phoneNumber"),
                        rs.getInt("age"));
                loggedInUser.setUserId(rs.getInt("userId"));
                LoginUser.setLoginUser(loggedInUser);
                getServletContext().getRequestDispatcher("/postAd").forward(request, response);
            }

            rs.close();

            conn.close();
        }
        catch (Exception e){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}

package servlets;

import models.User;
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

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User loggedInUser = null;

        try{
            Connection conn = DatabaseConnection.getDatabaseConnection();

            // the mysql insert statement
            String query = " select * from users where email = ? and password = ? LIMIT 1";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString (2, password);


            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {
                loggedInUser = new User(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        "",
                        rs.getString("userType"),
                        rs.getString("phoneNumber"),
                        rs.getInt("age"));
                loggedInUser.setUserId(rs.getInt("userId"));

                HttpSession sessionOld = request.getSession();
                sessionOld.invalidate();

                HttpSession sessionNew = request.getSession();
                sessionNew.setAttribute("loggedInUserId", loggedInUser.getUserId());
                sessionNew.setAttribute("loggedInUserType", loggedInUser.getUserType());
                sessionNew.setAttribute("loggedInUserName", loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
            }

            rs.close();

            conn.close();

        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

        if(loggedInUser != null){
            if(loggedInUser.getUserType().equalsIgnoreCase("Admin"))
                response.sendRedirect("/adminNewPostings");
            else{
                response.sendRedirect("/myCars");
            }
        }
        else{
            request.setAttribute("isLoginError", true);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}

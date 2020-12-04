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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "adminAllUsersServlet", urlPatterns = "/adminAllUsers")
public class adminAllUsersServlet extends HttpServlet {
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
        else{
            try{
                Connection conn = DatabaseConnection.getDatabaseConnection();

                String query = " select * from user where userType = ?";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, "Customer");

                ResultSet rs = preparedStmt.executeQuery();

                List<User> allCustomers = new ArrayList<User>();
                while (rs.next()) {
                    User customer = new User(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("userType"),
                            rs.getString("phoneNumber"),
                            rs.getInt("age"));
                    customer.setUserId(rs.getInt("userId"));
                    allCustomers.add(customer);
                }

                request.setAttribute("allCustomers", allCustomers);

                rs.close();

                conn.close();

            }
            catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

            getServletContext().getRequestDispatcher("/adminAllUsers.jsp").forward(request, response);
        }
    }
}


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;




import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author naray
 */

@WebServlet("/login")
public class login extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    private static final String DB_USER = "nghosh2";
    private static final String DB_PASSWORD = "ystoachi";
    private static final String LOGIN_QUERY = "SELECT passid FROM login WHERE username = ? AND passwd = ? ";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(LOGIN_QUERY);
            stmt.setString(1, user);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int pid = rs.getInt("passid");
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.println("Yes:" + pid);
            } else {
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.println("No");
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}


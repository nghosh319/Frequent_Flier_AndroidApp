
<%@page import="java.sql.*" %>
<%
  String spid = request.getParameter("spid");
  String dpid = request.getParameter("dpid");
  int npoints = Integer.parseInt(request.getParameter("npoints"));
  
  Connection conn = null;
  PreparedStatement stmt1 = null;
  PreparedStatement stmt2 = null;
  
  try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");
    
    // Deduct points from source passenger
    String sql1 = "UPDATE Point_Accounts SET total_points = total_points - ? WHERE passid = ?";
    stmt1 = conn.prepareStatement(sql1);
    stmt1.setInt(1, npoints);
    stmt1.setString(2, spid);
    stmt1.executeUpdate();
    
    // Add points to destination passenger
    String sql2 = "UPDATE Point_Accounts SET total_points = total_points + ? WHERE passid = ?";
    stmt2 = conn.prepareStatement(sql2);
    stmt2.setInt(1, npoints);
    stmt2.setString(2, dpid);
    stmt2.executeUpdate();
    
    out.println("<p>Points transfer successful.</p>");
    
  } catch (SQLException | ClassNotFoundException e) {
    e.printStackTrace();
  } finally {
    try {
      if (stmt1 != null) stmt1.close();
      if (stmt2 != null) stmt2.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
%>

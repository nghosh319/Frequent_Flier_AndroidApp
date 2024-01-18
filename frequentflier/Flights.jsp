<%@page import="java.sql.*" %>
<%
  String passid = request.getParameter("passid");
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
  
  try {
    // Load the Oracle JDBC driver
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    // Connect to the database
    conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");
    
    // Query the database for flight details
    String sql = "SELECT flight_id, flight_miles,destination FROM  passengers p, flights f WHERE f.passid = p.passid AND p.passid = ?";
    stmt = conn.prepareStatement(sql);
    stmt.setString(1, passid);
    rs = stmt.executeQuery();
    
    String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+"#";
            
            }
		conn.close();
        out.print(output);
    
  } catch (SQLException | ClassNotFoundException e) {
    e.printStackTrace();
  } finally {
    try {
      if (rs != null) rs.close();
      if (stmt != null) stmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
%>

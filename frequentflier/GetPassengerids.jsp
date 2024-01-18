
<%@page import="java.sql.*" %>
<%
  String currentPid = request.getParameter("passid");
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
  
  try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");
    String sql = "SELECT passid FROM passengers WHERE passid != ?";
    stmt = conn.prepareStatement(sql);
    stmt.setString(1, currentPid);
    rs = stmt.executeQuery();
	
	String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+"#";
            
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

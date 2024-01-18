
<%@page import="java.sql.*" %>
<%
  String pid = request.getParameter("passid");
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
  String name = "";
  int points = 0;
  
  try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");
    String sql = "select p.pname, pa.total_points from passengers p, point_accounts pa, login l where p.passid= pa.passid and p.passid = l.passid and l.passid= ? ";
    stmt = conn.prepareStatement(sql);
    stmt.setString(1, pid);
    rs = stmt.executeQuery();
    
    if (rs.next()) {
      name = rs.getString("pname");
      points = rs.getInt("total_points");
    }
    
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
  out.print(name + ":" + points);
%>


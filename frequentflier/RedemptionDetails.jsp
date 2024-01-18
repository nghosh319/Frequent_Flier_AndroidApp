<%@page import="java.sql.*" %>
<%
  String award_id = request.getParameter("AwardId");
  String passid = request.getParameter("passid");
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
  String awardDesc = "";
  int pointsNeeded = 0;
  String redemptionDate = "";
  String exchangeCenterName = "";
  
  try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");
    String sql = "select a_description, points_required, redemption_date, center_name from Awards a, Redemption_History r,ExchgCenters e where a.award_id = r.award_id  and  r.center_id= e.center_id and a.award_id = ? and passid = ?";
    stmt = conn.prepareStatement(sql);
    stmt.setString(1, award_id);
    stmt.setString(2, passid);
    rs = stmt.executeQuery();
    
    String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4);
            
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



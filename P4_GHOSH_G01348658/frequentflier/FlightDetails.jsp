
<%@page import="java.sql.*" %>
<%
  String flightId = request.getParameter("flightid");
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;
  String deptDateTime = "";
  String arrivalDateTime = "";
  int miles = 0;
  String tripIds = "";
  int tripMiles = 0;
  
  try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");
    String sql = "select f.DEPT_DATETIME,f.ARRIVAL_DATETIME,f.Flight_miles,t.trip_id,t.trip_miles  from flights f, trips t, flights_trips ft where f.flight_id = ft.flight_id and t.trip_id = ft.trip_id and f.flight_id = ?";
    stmt = conn.prepareStatement(sql);
    stmt.setString(1, flightId);
    rs = stmt.executeQuery();
	
	String output="";
      
        while(rs.next()){
            
            output+=rs.getObject(1)+"#"+rs.getObject(2)+"#"+rs.getObject(3)+"#"+rs.getObject(4)+"#"+rs.getObject(5);
            
            }
		conn.close();
        out.print(output);
    
    while (rs.next()) {
      deptDateTime = rs.getString("dept_datetime");
      arrivalDateTime = rs.getString("arrival_datetime");
      miles = rs.getInt("Flight_miles");
      tripIds += rs.getString("trip_id") + ",";
      tripMiles += rs.getInt("trip_miles");
    }
    
    // Remove the last comma from tripIds
    if (tripIds.length() > 0) {
      tripIds = tripIds.substring(0, tripIds.length() - 1);
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
%>

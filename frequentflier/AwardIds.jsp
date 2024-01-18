
<%@ page import="java.sql.*" %>

<%
		// Get passenger id from request parameter
		String passid = request.getParameter("passid");

		// Set up database connection and query
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", "nghosh2", "ystoachi");

			String sql = "SELECT DISTINCT award_id FROM Redemption_History WHERE passid = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, passid);
			rs = stmt.executeQuery();
           
		   String output="";
      
           while(rs.next()){
            
            output+= rs.getObject(1)+"#";
            
            }
		    conn.close();
            out.print(output);
			
		} catch (SQLException | ClassNotFoundException e) {
			out.println("<p>Error: " + e.getMessage() + "</p>");
		} finally {
			// Close resources
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				out.println("<p>Error closing resources: " + e.getMessage() + "</p>");
			}
		}
	%>


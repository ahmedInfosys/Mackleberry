

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Store_Assignment
 */
@WebServlet("/All_Assignment")
public class Get_grades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_grades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("text/html");
		
		String Store_table = "";
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		
		//creating connection to Oracle database using JDBC
		Connection conn;
		
		PreparedStatement preStatement;
		String sql_command = "select * from Gradebook";
		
		Store_table += "<div class=\"container-fluid\"> <table class=\"table table-striped table-bordered\">";
		Store_table += "<thread>";
		Store_table += "<tr>";
		Store_table += "<th> Assignment </th>";   
		Store_table += "<th> Grade </th>"; 
		Store_table += "</tr>";
		Store_table += "</thread>";
		Store_table +=  "<tbody>" ;
		
		
		
		try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(sql_command);
				ResultSet result = preStatement.executeQuery();
			    
			    while(result.next()){
					Store_table +=  "<tr>";
					Store_table +=  "<td>" + result.getString("Assignment") + "</td>";
					Store_table +=  "<td>" + result.getString("Grade") + "</td>";
					Store_table +=  "</tr> <br/>";
			    }
			    
			    
			    Store_table += "</tbody> </table> </div>";
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
		request.setAttribute("Assignment_table",Store_table);
		getServletContext().getRequestDispatcher("/All_Assignment.jsp").forward(request, response);
	

	  } 
	  
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

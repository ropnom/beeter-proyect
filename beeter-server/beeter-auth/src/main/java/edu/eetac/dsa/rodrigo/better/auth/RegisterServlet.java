package edu.eetac.dsa.rodrigo.better.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;

    
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		ds = DataSourceSPA.getInstance().getDataSource();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		
		if( action.equals("REGISTER"))
		{
			String username,pass,name,email;
            username = request.getParameter("nick");
            pass = request.getParameter("pass");
            name = request.getParameter("nombre");
            email = request.getParameter("correo");
            try {
                    Connection con = ds.getConnection();
                    Statement stmt = con.createStatement();
                    String update = "INSERT INTO users VALUES('"+username+"','"+MD5class.GetMD5(pass)+"','"+name+"','"+email+"');";
                    int row = stmt.executeUpdate(update);
                   
                    update = "INSERT INTO user_roles VALUES('"+username+"','registered');";
                    row = stmt.executeUpdate(update);
                    
            } catch (Exception e) {
            	e.printStackTrace();
            }
            String url = "/validar.jsp";
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(url);
            rd.forward(request, response);        
			
		} else
		{
			String url = "/Register.jsp";
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(url);
            rd.forward(request, response); 
		}
		
	}

}

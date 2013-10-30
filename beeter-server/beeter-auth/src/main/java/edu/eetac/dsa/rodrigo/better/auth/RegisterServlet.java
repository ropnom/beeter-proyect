package edu.eetac.dsa.rodrigo.better.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		// esto indica que si hay sesion me la usas pero sino no me las crees.
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("http://localhost:8000/error.html");
		}
		String action = request.getParameter("action");
		
		if( action.equals("REGISTER"))
		{
			String nick = request.getParameter("action");
			
		}
		
	}

}

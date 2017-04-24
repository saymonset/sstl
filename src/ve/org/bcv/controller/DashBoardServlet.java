/**
 * 
 */
package ve.org.bcv.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 28/06/2016 10:56:58
 * 2016
 * mail : oraclefedora@gmail.com
 */
@WebServlet(description = "go to the DashBoardServlet", urlPatterns = { "/dashBoardServlet" })
public class DashBoardServlet  extends HttpServlet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)  
			throws ServletException,IOException  
			{  
		doSame(req, res)  ;
			}
	public void doPost(HttpServletRequest req,HttpServletResponse res)  
			throws ServletException,IOException  
			{  
		doSame(req, res)  ;
			}
	
	public void doSame(HttpServletRequest request,HttpServletResponse response)  
			throws ServletException,IOException  
			{  
		
		
		
		if ("inicio".equals(request.getParameter("inicio.do"))) {
			String baseUrl=	"http://localhost:8080/sstl/services/ldapServicio";
			request.setAttribute("baseUrl",baseUrl);
		}
		
		String username = request
				.getParameter("username") != null
				? (String) request
						.getParameter("username") : "";
						
	String cedula = request
			.getParameter("cedula") != null
			? (String) request
					.getParameter("cedula") : "";					
		   RequestDispatcher despachador = null;
	    	   String actualizarPag = "./dashBoard.jsp";
	    	   
	    	   request.setAttribute("username", username);
	    	   request.setAttribute("cedula", cedula);
			despachador = request.getRequestDispatcher(actualizarPag);
			despachador.forward(request, response);
		 
			}
}

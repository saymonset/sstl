/**
 * 
 */
package ve.org.bcv.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 28/06/2016 15:33:47 2016 mail :
 *         oraclefedora@gmail.com
 */
@WebServlet(description = "go to the LoginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	private String aviso = "FinSesion";
	private RequestDispatcher despachador = null;
	private HttpSession sesion = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doSame(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doSame(req, res);
	}

	public void doSame(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(false) == null) {
			this.sesion = request.getSession(true);
			this.sesion.setAttribute("aviso", this.aviso);
		} else {
			String aviso=request.getParameter("aviso")!=null?(String)request.getParameter("aviso"):"";
			if ("reset".equals(request.getParameter("login.do"))) {
				HttpSession sesion = request.getSession(false);
				if (sesion != null) {
					sesion.invalidate();
				} else {
					sesion = request.getSession(true);
				}
			}else if ("FinSesion".equalsIgnoreCase(aviso)){
				request.setAttribute("aviso",aviso);
			}

			String baseUrl=	"http://localhost:8080/sstl/services/ldapServicio";
			String username = request.getParameter("username") != null
					? (String) request.getParameter("username")
					: "";
			String login = "./login.jsp";
			request.setAttribute("baseUrl",baseUrl);
			request.setAttribute("username", username);
			despachador = request.getRequestDispatcher(login);
			despachador.forward(request, response);

		}

	}
}

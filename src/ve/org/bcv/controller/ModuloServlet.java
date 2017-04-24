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

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 30/06/2016 09:02:37 2016 mail :
 *         oraclefedora@gmail.com
 */
@WebServlet(description = "go to the CategoriaServlet", urlPatterns = {"/moduloServlet"})
public class ModuloServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		String modulo = request.getParameter("modulo") != null
				? (String) request.getParameter("modulo")
				: "";

		String username = request.getParameter("username") != null
				? (String) request.getParameter("username")
				: "";

		String cedula = request.getParameter("cedula") != null
				? (String) request.getParameter("cedula")
				: "";

		RequestDispatcher despachador = null;
		String actualizarPag = "./modulo.jsp";
		request.setAttribute("modulo", modulo);
		 request.setAttribute("username", username);
  	   request.setAttribute("cedula", cedula);
		despachador = request.getRequestDispatcher(actualizarPag);
		despachador.forward(request, response);

	}

}

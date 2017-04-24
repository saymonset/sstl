/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.novell.ldap.LDAPException;

import ve.org.bcv.dto.TestBook;
import ve.org.bcv.dto.UserInfo;
import ve.org.bcv.services.Autenticar;
import ve.org.bcv.services.LdapServicio;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 23/06/2016 09:00:12
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/ldapServicioResource")
public class LdapServicioResource  extends HttpServlet implements Serializable, LdapServicio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grupo = "";
	private   String nombreUsuario = "";
	private   String cedulaUsuario = "";
    private   String companiaAnalista = "";	  
     
    private static Logger log = Logger.getLogger(LdapServicioResource.class);
	@Inject
	private Autenticar autenticar;
	@POST
	@Path("/{login}/{password}")
	@Produces("application/json")
	public UserInfo login(@PathParam("login") String login,
			@PathParam("password") String password) {
		UserInfo userInfo =new UserInfo();
		userInfo.setLogin(login);
		userInfo.setPassword(password);
		try {
			String[] resultadoConsulta=	autenticar.autenticar(login, password);
			
			
			 if (resultadoConsulta == null) {
				 userInfo =new UserInfo();
	          }
	          else
	          {
	            
	         
	            grupo = resultadoConsulta[3];
	            
	            log.debug("Valor de la variable grupo: " + grupo);
	            if (grupo.compareToIgnoreCase("SG") == 0)
	            {
	              log.info("USUARIO NO AUTORIZADO PARA USAR LA APLICACIÓN");
	       
	           
	            }
	            else
	            {
	              nombreUsuario = resultadoConsulta[0];
	              cedulaUsuario = resultadoConsulta[2];
	              /**Siempre vamos a trabjar con esta compania 01**/
	              companiaAnalista ="01";
	                
	              boolean isAdmin=false;
	              if("GC_User_RHEI_ADMIN".equalsIgnoreCase(grupo)){
	           	   isAdmin=true;
	              }

	              userInfo.setCedula(cedulaUsuario);
	              userInfo.setUsername(nombreUsuario);
	              userInfo.setCompaniaAnalista(companiaAnalista);
	              userInfo.setAdmin(isAdmin);
	              userInfo.setGrupo(grupo);
	              userInfo.setLogged(true);
	            }
	          }
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String save(@PathParam("id") String id) {
		 System.out.println("-------------------update ="+id);
			return id;
	}
	
	
	@POST
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String create(@PathParam("id") String id) {
		 System.out.println("-------------------update ="+id);
			return id;
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public String DELETE(@PathParam("id") String id) throws URISyntaxException {
		 
	 System.out.println("-------------------eliminando="+id );
		return id;
	}

	
	
	@GET
	@Path("/products")
	@Produces("application/json")
	public List<TestBook> testBook() {
		List<TestBook> list= new ArrayList<TestBook>();
		TestBook testBook =new TestBook();
		testBook.setCategory("Categoria gimnasio");
		testBook.setName("Nombre gimnasio");
		testBook.setPrice(1200);
		list.add(testBook);
		testBook =new TestBook();
		testBook.setCategory("Categoria Teatro");
		testBook.setName("Nombre Teatro");
		testBook.setPrice(1500);
		list.add(testBook);
		testBook =new TestBook();
		testBook.setCategory("Categoria Cine");
		testBook.setName("Nombre Cine");
		testBook.setPrice(2870);
		list.add(testBook);
		
		return list;
	}
	
	
}

/**
 * 
 */
package ve.org.bcv.autenticador.ldap.connection;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import ve.org.bcv.configuration.PropertiesUrl;
import ve.org.bcv.services.Autenticar;

import com.novell.ldap.LDAPException;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 03/06/2016 09:15:39 2016 mail :
 *         oraclefedora@gmail.com
 */
public class AutenticarImpl implements Autenticar {
	   private static String ATRIBUTOS_USUARIO_LDAP = "bcv.ldap.usuario.atributos";
	   private static Logger log = Logger.getLogger(AutenticarImpl.class);
	   private String grupo = "";
	   private   String nombreUsuario = "";
	   private   String cedulaUsuario = "";
	   private   String companiaAnalista = "";	  
	public String[] autenticar(String user, String Passwd) throws UnsupportedEncodingException, LDAPException, Exception {
	 
		
		  String[] resultadoConsulta = retornarConsulta(user, Passwd);
          
		
		return resultadoConsulta;
	}
	
	
	private String[] retornarConsulta(String usuario, String clave)
		     throws UnsupportedEncodingException, LDAPException, Exception
		   {
		  
		     String[] resultado = new String[5];
		     LDAPUtilities lDAPUtilities = new LDAPUtilities();
		     LDAPDelegador delegator = new LDAPDelegador(lDAPUtilities.validarUsuarioExt(usuario));
		     
		     String[] attrUsuario = cargarAtributos();
		     if (delegator.autenticarUsuario(usuario, clave))
		     {
		    	 
 
		    	 
		    	 
		    	 
		       Collection busquedaUsuario = delegator.obtenerAtributos(usuario, attrUsuario);
		       if (busquedaUsuario == null)
		       {
		         resultado = (String[])null;
		       }
		       else
		       {
		         resultado = buscarValorDeAtributoUsuario(busquedaUsuario, resultado);
		        
		       }
		     }
		     else
		     {
		       resultado = (String[])null;
		     }
		     return resultado;
		   }
	
	 private String[] cargarAtributos()
		     throws Exception
		   {
		     
		     String[] atributos = (String[])null;
		     
		     Properties prop = new Properties();
		     PropertiesUrl pUrl= new PropertiesUrl();
		     
		     prop.load(pUrl.getClass().getResourceAsStream("config.properties"));
		     
		     String arg = (String)prop.get(ATRIBUTOS_USUARIO_LDAP);
		     
		     
		     atributos = arg.split(",");
		     
		     
		     return atributos;
		   }
	 
	 private String[] buscarValorDeAtributoUsuario(Collection busquedaUsuario, String[] resultado)
	   {
	     boolean bandera = false;
	     boolean bandera2 = false;
	     boolean bandera3 = false;
	     boolean bandera4 = false;
	     boolean bandera5=false;
	     Iterator it = busquedaUsuario.iterator();
	     while (it.hasNext())
	     {
	       AtributoDTO atrib = new AtributoDTO();
	       atrib = (AtributoDTO)it.next();

	       if (atrib.getNombre().equals("fullName"))
	       {
	         if (((ArrayList)atrib.getValor()).size() == 0) {
	           resultado[0] = "";
	         } else {
	           resultado[0] = ((String)((ArrayList)atrib.getValor()).get(0));
	         }
	       }
	       else if (atrib.getNombre().equals("mail"))
	       {
	         if (((ArrayList)atrib.getValor()).size() == 0) {
	           resultado[1] = "";
	         } else {
	           resultado[1] = ((String)((ArrayList)atrib.getValor()).get(0));
	         }
	       }
	       else if (atrib.getNombre().equals("cn"))
	       {
	         if (((ArrayList)atrib.getValor()).size() == 0)
	         {
	           resultado[2] = "";
	         }
	         else
	         {
	           Collection valor1 = atrib.getValor();
	           Iterator itValor1 = valor1.iterator();
	           while (itValor1.hasNext())
	           {
	             String cnLdap = (String)itValor1.next();
	             
	             if (StringUtils.isNumeric(cnLdap)) {
	               resultado[2] = cnLdap;
	             }
	           }
	         }
	       }
	       else if (atrib.getNombre().equals("groupMembership"))
	       {
	         if (((ArrayList)atrib.getValor()).size() == 0) {
	           resultado[3] = "SG";
	         } else {
	           try
	           {
	             Pattern patronBuscarGrupo = Pattern.compile("(.*)(cn=)([^,]*)(.*)");
	             Collection valor = atrib.getValor();
	             Iterator itValor = valor.iterator();
	             while (itValor.hasNext())
	             {
	               String grupoLdap = (String)itValor.next();
	               Matcher match = patronBuscarGrupo.matcher(grupoLdap);
	               if (match.matches())
	               {
	                 String grupo = match.group(3);
	                 if (grupo.equals("GC_User_RHEI_ADMIN"))
	                 {
	                  
	                   bandera = true;
	                 }
	                 else if (!bandera)
	                 {
	                   if (grupo.equals("GC_User_RHEI_Analista_SOL"))
	                   {
	                  
	                     bandera2 = true;
	                     
	                   }
	                   else if (grupo.equals("GC_User_RHEI_AnalistaPago"))
	                   {
	              
	                     bandera3 = true;
	                     
	                   }
	                   else if (grupo.equals("GC_User_RHEI_Monitoreo"))
	                   {
	                   
	                     bandera4 = true;
	                 
	                   }else if (grupo.equals("G_SAPI_EMPLEADOS")) {
									 
									bandera5 = true;
								}else if (grupo.equals("G_SAPI_NOMINA_EJECUTIVA")) {
								 
									bandera5 = true;
								}else if (grupo.equals("G_SAPI_OBREROS")) {
								 
									bandera5 = true;
								}else if (grupo.equals("G_SAPI_OBREROS_CONTRATADOS")) {
								 
									bandera5 = true;
								}else if (grupo.equals("G_SAPI_CONTRATADOS_BCV")) {
								 
									bandera5 = true;
								}else if (grupo.equals("G_SAPI_JUBILADOS")) {
									 
									bandera5 = true;
								}
	                   
	                   
	                   
	                 }
	               }
	             }
	           
	             if (bandera) {
	               resultado[3] = "GC_User_RHEI_ADMIN";
	             } else if ((bandera2) && (bandera3)) {
	               resultado[3] = "GC_SOLICITUD";
	              
	             } else if ((bandera2) && (!bandera3)) {
	               resultado[3] = "GC_SOLICITUD";
	             } else if ((!bandera2) && (bandera3)) {
	               resultado[3] = "GC_SOLICITUD";
	             } else if ((!bandera) && (!bandera2) && 
	               (!bandera3) && (bandera4)) {
	               resultado[3] = "GC_SOLICITUD";
	             }else if(bandera5){
	            	  resultado[3] = "GC_SOLICITUD";
	             }
	             if (resultado[3] != null) {
	               continue;
	             }
	             resultado[3] = "SG";
	           }
	           catch (Exception e)
	           {
	             resultado[3] = "SG";
	           }
	         }
	       }
	       else if (atrib.getNombre().equals("DN")) {
	         if (((ArrayList)atrib.getValor()).size() == 0) {
	           resultado[4] = "";
	         } else {
	           resultado[4] = ((String)((ArrayList)atrib.getValor()).get(0));
	         }
	       }
	     }
	     
	     return resultado;
	   }

}

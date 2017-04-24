/**
 * 
 */
package ve.org.bcv.services;

import java.io.UnsupportedEncodingException;

import com.novell.ldap.LDAPException;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 03/06/2016 09:15:07
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface Autenticar {
	String[] autenticar(String user,String Passwd) throws UnsupportedEncodingException, LDAPException, Exception ;
}

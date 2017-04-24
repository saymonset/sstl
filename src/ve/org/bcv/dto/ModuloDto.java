/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 13/06/2016 14:29:21 2016 mail :
 *         oraclefedora@gmail.com
 */
public class ModuloDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nbModulo;
	 
 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNbModulo() {
		return nbModulo;
	}


	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}
	 

}

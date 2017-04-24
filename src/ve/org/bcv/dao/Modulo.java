/**
 * 
 */
package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 13/06/2016 15:01:52 2016 mail :
 *         oraclefedora@gmail.com
 */
@Entity
@Table(name = "MODULO")
public class Modulo implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "nb_modulo", length = 200, nullable = false)
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

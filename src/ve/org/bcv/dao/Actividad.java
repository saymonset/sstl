/**
 * 
 */
package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 13/06/2016 15:27:17
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Entity
@Table(name = "ACTIVIDAD")
public class Actividad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ActividadPK id;
 
 

	public ActividadPK getId() {
		return id;
	}

	public void setId(ActividadPK id) {
		this.id = id;
	}

	 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

 
}

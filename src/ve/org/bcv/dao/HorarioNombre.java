package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   31 de ene. de 2017 2:48:45 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
@Entity
@Table(name = "HORARIO_NOMBRE")
public class HorarioNombre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private HorarioNombrePk id;

	public HorarioNombrePk getId() {
		return id;
	}

	public void setId(HorarioNombrePk id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

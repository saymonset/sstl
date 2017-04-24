package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.EmbeddedId;

//@Entity
//@Table(name = "ASISTENCIA")
public class Asistencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AsistenciaPK id;

	public AsistenciaPK getId() {
		return id;
	}

	public void setId(AsistenciaPK id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

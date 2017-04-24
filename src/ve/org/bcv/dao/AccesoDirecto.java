package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 *10 de mar. de 2017    8:57:34 a. m.
 * mail : oraclefedora@gmail.com
 */

@Entity
@Table(name = "ACCESO_DIRECTO")
public class AccesoDirecto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private AccesoDirectoPK id;
	
	@Column(name="personalizar_horario")
	private boolean personalizarHorario;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="nb_accesoDirecto")
	private String nbAccesoDirecto;

	 

	public boolean isPersonalizarHorario() {
		return personalizarHorario;
	}

	public void setPersonalizarHorario(boolean personalizarHorario) {
		this.personalizarHorario = personalizarHorario;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AccesoDirectoPK getId() {
		return id;
	}

	public void setId(AccesoDirectoPK id) {
		this.id = id;
	}

	public String getNbAccesoDirecto() {
		return nbAccesoDirecto;
	}

	public void setNbAccesoDirecto(String nbAccesoDirecto) {
		this.nbAccesoDirecto = nbAccesoDirecto;
	}

	 

}

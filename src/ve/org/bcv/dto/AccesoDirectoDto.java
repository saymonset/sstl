package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 *10 de mar. de 2017    9:44:50 a. m.
 * mail : oraclefedora@gmail.com
 */


public class AccesoDirectoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nbModulo;

	private String nbGrupo;

	private String nbSubGrupo;

	private String nbHorario;

	private String nbActividad;

	private String nbAccesoDirecto;

	private boolean personalizarHorario;

	private boolean status;
	
	 

	public String getNbModulo() {
		return nbModulo;
	}

	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}

	public String getNbGrupo() {
		return nbGrupo;
	}

	public void setNbGrupo(String nbGrupo) {
		this.nbGrupo = nbGrupo;
	}

	public String getNbSubGrupo() {
		return nbSubGrupo;
	}

	public void setNbSubGrupo(String nbSubGrupo) {
		this.nbSubGrupo = nbSubGrupo;
	}

	public String getNbHorario() {
		return nbHorario;
	}

	public void setNbHorario(String nbHorario) {
		this.nbHorario = nbHorario;
	}

	public String getNbActividad() {
		return nbActividad;
	}

	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
	}

	public String getNbAccesoDirecto() {
		return nbAccesoDirecto;
	}

	public void setNbAccesoDirecto(String nbAccesoDirecto) {
		this.nbAccesoDirecto = nbAccesoDirecto;
	}

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

	 
}

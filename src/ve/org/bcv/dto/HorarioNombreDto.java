package ve.org.bcv.dto;

import java.io.Serializable;

public class HorarioNombreDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nbModulo;
	private String nbGrupo;

	private String nbSubGrupo;
	
	private String nbActividad;

	private String nbHorario;
	private boolean isDelete;
	private boolean modificable;
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

	public String getNbActividad() {
		return nbActividad;
	}

	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
	}

	public String getNbHorario() {
		return nbHorario;
	}

	public void setNbHorario(String nbHorario) {
		this.nbHorario = nbHorario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}
}

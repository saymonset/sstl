package ve.org.bcv.dto;

import java.io.Serializable;

public class SubGrupoModuloDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nbModulo;
	private String nbGrupoModulo;
	private String nbSubGrupoModulo;
	private String nbModificar;
	private boolean isDelete;
	private boolean modificable;
	public String getNbModulo() {
		return nbModulo;
	}
	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}
	public String getNbGrupoModulo() {
		return nbGrupoModulo;
	}
	public void setNbGrupoModulo(String nbGrupoModulo) {
		this.nbGrupoModulo = nbGrupoModulo;
	}
	public String getNbSubGrupoModulo() {
		return nbSubGrupoModulo;
	}
	public void setNbSubGrupoModulo(String nbSubGrupoModulo) {
		this.nbSubGrupoModulo = nbSubGrupoModulo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNbModificar() {
		return nbModificar;
	}
	public void setNbModificar(String nbModificar) {
		this.nbModificar = nbModificar;
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

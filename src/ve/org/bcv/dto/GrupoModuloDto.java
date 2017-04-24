package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   24 de ene. de 2017 2:16:54 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
public class GrupoModuloDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nbModulo;
	private String nbGrupoModulo;
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
	public void setModificable(boolean isModificable) {
		this.modificable = isModificable;
	}


}

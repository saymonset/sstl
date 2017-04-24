/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 07/06/2016 09:35:55
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ActividadDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	 
	
	private String nbModulo;
	private String nbActividad;
	private boolean isDelete;
	private boolean modificable;
	private String nbGrupo;
	private String nbSubGrupo;
 
	 
	 
 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
 
	/**
	 * 
	 */
	public ActividadDto() {
		super();
		// TODO Auto-generated constructor stub
	}

 
 


	public String getNbModulo() {
		return nbModulo;
	}


	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}


	public String getNbActividad() {
		return nbActividad;
	}


	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
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
 

 
}

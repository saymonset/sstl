/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;


/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 15/06/2016 14:53:43
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ActividadPKDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nbModulo;
	private String nbActividad;
	 
	public String getNbActividad() {
		return nbActividad;
	}
	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @param nbModulo
	 * @param nbActividad
	 */
	public ActividadPKDto(String nbModulo, String nbActividad) {
		super();
		this.nbModulo = nbModulo;
		this.nbActividad = nbActividad;
	}
	/**
	 * 
	 */
	public ActividadPKDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNbModulo() {
		return nbModulo;
	}
	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}
}

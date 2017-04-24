/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 07/06/2016 09:36:59
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class AtencionDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String start;
	private String end;
	private String id;
	private String nbModulo;
	private String nbActividad;
	private String cedula;
	private String text;
	private String atencionRealizada;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAtencionRealizada() {
		return atencionRealizada;
	}
	public void setAtencionRealizada(String atencionRealizada) {
		this.atencionRealizada = atencionRealizada;
	}
	 

}

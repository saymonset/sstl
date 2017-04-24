package ve.org.bcv.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class AsistenciaDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nbModulo;
	
	private String nbGrupo;

	private String nbSubGrupo;
	
	private String nbActividad;

	private String nbHorario;

	private String fecha;
	private String dia;
    private String start;
	private String end;
	
	
	private String cedula;
	private java.util.Date feRegistro;
	public String getNbModulo() {
		return nbModulo;
	}
	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public java.util.Date getFeRegistro() {
		return feRegistro;
	}
	public void setFeRegistro(java.util.Date feRegistro) {
		this.feRegistro = feRegistro;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AsistenciaDto(String nbModulo, String cedula, Date feRegistro) {
		super();
		this.nbModulo = nbModulo;
		this.cedula = cedula;
		this.feRegistro = feRegistro;
	}
	public AsistenciaDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
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
}

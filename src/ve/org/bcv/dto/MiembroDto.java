/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 09:45:53
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class MiembroDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String tipoEmp;
	
	
	private String nbModulo;
	private String nbActividad;
	private String nbGrupo;
	private String nbSubGrupo;
	private String nbHorario;
	private String cedula;
	
	
	
	
	private Date feRegistro;
	private Date feRenovacion;
	private boolean isProfesor;
	private String fechaStr;
	private boolean isRenovable;
	private boolean isRenovableVencido;
	private List<HorarioDto> horarioDtos; 
    private List<AsistenciaDto> asistenciaDtos;
    private String cantDiasAsistPorMes;
    private String porcentajeAceptacion;
    private String porcentajeCompletado;
    private String metaResultado;
    private int diasTranscurridos;
    private boolean resultado;
//    Corresponde a la cantidad de días a asistir por mes.
//    Porcentaje de Aceptación

	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoEmp() {
		return tipoEmp;
	}
	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public Date getFeRegistro() {
		return feRegistro;
	}
	public void setFeRegistro(Date feRegistro) {
		this.feRegistro = feRegistro;
	}
	public Date getFeRenovacion() {
		return feRenovacion;
	}
	public void setFeRenovacion(Date feRenovacion) {
		this.feRenovacion = feRenovacion;
	}
	public boolean isProfesor() {
		return isProfesor;
	}
	public void setProfesor(boolean isProfesor) {
		this.isProfesor = isProfesor;
	}
	public String getFechaStr() {
		return fechaStr;
	}
	public void setFechaStr(String fechaStr) {
		this.fechaStr = fechaStr;
	}
	public boolean isRenovable() {
		return isRenovable;
	}
	public void setRenovable(boolean isRenovable) {
		this.isRenovable = isRenovable;
	}
	public List<HorarioDto> getHorarioDtos() {
		return horarioDtos;
	}
	public void setHorarioDtos(List<HorarioDto> horarioDtos) {
		this.horarioDtos = horarioDtos;
	}
	public boolean isRenovableVencido() {
		return isRenovableVencido;
	}
	public void setRenovableVencido(boolean isRenovableVencido) {
		this.isRenovableVencido = isRenovableVencido;
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
	public List<AsistenciaDto> getAsistenciaDtos() {
		return asistenciaDtos;
	}
	public void setAsistenciaDtos(List<AsistenciaDto> asistenciaDtos) {
		this.asistenciaDtos = asistenciaDtos;
	}
	public String getCantDiasAsistPorMes() {
		return cantDiasAsistPorMes;
	}
	public void setCantDiasAsistPorMes(String cantDiasAsistPorMes) {
		this.cantDiasAsistPorMes = cantDiasAsistPorMes;
	}
	public String getPorcentajeAceptacion() {
		return porcentajeAceptacion;
	}
	public void setPorcentajeAceptacion(String porcentajeAceptacion) {
		this.porcentajeAceptacion = porcentajeAceptacion;
	}
	public String getPorcentajeCompletado() {
		return porcentajeCompletado;
	}
	public void setPorcentajeCompletado(String porcentajeCompletado) {
		this.porcentajeCompletado = porcentajeCompletado;
	}
	public String getMetaResultado() {
		return metaResultado;
	}
	public void setMetaResultado(String metaResultado) {
		this.metaResultado = metaResultado;
	}
	public int getDiasTranscurridos() {
		return diasTranscurridos;
	}
	public void setDiasTranscurridos(int diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

}

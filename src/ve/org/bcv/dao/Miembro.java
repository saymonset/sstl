/**
 * 
 */
package ve.org.bcv.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 10:14:30
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Entity
@Table(name = "MIEMBRO")
@EntityListeners(ve.org.bcv.dao.listeners.MiembroListener.class)
public class Miembro  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private MiembroPK id;
	@Transient
	private String nombre;
	@Transient
	private String tipoEmp;
	
	@Column(name = "FE_REGISTRO")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date feRegistro;
	@Column(name = "FE_RENOVACION")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date feRenovacion;
	@Column(name = "IN_PROFESOR")
	private boolean isProfesor;
	@Column(name = "IN_RENOVABLE")
	private boolean isRenovable;
	
	
 

	 
 
 
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
	
	public Miembro() {
	}
	/**
	 * @param cedula
	 * @param nombre
	 * @param tipoEmp
	 * @param nbModulo
	 * @param nbActividad
	 */
	public Miembro(String cedula, String nombre, String tipoEmp,
			String nbModulo, String nbActividad) {
		super();
		this.id.setCedula(cedula);
		this.id.setNbModulo(nbModulo);
		this.id.setNbActividad(nbActividad);
		this.nombre = nombre;
		this.tipoEmp = tipoEmp;
		 
	}
	public MiembroPK getId() {
		return id;
	}
	public void setId(MiembroPK id) {
		this.id = id;
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
	public boolean isRenovable() {
		return isRenovable;
	}
	public void setRenovable(boolean isRenovable) {
		this.isRenovable = isRenovable;
	}
 
	 
}

/**
 * 
 */
package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 09:47:13
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Embeddable
public class MiembroHorarioPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="nb_modulo")
	private String nbModulo;
	
	@Column(name="nb_grupo")
	private String nbGrupo;

	@Column(name="nb_subgrupo")
	private String nbSubGrupo;
	
	@Column(name="nb_horario")
	private String nbHorario;

	@Column(name="nb_actividad")
	private String nbActividad;
	
	
	
	
	@Column(name="cedula")
	private String cedula;
	
	@Column(name = "co_horario", length = 200, nullable = false)
	private String id;
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MiembroHorarioPK)) {
			return false;
		}
		MiembroHorarioPK castOther = (MiembroHorarioPK)other;
		return 
			this.nbModulo == (castOther.nbModulo)
			&&		this.nbGrupo == (castOther.nbGrupo)
					&&		this.nbSubGrupo == (castOther.nbSubGrupo)
							&&		this.nbHorario == (castOther.nbHorario)
			&& (this.nbActividad == castOther.nbActividad) && (this.cedula == castOther.cedula) && (this.id == castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nbModulo.hashCode();
		hash = hash * prime + this.nbGrupo.hashCode();
		hash = hash * prime + this.nbSubGrupo.hashCode();
		hash = hash * prime + this.nbHorario.hashCode();
		hash = hash * prime + this.nbActividad.hashCode();
		hash = hash * prime + this.cedula.hashCode();
		hash = hash * prime + ((int) (this.nbActividad.hashCode() ^ (this.nbActividad.hashCode() >>> 32)));
		
		return hash;
	}


	/**
	 * @param nbAplicacion
	 * @param nbActividad
	 */
	public MiembroHorarioPK(String nbModulo,String nbGrupo,String nbSubGrupo,String nbHorario, String nbActividad, String cedula,String id) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo=nbGrupo;
		this.nbSubGrupo=nbSubGrupo;
		this.nbHorario=nbHorario;
		this.nbActividad = nbActividad;
		this.cedula=cedula;
		this.id=id;
	}

	
	public MiembroHorarioPK() {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}

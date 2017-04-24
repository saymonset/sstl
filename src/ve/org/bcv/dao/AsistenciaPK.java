package ve.org.bcv.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author  Ing Simon Alberto Rodriguez Pacheco
 * 14 de nov. de 2016 8:24:19 a.�m.
 * mail: oraclefedora@gmail.com
 */
@Embeddable
public class AsistenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="nb_modulo")
	private String nbModulo;
	
	@Column(name="nb_grupo")
	private String nbGrupo;

	@Column(name="nb_subgrupo")
	private String nbSubGrupo;
	
	@Column(name="nb_actividad")
	private String nbActividad;
	
	
	@Column(name="cedula")
	private String cedula;
	
	@Column(name = "FE_REGISTRO",nullable=false)
	@Temporal(TemporalType.DATE)
	private java.util.Date feRegistro;
	
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsistenciaPK)) {
			return false;
		}
		AsistenciaPK castOther = (AsistenciaPK)other;
		return 
			this.nbModulo == (castOther.nbModulo) && this.nbGrupo == (castOther.nbGrupo) &&
					this.nbSubGrupo == (castOther.nbSubGrupo) &&
							this.nbActividad == (castOther.nbActividad) &&(this.cedula == castOther.cedula)
				&& (this.feRegistro == castOther.feRegistro);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.feRegistro.hashCode();
		hash = hash * prime + this.cedula.hashCode();
		hash = hash * prime + this.nbGrupo.hashCode();
		hash = hash * prime + this.nbSubGrupo.hashCode();
		hash = hash * prime + this.nbActividad.hashCode();
		hash = hash * prime+((int) (this.nbModulo.hashCode() ^ (this.nbModulo.hashCode() >>> 32))) ;
		
		return hash;
	}
	
	

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

	public AsistenciaPK(String nbModulo,String nbGrupo,String nbSubGrupo,String nbActividad, String cedula, Date feRegistro) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo =nbGrupo;
		this.nbSubGrupo =nbSubGrupo;
		this.nbActividad =nbActividad;
		this.cedula = cedula;
		this.feRegistro = feRegistro;
	}

	public AsistenciaPK() {
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
	

}

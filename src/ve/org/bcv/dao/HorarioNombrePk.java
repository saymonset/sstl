package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   31 de ene. de 2017 2:42:59 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
@Embeddable
public class HorarioNombrePk  implements Serializable {
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

	@Column(name="nb_horario")
	private String nbHorario;
	
	
	public HorarioNombrePk() {
	}
 

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HorarioNombrePk)) {
			return false;
		}
		HorarioNombrePk castOther = (HorarioNombrePk)other;
		return 
			this.nbModulo == (castOther.nbModulo)
			&& this.nbGrupo == (castOther.nbGrupo)
			&& this.nbSubGrupo == (castOther.nbSubGrupo)
			&& (this.nbActividad == castOther.nbActividad)
			&& (this.nbHorario == castOther.nbHorario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nbModulo.hashCode();
		hash = hash * prime + this.nbGrupo.hashCode();
		hash = hash * prime + this.nbSubGrupo.hashCode();
		hash = hash * prime + this.nbHorario.hashCode();
		hash = hash * prime + ((int) (this.nbActividad.hashCode() ^ (this.nbActividad.hashCode() >>> 32)));
		
		return hash;
	}


	
	public HorarioNombrePk(String nbModulo, String nbActividad,String nbGrupo, String nbSubGrupo,String nbHorario) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo = nbGrupo;
		this.nbSubGrupo = nbSubGrupo;
		this.nbActividad = nbActividad;
		this.nbHorario=nbHorario;
	}
	 

	public String getNbActividad() {
		return nbActividad;
	}


	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
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
/**
 * 
 */
package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/08/2016 09:03:02
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Embeddable
public class ParametroPk implements Serializable {

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
	
	@Column(name="nb_actividad")
	private String nbActividad;

	@Column(name="nb_horario")
	private String nbHorario;
 
	@Column(name = "TX_NOMBRE_PARAMETRO", length = 200, nullable = false)
	private String txNombreParametro;
	
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MiembroPK)) {
			return false;
		}
		ParametroPk castOther = (ParametroPk)other;
		return 
			this.nbModulo == (castOther.nbModulo)
					&& (this.nbGrupo == castOther.nbGrupo)&& (this.nbSubGrupo == castOther.nbSubGrupo)&& (this.nbActividad == castOther.nbActividad)
					&& (this.nbHorario == castOther.nbHorario) && (this.txNombreParametro == castOther.txNombreParametro);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nbModulo.hashCode();
		hash = hash * prime + this.nbGrupo.hashCode();
		hash = hash * prime + this.nbSubGrupo.hashCode();
		hash = hash * prime + this.nbActividad.hashCode();
		hash = hash * prime + this.nbHorario.hashCode();
		hash = hash * prime + ((int) (this.nbActividad.hashCode() ^ (this.nbActividad.hashCode() >>> 32)));
		
		return hash;
	}


	/**
	 * @param nbAplicacion
	 * @param nbActividad
	 */
	//ParametroPk(  nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,txNombreParametro);
	public ParametroPk(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario, String txNombreParametro) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo=nbGrupoModulo;
		this.nbSubGrupo=nbSubGrupoModulo;
		this.nbActividad = nbActividad;
		this.nbHorario = nbHorario;
		this.txNombreParametro=txNombreParametro;
	}

	
	public ParametroPk() {
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
	 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTxNombreParametro() {
		return txNombreParametro;
	}

	public void setTxNombreParametro(String txNombreParametro) {
		this.txNombreParametro = txNombreParametro;
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

package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GrupoModuloPK  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="nb_modulo")
	private String nbModulo;

	@Column(name="nb_grupo")
	private String nbGrupo;

	
	public GrupoModuloPK() {
	}
 

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupoModuloPK)) {
			return false;
		}
		GrupoModuloPK castOther = (GrupoModuloPK)other;
		return 
			this.nbModulo == (castOther.nbModulo)
			&& (this.nbGrupo == castOther.nbGrupo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nbGrupo.hashCode();
		hash = hash * prime + ((int) (this.nbGrupo.hashCode() ^ (this.nbGrupo.hashCode() >>> 32)));
		
		return hash;
	}


	/**
	 * @param nbAplicacion
	 * @param nbGrupo
	 */
	public GrupoModuloPK(String nbModulo, String nbGrupo) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo = nbGrupo;
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

 
}
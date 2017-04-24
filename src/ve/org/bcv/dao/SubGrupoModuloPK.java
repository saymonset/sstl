package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;

public class SubGrupoModuloPK  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="nb_modulo")
	private String nbModulo;

	@Column(name="nb_grupo")
	private String nbGrupo;

	@Column(name="nb_subgrupo")
	private String nbSubGrupo;
	
	public SubGrupoModuloPK() {
	}
 

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubGrupoModuloPK)) {
			return false;
		}
		SubGrupoModuloPK castOther = (SubGrupoModuloPK)other;
		return 
			this.nbModulo == (castOther.nbModulo)
			&& (this.nbGrupo == castOther.nbGrupo)&& (this.nbSubGrupo == castOther.nbSubGrupo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nbGrupo.hashCode();
		hash = hash * prime + ((int) (this.nbGrupo.hashCode() ^ (this.nbGrupo.hashCode() >>> 32)));
		hash = hash * prime + ((int) (this.nbSubGrupo.hashCode() ^ (this.nbSubGrupo.hashCode() >>> 32)));
		
		return hash;
	}


	/**
	 * @param nbAplicacion
	 * @param nbGrupo
	 */
	public SubGrupoModuloPK(String nbModulo, String nbGrupo, String nbSubGrupo) {
		super();
		this.nbModulo = nbModulo;
		this.nbGrupo = nbGrupo;
		this.nbSubGrupo=nbSubGrupo;
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

 
}
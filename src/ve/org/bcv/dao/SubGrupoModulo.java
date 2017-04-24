package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_GRUPO")
public class SubGrupoModulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	private SubGrupoModuloPK id;
	@Column(name="nb_Modificar",nullable=false)
	private String nbModificar;
	;


	public SubGrupoModuloPK getId() {
		return id;
	}


	public void setId(SubGrupoModuloPK id) {
		this.id = id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNbModificar() {
		return nbModificar;
	}


	public void setNbModificar(String nbModificar) {
		this.nbModificar = nbModificar;
	}


 

	 
}

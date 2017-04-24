package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   24 de ene. de 2017 10:49:20 a. m.
 *
 * mail: oraclefedora@gmail.com
 */
@Entity
@Table(name = "GRUPO")
public class GrupoModulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private GrupoModuloPK id;
	@Column(name="nb_Modificar",nullable=false)
	private String nbModificar;
 
 
 
	 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	public GrupoModuloPK getId() {
		return id;
	}





	public void setId(GrupoModuloPK id) {
		this.id = id;
	}





	public String getNbModificar() {
		return nbModificar;
	}





	public void setNbModificar(String nb_Modificar) {
		this.nbModificar = nb_Modificar;
	}



 

 
}

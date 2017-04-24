/**
 * 
 */
package ve.org.bcv.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 26/07/2016 08:57:36
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Entity
@Table(name = "ATENCION")
public class Atencion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private AtencionPK id;
	
	 
	@Column(name="tx_comentario")
	private String text;
	
	@Column(name="tx_atencion_realizada")
	private String atencionRealizada;

	


	public AtencionPK getId() {
		return id;
	}


	public void setId(AtencionPK id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getAtencionRealizada() {
		return atencionRealizada;
	}


	public void setAtencionRealizada(String atencionRealizada) {
		this.atencionRealizada = atencionRealizada;
	}

}

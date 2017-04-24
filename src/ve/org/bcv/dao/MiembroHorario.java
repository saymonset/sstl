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
 * 04/08/2016 09:17:39
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Entity
@Table(name = "MIEMBRO_HORARIO")
public class MiembroHorario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MiembroHorarioPK pk;
	
	
	@Column(name="tx_comentario")
	private String text;
	@Column(name="tx_inicio")
	private String start;
	@Column(name="tx_fin")
	private String end;
	 
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MiembroHorarioPK getPk() {
		return pk;
	}
	public void setPk(MiembroHorarioPK pk) {
		this.pk = pk;
	}
	
	 

}

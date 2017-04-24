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
 * 28/07/2016 15:18:17
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Entity
@Table(name = "PARAMETRO")
public class Parametro  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ParametroPk id;
	 
	
	@Column(name = "TX_OBSERVACIONES", length = 200, nullable = false)
	private String txObservaciones;
	
	@Column(name = "TX_VALOR_PARAMETRO", length = 200, nullable = false)
	private String txValorParametro;
	
	@Column(name = "IN_TIPO_PARAMETRO", length = 2, nullable = false)
	private String tipoParametro="C";
	
	@Column(name="personalizar_horario")
	private boolean personalizarHorario;


	
	 
	public String getTxObservaciones() {
		return txObservaciones;
	}
	public void setTxObservaciones(String txObservaciones) {
		this.txObservaciones = txObservaciones;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ParametroPk getId() {
		return id;
	}
	public void setId(ParametroPk id) {
		this.id = id;
	}
	public String getTipoParametro() {
		return tipoParametro;
	}
	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}
	public String getTxValorParametro() {
		return txValorParametro;
	}
	public void setTxValorParametro(String txValorParametro) {
		this.txValorParametro = txValorParametro;
	}
	public boolean isPersonalizarHorario() {
		return personalizarHorario;
	}
	public void setPersonalizarHorario(boolean personalizarHorario) {
		this.personalizarHorario = personalizarHorario;
	}
}

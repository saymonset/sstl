package ve.org.bcv.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  Ing Simon Alberto Rodriguez Pacheco
 * 15 de nov. de 2016 10:34:13 a. m.
 * mail: oraclefedora@gmail.com
 */
public class EstadisticasByModuloDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date feDesde;
	private Date feHasta;
	private String modulo;
	private boolean isSwFeDesde;
	private boolean isSwFeHasta;
	public Date getFeDesde() {
		return feDesde;
	}
	public void setFeDesde(Date feDesde) {
		this.feDesde = feDesde;
	}
	public Date getFeHasta() {
		return feHasta;
	}
	public void setFeHasta(Date feHasta) {
		this.feHasta = feHasta;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isSwFeDesde() {
		return isSwFeDesde;
	}
	public void setSwFeDesde(boolean isSwFeDesde) {
		this.isSwFeDesde = isSwFeDesde;
	}
	public boolean isSwFeHasta() {
		return isSwFeHasta;
	}
	public void setSwFeHasta(boolean isSWFeHasta) {
		this.isSwFeHasta = isSWFeHasta;
	}
	 
}

/**
 * 
 */
package ve.org.bcv.dto;


/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/08/2016 09:11:58
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ParametroDto {
	private String nbModulo;
	private String nbActividad;
	private String txValorParametro;
	private String txObservaciones;
	private String  tipoParametro;
	private String txNombreParametro;
	private boolean personalizarHorario;
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
	public String getTxValorParametro() {
		return txValorParametro;
	}
	public void setTxValorParametro(String txValorParametro) {
		this.txValorParametro = txValorParametro;
	}
	public String getTxObservaciones() {
		return txObservaciones;
	}
	public void setTxObservaciones(String txObservaciones) {
		this.txObservaciones = txObservaciones;
	}
	public String getTipoParametro() {
		return tipoParametro;
	}
	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}
	public String getTxNombreParametro() {
		return txNombreParametro;
	}
	public void setTxNombreParametro(String txNombreParametro) {
		this.txNombreParametro = txNombreParametro;
	}
	public boolean isPersonalizarHorario() {
		return personalizarHorario;
	}
	public void setPersonalizarHorario(boolean personalizarHorario) {
		this.personalizarHorario = personalizarHorario;
	}
}

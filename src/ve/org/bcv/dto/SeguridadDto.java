/**
 * 
 */
package ve.org.bcv.dto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 15:20:03
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class SeguridadDto {
	
	private boolean configuracionShow;
	private boolean horarioShow;
	private boolean inscripcionShow;
	private boolean miembroShow;
	private boolean atencionShow;
	private boolean estadisticaShow;
	public boolean isConfiguracionShow() {
		return configuracionShow;
	}
	public void setConfiguracionShow(boolean configuracionShow) {
		this.configuracionShow = configuracionShow;
	}
	public boolean isHorarioShow() {
		return horarioShow;
	}
	public void setHorarioShow(boolean horarioShow) {
		this.horarioShow = horarioShow;
	}
	public boolean isInscripcionShow() {
		return inscripcionShow;
	}
	public void setInscripcionShow(boolean inscripcionShow) {
		this.inscripcionShow = inscripcionShow;
	}
	public boolean isMiembroShow() {
		return miembroShow;
	}
	public void setMiembroShow(boolean miembroShow) {
		this.miembroShow = miembroShow;
	}
	public boolean isAtencionShow() {
		return atencionShow;
	}
	public void setAtencionShow(boolean atencionShow) {
		this.atencionShow = atencionShow;
	}
	public boolean isEstadisticaShow() {
		return estadisticaShow;
	}
	public void setEstadisticaShow(boolean estadisticaShow) {
		this.estadisticaShow = estadisticaShow;
	}
	

}

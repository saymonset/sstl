/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 10:02:40
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class MiembroHorarioDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String text;
	private String start;
	private String end;
	private String nbModulo;
	private String nbActividad;
	private String cedula;
	private String id;
	private boolean  moveDisabled;
	private boolean  resizeDisabled;
	private boolean  clickDisabled;
	private String backColor=  "#a8fb53";
	private boolean isDiaPrevious;
	private boolean isDiaNext;
	private boolean isDiaBusy;
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
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isMoveDisabled() {
		return moveDisabled;
	}
	public void setMoveDisabled(boolean moveDisabled) {
		this.moveDisabled = moveDisabled;
	}
	public boolean isResizeDisabled() {
		return resizeDisabled;
	}
	public void setResizeDisabled(boolean resizeDisabled) {
		this.resizeDisabled = resizeDisabled;
	}
	public boolean isClickDisabled() {
		return clickDisabled;
	}
	public void setClickDisabled(boolean clickDisabled) {
		this.clickDisabled = clickDisabled;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public boolean isDiaPrevious() {
		return isDiaPrevious;
	}
	public void setDiaPrevious(boolean isDiaPrevious) {
		this.isDiaPrevious = isDiaPrevious;
	}
	public boolean isDiaNext() {
		return isDiaNext;
	}
	public void setDiaNext(boolean isDiaNext) {
		this.isDiaNext = isDiaNext;
	}
	public boolean isDiaBusy() {
		return isDiaBusy;
	}
	public void setDiaBusy(boolean isDiaBusy) {
		this.isDiaBusy = isDiaBusy;
	}
	
}

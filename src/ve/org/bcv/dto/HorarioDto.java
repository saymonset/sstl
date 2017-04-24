/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 07/06/2016 09:21:54
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class HorarioDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String start;
	private String end;
	private String id;
	private String nbModulo;
	private String nbActividad;
	private String text;
	private boolean  moveDisabled;
	private boolean  resizeDisabled;
	private boolean  clickDisabled;
	private String backColor=  "#a8fb53";
	private String bubbleHtml="Not Available";
	
	
	
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
	 
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 
	public String getNbActividad() {
		return nbActividad;
	}
	public void setNbActividad(String nbActividad) {
		this.nbActividad = nbActividad;
	}
	public String getNbModulo() {
		return nbModulo;
	}
	public void setNbModulo(String nbModulo) {
		this.nbModulo = nbModulo;
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
	public String getBubbleHtml() {
		return bubbleHtml;
	}
	public void setBubbleHtml(String bubbleHtml) {
		this.bubbleHtml = bubbleHtml;
	}
	 
	 

}

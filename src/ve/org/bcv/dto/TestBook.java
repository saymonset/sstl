/**
 * 
 */
package ve.org.bcv.dto;

import java.io.Serializable;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 07/07/2016 14:49:41
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class TestBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String category;
	private double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

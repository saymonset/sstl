/**
 * 
 */
package ve.org.bcv.json;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 03/06/2016 09:41:05
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class Address {
    
    private String street;
    private String city;
    private int zipcode;
     
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
     
    @Override
    public String toString(){
        return getStreet() + ", "+getCity()+", "+getZipcode();
    }
}

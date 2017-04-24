/**
 * 
 */
package ve.org.bcv.json;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 03/06/2016 09:57:48
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class EmployeeGsonExample {
	 
    public static void main(String[] args) throws IOException {
        Employee emp = createEmployee();
 
        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
 
        // read JSON file data as String
        
        URL url = Resources.getResource("ve/org/bcv/json/employee.txt");
        String fileData = Resources.toString(url, Charsets.UTF_8);
//        String fileData = new String(Files.readAllBytes(Paths
//                .get("employee.txt")));
// 
        // parse json string to object
        Employee emp1 = gson.fromJson(fileData, Employee.class);
 
        // print object data
        System.out.println("\n\nEmployee Object\n\n" + emp1);
 
        // create JSON String from Object
        String jsonEmp = gson.toJson(emp);
        System.out.print(jsonEmp);
 
    }
 
    public static Employee createEmployee() {
 
        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[] { 123456, 987654 });
        emp.setRole("Manager");
 
        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);
 
        List<String> cities = new ArrayList<String>();
        cities.add("Los Angeles");
        cities.add("New York");
        emp.setCities(cities);
 
        Map<String, String> props = new HashMap<String, String>();
        props.put("salary", "1000 Rs");
        props.put("age", "28 years");
        emp.setProperties(props);
 
        return emp;
    }
}
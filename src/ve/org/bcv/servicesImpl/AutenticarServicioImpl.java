/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.io.IOException;
import java.net.URL;

import ve.org.bcv.json.Employee;
import ve.org.bcv.json.EmployeeGsonExample;
import ve.org.bcv.services.AutenticarService;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 03/06/2016 09:15:39 2016 mail :
 *         oraclefedora@gmail.com
 */
public class AutenticarServicioImpl implements AutenticarService {

	public String getAutenticarJson(String user, String Passwd) {
		Employee emp = EmployeeGsonExample.createEmployee();

		// Get Gson object
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// read JSON file data as String
		Employee emp1 = null;
		URL url = Resources.getResource("ve/org/bcv/json/employee.txt");
		String fileData;
		try {
			fileData = Resources.toString(url, Charsets.UTF_8);
			// parse json string to object
			emp1 = gson.fromJson(fileData, Employee.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String fileData = new String(Files.readAllBytes(Paths
		// .get("employee.txt")));
		//

		// print object data
		System.out.println("\n\nEmployee Object\n\n" + emp1);

		// create JSON String from Object
		String jsonEmp = gson.toJson(emp);
		System.out.print(jsonEmp);
		return jsonEmp;
	}

}

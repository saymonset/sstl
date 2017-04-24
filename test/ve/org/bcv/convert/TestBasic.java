/**
 * 
 */
package ve.org.bcv.convert;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import pojo.from.Person;
import pojo.to.OtherPerson;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 16/06/2016 15:44:32 2016 mail :
 *         oraclefedora@gmail.com
 */
public class TestBasic {
	public static void main(String[] args) {

		Person person = new Person();
		person.setAge(15);
		person.setName("rene");

		OtherPerson othePerson = new OtherPerson();

		System.out.println("*** Before BeanUtils.copyProperties ***");

		System.out.println("Person");
		System.out.println(person.getAge());
		System.out.println(person.getName());

		System.out.println("othePerson");
		System.out.println(othePerson.getAge());
		System.out.println(othePerson.getName());

		// copy properties from (target, source)
		try {
			BeanUtils.copyProperties(othePerson, person);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n*** After BeanUtils.copyProperties ***");

		System.out.println("Person");
		System.out.println(person.getAge());
		System.out.println(person.getName());

		System.out.println("othePerson");
		System.out.println(othePerson.getAge());
		System.out.println(othePerson.getName());
	}
}

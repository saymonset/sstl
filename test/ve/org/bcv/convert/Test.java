/**
 * 
 */
package ve.org.bcv.convert;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import ve.org.bcv.dao.ActividadPK;
import ve.org.bcv.dto.ActividadPKDto;
/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 15/06/2016 14:55:15
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class Test {
	
	public static void main(String[] args) {

		ActividadPK fromBean = new ActividadPK("fromBean", "fromBeanAProp");
		ActividadPKDto toBean = new ActividadPKDto();
		
		System.out.println("fromBean copy");
		System.out.println(fromBean.getNbActividad());

		System.out.println("othePerson to");
		System.out.println(toBean.getNbActividad());
		
		try {
			System.out.println("Copying properties from fromBean to toBean");
			BeanUtils.copyProperties(toBean, fromBean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("fromBean copy 2");
		System.out.println(fromBean.getNbActividad());

		System.out.println("othePerson to 2");
		System.out.println(toBean.getNbActividad());
	}

}
 
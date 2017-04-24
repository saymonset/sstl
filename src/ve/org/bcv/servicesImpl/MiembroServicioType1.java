/**
 * 
 */
package ve.org.bcv.servicesImpl;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 10:09:56
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, TYPE, METHOD})
public @interface MiembroServicioType1 {

}

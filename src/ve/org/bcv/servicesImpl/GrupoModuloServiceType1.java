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
   24 de ene. de 2017 2:29:05 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, TYPE, METHOD})
public @interface GrupoModuloServiceType1 {

}

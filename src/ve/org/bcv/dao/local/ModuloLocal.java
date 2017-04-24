/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.Modulo;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 14/06/2016 15:04:28
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface ModuloLocal extends EntityRepository<Modulo, Long>, Serializable {
	List<Modulo> findAll() ;
}

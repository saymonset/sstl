package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.AccesoDirecto;
import ve.org.bcv.dao.AccesoDirectoPK;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 *10 de mar. de 2017    9:05:20 a. m.
 * mail : oraclefedora@gmail.com
 */


public interface AccesoDirectoLocal extends EntityRepository<AccesoDirecto, Long>, Serializable {
	 AccesoDirecto find(AccesoDirectoPK id) ;
	 List<AccesoDirecto> findAll(String cedula) ;
}

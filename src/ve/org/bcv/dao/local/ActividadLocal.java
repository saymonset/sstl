/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.Actividad;
import ve.org.bcv.dao.ActividadPK;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 14/06/2016 15:08:21
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface ActividadLocal extends EntityRepository<Actividad, Long>, Serializable {
	List<Actividad> findByModulo(String nbModulo);
	 Actividad find(ActividadPK id) ;
	 List<Actividad> findAll(ActividadPK id) ;
	 Actividad findSameModificar(ActividadPK id) ;
	 List<Actividad> findByModulo(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo);
}

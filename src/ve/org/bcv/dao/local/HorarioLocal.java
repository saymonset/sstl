/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dao.Horario;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 14/06/2016 15:01:11 2016 mail :
 *         oraclefedora@gmail.com
 */
@Local
public interface HorarioLocal extends EntityRepository<Horario, Long>, Serializable {
	List<Horario> findByModuloActividad(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario);

	Horario find(String id);

	Horario find(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario, String id);

	List<Horario> atencionByModulo(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario);

	List<Horario> findByModuloActividad(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad) ; 

}

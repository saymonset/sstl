/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dao.MiembroHorario;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 09:54:33
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Local
public interface MiembroHorarioLocal  extends EntityRepository<MiembroHorario, Long>, Serializable {
	MiembroHorario save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula,String id,String start,String end,String text)throws Exception;
	MiembroHorario findByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula)throws Exception;
	List<MiembroHorario> findHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula) throws Exception ;
	List<MiembroHorario> findByModuloActividad(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario) throws Exception ;
	List<MiembroHorario> byModuloMiembros(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad, Date feDesde, Date feHasta) throws Exception;
	List<MiembroHorario> findNbHorario(String nbHorario) throws Exception ;

}

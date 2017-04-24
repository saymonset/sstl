/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dao.Atencion;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 26/07/2016 11:42:45
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Local
public interface AtencionLocal  extends EntityRepository<Atencion, Long>, Serializable {
	 List<Atencion> findByModuloActivdadStadistica(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario);
	 List<Atencion> findByModuloActivdadByUsuarioStadistica(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario,String cedula);
	 List<Atencion> atencionByCedula(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario, String cedula);
	 List<Atencion> atencionByText(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario, String text, String id,String feDesdeStr,String feHastaStr); 
	 List<Atencion> byModuloAtenciones(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String horario, String cedula, Date feDesde,Date feHasta) throws Exception;
	 List<Atencion> byModuloCedulaAtenciones(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,String nbHorario, String cedula, Date feDesde, Date feHasta) throws Exception;
	 List<Atencion> asistencia(String nbModulo, String nbGrupo, String nbSubGrupo,String nbActividad,String nbHorario, String cedula, Date feDesde, Date feHasta) throws Exception;
	 List<Atencion> findNbHorario(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,String nbHorario );
	 
}

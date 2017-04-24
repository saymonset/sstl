/**
 * 
 */
package ve.org.bcv.services;

import java.util.Date;
import java.util.List;

import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.HorarioDto;
import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 09:56:38
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface MiembroService {
	  List<MiembroDto> findMiembrosByActMod(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario);
	  
	  MiembroDto findMiembroByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula);
	  
	  List<MiembroDto> findMiembrosByActMod(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad);
	  ChartDto findByEstadisticasByModulo(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,  Date feDesdeStr, Date feHastaStr);
	  ChartDto estadisticasByModuloActividad(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad, String nbHorario,Date feDesdeStr,Date feHastaStr);
	  List<MiembroDto> moduloActividadDesdeHasta(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad, String nbHorario,Date feDesdeStr, Date feHastaStr);
	  MiembroDto update(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula,String nombre, String tipoEmp, boolean renovable, boolean profesor,Date fechaRenovacion);
	  MiembroDto saveInscripcion(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula,String nombre, String tipoEmp,boolean renovable,boolean profesor,String fechaStr,List<HorarioDto> horarioDtos)throws Exception;
	  MiembroDto delete(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula)throws Exception;
	  MiembroDto save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula)throws Exception;
	  String fechaRenovacion(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario);
	  MiembroDto findUserFromPersonalTdoEmpleados(String ceula);
	  MiembroChartDto estadisticasGetMiembroChart(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad, String nbHorario, Date feDesdeStr, Date feHastaStr);

	 
}

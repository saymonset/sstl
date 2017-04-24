/**
 * 
 */
package ve.org.bcv.services;

import java.util.Date;
import java.util.List;

import ve.org.bcv.dto.AtencionDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.MiembroChartDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 26/07/2016 11:30:54
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface AtencionService {
    AtencionDto save(String text,String id,String start,String end,String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula,String atencionRealizada)throws Exception;
    ChartDto findByModuloActivdadStadistica(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario);
    ChartDto findByModuloActivdadStadistica(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula);
    List<AtencionDto> atencionByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario, String cedula);
    MiembroChartDto findByEstadisticasByModulo(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String feDesdeStr,String feHastaStr) ;
    List<AtencionDto> byModuloAtenciones(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String horario,String cedula, Date feDesde, Date feHasta) throws Exception;
    List<AtencionDto> deleteAtencionByCedula(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula) ;
}

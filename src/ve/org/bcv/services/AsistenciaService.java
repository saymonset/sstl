package ve.org.bcv.services;

import java.util.Date;
import java.util.List;

import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;

public interface AsistenciaService {
	 //ChartDto findAsistenciaByModulo(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,  Date feDesdeStr, Date feHastaStr) ;
	MiembroChartDto buscarByModuloCedula(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,String nbHorario,String cedula,  Date feDesdeStr, Date feHastaStr) ;
	 List<MiembroDto> findMiembrosAsistenciaByModulo(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,String nbHorario,String cedula, Date feDesdeStr, Date feHastaStr) ;
}

package ve.org.bcv.services;

import java.util.List;

import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.HorarioNombreDto;

public interface HorarioNombreService {
	List<HorarioNombreDto> findByActividadesByModulo(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo,String actividad);
	HorarioNombreDto delete(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad, String nbHorario)throws Exception;
	HorarioNombreDto save(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,String actividad, String nbHorario)throws Exception;
	HorarioNombreDto update(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String nbHorarioModif) throws Exception;
	Boolean noExiste(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,String actividad,String nbHorario)
			throws Exception;
	 DeleteYesDto isDelete(String nbModulo, String nbGrupo, String nbSubGrupoModulo, String actividad, String nbHorario) ;
}

/**
 * 
 */
package ve.org.bcv.services;

import java.util.Date;
import java.util.List;

import ve.org.bcv.dto.MiembroHorarioDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 10:05:44
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface MiembroHorarioService {
	MiembroHorarioDto save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula,String id,String start,String end,String text)throws Exception;
	 MiembroHorarioDto findByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula) throws Exception;
	 List<MiembroHorarioDto> findHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula) throws Exception;
	 List<MiembroHorarioDto> findSinColorHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula) throws Exception;
	 List<MiembroHorarioDto> deleteHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula) throws Exception;
	 List<MiembroHorarioDto> byModuloMiembros(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad, Date feDesde, Date feHasta) throws Exception;
}

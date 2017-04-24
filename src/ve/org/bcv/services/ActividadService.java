/**
 * 
 */
package ve.org.bcv.services;

import java.util.List;

import ve.org.bcv.dto.ActividadDto;
import ve.org.bcv.dto.DeleteYesDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 20/07/2016 10:58:10
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface ActividadService {
	ActividadDto save(String nbModulo,String nbActividad)throws Exception;
	  List<ActividadDto> findByActividadesByModulo(String nbModulo);
	  ActividadDto update(  String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,String actividad,String nbActividadModif) throws Exception ;
		ActividadDto delete(String nbModulo,String nbActividad)throws Exception;
		Boolean noExiste(String actividad, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo) throws Exception;
		List<ActividadDto> findByActividadesByModulo(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo);
		ActividadDto save( String actividad, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo) throws Exception ;
		 ActividadDto delete(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad) throws Exception ;
		 DeleteYesDto isDelete(String nbModulo, String nbGrupo, String nbSubGrupoModulo, String actividad);
}

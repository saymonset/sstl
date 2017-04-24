/**
 * 
 */
package ve.org.bcv.services;

import java.util.List;

import ve.org.bcv.dto.HorarioDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 07/06/2016 09:29:08
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface HorarioService {
  
      HorarioDto save(String id,String start,String end,String nbModulo,String nbActividad,String text)throws Exception;
      List<HorarioDto> findByModuloActividad(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario);
      //List<HorarioDto> findByModuloActividadMoveDisabled(String nbModulo, String nbActividad);
      HorarioDto save(String nbModulo,String nbGrupo,String nbSubGrupo,String nbActividad,String nbHorario,String id,String text,
    		  String start,String end)throws Exception;
      HorarioDto delete(String nbModulo,String nbGrupo,String nbSubGrupo,String nbActividad,String nbHorario,String id,String text)throws Exception;
     // List<HorarioDto> findByModuloActividad(String nbModulo, String nbGrupo,String nbSubGrupo,String nbActividad) ;

      
}

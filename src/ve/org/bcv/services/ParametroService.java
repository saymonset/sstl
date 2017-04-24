/**
 * 
 */
package ve.org.bcv.services;

import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dto.ParametroDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/08/2016 09:14:26
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Local
public interface ParametroService {
	ParametroDto save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro,String txValorParametro,String txObservaciones,String tipoParametro,	boolean personalizarHorario );
	
	ParametroDto update(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro,String txValorParametro,String txObservaciones,String tipoParametro,boolean personalizarHorario);
	
	List<ParametroDto> findAll(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario);
	
	ParametroDto find(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro);
	
	 

}

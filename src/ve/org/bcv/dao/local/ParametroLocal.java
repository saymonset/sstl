/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.Parametro;
import ve.org.bcv.dao.ParametroPk;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/08/2016 09:09:39
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface ParametroLocal  extends EntityRepository<Parametro, Long>, Serializable {
	
	Parametro save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro,String txValorParametro,String txObservaciones,String tipoParametro,boolean personalizarHorario);
	
	Parametro update(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro,String txValorParametro,String txObservaciones,String tipoParametro,boolean personalizarHorario);
	
	List<Parametro> findAll(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario);
	
	Parametro find(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txValorParametro);
	
	 List<Parametro> find(ParametroPk id);
}

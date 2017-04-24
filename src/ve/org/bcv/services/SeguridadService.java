/**
 * 
 */
package ve.org.bcv.services;

import javax.ejb.Local;

import ve.org.bcv.dto.SeguridadDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 15:25:16
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Local
public interface SeguridadService {
	SeguridadDto find(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario); 
}

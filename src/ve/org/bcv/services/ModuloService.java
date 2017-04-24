/**
 * 
 */
package ve.org.bcv.services;

import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dto.ModuloDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 17/06/2016 14:53:53
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Local
public interface ModuloService {
	ModuloDto save(String nb);
	List<ModuloDto> findAll();
}

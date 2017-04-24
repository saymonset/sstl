package ve.org.bcv.services;

import java.util.List;

import ve.org.bcv.dto.AccesoDirectoDto;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 *10 de mar. de 2017    9:46:39 a. m.
 * mail : oraclefedora@gmail.com
 */


public interface AccesoDirectoService {
	List<AccesoDirectoDto> findAll(String cedula);
}

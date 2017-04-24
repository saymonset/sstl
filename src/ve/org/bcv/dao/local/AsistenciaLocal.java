package ve.org.bcv.dao.local;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dao.Atencion;

/**
 * @author  Ing Simon Alberto Rodriguez Pacheco
 * 14 de nov. de 2016 9:29:03 a.�m.
 * mail: oraclefedora@gmail.com
 */
@Local
public interface AsistenciaLocal {
	List<Atencion> asistenciaByCedula(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String cedula,  Date feDesdeStr, Date feHastaStr) ;

}
 
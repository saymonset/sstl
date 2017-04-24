/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ve.org.bcv.dao.Atencion;
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.MiembroPK;
import ve.org.bcv.util.ManejadorDB;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 10:18:54
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface MiembroLocal extends EntityRepository<Miembro, Long>, Serializable {
	

	
	  Miembro find(MiembroPK id) ;
	
	  
	  
	  Miembro findUserFromPersonalTdoEmpleados(String ceula,ManejadorDB manejadorDB);
	 
	  List<Miembro> findMiembrosByActMod(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,Date feDesdeStr,Date feHastaStr);
	  List<Miembro> findMiembrosByActMod(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario);
	  List<Miembro> findMiembrosByMod(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,Date feDesdeStr,Date feHastaStr);
	  Miembro save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula,String nombre, String tipoEmp)throws Exception;
	
	  Miembro saveInscripcion(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,
		   	  String cedula,String nombre, String tipoEmp,boolean renovable,boolean profesor,Date fechaRenovacion);
	  Miembro update(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario,String cedula,String nombre, String tipoEmp, boolean renovable, boolean profesor,Date fechaRenovacion);
	  
	  List<Miembro> findMiembrosByActMod(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad);
	  
	  List<Miembro> findMiembrosByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario, String cedula);
	  
	  List<Miembro> findMiembrosByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
				String nbActividad,String nbHorario, String cedula,Date feDesdeStr,Date feHastaStr);
	  

 
}

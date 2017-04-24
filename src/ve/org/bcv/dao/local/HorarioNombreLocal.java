package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.HorarioNombre;
import ve.org.bcv.dao.HorarioNombrePk;

public interface HorarioNombreLocal  extends EntityRepository<HorarioNombre, Long>, Serializable {
	List<HorarioNombre> findByModulo(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo,String actividad);
	HorarioNombre find(HorarioNombrePk id);
	HorarioNombre findSameModificar(HorarioNombrePk id) ;
	boolean isDeleteActividadInHorario(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad) ;
}

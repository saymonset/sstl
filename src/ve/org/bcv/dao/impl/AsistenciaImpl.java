package ve.org.bcv.dao.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.TemporalType;

import ve.org.bcv.dao.Atencion;
import ve.org.bcv.dao.local.AsistenciaLocal;

@Named("asistenciaLocal")
public class AsistenciaImpl  extends BaseDaoImpl<Atencion, Long> implements AsistenciaLocal {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Override
	public Atencion reatach(Atencion entity) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	
	
	public List<Atencion> asistenciaByCedula(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String cedula,  Date feDesdeStr, Date feHastaStr)  {
		List<Atencion> asistencias = null;
		try {
			if (nbModulo!=null && nbModulo.length()>0 && cedula!=null && cedula.length()>0 && null!=feDesdeStr && null!=feHastaStr ){
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u FROM Atencion u WHERE lower(u.id.nbModulo)=:nbModulo  ");
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupo ");
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo ");
				sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
				sql.append(" and u.id.cedula=:cedula ");
				sql.append(" and substring(u.id.start,1,10)>=:fechaDesde and substring(u.id.end,1,10)<=:fechaHasta");
				asistencias = (List<Atencion>)em.createQuery(sql.toString())
		                .setParameter("nbModulo", nbModulo.toLowerCase())
		                .setParameter("nbGrupo", nbGrupo.toLowerCase())
		                .setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
		                .setParameter("nbActividad", nbActividad.toLowerCase())
		                .setParameter("cedula", cedula)
		                .setParameter("fechaDesde",feDesdeStr, TemporalType.DATE) 
		                .setParameter("fechaHasta", feHastaStr, TemporalType.DATE) 
		                .getResultList();	
			}
		} catch (Exception e) {
			
		}
	
		return asistencias;
	}

	@Override
	public Class<Atencion> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}


}

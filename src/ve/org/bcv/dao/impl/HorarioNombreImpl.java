package ve.org.bcv.dao.impl;

import java.util.List;

import ve.org.bcv.dao.Horario;
import ve.org.bcv.dao.HorarioNombre;
import ve.org.bcv.dao.HorarioNombrePk;
import ve.org.bcv.dao.local.HorarioNombreLocal;

public class HorarioNombreImpl extends BaseDaoImpl<HorarioNombre, Long> implements HorarioNombreLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public HorarioNombre reatach(HorarioNombre entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<HorarioNombre> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<HorarioNombre> findByModulo(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo,String actividad){
		List<HorarioNombre> objs =null;
		HorarioNombrePk id = new HorarioNombrePk();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
		id.setNbActividad(actividad);
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0
					&& id.getNbActividad() != null
					&& id.getNbActividad().length() > 0) {
			
			objs = (List<HorarioNombre>)em.createQuery("SELECT u FROM HorarioNombre u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo "
					+ " and lower(u.id.nbActividad)=:nbActividad")
	            	.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
					.setParameter("nbActividad", id.getNbActividad().toLowerCase())
	                .getResultList();	
		}
        return objs;
	}
	
	
	public boolean isDeleteActividadInHorario(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad) {
		boolean isDelete = false;
		List<Horario> horarios = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM HorarioNombre u WHERE ");
			sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");

			horarios = (List<Horario>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase()).setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase()).getResultList();
			if (horarios == null || horarios.size() == 0 || horarios.isEmpty()) {
				isDelete = true;
			}
		}

		 
		return isDelete;
	}
	

	
	 public HorarioNombre find(HorarioNombrePk id) {
		 HorarioNombre obj=null;
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0 && id.getNbActividad()!=null && id.getNbActividad().length()>0
					&& id.getNbHorario()!=null && id.getNbHorario().length()>0){
		        List<HorarioNombre> objs = (List<HorarioNombre>)em.createQuery("SELECT u FROM HorarioNombre u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo and lower(u.id.nbActividad)=:nbActividad "
		        		+ " and lower(u.id.nbHorario)=:nbHorario")
		            	.setParameter("nbModulo", id.getNbModulo().toLowerCase())
						.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
						.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
						.setParameter("nbActividad", id.getNbActividad().toLowerCase())
						.setParameter("nbHorario", id.getNbHorario().toLowerCase())
		                .getResultList();
		        
		        if (objs!=null && objs.size()>0){
		        	obj=objs.get(0);
		        }
				
			}
	        
	        return obj;
	    }

	 public HorarioNombre findSameModificar(HorarioNombrePk id) {
			HorarioNombre obj = null;
			 
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0 && id.getNbActividad() != null
					&& id.getNbActividad().length() > 0
					&& id.getNbHorario()!=null && id.getNbHorario().length()>0){
				List<HorarioNombre> objs = (List<HorarioNombre>) em
						.createQuery(
								"SELECT u FROM HorarioNombre u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo  and lower(u.id.nbActividad)=:nbActividad "
		        		+ " and lower(u.id.nbHorario)=:nbHorario order by u.id.nbHorario asc")
						.setParameter("nbModulo", id.getNbModulo().toLowerCase())
						.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
						.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
						.setParameter("nbActividad", id.getNbActividad().toLowerCase())
						.setParameter("nbHorario", id.getNbHorario().toLowerCase())
						.getResultList();

				if (objs != null && objs.size() > 0) {
					obj = objs.get(0);
				}
			}
			return obj;
		}
	
}

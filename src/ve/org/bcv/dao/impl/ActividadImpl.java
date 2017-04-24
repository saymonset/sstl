/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.util.List;

import ve.org.bcv.dao.Actividad;
import ve.org.bcv.dao.ActividadPK;
import ve.org.bcv.dao.local.ActividadLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 14/06/2016 15:09:20
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ActividadImpl extends BaseDaoImpl<Actividad, Long> implements ActividadLocal {

	 

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Deprecated
	public List<Actividad> findByModulo(String nbModulo) {
		List<Actividad> actividades =null;
		if (nbModulo!=null && nbModulo.length()>0){
			actividades = (List<Actividad>)em.createQuery("SELECT u FROM Actividad u WHERE lower(u.id.nbModulo)=:nbModulo")
	                .setParameter("nbModulo", nbModulo.toLowerCase())
	                .getResultList();	
		}
		
        
        return actividades;
	}
	
	public List<Actividad> findByModulo(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo){
		List<Actividad> actividades =null;
		ActividadPK id = new ActividadPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0 ) {
			
			actividades = (List<Actividad>)em.createQuery("SELECT u FROM Actividad u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo")
	            	.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
	                .getResultList();	
		}
		
        
        return actividades;
	}
	
	 public Actividad find(ActividadPK id) {
		  Actividad actividad=null;
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0 && id.getNbActividad()!=null && id.getNbActividad().length()>0){
		        List<Actividad> actividades = (List<Actividad>)em.createQuery("SELECT u FROM Actividad u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo and lower(u.id.nbActividad)=:nbActividad ")
		            	.setParameter("nbModulo", id.getNbModulo().toLowerCase())
						.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
						.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
						.setParameter("nbActividad", id.getNbActividad().toLowerCase())
		                .getResultList();
		        
		        if (actividades!=null && actividades.size()>0){
		        	actividad=actividades.get(0);
		        }
				
			}
	        
	        return actividad;
	    }
	

	/* (non-Javadoc)
	 * @see ve.org.bcv.dao.local.EntityRepository#reatach(java.lang.Object)
	 */
	@Override
	public Actividad reatach(Actividad entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ve.org.bcv.dao.impl.BaseDaoImpl#getEntityClass()
	 */
	@Override
	public Class<Actividad> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Actividad> findAll(ActividadPK id) {
		List<Actividad> objs = null;
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null && id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null && id.getNbSubGrupo().length() > 0) {
			objs = (List<Actividad>) em
					.createQuery(
							"SELECT u FROM Actividad u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo order by u.id.nbActividad asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
					.getResultList();
		}
		return objs;
	}
	
	
	public Actividad findSameModificar(ActividadPK id) {
		Actividad obj = null;
		 
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
				&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
				&& id.getNbSubGrupo().length() > 0 && id.getNbActividad() != null
				&& id.getNbActividad().length() > 0) {
			List<Actividad> objs = (List<Actividad>) em
					.createQuery(
							"SELECT u FROM Actividad u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo  and lower(u.id.nbActividad)=:nbActividad order by u.id.nbActividad asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
					.setParameter("nbActividad", id.getNbActividad().toLowerCase())
					.getResultList();

			if (objs != null && objs.size() > 0) {
				obj = objs.get(0);
			}
		}
		return obj;
	}

}

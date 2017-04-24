package ve.org.bcv.dao.impl;

import java.util.List;

import ve.org.bcv.dao.AccesoDirecto;
import ve.org.bcv.dao.AccesoDirectoPK;
import ve.org.bcv.dao.local.AccesoDirectoLocal;

public class AccesoDirectoImpl extends BaseDaoImpl<AccesoDirecto, Long> implements AccesoDirectoLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AccesoDirecto reatach(AccesoDirecto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<AccesoDirecto> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AccesoDirecto> findAll(String cedula) {
		List<AccesoDirecto> modulos = null;
		 
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u FROM AccesoDirecto u ");
				modulos = (List<AccesoDirecto>)em.createQuery(sql.toString())
		                .getResultList();	
			} catch (Exception e) {
                  
			}
		return modulos;
	}
	 
	 public AccesoDirecto find(AccesoDirectoPK id) {
		  AccesoDirecto obj=null;
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0 && id.getNbActividad()!=null && id.getNbActividad().length()>0){
		        List<AccesoDirecto> objs = (List<AccesoDirecto>)em.createQuery("SELECT u FROM AccesoDirecto u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo and lower(u.id.nbActividad)=:nbActividad and lower(u.id.nbHorario)=:nbHorario")
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
	 
	  
	 
	 
	 

}

/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.util.List;

import ve.org.bcv.dao.Parametro;
import ve.org.bcv.dao.ParametroPk;
import ve.org.bcv.dao.local.ParametroLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/08/2016 09:09:07
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ParametroImpl extends BaseDaoImpl<Parametro, Long>  implements ParametroLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see ve.org.bcv.dao.local.EntityRepository#reatach(java.lang.Object)
	 */
	@Override
	public Parametro reatach(Parametro entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ve.org.bcv.dao.impl.BaseDaoImpl#getEntityClass()
	 */
	@Override
	public Class<Parametro> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

 
	public   Parametro  save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro,
			String txValorParametro, String txObservaciones,String tipoParametro,boolean personalizarHorario) {
		Parametro parametro = null;
		try {
			if (find(   nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario, txNombreParametro)==null){
				parametro = new Parametro();
				ParametroPk parametroPK = new ParametroPk(  nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,txNombreParametro);
				parametro.setId(parametroPK);
				parametro.setTxObservaciones(txObservaciones);
				parametro.setTipoParametro(tipoParametro);
				parametro.setTxValorParametro(txValorParametro);
				parametro.setPersonalizarHorario(personalizarHorario);
				em.persist(parametro);
			}
			
				
		} catch (Exception e) {
		    System.out.println("No se pudo persistir el objeto.. txNombreParametro="+txNombreParametro);
		}
		
		
		return parametro;
	}

	 
	public Parametro update(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,
			String txNombreParametro,String txValorParametro,
			String txObservaciones,String tipoParametro,boolean personalizarHorario) {
		Parametro parametro=find(   nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario, txNombreParametro);
		if (parametro!=null){
			parametro.setTxValorParametro(txValorParametro);
			parametro.setTipoParametro(tipoParametro);
			parametro.setTxObservaciones(txObservaciones);
			parametro.setPersonalizarHorario(personalizarHorario);
			em.merge(parametro);
		 
			 
			 
		}
		return parametro;
	}

	
	
	
	public List<Parametro> findAll(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario) {
		List<Parametro> parametros = null;
		if (nbModulo!=null && nbModulo.length()>0 && nbGrupoModulo!=null && nbGrupoModulo.length()>0
				&& nbSubGrupoModulo!=null && nbSubGrupoModulo.length()>0
				&& nbActividad!=null && nbActividad.length()>0
				&& nbHorario!=null && nbHorario.length()>0){
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u FROM Parametro u WHERE lower(u.id.nbModulo)=:nbModulo ");
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupo");
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo");
				sql.append(" and lower(u.id.nbActividad)=:nbActividad");
				sql.append(" and lower(u.id.nbHorario)=:nbHorario");
				parametros = (List<Parametro>)em.createQuery(sql.toString())
		                .setParameter("nbModulo", nbModulo.toLowerCase())
		                .setParameter("nbGrupo", nbGrupoModulo.toLowerCase())
		                .setParameter("nbSubGrupo", nbSubGrupoModulo.toLowerCase())
		                .setParameter("nbActividad", nbActividad.toLowerCase())
		                .setParameter("nbHorario", nbHorario.toLowerCase())
		                .getResultList();	
			} catch (Exception e) {
                  
			}
			
		}
	 

		
		
		return parametros;
	}
	
	 public List<Parametro> find(ParametroPk id) {
		 List<Parametro> objs = null;
			if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
					&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
					&& id.getNbSubGrupo().length() > 0 && id.getNbActividad()!=null && id.getNbActividad().length()>0){
		        objs = (List<Parametro>)em.createQuery("SELECT u FROM Parametro u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo and lower(u.id.nbActividad)=:nbActividad and lower(u.id.nbHorario)=:nbHorario")
		            	.setParameter("nbModulo", id.getNbModulo().toLowerCase())
						.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
						.setParameter("nbSubGrupo", id.getNbSubGrupo().toLowerCase())
						.setParameter("nbActividad", id.getNbActividad().toLowerCase())
						.setParameter("nbHorario", id.getNbHorario().toLowerCase())
		                .getResultList();
			}
	        return objs;
	    }
	 

	public Parametro find(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,
			String txNombreParametro) {
	
		
		
		 Parametro obj=null;
		 if (nbModulo!=null && nbModulo.length()>0 && nbGrupoModulo!=null && nbGrupoModulo.length()>0
					&& nbSubGrupoModulo!=null && nbSubGrupoModulo.length()>0
					&& nbActividad!=null && nbActividad.length()>0
					&& nbHorario!=null && nbHorario.length()>0){
				
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u FROM Parametro u WHERE lower(u.id.nbModulo)=:nbModulo ");
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupo");
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo");
				sql.append(" and lower(u.id.nbActividad)=:nbActividad");
				sql.append(" and lower(u.id.nbHorario)=:nbHorario");
				sql.append(" and u.id.txNombreParametro=:txNombreParametro");
				
				
				List<Parametro> objs = (List<Parametro>)em.createQuery(sql.toString())
						  .setParameter("nbModulo", nbModulo.toLowerCase())
			                .setParameter("nbGrupo", nbGrupoModulo.toLowerCase())
			                .setParameter("nbSubGrupo", nbSubGrupoModulo.toLowerCase())
			                .setParameter("nbActividad", nbActividad.toLowerCase())
			                .setParameter("nbHorario", nbHorario.toLowerCase())
		                .setParameter("txNombreParametro", txNombreParametro)
		                .getResultList();
		        
		        if (objs!=null && objs.size()>0){
		        	obj=objs.get(0);
		        }
			}
	        
		
		
		
		return obj;
	}

	
}

/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import ve.org.bcv.dao.MiembroHorario;
import ve.org.bcv.dao.MiembroHorarioPK;
import ve.org.bcv.dao.local.MiembroHorarioLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 09:55:49
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Named("miembroHorarioLocal")
public class MiembroHorarioImpl extends BaseDaoImpl<MiembroHorario, Long> implements MiembroHorarioLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see ve.org.bcv.dao.local.EntityRepository#reatach(java.lang.Object)
	 */
	@Override
	public MiembroHorario reatach(MiembroHorario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ve.org.bcv.dao.impl.BaseDaoImpl#getEntityClass()
	 */
	@Override
	public Class<MiembroHorario> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
 
	 
	public MiembroHorario save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,
			String cedula, String id, String start, String end, String text)
			throws Exception {
		MiembroHorarioPK miembroHorarioPk = new MiembroHorarioPK();
		miembroHorarioPk.setNbModulo(nbModulo);
		miembroHorarioPk.setNbGrupo(nbGrupoModulo);
		miembroHorarioPk.setNbSubGrupo(nbSubGrupoModulo);
		miembroHorarioPk.setNbActividad(nbActividad);
		miembroHorarioPk.setNbHorario(nbHorario);
		miembroHorarioPk.setCedula(cedula);
		miembroHorarioPk.setId(id);
		
		MiembroHorario miembroHorario = new MiembroHorario();
		miembroHorario.setPk(miembroHorarioPk);
		miembroHorario.setEnd(end);
		miembroHorario.setStart(start);
		miembroHorario.setText(text);
		em.persist(miembroHorario);
		 
		return miembroHorario;
	}

 
	public MiembroHorario findByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,
			String cedula) throws Exception {
		
		MiembroHorario obj=null;
		if (nbModulo!=null && nbModulo.length()>0
				&& nbGrupoModulo!=null && nbGrupoModulo.length()>0
				&& nbSubGrupoModulo!=null && nbSubGrupoModulo.length()>0
				&& nbActividad!=null && nbActividad.length()>0
				&& nbHorario!=null && nbHorario.length()>0
				&& cedula!=null && cedula.length()>0
				){
			
			List<MiembroHorario> objetos = findHorarioByCedula( nbModulo, nbGrupoModulo, nbSubGrupoModulo,
					 nbActividad, nbHorario,
					 cedula);
		 if (objetos!=null && objetos.size()>0){
			 obj=objetos.get(0);
		 }	
		}
		 
		
		
		return obj;
	}
	
	
	public List<MiembroHorario> findNbHorario(String nbHorario) throws Exception {
		List<MiembroHorario> objetos = null;
		if (nbHorario!=null && nbHorario.length()>0
				){
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM MiembroHorario u WHERE ");
			sql.append("   lower(u.pk.nbHorario)=:nbHorario");
			 
			 objetos = (List<MiembroHorario>)em.createQuery(sql.toString())
		                .setParameter("nbHorario",nbHorario.toLowerCase())
		                .getResultList();
		}
		
		
		
		return objetos;
	}
	
	
	public List<MiembroHorario> findHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,
			String cedula) throws Exception {
		List<MiembroHorario> objetos = null;
		if (nbModulo!=null && nbModulo.length()>0
				&& nbGrupoModulo!=null && nbGrupoModulo.length()>0
				&& nbSubGrupoModulo!=null && nbSubGrupoModulo.length()>0
				&& nbActividad!=null && nbActividad.length()>0
				&& nbHorario!=null && nbHorario.length()>0
				&& cedula!=null && cedula.length()>0
				){
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM MiembroHorario u WHERE ");
			sql.append(" lower(u.pk.nbModulo)=:nbModulo ");
			sql.append(" and lower(u.pk.nbGrupo)=:nbGrupo");
			sql.append(" and lower(u.pk.nbSubGrupo)=:nbSubGrupo");
			sql.append(" and lower(u.pk.nbActividad)=:nbActividad");
			sql.append(" and lower(u.pk.nbHorario)=:nbHorario");
			sql.append(" and       u.pk.cedula=:cedula");

			 objetos = (List<MiembroHorario>)em.createQuery(sql.toString())
		                .setParameter("nbModulo", nbModulo.toLowerCase())
		                .setParameter("nbGrupo", nbGrupoModulo.toLowerCase())
		                .setParameter("nbSubGrupo", nbSubGrupoModulo.toLowerCase())
		                .setParameter("nbActividad", nbActividad.toLowerCase())
		                .setParameter("nbHorario",nbHorario.toLowerCase())
		                .setParameter("cedula", cedula)
		                .getResultList();
		}
		
		
		
		return objetos;
	}

	 
	public List<MiembroHorario> findByModuloActividad(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario) throws Exception {
		List<MiembroHorario> objetos = null;
		if (nbModulo!=null && nbModulo.length()>0
				&& nbGrupoModulo!=null && nbGrupoModulo.length()>0
				&& nbSubGrupoModulo!=null && nbSubGrupoModulo.length()>0
				&& nbActividad!=null && nbActividad.length()>0
				&& nbHorario!=null && nbHorario.length()>0
				){
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u FROM MiembroHorario u WHERE ");
				sql.append(" lower(u.pk.nbModulo)=:nbModulo ");
				sql.append(" and lower(u.pk.nbGrupo)=:nbGrupo");
				sql.append(" and lower(u.pk.nbSubGrupo)=:nbSubGrupo");
				sql.append(" and lower(u.pk.nbActividad)=:nbActividad");
				sql.append(" and lower(u.pk.nbHorario)=:nbHorario");

				 objetos = (List<MiembroHorario>)em.createQuery(sql.toString())
			                .setParameter("nbModulo", nbModulo.toLowerCase())
			                .setParameter("nbGrupo", nbGrupoModulo.toLowerCase())
			                .setParameter("nbSubGrupo", nbSubGrupoModulo.toLowerCase())
			                .setParameter("nbActividad", nbActividad.toLowerCase())
			                .setParameter("nbHorario",nbHorario.toLowerCase()) 
			                .getResultList();
			 
			 
		}
	
		
		
		return objetos;
	}

	public List<MiembroHorario> byModuloMiembros(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad, Date feDesde, Date feHasta) throws Exception {
		List<MiembroHorario> objetos = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr=sdf.format(feDesde);
		String feHastaStr=sdf.format(feHasta);
		if (nbModulo!=null && nbModulo.length()>0
				&& nbGrupoModulo!=null && nbGrupoModulo.length()>0
				&& nbSubGrupoModulo!=null && nbSubGrupoModulo.length()>0
				&& nbActividad!=null && nbActividad.length()>0
				){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM MiembroHorario u WHERE ");
			sql.append(" lower(u.pk.nbModulo)=:nbModulo ");
			sql.append(" and lower(u.pk.nbGrupo)=:nbGrupo");
			sql.append(" and lower(u.pk.nbSubGrupo)=:nbSubGrupo");
			sql.append(" and lower(u.pk.nbActividad)=:nbActividad");
			sql.append(" and u.start>=:fechaDesde and u.end<=:fechaHasta");
			 objetos = (List<MiembroHorario>)em.createQuery(sql.toString())
		                .setParameter("nbModulo", nbModulo.toLowerCase())
		                .setParameter("nbGrupo", nbGrupoModulo.toLowerCase())
		                .setParameter("nbSubGrupo", nbSubGrupoModulo.toLowerCase())
		                .setParameter("nbActividad", nbActividad.toLowerCase())
		                .setParameter("fechaDesde",feDesdeStr) 
		                .setParameter("fechaHasta",feHastaStr) 
		                .getResultList();
		}
		return objetos;
	}
	
	 
	
	

}

/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import ve.org.bcv.dao.Atencion;
import ve.org.bcv.dao.local.AtencionLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 26/07/2016 11:47:26 2016 mail :
 *         oraclefedora@gmail.com
 */
@Named("atencionLocal")
public class AtencionImpl extends BaseDaoImpl<Atencion, Long> implements AtencionLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ve.org.bcv.dao.local.EntityRepository#reatach(java.lang.Object)
	 */
	@Override
	public Atencion reatach(Atencion entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ve.org.bcv.dao.impl.BaseDaoImpl#getEntityClass()
	 */
	@Override
	public Class<Atencion> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Atencion> findByModuloActivdadStadistica(String nbModulo, String nbGrupo, String nbSubGrupo,
			String nbActividad, String nbHorario) {
		List<Atencion> atenciones = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && nbHorario != null
				&& nbHorario.length() > 0) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Atencion u WHERE ");
			sql.append("  lower(u.id.nbModulo)=:nbModulo  ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			sql.append(" and lower(u.id.nbHorario)=:nbHorario ");

			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase()).setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).getResultList();

		}

		return atenciones;
	}
	
	
	public List<Atencion> findNbHorario(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario ) {
		List<Atencion> atenciones = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && nbHorario != null
				&& nbHorario.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Atencion u WHERE ");
			sql.append("  lower(u.id.nbModulo)=:nbModulo  ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			sql.append(" and lower(u.id.nbHorario)=:nbHorario ");
			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase())
					.getResultList();
		}

		return atenciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ve.org.bcv.dao.local.atencionLocal#
	 * findByModuloActivdadByUsuarioStadistica(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public List<Atencion> findByModuloActivdadByUsuarioStadistica(String nbModulo, String nbGrupo, String nbSubGrupo,
			String nbActividad, String nbHorario, String cedula) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Atencion> atencionByCedula(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario, String cedula) {
		List<Atencion> atenciones = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && nbHorario != null
				&& nbHorario.length() > 0 && cedula != null && cedula.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Atencion u WHERE ");
			sql.append("  lower(u.id.nbModulo)=:nbModulo  ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			sql.append(" and lower(u.id.nbHorario)=:nbHorario ");
			sql.append(" and u.id.cedula=:cedula ");
			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase()).setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).setParameter("cedula", cedula).getResultList();
		}

		return atenciones;
	}

	public List<Atencion> atencionByText(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario, String text, String id, String feDesdeStr, String fechaHastaStr) {
		List<Atencion> atenciones = null;

		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && nbHorario != null
				&& nbHorario.length() > 0 && text != null && text.length() > 0
						&& id != null && id.length() > 0  && feDesdeStr != null
				&& feDesdeStr.length() > 0 && fechaHastaStr != null && fechaHastaStr.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Atencion u WHERE ");
			sql.append("  lower(u.id.nbModulo)=:nbModulo  ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			sql.append(" and lower(u.id.nbHorario)=:nbHorario ");
			sql.append(" and lower(u.text)=:text ");
			//sql.append(" and lower(u.id.id)=:id ");
			sql.append(" and substring(u.id.start,1,10)>=:fechaDesde and substring(u.id.end,1,10)<=:fechaHasta");

			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase())
					.setParameter("text", text.toLowerCase())
					//.setParameter("id", id)
					.setParameter("fechaDesde", feDesdeStr)
					.setParameter("fechaHasta", fechaHastaStr).getResultList();

		}
		return atenciones;
	}

	public List<Atencion> byModuloAtenciones(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String horario, String cedula, Date feDesde, Date feHasta) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr = sdf.format(feDesde);
		String feHastaStr = sdf.format(feHasta);
		List<Atencion> atenciones = null;

		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && horario != null
				&& horario.length() > 0 && cedula != null && cedula.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Atencion u WHERE ");
			sql.append("  lower(u.id.nbModulo)=:nbModulo  ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			sql.append(" and lower(u.id.id)=:horario ");
			sql.append(" and substring(u.id.start,1,10)>=:fechaDesde and substring(u.id.end,1,10)<=:fechaHasta");
			sql.append(" and u.id.cedula=:cedula");

			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase()).setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("horario", horario.toLowerCase()).setParameter("fechaDesde", feDesdeStr)
					.setParameter("fechaHasta", feHastaStr).setParameter("cedula", cedula).getResultList();
		}

		return atenciones;
	}

	public List<Atencion> byModuloCedulaAtenciones(String nbModulo, String nbGrupo, String nbSubGrupo,
			String nbActividad,String nbHorario, String cedula, Date feDesde, Date feHasta) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr = sdf.format(feDesde);
		String feHastaStr = sdf.format(feHasta);
		List<Atencion> atenciones = null;
		StringBuilder sql = new StringBuilder();

		if (nbModulo != null && nbModulo.length() > 0 
				&& nbGrupo != null && nbGrupo.length() > 0 
				&& nbSubGrupo != null && nbSubGrupo.length() > 0 
				&& nbActividad != null && nbActividad.length() > 0 
				&& nbHorario != null && nbHorario.length() > 0 
				&& cedula != null	&& cedula.length() > 0
				&& feDesde != null && feHasta != null) {
			
 
			
			sql.append("SELECT u FROM Atencion u WHERE ");
			
			if (!"none".equalsIgnoreCase(nbModulo)){
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			}else{
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}
			
			if (!"none".equalsIgnoreCase(nbGrupo)){
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");	
			}else{
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupo  ");
			}
			
			if (!"none".equalsIgnoreCase(nbSubGrupo)){
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");	
			}else{
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupo  ");
			}
			
			if (!"none".equalsIgnoreCase(nbActividad)){
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");	
			}else{
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}
			
			if (!"none".equalsIgnoreCase(nbHorario)){
				sql.append(" and lower(u.id.nbHorario)=:nbHorario  ");	
			}else{
				sql.append(" and lower(u.id.nbHorario)<>:nbHorario  ");
			}
			
			if (!"none".equalsIgnoreCase(cedula)){
				sql.append(" and lower(u.id.cedula)=:cedula  ");	
			}else{
				sql.append(" and lower(u.id.cedula)<>:cedula  ");
			}
			
			sql.append(" and substring(u.id.start,1,10)>=:fechaDesde and substring(u.id.end,1,10)<=:fechaHasta");
		 
			
			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase())
		          	.setParameter("cedula", cedula)
					.setParameter("fechaDesde", feDesdeStr)
					.setParameter("fechaHasta", feHastaStr)
					.getResultList();

		}  
		return atenciones;
	}
	
	
	
	public List<Atencion> asistencia(String nbModulo, String nbGrupo, String nbSubGrupo,
			String nbActividad,String nbHorario, String cedula, Date feDesde, Date feHasta) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr = sdf.format(feDesde);
		String feHastaStr = sdf.format(feHasta);
		List<Atencion> atenciones = null;
		StringBuilder sql = new StringBuilder();

		if (nbModulo != null && nbModulo.length() > 0 
				&& nbGrupo != null && nbGrupo.length() > 0 
				&& nbSubGrupo != null && nbSubGrupo.length() > 0 
				&& nbActividad != null && nbActividad.length() > 0 
				&& nbHorario != null && nbHorario.length() > 0 
				&& cedula != null	&& cedula.length() > 0
				&& feDesde != null && feHasta != null) {
			
 
			
			sql.append("SELECT u FROM Atencion u WHERE ");
			
			if (!"none".equalsIgnoreCase(nbModulo)){
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			}else{
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}
			
			if (!"none".equalsIgnoreCase(nbGrupo)){
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");	
			}else{
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupo  ");
			}
			
			if (!"none".equalsIgnoreCase(nbSubGrupo)){
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");	
			}else{
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupo  ");
			}
			
			if (!"none".equalsIgnoreCase(nbActividad)){
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");	
			}else{
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}
			
			if (!"none".equalsIgnoreCase(nbHorario)){
				sql.append(" and lower(u.id.nbHorario)=:nbHorario  ");	
			}else{
				sql.append(" and lower(u.id.nbHorario)<>:nbHorario  ");
			}
			
			if (!"none".equalsIgnoreCase(cedula)){
				sql.append(" and lower(u.id.cedula)=:cedula  ");	
			}else{
				sql.append(" and lower(u.id.cedula)<>:cedula  ");
			}
			
			sql.append(" and substring(u.id.start,1,10)>=:fechaDesde and substring(u.id.end,1,10)<=:fechaHasta");
		 
			
			atenciones = (List<Atencion>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase())
		          	.setParameter("cedula", cedula)
					.setParameter("fechaDesde", feDesdeStr)
					.setParameter("fechaHasta", feHastaStr)
					.getResultList();
			
			if (null!=atenciones && atenciones.size()>0){
				Map<String,String> unicoCedula = new HashMap<String,String>();
				List<Atencion> atencionesAux = new ArrayList<Atencion>();;
				for (Atencion atencion:atenciones){
					//substring(u.id.start,1,10)
					if (!unicoCedula.containsKey(atencion.getId().getCedula() + atencion.getId().getStart().substring(1, 10))){
						unicoCedula.put(atencion.getId().getCedula() + atencion.getId().getStart().substring(1, 10), atencion.getId().getCedula() + atencion.getId().getStart().substring(1, 10));
						atencionesAux.add(atencion);
					}
				}
				atenciones.clear();
				atenciones.addAll(atencionesAux);
			}

		}  
		return atenciones;
	}

}

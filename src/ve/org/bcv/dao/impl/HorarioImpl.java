/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.util.List;

import javax.inject.Named;

import ve.org.bcv.dao.Horario;
import ve.org.bcv.dao.local.HorarioLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 14/06/2016 15:03:15 2016 mail :
 *         oraclefedora@gmail.com
 */
@Named("horarioLocal")
public class HorarioImpl extends BaseDaoImpl<Horario, Long> implements HorarioLocal {

	/**
	 * extends BaseDaoImpl<Negotiation, Long>implements NegotiationDao {
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ve.org.bcv.dao.local.EntityRepository#reatach(java.lang.Object)
	 */
	@Override
	public Horario reatach(Horario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ve.org.bcv.dao.impl.BaseDaoImpl#getEntityClass()
	 */
	@Override
	public Class<Horario> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Horario> findByModuloActividad(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario) {
		List<Horario> horarios = null;

		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && nbHorario != null
				&& nbHorario.length() > 0) {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Horario u WHERE ");
			sql.append("  lower(u.id.nbModulo)=:nbModulo  ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			sql.append(" and lower(u.id.nbHorario)=:nbHorario ");

			horarios = (List<Horario>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase()).setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).getResultList();
		}

		return horarios;
	}

	public List<Horario> findByModuloActividad(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad) {
		List<Horario> horarios = null;
		if (null != nbModulo && nbModulo.length() > 0 && null != nbGrupo && nbGrupo.length() > 0 && null != nbSubGrupo
				&& nbSubGrupo.length() > 0 && null != nbActividad && nbActividad.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Horario u ");
			sql.append(
					" WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad ");
			horarios = (List<Horario>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase()).setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase()).getResultList();
		}
		return horarios;
	}

	

	public Horario find(String id) {
		Horario horario = null;
		List<Horario> horarios = (List<Horario>) em.createQuery("SELECT u FROM Horario u WHERE u.id.id=:id")
				.setParameter("id", id).getResultList();

		if (horarios != null && horarios.size() > 0) {
			horario = horarios.get(0);
		}
		return horario;
	}

	public Horario find(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario,
			String id) {
		Horario horario = null;
		if (null != nbModulo && nbModulo.length() > 0 && null != nbGrupo && nbGrupo.length() > 0 && null != nbSubGrupo
				&& nbSubGrupo.length() > 0 && null != nbActividad && nbActividad.length() > 0 && null != nbHorario
				&& nbHorario.length() > 0 && null != id && id.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT u FROM Horario u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and lower(u.id.nbSubGrupo)=:nbSubGrupo");
			sql.append(
					" and lower(u.id.nbActividad)=:nbActividad and lower(u.id.nbHorario)=:nbHorario and lower(u.id.id)=:id");
			List<Horario> horarios = (List<Horario>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase()).setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).setParameter("id", id.toLowerCase())
					.getResultList();

			if (horarios != null && horarios.size() > 0) {
				horario = horarios.get(0);
			}
		}

		return horario;
	}

	public List<Horario> atencionByModulo(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario) {
		List<Horario> horarios = null;

		if (nbModulo != null && nbModulo.length() > 0 && nbGrupo != null && nbGrupo.length() > 0 && nbSubGrupo != null
				&& nbSubGrupo.length() > 0 && nbActividad != null && nbActividad.length() > 0 && nbHorario != null
				&& nbHorario.length() > 0) {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT u FROM Horario u WHERE ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupo  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			} else {
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}

			if (!"none".equalsIgnoreCase(nbHorario)) {
				sql.append(" and lower(u.id.nbHorario)=:nbHorario  ");
			} else {
				sql.append(" and lower(u.id.nbHorario)<>:nbHorario  ");
			}

			horarios = (List<Horario>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupo", nbGrupo.toLowerCase())
					.setParameter("nbSubGrupo", nbSubGrupo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).getResultList();
		}

		return horarios;
	}

}

/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TemporalType;

import ve.org.bcv.dao.Atencion;
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.MiembroPK;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.util.ManejadorDB;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 25/07/2016 10:24:27 2016 mail :
 *         oraclefedora@gmail.com
 */

public class MiembroImpl extends BaseDaoImpl<Miembro, Long> implements MiembroLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Miembro reatach(Miembro entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<Miembro> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 


	/*
	 * Traemos la data desde base de datos oracle personal.todos_empleados
	 */

	public Miembro findUserFromPersonalTdoEmpleados(String ceula, ManejadorDB manejadorDB) {
		Connection con = null;
		Miembro miembro = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();
			if (null != con) {
				StringBuilder sql = new StringBuilder();
				sql.append(
						"select apellido1 || ' ' || apellido2 || ' ' ||   nombre1 || ' ' || nombre2  as nombre, tipo_emp  from PERSONAL.TODOS_EMPLEADOS t where T.CEDULA=")
						.append(ceula).append(" order by nombre");
				pstmt = con.prepareStatement(sql.toString());
				ResultSet result = pstmt.executeQuery();
				if (result != null) {
					while (result.next()) {
						miembro = new Miembro();
						String nombre = result.getString("nombre");
						String tipoEmp = result.getString("tipo_emp");
						miembro.setNombre(nombre);
						miembro.setTipoEmp(tipoEmp);
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return miembro;
	}

	public List<Miembro> findMiembrosByActMod(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario) {
		List<Miembro> miembros = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0 && nbHorario != null && nbHorario.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u FROM Miembro u WHERE  ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupoModulo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupoModulo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
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

			sql.append("  order by  u.id.cedula asc");
			miembros = (List<Miembro>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
					.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).getResultList();

		}
		return miembros;
	}

	public List<Miembro> findMiembrosByActMod(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, Date feDesdeStr, Date feHastaStr) {
		List<Miembro> miembros = null;
		StringBuilder sql = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0 && nbHorario != null && nbHorario.length() > 0) {
			sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupoModulo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupoModulo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
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

			sql.append(" and u.feRegistro>=:fechaDesde and u.feRegistro<=:fechaHasta  order by  u.id.cedula asc");

			miembros = (List<Miembro>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
					.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase())
					.setParameter("fechaDesde", feDesdeStr, TemporalType.DATE)
					.setParameter("fechaHasta", feHastaStr, TemporalType.DATE).getResultList();
		} else if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0) {
			sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");
			sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			sql.append(" and u.feRegistro>=:fechaDesde and u.feRegistro<=:fechaHasta order by  u.id.cedula asc");

			miembros = (List<Miembro>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
					.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("fechaDesde", feDesdeStr, TemporalType.DATE)
					.setParameter("fechaHasta", feHastaStr, TemporalType.DATE).getResultList();
		}

		return miembros;
	}

	public List<Miembro> findMiembrosByMod(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, Date feDesdeStr, Date feHastaStr) {
		List<Miembro> miembros = null;

		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupoModulo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupoModulo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			} else {
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}

			sql.append(" and u.feRegistro>=:fechaDesde and u.feRegistro<=:fechaHasta  order by  u.id.cedula asc");
			if (nbModulo != null && nbModulo.length() > 0) {
				miembros = (List<Miembro>) em.createQuery(sql.toString())
						.setParameter("nbModulo", nbModulo.toLowerCase())
						.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
						.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
						.setParameter("nbActividad", nbActividad.toLowerCase())
						.setParameter("fechaDesde", feDesdeStr, TemporalType.DATE)
						.setParameter("fechaHasta", feHastaStr, TemporalType.DATE).getResultList();
			}
		}

		return miembros;
	}

	public Miembro save(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String cedula, String nombre, String tipoEmp) throws Exception {
		Miembro miembro = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbActividad != null && nbActividad.length() > 0) {
			miembro = new Miembro();
			MiembroPK miembroPK = new MiembroPK(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
					cedula);
			miembro.setId(miembroPK);
			try {
				em.persist(miembro);
			} catch (Exception e) {
				System.out.println("No se pudo persistir el objeto.. ");
			}

		} else {
			throw new Exception("Modulo o actividad null.. , no se pudo crear");
		}

		return miembro;
	}

	public Miembro find(MiembroPK id) {
		Miembro obj = null;

		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbActividad() != null
				&& id.getNbActividad().length() > 0 && id.getNbHorario() != null && id.getNbHorario().length() > 0) {

			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");

			if (!"none".equalsIgnoreCase(id.getNbModulo())) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(id.getNbGrupo())) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(id.getNbSubGrupo())) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(id.getNbActividad())) {
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			} else {
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}

			if (!"none".equalsIgnoreCase(id.getNbHorario())) {
				sql.append(" and lower(u.id.nbHorario)=:nbHorario  ");
			} else {
				sql.append(" and lower(u.id.nbHorario)<>:nbHorario  ");
			}

			sql.append(" and u.id.cedula=:cedula  order by  u.id.cedula asc");

			List<Miembro> objs = (List<Miembro>) em.createQuery(sql.toString())
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupoModulo", id.getNbGrupo().toLowerCase())
					.setParameter("nbSubGrupoModulo", id.getNbSubGrupo().toLowerCase())
					.setParameter("nbActividad", id.getNbActividad().toLowerCase())
					.setParameter("nbHorario", id.getNbHorario().toLowerCase()).setParameter("cedula", id.getCedula())
					.getResultList();

			if (objs != null && objs.size() > 0) {
				obj = objs.get(0);
			}
		}

		return obj;
	}

	public Miembro saveInscripcion(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String cedula, String nombre, String tipoEmp, boolean renovable, boolean profesor,
			Date fechaRenovacion) {
		Miembro miembro = new Miembro();
		MiembroPK miembroPK = new MiembroPK(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario, cedula);
		miembro.setId(miembroPK);
		miembro.setFeRegistro(new Date());
		miembro.setFeRenovacion(fechaRenovacion);
		miembro.setProfesor(profesor);
		miembro.setRenovable(renovable);
		try {
			em.persist(miembro);
		} catch (Exception e) {
			System.out.println("No se pudo persistir el objeto.. ");
		}
		return miembro;
	}

	public Miembro update(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String cedula, String nombre, String tipoEmp, boolean renovable, boolean profesor,
			Date fechaRenovacion) {
		Miembro miembro = new Miembro();
		MiembroPK miembroPK = new MiembroPK(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario, cedula);
		miembro = find(miembroPK);
		miembro.setFeRegistro(new Date());
		miembro.setFeRenovacion(fechaRenovacion);
		miembro.setProfesor(profesor);
		miembro.setRenovable(renovable);
		try {
			em.merge(miembro);
		} catch (Exception e) {
			System.out.println("No se pudo persistir el objeto.. ");
		}
		return miembro;
	}

	public List<Miembro> findMiembrosByActMod(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad) {
		List<Miembro> miembros = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0) {

			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupoModulo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupoModulo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			} else {
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}

			sql.append("  order by  u.id.cedula asc");
			miembros = (List<Miembro>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
					.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase()).getResultList();
		}

		if (null != miembros) {
			Map unico = new HashMap<String, Object>();
			List<Miembro> miembrosAux = new ArrayList<Miembro>();
			;
			Miembro miembroFromPersonal = null;
			for (Miembro miembro : miembros) {
				if (!unico.containsKey(miembro.getId().getCedula())) {
					unico.put(miembro.getId().getCedula(), miembro);

					miembrosAux.add(miembro);
				}
			}
			miembros.clear();
			miembros.addAll(miembrosAux);
		}

		return miembros;
	}

	public List<Miembro> findMiembrosByCedula(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula) {
		List<Miembro> miembros = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0 && nbHorario != null && nbHorario.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupoModulo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupoModulo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			} else {
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbHorario)=:nbHorario  ");
			} else {
				sql.append(" and lower(u.id.nbHorario)<>:nbHorario  ");
			}

			sql.append(" and lower(u.id.cedula)=:cedula  ");
			sql.append("  order by  u.id.cedula asc ");
			miembros = (List<Miembro>) em.createQuery(sql.toString()).setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
					.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase()).setParameter("cedula", cedula).getResultList();
		}
		return miembros;
	}

	public List<Miembro> findMiembrosByCedula(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula, Date feDesdeStr, Date feHastaStr) {
		List<Miembro> miembros = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0 && nbHorario != null && nbHorario.length() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u FROM Miembro u WHERE ");

			if (!"none".equalsIgnoreCase(nbModulo)) {
				sql.append(" lower(u.id.nbModulo)=:nbModulo ");
			} else {
				sql.append(" lower(u.id.nbModulo)<>:nbModulo ");
			}

			if (!"none".equalsIgnoreCase(nbGrupoModulo)) {
				sql.append(" and lower(u.id.nbGrupo)=:nbGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbGrupo)<>:nbGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbSubGrupoModulo)) {
				sql.append(" and lower(u.id.nbSubGrupo)=:nbSubGrupoModulo  ");
			} else {
				sql.append(" and lower(u.id.nbSubGrupo)<>:nbSubGrupoModulo  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbActividad)=:nbActividad  ");
			} else {
				sql.append(" and lower(u.id.nbActividad)<>:nbActividad  ");
			}

			if (!"none".equalsIgnoreCase(nbActividad)) {
				sql.append(" and lower(u.id.nbHorario)=:nbHorario  ");
			} else {
				sql.append(" and lower(u.id.nbHorario)<>:nbHorario  ");
			}

			sql.append(" and lower(u.id.cedula)=:cedula  ");
			sql.append(" and u.feRegistro>=:fechaDesde ");
			sql.append("  and u.feRegistro<=:fechaHasta  ");
			sql.append("  order by  u.id.cedula asc ");
			miembros = (List<Miembro>) em.createQuery(sql.toString())
					.setParameter("nbModulo", nbModulo.toLowerCase())
					.setParameter("nbGrupoModulo", nbGrupoModulo.toLowerCase())
					.setParameter("nbSubGrupoModulo", nbSubGrupoModulo.toLowerCase())
					.setParameter("nbActividad", nbActividad.toLowerCase())
					.setParameter("nbHorario", nbHorario.toLowerCase())
					.setParameter("cedula", cedula)
					.setParameter("fechaDesde", feDesdeStr, TemporalType.DATE)
					.setParameter("fechaHasta", feHastaStr, TemporalType.DATE).getResultList();
		}
		return miembros;

	}
}

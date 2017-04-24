package ve.org.bcv.dao.impl;

import java.util.List;
import java.util.Random;

import ve.org.bcv.dao.SubGrupoModulo;
import ve.org.bcv.dao.SubGrupoModuloPK;
import ve.org.bcv.dao.local.SubGrupoModuloLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   24 de ene. de 2017 2:15:37 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
public class SubGrupoModuloImpl extends BaseDaoImpl<SubGrupoModulo, Long> implements SubGrupoModuloLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SubGrupoModulo reatach(SubGrupoModulo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<SubGrupoModulo> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SubGrupoModulo find(SubGrupoModuloPK id) {
		SubGrupoModulo obj = null;
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
				&& id.getNbGrupo().length() > 0 && id.getNbSubGrupo() != null
				&& id.getNbSubGrupo().length() > 0) {
			List<SubGrupoModulo> objs = (List<SubGrupoModulo>) em
					.createQuery(
							"SELECT u FROM SubGrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo and u.id.nbSubGrupo=:nbSubGrupo order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.setParameter("nbSubGrupo", id.getNbSubGrupo()).getResultList();

			if (objs != null && objs.size() > 0) {
				obj = objs.get(0);
			}
		}
		return obj;
	}
	
	public List<SubGrupoModulo> findAllByModuloGrupo(SubGrupoModuloPK id) {
		SubGrupoModulo obj = null;
		List<SubGrupoModulo> objs = null;
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
				&& id.getNbGrupo().length() > 0 ) {
		objs = (List<SubGrupoModulo>) em
					.createQuery(
							"SELECT u FROM SubGrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo  order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.getResultList();
		}
		return objs;
	}

	public List<SubGrupoModulo> findAll(SubGrupoModuloPK id) {
		List<SubGrupoModulo> objs = null;
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null && id.getNbGrupo().length() > 0) {
			objs = (List<SubGrupoModulo>) em
					.createQuery(
							"SELECT u FROM SubGrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.getResultList();
		}

		return objs;
	}

	public SubGrupoModulo findSameModificar(SubGrupoModuloPK id, String nbModificar) {
		SubGrupoModulo obj = null;
		 
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
				&& id.getNbGrupo().length() > 0 && null!=nbModificar && nbModificar.length()>0) {
			List<SubGrupoModulo> objs = (List<SubGrupoModulo>) em
					.createQuery(
							"SELECT u FROM SubGrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo  and lower(u.nbModificar)=:nbModificar order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.setParameter("nbModificar", nbModificar.toLowerCase())
					.getResultList();

			if (objs != null && objs.size() > 0) {
				obj = objs.get(0);
			}
		}
		return obj;
	}
	
	public SubGrupoModulo saveAgain(String nbModulo, String nbGrupoModulo,String nbSubGrupo){
		SubGrupoModulo obj = new SubGrupoModulo();
		obj.setNbModificar(nbSubGrupo);
		SubGrupoModuloPK id = new SubGrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		Random rn = new Random();
		int answer = rn.nextInt(1000) + 1;
		id.setNbSubGrupo(nbSubGrupo+"_"+answer);
		obj.setId(id);
		em.persist(obj);
		return obj;
	}

	 
}

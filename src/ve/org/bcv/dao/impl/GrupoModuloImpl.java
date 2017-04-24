package ve.org.bcv.dao.impl;

import java.util.List;
import java.util.Random;

import ve.org.bcv.dao.GrupoModulo;
import ve.org.bcv.dao.GrupoModuloPK;
import ve.org.bcv.dao.local.GrupoModuloLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   24 de ene. de 2017 2:15:33 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
public class GrupoModuloImpl extends BaseDaoImpl<GrupoModulo, Long> implements GrupoModuloLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public GrupoModulo reatach(GrupoModulo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<GrupoModulo> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public GrupoModulo findSameModificar(GrupoModuloPK id,String nbModificar) {
		GrupoModulo obj = null;
		 
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
				&& id.getNbGrupo().length() > 0 && null!=nbModificar && nbModificar.length()>0) {
			List<GrupoModulo> objs = (List<GrupoModulo>) em
					.createQuery(
							"SELECT u FROM GrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.nbModificar)=:nbModificar order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbModificar", nbModificar.toLowerCase())
					.getResultList();

			if (objs != null && objs.size() > 0) {
				obj = objs.get(0);
			}
		}
		return obj;
	}

	public GrupoModulo find(GrupoModuloPK id) {
		GrupoModulo obj = null;
		 
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0 && id.getNbGrupo() != null
				&& id.getNbGrupo().length() > 0 ) {
			List<GrupoModulo> objs = (List<GrupoModulo>) em
					.createQuery(
							"SELECT u FROM GrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo and lower(u.id.nbGrupo)=:nbGrupo order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.setParameter("nbGrupo", id.getNbGrupo().toLowerCase())
					.getResultList();

			if (objs != null && objs.size() > 0) {
				obj = objs.get(0);
			}
		}
		return obj;
	}
	
	public List<GrupoModulo> findAll(GrupoModuloPK id) {
		List<GrupoModulo> objs = null;
		if (id.getNbModulo() != null && id.getNbModulo().length() > 0) {
			objs = (List<GrupoModulo>) em
					.createQuery(
							"SELECT u FROM GrupoModulo u WHERE lower(u.id.nbModulo)=:nbModulo order by u.nbModificar asc")
					.setParameter("nbModulo", id.getNbModulo().toLowerCase())
					.getResultList();
		}

		return objs;
	}
	
 

	public GrupoModulo save(String nbModulo, String nbGrupoModulo ) throws Exception {
		GrupoModulo grupoModulo = new GrupoModulo();
		grupoModulo.setNbModificar(nbGrupoModulo);
		GrupoModuloPK id = new GrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		grupoModulo.setId(id);
		em.persist(grupoModulo);	
		return grupoModulo;
	}
	
	public GrupoModulo saveAgain(String nbModulo, String nbGrupoModulo){
		GrupoModulo grupoModulo = new GrupoModulo();
		grupoModulo = new GrupoModulo();
		grupoModulo.setNbModificar(nbGrupoModulo);
		GrupoModuloPK id = new GrupoModuloPK();
		id.setNbModulo(nbModulo);
		Random rn = new Random();
		int answer = rn.nextInt(1000) + 1;
		id.setNbGrupo(nbGrupoModulo+"_"+answer);
		grupoModulo.setId(id);
		em.persist(grupoModulo);
		return grupoModulo;
	}

	
}

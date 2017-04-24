/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.util.List;

import ve.org.bcv.dao.Modulo;
import ve.org.bcv.dao.local.ModuloLocal;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 14/06/2016 15:07:02
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class ModuloImpl extends BaseDaoImpl<Modulo, Long>  implements ModuloLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 
	 
	public Modulo reatach(Modulo entity) {
		// TODO Auto-generated method stub
		return null;
	}
 
	public Class<Modulo> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Modulo> findAll() {
		List<Modulo> modulos = null;
		 
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT u FROM Modulo u ");
				modulos = (List<Modulo>)em.createQuery(sql.toString())
		                .getResultList();	
			} catch (Exception e) {
                  
			}
		return modulos;
	}

	

}

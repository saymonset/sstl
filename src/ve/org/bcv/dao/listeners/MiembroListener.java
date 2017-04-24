/**
 * 
 */
package ve.org.bcv.dao.listeners;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ve.org.bcv.dao.Miembro;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 28/07/2016 14:36:09
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class MiembroListener {
	@PrePersist
	public void prePresist(Miembro klass) {
		final Date now = new Date();
		klass.setFeRegistro(now);
	}

	@PreUpdate
	public void preUpdate(Miembro model) {

	}
}

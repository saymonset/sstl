package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.GrupoModulo;
import ve.org.bcv.dao.GrupoModuloPK;

public interface GrupoModuloLocal extends EntityRepository<GrupoModulo, Long>, Serializable {
	GrupoModulo find(GrupoModuloPK id) ;
	GrupoModulo save(String nbModulo, String nbGrupoModulo ) throws Exception ;
	GrupoModulo saveAgain(String nbModulo, String nbGrupoModulo);
	List<GrupoModulo> findAll(GrupoModuloPK id);
	GrupoModulo findSameModificar(GrupoModuloPK id,String nbModificar);
}

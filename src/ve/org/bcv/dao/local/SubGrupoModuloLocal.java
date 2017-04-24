package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

import ve.org.bcv.dao.SubGrupoModulo;
import ve.org.bcv.dao.SubGrupoModuloPK;

public interface SubGrupoModuloLocal extends EntityRepository<SubGrupoModulo, Long>, Serializable {

	SubGrupoModulo find(SubGrupoModuloPK id) ;
	 List<SubGrupoModulo> findAll(SubGrupoModuloPK id) ;
	 SubGrupoModulo findSameModificar(SubGrupoModuloPK id,String nbModificar);
	 public SubGrupoModulo saveAgain(String nbModulo, String nbGrupoModulo,String nbSubGrupo);
}

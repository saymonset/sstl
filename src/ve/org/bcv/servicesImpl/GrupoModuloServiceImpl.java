package ve.org.bcv.servicesImpl;

import ve.org.bcv.dao.GrupoModulo;
import ve.org.bcv.dao.GrupoModuloPK;
import ve.org.bcv.dao.SubGrupoModulo;
import ve.org.bcv.dao.SubGrupoModuloPK;
import ve.org.bcv.dao.local.GrupoModuloLocal;
import ve.org.bcv.dao.local.SubGrupoModuloLocal;
import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.GrupoModuloDto;
import ve.org.bcv.services.GrupoModuloService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 24 de ene. de 2017 2:30:57 p. m.
 *
 *         mail: oraclefedora@gmail.com
 */
@GrupoModuloServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GrupoModuloServiceImpl implements GrupoModuloService {
	@Inject
	private GrupoModuloLocal grupoModuloLocal;
	@Inject
	private SubGrupoModuloLocal subGrupoModuloLocal;


	public GrupoModuloDto save(String nbModulo, String nbGrupoModulo)throws Exception  {
		GrupoModuloDto out = new GrupoModuloDto();
		GrupoModulo grupoModulo = null;
		GrupoModuloPK grupoModuloPK = new GrupoModuloPK();
		grupoModuloPK.setNbModulo(nbModulo);
		grupoModuloPK.setNbGrupo(nbGrupoModulo);
		if (grupoModuloLocal.find(grupoModuloPK)==null ){
			grupoModulo = grupoModuloLocal.save(nbModulo,nbGrupoModulo);
			out.setDelete(true);
		}else{
			grupoModulo = grupoModuloLocal.saveAgain(nbModulo,nbGrupoModulo);
		}
		if (null != grupoModulo) {
			out.setNbGrupoModulo(grupoModulo.getId().getNbGrupo());
			out.setNbModulo(grupoModulo.getId().getNbModulo());
			out.setNbModificar(grupoModulo.getNbModificar());
			SubGrupoModuloPK subGrupoModuloPK = new SubGrupoModuloPK();
			subGrupoModuloPK.setNbModulo(nbModulo);
			subGrupoModuloPK.setNbGrupo(grupoModulo.getId().getNbGrupo());
//			if (subGrupoModuloLocal.findAll(subGrupoModuloPK)==null || subGrupoModuloLocal.findAll(subGrupoModuloPK).size()==0){
//				out.setDelete(true);
//			}
		}
		return out;
	}

	public List<GrupoModuloDto> findAll(String nbModulo) throws Exception {
		List<GrupoModuloDto> outs = new ArrayList<GrupoModuloDto>();
		GrupoModuloPK id = new GrupoModuloPK();
		id.setNbModulo(nbModulo);
		List<GrupoModulo> grupoModulos = grupoModuloLocal.findAll(id);
		GrupoModuloDto out = null;
		SubGrupoModuloPK subGrupoModuloPK=null;
		for (GrupoModulo grupoModulo:grupoModulos){
			out = new GrupoModuloDto();
			out.setNbGrupoModulo(grupoModulo.getId().getNbGrupo());
			out.setNbModulo(grupoModulo.getId().getNbModulo());
			out.setNbModificar(grupoModulo.getNbModificar());
			subGrupoModuloPK = new SubGrupoModuloPK();
			subGrupoModuloPK.setNbModulo(nbModulo);
			subGrupoModuloPK.setNbGrupo(grupoModulo.getId().getNbGrupo());

//			if (subGrupoModuloLocal.findAll(subGrupoModuloPK)==null || subGrupoModuloLocal.findAll(subGrupoModuloPK).size()==0){
//				out.setDelete(true);
//			}
			
			outs.add(out);
		}
		return outs;
	}

	public GrupoModuloDto update(String nbModuloModificar, String nbModulo, String nbGrupoModulo) throws Exception {
		GrupoModuloDto out = null;
		/** Verificamos que no se repita el mismo nombre del grupo */
		GrupoModuloPK id = new GrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		GrupoModulo dao = grupoModuloLocal.findSameModificar(id, nbModuloModificar);
		if (null == dao) {
			dao = new GrupoModulo();
			id = new GrupoModuloPK();
			id.setNbModulo(nbModulo);
			id.setNbGrupo(nbGrupoModulo);
			dao.setId(id);
			dao = grupoModuloLocal.find(id);
			if (null != dao) {
				out = new GrupoModuloDto();
				dao.setNbModificar(nbModuloModificar);
				dao = grupoModuloLocal.update(dao);
				SubGrupoModuloPK subGrupoModuloPK = new SubGrupoModuloPK();
				subGrupoModuloPK.setNbModulo(nbModulo);
				subGrupoModuloPK.setNbGrupo(nbGrupoModulo);
//				if (subGrupoModuloLocal.findAll(subGrupoModuloPK)==null || subGrupoModuloLocal.findAll(subGrupoModuloPK).size()==0){
//					out.setDelete(true);
//				}
				out.setNbGrupoModulo(dao.getId().getNbGrupo());
				out.setNbModulo(dao.getId().getNbModulo());
				out.setNbModificar(dao.getNbModificar());
			}
		}
		return out;
	}

	public Boolean noExiste(String nbModuloModificar, String nbModulo, String nbGrupoModulo) throws Exception {
		/** Verificamos que no se repita el mismo nombre del grupo */
		GrupoModuloPK id = new GrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		GrupoModulo dao = grupoModuloLocal.findSameModificar(id, nbModuloModificar);

		return (dao == null);
	}

	public GrupoModuloDto delete(String nbModulo, String nbGrupoModulo) throws Exception {
		GrupoModuloDto out = null;
		/** Verificamos que no se repita el mismo nombre del grupo */
		GrupoModuloPK id = new GrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		GrupoModulo dao = grupoModuloLocal.find(id);
		if (null != dao) {
				out = new GrupoModuloDto();
				out.setNbGrupoModulo(dao.getId().getNbGrupo());
				out.setNbModulo(dao.getId().getNbModulo());
				out.setNbModificar(dao.getNbModificar());
				grupoModuloLocal.remove(dao);
		}
		return out;
		}

	public DeleteYesDto isDelete(String nbModulo,String nbGrupo) {
		DeleteYesDto deleteYesDto = new DeleteYesDto();
		SubGrupoModuloPK subGrupoModuloPK = new SubGrupoModuloPK();
		subGrupoModuloPK.setNbModulo(nbModulo);
		subGrupoModuloPK.setNbGrupo(nbGrupo);
		List<SubGrupoModulo>  lista=subGrupoModuloLocal.findAll(subGrupoModuloPK);
		if (lista == null
				|| lista.size() == 0) {
			deleteYesDto.setDeleteObj(true);
		}
		return deleteYesDto;
	}

}

package ve.org.bcv.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ve.org.bcv.dao.Actividad;
import ve.org.bcv.dao.ActividadPK;
import ve.org.bcv.dao.SubGrupoModulo;
import ve.org.bcv.dao.SubGrupoModuloPK;
import ve.org.bcv.dao.local.ActividadLocal;
import ve.org.bcv.dao.local.SubGrupoModuloLocal;
import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.SubGrupoModuloDto;
import ve.org.bcv.services.SubGrupoModuloService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 24 de ene. de 2017 2:31:36 p. m.
 *
 *         mail: oraclefedora@gmail.com
 */
@SubGrupoModuloServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SubGrupoModuloServiceImpl implements SubGrupoModuloService {
	@Inject
	private SubGrupoModuloLocal subGrupoModuloLocal;
    @Inject
    private ActividadLocal actividadLocal;
    
    
    
	public DeleteYesDto isDelete(String nbModulo,String nbGrupo,String nbSubGrupoModulo) {
		DeleteYesDto deleteYesDto = new DeleteYesDto();
		ActividadPK objPK = new ActividadPK();
		objPK.setNbModulo(nbModulo);
		objPK.setNbGrupo(nbGrupo);
		objPK.setNbSubGrupo(nbSubGrupoModulo);
		List<Actividad> lista=actividadLocal.findAll(objPK);
	 
		if (lista == null
				|| lista.size() == 0) {
			deleteYesDto.setDeleteObj(true);
		}
		return deleteYesDto;
	}

    
	public SubGrupoModuloDto save(String nbModulo, String nbGrupoModulo,String nbSubGrupoModulo) throws Exception {
		SubGrupoModuloDto out = new SubGrupoModuloDto();
		SubGrupoModulo obj = new SubGrupoModulo();
		obj.setNbModificar(nbSubGrupoModulo);
		SubGrupoModuloPK id = new SubGrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
		obj.setId(id);
		if (subGrupoModuloLocal.find(id)==null ){
			obj = subGrupoModuloLocal.save(obj);	
		}else{
			obj = subGrupoModuloLocal.saveAgain(nbModulo, nbGrupoModulo, nbSubGrupoModulo);
		}
		
		if (null != obj) {

			out.setNbGrupoModulo(obj.getId().getNbGrupo());
			out.setNbModulo(obj.getId().getNbModulo());
			out.setNbSubGrupoModulo(obj.getId().getNbSubGrupo());
			out.setNbModificar(obj.getNbModificar());
		}
		return out;
	}

    
	

	public List<SubGrupoModuloDto> findAll() throws Exception {
		List<SubGrupoModuloDto> outs = new ArrayList<SubGrupoModuloDto>();
		List<SubGrupoModulo> daos = subGrupoModuloLocal.findAll();
		SubGrupoModuloDto out = null;
		for (SubGrupoModulo dao : daos) {
			out = new SubGrupoModuloDto();
			out.setNbGrupoModulo(dao.getId().getNbGrupo());
			out.setNbModulo(dao.getId().getNbModulo());
			out.setNbModificar(dao.getNbModificar());
			outs.add(out);
		}
		return outs;
	}
	
	public List<SubGrupoModuloDto> findAll(String nbModulo,String nbGrupoModulo) throws Exception {
		List<SubGrupoModuloDto> outs = new ArrayList<SubGrupoModuloDto>();
		SubGrupoModuloPK id = new SubGrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		List<SubGrupoModulo> objs = subGrupoModuloLocal.findAll(id);
		SubGrupoModuloDto out = null;
		ActividadPK existePK=null;
		if (null!=objs){
			for (SubGrupoModulo obj:objs){
				out = new SubGrupoModuloDto();
				out.setNbModulo(obj.getId().getNbModulo());
				out.setNbGrupoModulo(obj.getId().getNbGrupo());
				out.setNbSubGrupoModulo(obj.getId().getNbSubGrupo());
				out.setNbModificar(obj.getNbModificar());
				existePK = new ActividadPK();
				existePK.setNbModulo(nbModulo);
				existePK.setNbGrupo(obj.getId().getNbGrupo());
				existePK.setNbSubGrupo(obj.getId().getNbSubGrupo());
			 
				outs.add(out);
			}
		}
		
		return outs;
	}
	
	

	public SubGrupoModuloDto update(String nbModuloModificar, String nbModulo, String nbGrupoModulo,
			String nbSubGrupoModulo) throws Exception {
		SubGrupoModuloDto out = null;
		SubGrupoModuloPK id = new SubGrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
		SubGrupoModulo dao = subGrupoModuloLocal.find(id);
		ActividadPK existePK=null;
		if (null != dao) {
			dao.setNbModificar(nbModuloModificar);
			dao = subGrupoModuloLocal.update(dao);
			out = new SubGrupoModuloDto();
			out.setNbGrupoModulo(dao.getId().getNbGrupo());
			out.setNbModulo(dao.getId().getNbModulo());
			out.setNbModificar(dao.getNbModificar());
			existePK = new ActividadPK();
			existePK.setNbModulo(nbModulo);
			existePK.setNbGrupo(dao.getId().getNbGrupo());
			existePK.setNbSubGrupo(dao.getId().getNbSubGrupo());
			 
		}
		return out;
	}

	 
	public Boolean noExiste(String nbModuloModificar, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo)
			throws Exception {
		/** Verificamos que no se repita el mismo nombre del grupo */
		SubGrupoModuloPK id = new SubGrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		SubGrupoModulo dao = subGrupoModuloLocal.findSameModificar(id, nbModuloModificar);

		return (dao == null);
	}

	
	public SubGrupoModuloDto delete(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo) throws Exception {
		SubGrupoModuloDto out = null;
		/** Verificamos que no se repita el mismo nombre del grupo */
		SubGrupoModuloPK id = new SubGrupoModuloPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
		SubGrupoModulo dao = subGrupoModuloLocal.find(id);
		if (null != dao) {
				out = new SubGrupoModuloDto();
				out.setNbGrupoModulo(dao.getId().getNbGrupo());
				out.setNbModulo(dao.getId().getNbModulo());
				out.setNbModificar(dao.getNbModificar());
				subGrupoModuloLocal.remove(dao);
		}
		return out;
		}
}

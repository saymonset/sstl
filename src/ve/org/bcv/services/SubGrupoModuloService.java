package ve.org.bcv.services;

import java.util.List;

import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.SubGrupoModuloDto;

public interface SubGrupoModuloService {
	SubGrupoModuloDto save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo)throws Exception;
	List<SubGrupoModuloDto> findAll()throws Exception;
	SubGrupoModuloDto update(String nbModuloModificar,String nbModulo,String nbGrupoModulo, String nbSubGrupoModulo)throws Exception;
	List<SubGrupoModuloDto> findAll(String nbModulo,String nbGrupoModulo) throws Exception ;
	Boolean noExiste(String nbModuloModificar, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo) throws Exception ;
	SubGrupoModuloDto delete(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo) throws Exception ;
	 DeleteYesDto isDelete(String nbModulo,String nbGrupo,String nbSubGrupoModulo) ;
	
}

package ve.org.bcv.services;

import java.util.List;

import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.GrupoModuloDto;

public interface GrupoModuloService { 	
	GrupoModuloDto save(String nbModulo,String nbGrupoModulo)throws Exception ;;
	List<GrupoModuloDto> findAll(String nbModulo)throws Exception;
	GrupoModuloDto update(String nbModuloModificar,String nbModulo,String nbGrupoModulo)throws Exception;
	public Boolean noExiste(String nbModuloModificar,String nbModulo, String nbGrupoModulo) throws Exception ;
	GrupoModuloDto delete(String nbModulo,String nbGrupoModulo)throws Exception;
	DeleteYesDto   isDelete(String nbModulo,String nbGrupo);  
}

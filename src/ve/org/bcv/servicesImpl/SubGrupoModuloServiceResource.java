package ve.org.bcv.servicesImpl;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.SubGrupoModuloDto;
import ve.org.bcv.services.SubGrupoModuloService;
@Path("/subGrupoModuloServiceResource")
@RequestScoped
@Named
public class SubGrupoModuloServiceResource implements Serializable {
	/**
	 * @Path("book")
       @RequestScoped
       http://localhost:8080/sstl/services/actividadCtrl/actividadesByModulo/gimnasio
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@SubGrupoModuloServiceType1
	private SubGrupoModuloService subGrupoModuloService;;

	
 
	
 
	
	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}")
	@Produces("application/json")
	public  List<SubGrupoModuloDto> listar(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo)
			throws URISyntaxException {
		List<SubGrupoModuloDto>  list=null;
		try {
			list=subGrupoModuloService.findAll(nbModulo,nbGrupoModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GET
	@Path("/isDdelete/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}")
	@Produces("application/json")
	public DeleteYesDto isDdelete(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo)
			throws URISyntaxException {
		DeleteYesDto dto = new DeleteYesDto();
		try {
			dto=subGrupoModuloService.isDelete( nbModulo,nbGrupoModulo,nbSubGrupoModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	  
	
	@PostConstruct
	void init() {
	
	}
	
	@DELETE
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}")
	@Produces("application/json")
	public SubGrupoModuloDto remove(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo)
			throws URISyntaxException {
		SubGrupoModuloDto dto = new SubGrupoModuloDto();
		try {
			dto=subGrupoModuloService.delete( nbModulo,nbGrupoModulo,nbSubGrupoModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	
 

	@POST
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}")
	@Produces("application/json")
	public  SubGrupoModuloDto save(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo)
			throws URISyntaxException {
		SubGrupoModuloDto dto =null;
		try {
			dto=subGrupoModuloService.save(nbModulo, nbGrupoModulo,nbSubGrupoModulo);
		} catch (Exception e) {
			e.printStackTrace();
			dto=null;
		}
		return dto;
	}


	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbModificar}")
	@Produces("application/json")
	public  SubGrupoModuloDto update(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbModificar") String nbModificar)
			throws URISyntaxException {
		SubGrupoModuloDto dto = null;
		try {
			dto=subGrupoModuloService.update(nbModificar,nbModulo, nbGrupoModulo,nbSubGrupoModulo );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	
	@GET
	@Path("/queryNoExiste/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbModificar}")
	@Produces("application/json")
	public  SubGrupoModuloDto queryNoExiste(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbModificar") String nbModificar)
			throws URISyntaxException {
		SubGrupoModuloDto obj = new SubGrupoModuloDto();
		try {
			//String nbModuloModificar, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo)
			obj.setModificable(subGrupoModuloService.noExiste(nbModificar,nbModulo, nbGrupoModulo,nbSubGrupoModulo ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	  

}

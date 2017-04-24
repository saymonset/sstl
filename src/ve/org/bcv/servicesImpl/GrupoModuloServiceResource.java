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
import ve.org.bcv.dto.GrupoModuloDto;
import ve.org.bcv.services.GrupoModuloService;
@Path("/grupoModuloServiceResource")
@RequestScoped
@Named
public class GrupoModuloServiceResource implements Serializable {
	/**
	 * @Path("book")
       @RequestScoped
       http://localhost:8080/sstl/services/actividadCtrl/actividadesByModulo/gimnasio
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@GrupoModuloServiceType1
	private GrupoModuloService grupoModuloService;;
	
 
	
 
	
	
	@GET
	@Path("/{nbModulo}")
	@Produces("application/json")
	public  List<GrupoModuloDto> listar(@PathParam("nbModulo") String nbModulo)
			throws URISyntaxException {
		List<GrupoModuloDto>  list=null;
		try {
			list=grupoModuloService.findAll(nbModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}")
	@Produces("application/json")
	public  DeleteYesDto isDelete(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo)
			throws URISyntaxException {
		DeleteYesDto deleteYesDto=null;
		try {
			deleteYesDto = grupoModuloService.isDelete(nbModulo,nbGrupoModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteYesDto;
	}
	
	
	  
	
	@PostConstruct
	void init() {
	
	}
	
	@DELETE
	@Path("/{nbModulo}/{nbGrupoModulo}")
	@Produces("application/json")
	public GrupoModuloDto remove(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo)
			throws URISyntaxException {
		GrupoModuloDto dto = new GrupoModuloDto();
		try {
			dto=grupoModuloService.delete( nbModulo,nbGrupoModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	

	@POST
	@Path("/{nbModulo}/{nbGrupoModulo}")
	@Produces("application/json")
	public  GrupoModuloDto save(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo)
			throws URISyntaxException {
		GrupoModuloDto dto =null;
		try {
			dto=grupoModuloService.save(nbModulo, nbGrupoModulo);
		} catch (Exception e) {
			e.printStackTrace();
			dto=null;
		}
		return dto;
	}


	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbModificar}")
	@Produces("application/json")
	public  GrupoModuloDto update(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbModificar") String nbModificar)
			throws URISyntaxException {
		GrupoModuloDto dto = null;
		try {
			dto=grupoModuloService.update(nbModificar,nbModulo, nbGrupoModulo );
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	
	@GET
	@Path("/queryNoExiste/{nbModulo}/{nbGrupoModulo}/{nbModificar}")
	@Produces("application/json")
	public  GrupoModuloDto queryNoExiste(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbModificar") String nbModificar)
			throws URISyntaxException {
		GrupoModuloDto grupoModuloDto = new GrupoModuloDto();
		try {
			grupoModuloDto.setModificable(grupoModuloService.noExiste(nbModificar,nbModulo, nbGrupoModulo ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grupoModuloDto;
	}

	  

}

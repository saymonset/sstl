/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import ve.org.bcv.dto.ActividadDto;
import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.services.ActividadService;
import ve.org.bcv.services.ModuloService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 30/06/2016 09:58:40
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/actividadServiceResource")
@RequestScoped
@Named
public class ActividadServiceResource  implements Serializable {
	/**
	 * @Path("book")
       @RequestScoped
       http://localhost:8080/sstl/services/actividadCtrl/actividadesByModulo/gimnasio
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@ActividadServicioType1
	private ActividadService actividadService;;
	
	@Inject
	private ModuloService moduloService= new ModuloServiceImpl() ;
	
 
	//:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo
	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}")
	@Produces("application/json")
	public  List<ActividadDto> listar(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo)
			throws URISyntaxException {
		List<ActividadDto>  list=new ArrayList<ActividadDto>();
		try {
			list=actividadService.findByActividadesByModulo(nbModulo,nbGrupoModulo,nbSubGrupoModulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GET
	@Path("/isDdelete/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}")
	@Produces("application/json")
	public DeleteYesDto isDdelete(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nbActividad") String nbActividad)
			throws URISyntaxException {
		DeleteYesDto dto = new DeleteYesDto();
		try {
			dto=actividadService.isDelete( nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad);
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
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}")
	@Produces("application/json")
	public ActividadDto remove(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad)
			throws URISyntaxException {
		ActividadDto dto = new ActividadDto();
		try {
			dto=actividadService.delete( nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	
//	url:'services/actividadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
	@POST
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}")
	@Produces("application/json")
	public  ActividadDto save(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad)
			throws URISyntaxException {
		ActividadDto dto =null;
		try {
			dto=actividadService.save(nbActividad,nbModulo,nbGrupoModulo,nbSubGrupoModulo);
		} catch (Exception e) {
			e.printStackTrace();
			dto=null;
		}
		return dto;
	}
 

	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbActividadModif}")
	@Produces("application/json")
	public  ActividadDto update(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad
			,@PathParam("nbActividadModif") String nbActividadModif)
			throws URISyntaxException {
		ActividadDto dto = new ActividadDto();
		try {
			dto=actividadService.update(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad, nbActividadModif );
			System.out.println("Fashion 2016/10/21");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	@GET
	@Path("/queryNoExiste/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}")
	@Produces("application/json")
	public  ActividadDto queryNoExiste(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad)
			throws URISyntaxException {
		ActividadDto obj = new ActividadDto();
		try {
			obj.setModificable(actividadService.noExiste(nbActividad,nbModulo, nbGrupoModulo,nbSubGrupoModulo ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}



	 


	public ModuloService getTipoAplicacionService() {
		return moduloService;
	}



	public void setTipoAplicacionService(ModuloService tipoAplicacionService) {
		this.moduloService = tipoAplicacionService;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

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

import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.HorarioNombreDto;
import ve.org.bcv.services.HorarioNombreService;
@Path("/horarioNombreServiceResource")
@RequestScoped
@Named
public class HorarioNombreServiceResource implements Serializable {
	/**
	 * @Path("book")
	 * 
	 * @RequestScoped
	 */
	private static final long serialVersionUID = 1L;


	@Inject
	@HorarioNombreServicioType1
	private HorarioNombreService horarioNombreService;;
	
 
	@GET
	@Path("/isDdelete/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public DeleteYesDto isDdelete(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		DeleteYesDto dto = new DeleteYesDto();
		try {
			dto=horarioNombreService.isDelete( nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{actividad}")
	@Produces("application/json")
	public  List<HorarioNombreDto> listar(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("actividad") String actividad)
			throws URISyntaxException {
		List<HorarioNombreDto>  list=new ArrayList<HorarioNombreDto>();
		try {
			list=horarioNombreService.findByActividadesByModulo(nbModulo,nbGrupoModulo,nbSubGrupoModulo,actividad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	  
	
	@PostConstruct
	void init() {
	
	}
	
	
	//	            	url:'services/horarioNombreServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
	@DELETE
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public HorarioNombreDto remove(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		HorarioNombreDto dto = new HorarioNombreDto();
		try {
			dto=horarioNombreService.delete( nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	
	@POST
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public  HorarioNombreDto save(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad
			,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		HorarioNombreDto dto =null;
		try {
			dto=horarioNombreService.save(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario);
		} catch (Exception e) {
			e.printStackTrace();
			dto=null;
		}
		return dto;
	}
 

	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{nbHorarioModif}")
	@Produces("application/json")
	public  HorarioNombreDto update(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad
			,@PathParam("nbHorario") String nbHorario,@PathParam("nbHorarioModif") String nbHorarioModif)
			throws URISyntaxException {
		HorarioNombreDto dto =null;
		try {
			dto=horarioNombreService.update(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad, nbHorario,nbHorarioModif );
			System.out.println("Fashion 2016/10/21");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	@GET
	@Path("/queryNoExiste/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public  HorarioNombreDto queryNoExiste(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad
			,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		HorarioNombreDto obj = new HorarioNombreDto();
		try {
			obj.setModificable(horarioNombreService.noExiste(nbModulo, nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}



	 

 



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

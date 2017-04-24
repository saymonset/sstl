/**
 * 
 */
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.HorarioDto;
import ve.org.bcv.services.HorarioService;
import ve.org.bcv.services.ModuloService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 07/06/2016 09:55:39 2016 mail :
 *         oraclefedora@gmail.com
 */
@Path("/horarioServiceResource")
@RequestScoped
@Named
public class HorarioServiceResource implements Serializable {
	/**
	 * @Path("book")
	 * 
	 * @RequestScoped
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@HorarioServicioType1
	private HorarioService horarioService;;

	@Inject
	private ModuloService moduloService = new ModuloServiceImpl();

	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}")
	@Produces("application/json")
	public List<HorarioDto> findByModuloActividad(@PathParam("nbModulo") String nbModulo,
			@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario) {

		List<HorarioDto> list = horarioService.findByModuloActividad(  nbModulo,   nbGrupoModulo,   nbSubGrupoModulo,
				nb_actividad,nbHorario);
			
		return list;
	}

	@GET
	@Path("/findByModuloActividadMoveDisabled/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}")
	@Produces("application/json")
	public List<HorarioDto> findByModuloActividadMoveDisabled(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario) {

		List<HorarioDto> list = horarioService.findByModuloActividad(nbModulo, nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario);

		return list;
	}

	@PostConstruct
	void init() {

	}

	@DELETE
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{text}/{id}")
	@Produces("application/json")
	public HorarioDto remove(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario, @PathParam("text") String text, @PathParam("id") String id)
			throws URISyntaxException {
		HorarioDto horarioDto = null;
		try {

			horarioDto = horarioService.delete(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nb_actividad, nbHorario, id,
					text);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return horarioDto;
	}

	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{text}/{id}/{start}/{end}")
	@Produces("application/json")
	public HorarioDto update(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario, @PathParam("text") String text, @PathParam("id") String id,
			@PathParam("start") String start, @PathParam("end") String end) throws URISyntaxException {
		HorarioDto horarioDto = null;
		try {

			horarioDto = horarioService.save(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nb_actividad, nbHorario, id,
					text, start, end);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return horarioDto;
	}

	public HorarioService getHorarioService() {
		return horarioService;
	}

	public void setHorarioService(HorarioService horarioService) {
		this.horarioService = horarioService;
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

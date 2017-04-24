/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.SeguridadDto;
import ve.org.bcv.services.SeguridadService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 15:23:30
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/seguridadServiceResource")
@RequestScoped
@Named
public class SeguridadServiceResource  implements Serializable {
	@Inject
	@SeguridadServiceType1
	private SeguridadService seguridadService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public  SeguridadDto fechaRenovacion(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		SeguridadDto dto = new SeguridadDto();
		
		dto=seguridadService.find(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario);
		return dto;
	}
}

package ve.org.bcv.servicesImpl;

import java.net.URISyntaxException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.AccesoDirectoDto;
import ve.org.bcv.services.AccesoDirectoService;

@Path("/accesoDirectoService")
@RequestScoped
@Named
public class AccesoDirectoServiceResource {

	@Inject
	@AccesoDirectoServiceType1
	private AccesoDirectoService accesoDirectoService;
	
	@GET
	@Path("/{cedula}")
	@Produces("application/json")
	public List<AccesoDirectoDto>  findAll(@PathParam("cedula") String cedula)
			throws URISyntaxException {
		
		List<AccesoDirectoDto> objs=accesoDirectoService.findAll(cedula);
		 
		return objs;
	}
}

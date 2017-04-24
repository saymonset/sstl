package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.ModuloDto;
import ve.org.bcv.services.ModuloService;

@Path("/moduloServiceResource")
@RequestScoped
@Named
public class ModuloServiceResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ModuloService moduloService;
	
	 
	
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<ModuloDto>  findAll()
			throws URISyntaxException {
		
		List<ModuloDto> objs=moduloService.findAll();
		 
		return objs;
	}
	
	
	
}
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

import ve.org.bcv.dto.ParametroDto;
import ve.org.bcv.services.ParametroService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 14:37:36
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/parametroServiceResource")
@RequestScoped
@Named
public class ParametroServiceResource implements Serializable {
	
	

	@Inject
	@ParametroServicioType1
	private ParametroService parametroService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{txNombreParametro}")
	@Produces("application/json")
	public  ParametroDto get(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,@PathParam("nbActividad") String nbActividad
			,@PathParam("nbHorario") String nbHorario,@PathParam("txNombreParametro") String txNombreParametro)
			throws URISyntaxException {
		ParametroDto  parametroDto=null;
		try {
			 parametroDto= parametroService.find(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario,txNombreParametro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parametroDto;
	}

}

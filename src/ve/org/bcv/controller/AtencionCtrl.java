/**
 * 
 */
package ve.org.bcv.controller;

import java.io.Serializable;
import java.net.URISyntaxException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.AtencionDto;
import ve.org.bcv.services.AsistenciaService;
import ve.org.bcv.services.AtencionService;
import ve.org.bcv.servicesImpl.AsistenciaServicioType1;
import ve.org.bcv.servicesImpl.AtencionServicioType1;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 26/07/2016 11:26:16
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/atencionCtrl")
@RequestScoped
@Named
public class AtencionCtrl  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	@AtencionServicioType1
	private AtencionService atencionService ;;
	@Inject
	@AsistenciaServicioType1
	private AsistenciaService asistenciaService ;;
	@POST
	@Path("/{text}/{id}/{start}/{end}/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{cedula}/{atencionRealizada}")
	@Produces("application/json")
	public AtencionDto update(@PathParam("text") String text,@PathParam("id") String id,@PathParam("start") String start,@PathParam("end") String end,
			@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario
			,@PathParam("cedula") String cedula,@PathParam("atencionRealizada") String atencionRealizada)
			throws URISyntaxException {
		AtencionDto dto = new AtencionDto();
	
		try {
			dto=atencionService.save(text, id,start,end,
					nbModulo, nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario, cedula,atencionRealizada);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 
		return dto;
	}


}

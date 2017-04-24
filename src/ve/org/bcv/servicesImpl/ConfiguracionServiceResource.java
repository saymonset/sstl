/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.ParametroDto;
import ve.org.bcv.services.ParametroService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 02/08/2016 14:08:04 2016 mail :
 *         oraclefedora@gmail.com
 */
@Path("/configuracionServiceResource")
@RequestScoped
@Named
public class ConfiguracionServiceResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@ParametroServicioType1
	private ParametroService parametroService;

	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public List<ParametroDto> listar(@PathParam("nbModulo") String nbModulo,
			@PathParam("nbGrupoModulo") String nbGrupoModulo, @PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad, @PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		List<ParametroDto> list = null;
		try {
			list = parametroService.findAll(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return list;
	}

	@GET
	@Path("/findParametro/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{txNombreParametro}")
	@Produces("application/json")
	public ParametroDto findParametro(@PathParam("nbModulo") String nbModulo,
			@PathParam("nbGrupoModulo") String nbGrupoModulo, @PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad, @PathParam("nbHorario") String nbHorario,
			@PathParam("txNombreParametro") String txNombreParametro) throws URISyntaxException {
		ParametroDto param = null;
		;
		try {
			param = parametroService.find(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
					txNombreParametro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return param;
	}

	//

	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{txNombreParametro}/{txValorParametro}/{txObservaciones}/{tipoParametro}/{personalizar_horario}")
	@Produces("application/json")
	public ParametroDto update(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nbActividad") String nbActividad,
			@PathParam("nbHorario") String nbHorario, @PathParam("txNombreParametro") String txNombreParametro,
			@PathParam("txValorParametro") String txValorParametro,
			@PathParam("txObservaciones") String txObservaciones, @PathParam("tipoParametro") String tipoParametro,@PathParam("personalizar_horario") boolean personalizarHorario)
			throws URISyntaxException {
		ParametroDto dto = null;
		try {
			dto = parametroService.update(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
					txNombreParametro, txValorParametro, txObservaciones, tipoParametro,personalizarHorario);
		} catch (Exception e) {
			// e.printStackTrace();
			dto = null;
		}
		return dto;
	}

}

/**
 * 
 */
package ve.org.bcv.controller;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.ActividadDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.services.ActividadService;
import ve.org.bcv.services.AtencionService;
import ve.org.bcv.servicesImpl.ActividadServicioType1;
import ve.org.bcv.servicesImpl.AtencionServicioType1;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 27/07/2016 09:29:15
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/estadisticaCtrl")
@RequestScoped
@Named
public class EstadisticaCtrl {
	@Inject
	@AtencionServicioType1
	private AtencionService atencionService ;;
	
	@Inject
	@ActividadServicioType1
	private ActividadService actividadService ;;
	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}")
	@Produces("application/json")
	public ChartDto chart(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		ChartDto dto = new ChartDto();
		
		
		dto=atencionService.findByModuloActivdadStadistica(nbModulo,nbGrupoModulo,nbSubGrupoModulo,	nb_actividad,nbHorario) ;
		if (dto==null){
			 dto = new ChartDto();
		}
		return dto;
	}

	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{cedula}")
	@Produces("application/json")
	public ChartDto chartByUsuario(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula)
			throws URISyntaxException {
		ChartDto dto = new ChartDto();
		
		
		dto=atencionService.findByModuloActivdadStadistica(nbModulo,nbGrupoModulo,nbSubGrupoModulo,	nb_actividad,nbHorario,cedula) ;
		if (dto==null){
			 dto = new ChartDto();
		}
		return dto;
	}
	
	@GET
	@Path("estadisticasByModuloActividad/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public MiembroChartDto estadisticasByModuloActividad(@PathParam("nbModulo") String nbModulo, @PathParam("nbGrupoModulo") String nbGrupoModulo,
			@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo, @PathParam("nb_actividad") String nb_actividad,
			@PathParam("nbHorario") String nbHorario,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		MiembroChartDto dto = new MiembroChartDto();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr=sdf.format(feDesde);
		String feHastaStr=sdf.format(feHasta);
		dto=atencionService.findByEstadisticasByModulo(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario,feDesdeStr,feHastaStr) ;
		if (dto==null){
			 dto = new MiembroChartDto();
		}
		return dto;
	}
	
	
	
	@GET
	@Path("buscarActividadesByModulo/{modulo}")
	@Produces("application/json")
	public List<ActividadDto>  buscarActividadesByModulo(@PathParam("modulo") String modulo)
			throws URISyntaxException {
		
		 List<ActividadDto> objs=actividadService.findByActividadesByModulo(modulo) ;
		 
		return objs;
	}
	
}

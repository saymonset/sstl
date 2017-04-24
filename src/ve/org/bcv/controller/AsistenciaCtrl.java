/**
 * 
 */
package ve.org.bcv.controller;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;
import ve.org.bcv.services.ActividadService;
import ve.org.bcv.services.AsistenciaService;
import ve.org.bcv.services.AtencionService;
import ve.org.bcv.services.MiembroHorarioService;
import ve.org.bcv.services.MiembroService;
import ve.org.bcv.servicesImpl.ActividadServicioType1;
import ve.org.bcv.servicesImpl.AsistenciaServicioType1;
import ve.org.bcv.servicesImpl.AtencionServicioType1;
import ve.org.bcv.servicesImpl.MiembroHorarioServiceType1;
import ve.org.bcv.servicesImpl.MiembroServicioType1;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 09:53:13
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/asistenciaCtrl")
@RequestScoped
@Named
public class AsistenciaCtrl  implements Serializable {

	@Inject
	@MiembroHorarioServiceType1
	private MiembroHorarioService miembroHorarioService;
	
	@Inject
	@ActividadServicioType1
	private ActividadService actividadService ;;
	@Inject
	@AtencionServicioType1
	private AtencionService atencionService ;;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@MiembroServicioType1
	private MiembroService miembroService;
	@Inject
	@AsistenciaServicioType1
	private AsistenciaService asistenciaService;
	

	 
	/*******-------niicio-------*********/


	//	url:'services/asistenciaCtrl/buscarByModuloCedula/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula/:feDesde/:feHasta',
	@GET
	@Path("buscarByModuloCedula/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{cedula}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public MiembroChartDto buscarByModuloCedula(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		MiembroChartDto dto = new MiembroChartDto();
		 
		dto=asistenciaService.buscarByModuloCedula(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario,cedula,feDesde,feHasta) ;
		if (dto==null){
			 dto = new MiembroChartDto();
		}
		return dto;
	}
	
	
			/***
			 * Todo usuario debe tener su horario de cada modulo antes de tener una tencion o asistencia en la tabla miembro_horario
			 *  Saco todos los miembros de la tabla miembro_horario 
                correspondiente al modulo y la fecha
                Tengo cedula, Modulo,actividad, co_horario, fecha_fin, fecha_inicio
			 * ***/
		 
			// TODO Auto-generated catch block



	
	@GET
	@Path("buscarMiembrosByModulo/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}")
	@Produces("application/json")
	public List<MiembroDto>  buscarMiembrosByModulo(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad)
			throws URISyntaxException {
		
		List<MiembroDto>  list=null;
		try {
			list=miembroService.findMiembrosByActMod(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		 
	}
	
//
//	@GET
//	@Path("byModulo/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{feDesde}/{feHasta}")
//	@Produces("application/json")
//	public ChartDto byModulo(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
//			@PathParam("nbActividad") String nbActividad,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
//			throws URISyntaxException {
//		ChartDto dto = new ChartDto();
//		dto=	asistenciaService.findAsistenciaByModulo(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,feDesde,feHasta);
//		if (dto==null){
//			 dto = new ChartDto();
//		}
//		return dto;
//	}
	
	 
	
//	
//	@GET
//	@Path("asistenciaByModulo/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{feDesde}/{feHasta}")
//	@Produces("application/json")
//	public List<MiembroDto> asistenciaByModulo(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
//			@PathParam("nbActividad") String nbActividad,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
//			throws URISyntaxException {
//		List<MiembroDto> miembroDtos=null;
//		try {
//			/***
//			 * Todo usuario debe tener su horario de cada modulo antes de tener una atencion o asistencia en la tabla miembro_horario
//			 *  Saco todos los miembros de la tabla miembro_horario 
//                correspondiente al modulo y la fecha
//                Tengo cedula, Modulo,actividad, co_horario, fecha_fin, fecha_inicio
//			 * ***/
//		//	miembroDtos = asistenciaService.findMiembrosAsistenciaByModulo(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad, feDesde, feHasta);
//		 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return miembroDtos;
//	}
	
	
}
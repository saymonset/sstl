/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.ActividadDto;
import ve.org.bcv.dto.AtencionDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.HorarioDto;
import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;
import ve.org.bcv.dto.MiembroHorarioDto;
import ve.org.bcv.services.ActividadService;
import ve.org.bcv.services.AtencionService;
import ve.org.bcv.services.MiembroHorarioService;
import ve.org.bcv.services.MiembroService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 25/07/2016 09:53:13
 * 2016
 * mail : oraclefedora@gmail.com
 */
@Path("/miembroServiceResource")
@RequestScoped
@Named
public class MiembroServiceResource  implements Serializable {

	@Inject
	@MiembroHorarioServiceType1
	private MiembroHorarioService miembroHorarioService;
	
	@Inject
	@ActividadServicioType1
	private ActividadService actividadService ;;
	
	@Inject
	@AtencionServicioType1
	private AtencionService atencionService;;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@MiembroServicioType1
	private MiembroService miembroService;
	

	
	
	@GET
	@Path("/findUserFromPersonalTdoEmpleados/{cedula}")
	@Produces("application/json")
	public  MiembroDto findUserFromPersonalTdoEmpleados(@PathParam("cedula") String cedula)
			throws URISyntaxException {
		MiembroDto  obj=null;
		try {
			if (null!=cedula){
				obj=miembroService.findUserFromPersonalTdoEmpleados(cedula);
			}
			//System.out.println(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	@GET
	@Path("/findByCedula/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{cedula}")
	@Produces("application/json")
	public  MiembroDto findByCedula(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula)
			throws URISyntaxException {
		MiembroDto  obj=null;
		try {
			obj=miembroService.findMiembroByCedula(nbModulo,nbGrupoModulo,nbSubGrupoModulo,
					nbActividad,nbHorario, cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	
	@GET
	@Path("/findHorarioSinColorByCedula/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}/{cedula}")
	@Produces("application/json")
	public  List<MiembroHorarioDto> findHorarioSinColorByCedula(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula)
			throws URISyntaxException {
		List<MiembroHorarioDto> objs=null;
		
		try {
			objs=miembroHorarioService.findSinColorHorarioByCedula(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario, cedula);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objs;
	}
	
	@GET
	@Path("/findHorarioByCedula/{modulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{cedula}")
	@Produces("application/json")
	public  List<MiembroHorarioDto> findHorarioByCedula(@PathParam("modulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula)
			throws URISyntaxException {
		List<MiembroHorarioDto> objs=null;
		
		try {
			objs=miembroHorarioService.findHorarioByCedula(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nb_actividad,nbHorario, cedula);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objs;
	}
	
	
	 
	
	
	
	
	@GET
	@Path("/moduloFechaDesdeHasta/{modulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public  List<MiembroDto> moduloFechaDesdeHasta(@PathParam("modulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		List<MiembroDto>  list=null;
		try {
			
			list=miembroService.moduloActividadDesdeHasta(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,null,feDesde,feHasta);
			
//			
//			moduloActividadDesdeHasta(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
//					String nbActividad,String nbHorario, Date feDesdeStr, Date feHastaStr) 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GET
	@Path("/moduloActividadFechaDesdeHasta/{modulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public  List<MiembroDto> moduloActividadFechaDesdeHasta(@PathParam("modulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		List<MiembroDto>  list=null;
		try {
			
			list=miembroService.moduloActividadDesdeHasta(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario,feDesde,feHasta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GET
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nbActividad}/{nbHorario}")
	@Produces("application/json")
	public  List<MiembroDto> listar(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo,
			@PathParam("nbActividad") String nbActividad,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		List<MiembroDto>  list=null;
		try {
			list=miembroService.findMiembrosByActMod(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	///:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario

	@PUT
	@Path("/fechaRenovacion/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}")
	@Produces("application/json")
	public  MiembroDto fechaRenovacion(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		MiembroDto miembroDto = new MiembroDto();
		String fechaRenovacion=null;
		try {
			fechaRenovacion=miembroService.fechaRenovacion(nbModulo, nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		miembroDto.setFechaStr(fechaRenovacion);
		return miembroDto;
	}
	//
	
	
	@GET
	@Path("/listar/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}")
	@Produces("application/json")
	public  List<MiembroDto> listarGet(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario)
			throws URISyntaxException {
		List<MiembroDto>  list=null;
		try {
			list=miembroService.findMiembrosByActMod(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nb_actividad,nbHorario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
 
 
	
	@PUT
	@Path("/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{cedula}")
	@Produces("application/json")
	public  MiembroDto save(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula)
			throws URISyntaxException {
		MiembroDto dto =null;
		try {
			dto=miembroService.save(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nb_actividad,nbHorario,cedula);
		} catch (Exception e) {
			//e.printStackTrace();
			dto=null;
		}
		return dto;
	}
	
	
	@DELETE
	@Path("/delete/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{cedula}")
	@Produces("application/json")
	public MiembroDto delete(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula)
			throws URISyntaxException {
		MiembroDto dto = new MiembroDto();
		try {
			dto=miembroService.delete( nbModulo,nbGrupoModulo,nbSubGrupoModulo, nb_actividad,nbHorario,cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	
	@PUT
	@Path("/saveInscripcion")
	@Consumes("application/json")
	@Produces("application/json")
	public  MiembroDto saveInscripcion(MiembroDto miembroDto)
			throws URISyntaxException {
		
		MiembroDto dto =null;
		try {
			
				
			dto=miembroService.saveInscripcion(miembroDto.getNbModulo(),miembroDto.getNbGrupo(),miembroDto.getNbSubGrupo(),miembroDto.getNbActividad(),miembroDto.getNbHorario()
					,miembroDto.getCedula(),miembroDto.getNombre(),
					miembroDto.getTipoEmp(),miembroDto.isRenovable(),miembroDto.isProfesor(),miembroDto.getFechaStr(),miembroDto.getHorarioDtos());
		
			 
			
			
			if (dto!=null&& miembroDto.getHorarioDtos()!=null && miembroDto.getHorarioDtos().size()>0){
				
				for(HorarioDto horario:miembroDto.getHorarioDtos()){
					
					miembroHorarioService.save(miembroDto.getNbModulo(),miembroDto.getNbGrupo(),miembroDto.getNbSubGrupo(),miembroDto.getNbActividad(),miembroDto.getNbHorario()
							,miembroDto.getCedula(), horario.getId(), horario.getStart(), horario.getEnd(), horario.getText());		
				}
				
			} 
			
			
			
		} catch (Exception e) {
			//e.printStackTrace();
			dto=null;
		}
		return dto;
	}
	//:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario
	@PUT
	@Path("/updateMiembro/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{cedula}/{nombre}/{tipoEmp}/{renovable}/{profesor}/{feRenovacion}")
	@Produces("application/json")
	public  MiembroDto updateMiembro(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("cedula") String cedula,
			@PathParam("nombre") String nombre,@PathParam("tipoEmp") String tipoEmp,@PathParam("renovable") Boolean renovable,@PathParam("profesor") Boolean profesor
			,@PathParam("feRenovacion") Date feRenovacion
		)
			throws URISyntaxException {
		//	
		MiembroDto dto =null;
		try {            
			dto=miembroService.update(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario,cedula,nombre,tipoEmp,renovable,profesor,feRenovacion);
			
		} catch (Exception e) {
			//e.printStackTrace();
			dto=null;
		}
		return dto;
	}
	
	
	
	@PUT
	@Path("/deletesMiembros")
	@Consumes("application/json")
	@Produces("application/json")
	public List<MiembroDto> deletesMiembros(List<MiembroDto> miembroDtos)
			throws URISyntaxException {
		List<MiembroDto> dtoDeletes= new ArrayList<MiembroDto>();
		for (MiembroDto dto: miembroDtos){
			try {
				
				dto=miembroService.delete(dto.getNbModulo(),dto.getNbGrupo(),dto.getNbSubGrupo(),dto.getNbActividad(),dto.getNbHorario(),dto.getCedula());
				
				try {
					List<MiembroHorarioDto> miembrosHorarioDeleteDtos= miembroHorarioService.deleteHorarioByCedula(dto.getNbModulo(),dto.getNbGrupo(),dto.getNbSubGrupo(),dto.getNbActividad(),dto.getNbHorario(),dto.getCedula());	
					
					List<AtencionDto> atencionDtos= atencionService.deleteAtencionByCedula(dto.getNbModulo(),dto.getNbGrupo(),dto.getNbSubGrupo(),dto.getNbActividad(),dto.getNbHorario(),dto.getCedula());
					
				} catch (Exception e) {
                     e.printStackTrace();
				}
				 
 
				
				
				
				dtoDeletes.add(dto);
				
			} catch (Exception e) {
				//e.printStackTrace();
				dto=null;
			}	
		}
		
		return dtoDeletes;
	}
	
	
	
	@GET
	@Path("estadisticasByModulo/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public ChartDto estadisticasByModulo(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		ChartDto dto = new ChartDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr=sdf.format(feDesde);
		String feHastaStr=sdf.format(feHasta);
		dto=miembroService.findByEstadisticasByModulo(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,feDesde,feHasta) ;
		if (dto==null){
			 dto = new ChartDto();
		}
		return dto;
	}
	
	
	@GET
	@Path("estadisticasGetMiembroChart/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public MiembroChartDto estadisticasGetMiembroChart(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		MiembroChartDto dto = new MiembroChartDto();
		 
		dto=miembroService.estadisticasGetMiembroChart(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario,feDesde,feHasta) ;
		if (dto==null){
			 dto = new MiembroChartDto();
		}
		return dto;
	}
	
	@GET
	@Path("estadisticasByModuloActividad/{nbModulo}/{nbGrupoModulo}/{nbSubGrupoModulo}/{nb_actividad}/{nbHorario}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public ChartDto estadisticasByModuloActividad(@PathParam("nbModulo") String nbModulo,@PathParam("nbGrupoModulo") String nbGrupoModulo,@PathParam("nbSubGrupoModulo") String nbSubGrupoModulo
			,@PathParam("nb_actividad") String nb_actividad,@PathParam("nbHorario") String nbHorario,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta)
			throws URISyntaxException {
		ChartDto dto = new ChartDto();
		 
		dto=miembroService.estadisticasByModuloActividad(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nb_actividad,nbHorario,feDesde,feHasta) ;
		if (dto==null){
			 dto = new ChartDto();
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
/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ve.org.bcv.dto.HorarioDto;
import ve.org.bcv.dto.MiembroDto;
import ve.org.bcv.dto.ParametroDto;
import ve.org.bcv.dto.SeguridadDto;
import ve.org.bcv.services.HorarioService;
import ve.org.bcv.services.MiembroService;
import ve.org.bcv.services.ParametroService;
import ve.org.bcv.services.SeguridadService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 04/08/2016 15:28:14
 * 2016
 * mail : oraclefedora@gmail.com
 */
@SeguridadServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SeguridadServiceImpl implements SeguridadService {
	@Inject
	@ParametroServicioType1
	private ParametroService parametroService;
	@Inject
	@MiembroServicioType1
	private MiembroService miembroService;

	@Inject
	@HorarioServicioType1
	private HorarioService horarioService ;;
    
	
	private int numerocupos;
    private List<MiembroDto>  miembrosDtos;
	public SeguridadDto find(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario) {
		
		SeguridadDto seguridadDto = new SeguridadDto();
		
		ParametroDto  parametroDto=null;
		/***Buscamos el numero de cupos*/
		try {
			 parametroDto= parametroService.find(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbHorario,"numerocupos");
			 String numerocuposStr=parametroDto.getTxValorParametro();
			 if (numerocuposStr!=null && numerocuposStr.length()>0){
				 try {
					 numerocupos = new Integer(numerocuposStr);
				} catch (Exception e) {
					// TODO: handle exception
				}
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**Buscamos los miembros*/
		
		
		try {
			miembrosDtos=miembroService.findMiembrosByActMod(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**Buscamos los horarios*/
		List<HorarioDto> horarios= horarioService.findByModuloActividad(nbModulo,nbGrupoModulo,nbSubGrupoModulo,
				nbActividad,nbHorario);
		seguridadDto.setHorarioShow(true);
//		if (horarios!=null && horarios.size()>0){
//			
//		}
		
		if (miembrosDtos!=null && miembrosDtos.size()>0){
			seguridadDto.setMiembroShow(true);
			seguridadDto.setAtencionShow(true);
			numerocupos -=miembrosDtos.size();
		}
		
		if (horarios!=null && horarios.size()>0 && numerocupos>=1){
			seguridadDto.setInscripcionShow(true);
		}
		//miembros.length>=numeroCupos
		
		return seguridadDto;
	}

}


/***
 * 
 *
 * $scope.numeroCupos=0;
    
    $timeout(function() {
		var url = baseUrl + "/parametroCtrl/" + $scope.modulo+"/"+$scope.actividad+"/numerocupos";
		$http.post(url).success(
				function(data) {
					$scope.numeroCupos=data.txValorParametro;
				});
	}, 50);
   
    

	 
    
	$timeout(function() {
		var url = baseUrl + "/miembroCtrl/listar/" + $scope.modulo+"/"+$scope.actividad;
		$http.post(url).success(
				function(data) {
					$scope.miembros=data;
				});
	}, 50);
 * 
 * */
 
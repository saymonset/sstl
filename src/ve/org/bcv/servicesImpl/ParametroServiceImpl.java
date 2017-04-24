/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import ve.org.bcv.dao.AccesoDirecto;
import ve.org.bcv.dao.AccesoDirectoPK;
import ve.org.bcv.dao.Parametro;
import ve.org.bcv.dao.local.AccesoDirectoLocal;
import ve.org.bcv.dao.local.ParametroLocal;
import ve.org.bcv.dto.ParametroDto;
import ve.org.bcv.services.ParametroService;
import ve.org.bcv.util.ParametrosConstantes;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 02/08/2016 09:20:54 2016 mail :
 *         oraclefedora@gmail.com
 */
@ParametroServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

public class ParametroServiceImpl implements ParametroService {

	@Inject
	private ParametroLocal parametroLocal;
	@Inject 
	private AccesoDirectoLocal accesoDirectoLocal;

	public ParametroDto save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,String txNombreParametro,String txValorParametro, String txObservaciones,String tipoParametro,	boolean personalizarHorario ) {
		Parametro dao = parametroLocal.save(  nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,txNombreParametro,
				txValorParametro, txObservaciones,tipoParametro,personalizarHorario);

		ParametroDto dto = new ParametroDto();
		if (dao != null) {
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setTxNombreParametro(dao.getId().getTxNombreParametro());
				dto.setTxValorParametro(dao.getTxValorParametro());
				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setTxObservaciones(dao.getTxObservaciones());
				dto.setTipoParametro(dao.getTipoParametro());
				try {
					dto.setPersonalizarHorario(dao.isPersonalizarHorario());	
				} catch (Exception e) {
					dto.setPersonalizarHorario(false);
				}
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dto;
	}

	public ParametroDto update(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,
			String txNombreParametro, String txValorParametro,
			String txObservaciones,String tipoParametro,boolean personalizarHorario) {

		Parametro dao = parametroLocal.update(nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,nbHorario,
				txNombreParametro, txValorParametro, txObservaciones,tipoParametro,personalizarHorario);
		
		if (ParametrosConstantes.txNombreParametroAccesoDirectoInscripcion.equalsIgnoreCase(txNombreParametro)){
			AccesoDirectoPK accesoDirectoPK = new AccesoDirectoPK();
			accesoDirectoPK.setNbActividad(nbActividad);
			accesoDirectoPK.setNbGrupo(nbGrupoModulo);
			accesoDirectoPK.setNbSubGrupo(nbSubGrupoModulo);
			accesoDirectoPK.setNbModulo(nbModulo);
			accesoDirectoPK.setNbHorario(nbHorario);
			AccesoDirecto accesoDirecto = accesoDirectoLocal.find(accesoDirectoPK);
			if (null==accesoDirecto ){
				accesoDirecto = new AccesoDirecto();
				accesoDirectoPK = new AccesoDirectoPK();
				accesoDirectoPK.setNbActividad(nbActividad);
				accesoDirectoPK.setNbGrupo(nbGrupoModulo);
				accesoDirectoPK.setNbSubGrupo(nbSubGrupoModulo);
				accesoDirectoPK.setNbModulo(nbModulo);
				accesoDirectoPK.setNbHorario(nbHorario);
				accesoDirecto.setId(accesoDirectoPK);
				accesoDirecto.setStatus(true);
				accesoDirecto.setPersonalizarHorario(personalizarHorario);
				accesoDirecto.setNbAccesoDirecto(txObservaciones);
				accesoDirectoLocal.save(accesoDirecto);	
			}else{
				/**Es un NO*/
				if (ParametrosConstantes.txValorParametroAccesoDirectoInscripcion.equals(txValorParametro)){
					accesoDirectoLocal.remove(accesoDirecto);
				}else{
					/**Es un SI actualizamos*/
					accesoDirecto.setPersonalizarHorario(personalizarHorario);
					accesoDirecto.setNbAccesoDirecto(txObservaciones);
					accesoDirectoLocal.update(accesoDirecto);
				}
			}
			
		}
		
		ParametroDto dto = new ParametroDto();
		if (dao != null) {
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setTxNombreParametro(dao.getId().getTxNombreParametro());
				dto.setTxValorParametro(dao.getTxValorParametro());
				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setTxObservaciones(dao.getTxObservaciones());
				dto.setTipoParametro(dao.getTipoParametro());
				try {
					dto.setPersonalizarHorario(dao.isPersonalizarHorario());	
				} catch (Exception e) {
					dto.setPersonalizarHorario(false);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dto;
	}

	public List<ParametroDto> findAll(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario) {
		
		
	
		
        /******Actualizamos la data de los parametros**********/		
		actualizarParametros(    nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario);
		
		List<Parametro>  parametros=parametroLocal.findAll(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario);
		List<ParametroDto> lista= new ArrayList<ParametroDto>();
		if (parametros != null && parametros.size() > 0) {
			ParametroDto dto = null;
			for (Parametro dao : parametros) {
				dto = new ParametroDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setTxNombreParametro(dao.getId().getTxNombreParametro());
					dto.setTxValorParametro(dao.getTxValorParametro());
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setTxObservaciones(dao.getTxObservaciones());
					dto.setTipoParametro(dao.getTipoParametro());
					try {
						dto.setPersonalizarHorario(dao.isPersonalizarHorario());	
					} catch (Exception e) {
						dto.setPersonalizarHorario(false);
					}
					lista.add(dto);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
		return lista;
	}

	
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public void actualizarParametros(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario) {
		
		boolean personalizarHorario = false;
		
		Parametro parametro= null;
		parametro=parametroLocal.save( nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,ParametrosConstantes.txNombreParametroNumerocupos,
				ParametrosConstantes.txValorParametroNumerocupos,ParametrosConstantes.txObservacionesParametroNumerocupos,ParametrosConstantes.tipoParametroNumerocupos,personalizarHorario);
		
		parametro=parametroLocal.save(nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,ParametrosConstantes.txNombreParametroRenovacion,
				ParametrosConstantes.txValorParametroRenovacion,ParametrosConstantes.txObservacionesParametroRenovacion,ParametrosConstantes.tipoParametroRenovacion,personalizarHorario);
		
		parametro=parametroLocal.save( nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,ParametrosConstantes.txNombreParametroCantDiasAsistPorMes,
				ParametrosConstantes.txValorParametroCantDiasAsistPorMes,ParametrosConstantes.txObservacionesParametroCantDiasAsistPorMes,ParametrosConstantes.tipoParametroCantDiasAsistPorMes,personalizarHorario);
		
		parametro=parametroLocal.save( nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,ParametrosConstantes.txNombreParametroPorcentajeAceptacion,
				ParametrosConstantes.txValorParametroPorcentajeAceptacion,ParametrosConstantes.txObservacionesParametroPorcentajeAceptacion,ParametrosConstantes.tipoParametroPorcentajeAceptacion,personalizarHorario);
 
		parametro=parametroLocal.save( nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,ParametrosConstantes.txNombreParametroAccesoDirectoInscripcion,
				ParametrosConstantes.txValorParametroAccesoDirectoInscripcion,ParametrosConstantes.txObservacionesParametroAccesoDirectoInscripcion,ParametrosConstantes.tipoParametroAccesoDirectoInscripcion,personalizarHorario);
		
		
		/**
		 * 
		 * 	String txNombreParametroAccesoDirectoInscripcion ="accesoDirectoInscripcion";
	String txValorParametroAccesoDirectoInscripcion = "NO";
	String txObservacionesParametroAccesoDirectoInscripcion = "Acceso Directo a inscripciones";
	String tipoParametroAccesoDirectoInscripcion="b"; // Boolean 

		 * 
		 * */
	}
	
	
	 
	public ParametroDto find(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario,
			String txNombreParametro) {
	 
		ParametroDto dto = new ParametroDto();
		Parametro dao=parametroLocal.find(nbModulo,  nbGrupoModulo,  nbSubGrupoModulo,  nbActividad,  nbHorario,	txNombreParametro);
				if (dao!=null){
					try {
						BeanUtils.copyProperties(dto, dao);
						dto.setTxNombreParametro(dao.getId().getTxNombreParametro());
						dto.setTxValorParametro(dao.getTxValorParametro());
						dto.setNbModulo(dao.getId().getNbModulo());
						dto.setNbActividad(dao.getId().getNbActividad());
						dto.setTxObservaciones(dao.getTxObservaciones());
						dto.setTipoParametro(dao.getTipoParametro());
						try {
							dto.setPersonalizarHorario(dao.isPersonalizarHorario());	
						} catch (Exception e) {
							dto.setPersonalizarHorario(false);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		return dto;
	}

}

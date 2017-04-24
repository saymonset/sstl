/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import ve.org.bcv.dao.MiembroHorario;
import ve.org.bcv.dao.local.MiembroHorarioLocal;
import ve.org.bcv.dto.AtencionDto;
import ve.org.bcv.dto.MiembroHorarioDto;
import ve.org.bcv.services.AtencionService;
import ve.org.bcv.services.MiembroHorarioService;
import ve.org.bcv.util.FechasHorarioCalculos;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 04/08/2016 10:11:10 2016 mail :
 *         oraclefedora@gmail.com
 */
@MiembroHorarioServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MiembroHorarioServiceImpl implements MiembroHorarioService {

	@Inject
	@AtencionServicioType1
	private AtencionService atencionService;;
	@Inject
	private MiembroHorarioLocal miembroHorarioLocal;
	@Inject
	private FechasHorarioCalculos fechasHorarioCalculos;
	public MiembroHorarioDto save(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,String cedula, String id, String start, String end, String text)
			throws Exception {

		MiembroHorario dao = miembroHorarioLocal.save(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario,
				cedula, id, start, end, text);

		MiembroHorarioDto dto = new MiembroHorarioDto();
		try {
			BeanUtils.copyProperties(dto, dao);
			dto.setId(dao.getPk().getId());
			dto.setCedula(dao.getPk().getCedula());
			dto.setNbModulo(dao.getPk().getNbModulo());
			dto.setNbActividad(dao.getPk().getNbActividad());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return dto;
	}

	public MiembroHorarioDto findByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario,
			String cedula) throws Exception {
		MiembroHorario dao = miembroHorarioLocal.findByCedula(nbModulo,nbGrupoModulo,nbSubGrupoModulo,
				nbActividad,nbHorario, cedula);
		MiembroHorarioDto dto = new MiembroHorarioDto();
		if (dao != null) {
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setId(dao.getPk().getId());
				dto.setCedula(dao.getPk().getCedula());
				dto.setNbModulo(dao.getPk().getNbModulo());
				dto.setNbActividad(dao.getPk().getNbActividad());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}



	public List<MiembroHorarioDto> deleteHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario, String cedula) throws Exception {
		List<MiembroHorarioDto> deleteDtos = new ArrayList<MiembroHorarioDto>();
		List<MiembroHorario> daos = miembroHorarioLocal.findHorarioByCedula(
				nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario, cedula);
		for (MiembroHorario dao : daos) {
			miembroHorarioLocal.remove(dao);
			MiembroHorarioDto dto = new MiembroHorarioDto();
			if (dao != null) {
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setId(dao.getPk().getId());
					dto.setCedula(dao.getPk().getCedula());
					dto.setNbModulo(dao.getPk().getNbModulo());
					dto.setNbActividad(dao.getPk().getNbActividad());
					deleteDtos.add(dto);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return deleteDtos;
	}

	public List<MiembroHorarioDto> findSinColorHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario, String cedula) throws Exception {
		 
		/**sacamos el horario del usuario en bd*/
		List<MiembroHorarioDto> objs = new ArrayList<MiembroHorarioDto>();
		List<MiembroHorario> daos = miembroHorarioLocal.findHorarioByCedula(
				nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario, cedula);
		MiembroHorarioDto dto = null;
		for (MiembroHorario dao : daos) {
			if (dao != null) {
				dto = new MiembroHorarioDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setId(dao.getPk().getId());
					dto.setCedula(dao.getPk().getCedula());
					dto.setNbModulo(dao.getPk().getNbModulo());
					dto.setNbActividad(dao.getPk().getNbActividad());
					dto.setText(dao.getText());
					dto.setStart(dao.getStart());
					dto.setEnd(dto.getEnd());
					   /*******Colocamos solo las fechas de hoy para que apunten de lunes a viernes en el horario de java script*******/
					try {
						dto.setStart(fechasHorarioCalculos.changeFecha(dto
								.getStart()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						dto.setEnd(fechasHorarioCalculos.changeFecha(dto.getEnd()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dto.setMoveDisabled(true);
					dto.setResizeDisabled(true);
					dto.setClickDisabled(true);
					
					objs.add(dto);

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} 

		return objs;
	}

	 
	public List<MiembroHorarioDto> findHorarioByCedula(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad,String nbHorario, String cedula) throws Exception {
		
		List<MiembroHorarioDto> objsChecked = new ArrayList<MiembroHorarioDto>();;
		/**sacamos el horario del usuario en bd*/
		List<MiembroHorarioDto> objs = new ArrayList<MiembroHorarioDto>();
		List<MiembroHorario> daos = miembroHorarioLocal.findHorarioByCedula(
				nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario, cedula);
		MiembroHorarioDto dto = null;
		for (MiembroHorario dao : daos) {
			if (dao != null) {
				dto = new MiembroHorarioDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setId(dao.getPk().getId());
					dto.setCedula(dao.getPk().getCedula());
					dto.setNbModulo(dao.getPk().getNbModulo());
					dto.setNbActividad(dao.getPk().getNbActividad());
					dto.setText(dao.getText());
					dto.setStart(dao.getStart());
					dto.setEnd(dto.getEnd());
					   /*******Colocamos solo las fechas de hoy para que apunten de lunes a viernes en el horario de java script*******/
					try {
						dto.setStart(fechasHorarioCalculos.changeFecha(dto
								.getStart()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						dto.setEnd(fechasHorarioCalculos.changeFecha(dto.getEnd()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dto.setMoveDisabled(true);
					dto.setResizeDisabled(true);
					dto.setClickDisabled(true);
					
					objs.add(dto);

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		if (objs != null) {
			/**sacamos la Atencion  del usuario en bd*/
			
			List<AtencionDto> atenciones = atencionService.atencionByCedula(
					nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario, cedula);
			/**Por cada Atencion. colocamos un color*/
			for (MiembroHorarioDto obj : objs) {
				boolean changedColor=false;
				for (AtencionDto aten : atenciones) {
					if (obj.getStart() != null
							&& obj.getStart()
									.equalsIgnoreCase(aten.getStart())
							&& obj.getEnd() != null
							&& obj.getEnd().equalsIgnoreCase(aten.getEnd())
							&& obj.getId().equalsIgnoreCase(aten.getId())) {
						obj.setBackColor("#87ae85");
						obj.setDiaBusy(true);
						changedColor=true;
						break;
					}
				}
       /*******Colocamos colores*******/
				if (!changedColor && obj.getStart() != null
						&& fechasHorarioCalculos.diaAnteriorAlActual(obj
								.getStart())) {
					obj.setBackColor("#FFA07A");
					obj.setDiaPrevious(true);
				} else if (!changedColor && obj.getStart() != null
						&& fechasHorarioCalculos
								.diaNextAlActual(obj.getStart())) {
					obj.setBackColor("#00BFFF");
					obj.setDiaNext(true);
					//f0ff00
				}
			    

				objsChecked.add(obj);
			}
		}

		return objsChecked;
	}

	public List<MiembroHorarioDto> byModuloMiembros(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,
			String nbActividad, Date feDesde, Date feHasta) throws Exception {
		List<MiembroHorario> daos = miembroHorarioLocal.byModuloMiembros(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad, feDesde,feHasta) ;
		List<MiembroHorarioDto> objs = new ArrayList<MiembroHorarioDto>();
		MiembroHorarioDto dto=null;
		for (MiembroHorario dao : daos) {
			if (dao != null) {
				dto = new MiembroHorarioDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					/**Id Este es el codigo del horario*/
					dto.setId(dao.getPk().getId());
					dto.setCedula(dao.getPk().getCedula());
					dto.setNbModulo(dao.getPk().getNbModulo());
					dto.setNbActividad(dao.getPk().getNbActividad());
					dto.setText(dao.getText());
					dto.setStart(dao.getStart());
					dto.setEnd(dto.getEnd());
					objs.add(dto);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return objs;
	}
}

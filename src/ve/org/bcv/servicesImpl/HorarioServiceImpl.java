/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import ve.org.bcv.dao.Horario;
import ve.org.bcv.dao.HorarioPk;
import ve.org.bcv.dao.local.HorarioLocal;
import ve.org.bcv.dto.HorarioDto;
import ve.org.bcv.services.HorarioService;
import ve.org.bcv.util.FechasHorarioCalculos;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 07/06/2016 09:40:17 2016 mail :
 *         oraclefedora@gmail.com
 */
@HorarioServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HorarioServiceImpl implements HorarioService {

	@Inject
	private HorarioLocal horarioLocal;
	private FechasHorarioCalculos fechasHorarioCalculos = new FechasHorarioCalculos();

	@Deprecated
	public HorarioDto save(String id, String start, String end, String nbModulo, String nbActividad, String text)
			throws Exception {

		Horario dao = horarioLocal.find(id);
		if (dao == null) {
			dao = new Horario();
			dao.setEnd(end);
			HorarioPk horarioPk = new HorarioPk();
			horarioPk.setId(id);
			horarioPk.setNbActividad(nbActividad);
			horarioPk.setNbModulo(nbModulo);
			dao.setId(horarioPk);
			dao.setStart(start);
			dao.setText(text);
			dao = horarioLocal.save(dao);
		} else {
			dao.setEnd(end);
			dao.getId().setNbActividad(nbActividad);
			dao.getId().setNbModulo(nbModulo);
			dao.setStart(start);
			dao.setText(text);
			dao = horarioLocal.update(dao);
		}

		HorarioDto dto = new HorarioDto();
		try {
			BeanUtils.copyProperties(dto, dao);
			dto.setStart(fechasHorarioCalculos.changeFecha(dto.getStart()));
			dto.setEnd(fechasHorarioCalculos.changeFecha(dto.getEnd()));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public HorarioDto save(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario, String id, String text, String start, String end) throws Exception {
		
		Horario dao= horarioLocal.find( nbModulo,nbGrupo, nbSubGrupo, nbActividad,
				 nbHorario,  id);
		if (dao == null) {
			dao = new Horario();
			
			HorarioPk horarioPk = new HorarioPk();
			horarioPk.setId(id);
			horarioPk.setNbModulo(nbModulo);
			horarioPk.setNbGrupo(nbGrupo);
			horarioPk.setNbSubGrupo(nbSubGrupo);
			horarioPk.setNbActividad(nbActividad);
			horarioPk.setNbHorario(nbHorario);
            dao.setId(horarioPk);			
			dao.setText(text);
			dao.setStart(start);
			dao.setEnd(end);
			dao = horarioLocal.save(dao);
		} else {
			dao.setText(text);
			dao.setStart(start);
			dao.setEnd(end);
			dao = horarioLocal.update(dao);
		}
		HorarioDto dto = new HorarioDto();
		try {
			BeanUtils.copyProperties(dto, dao);
			dto.setStart(fechasHorarioCalculos.changeFecha(dto.getStart()));
			dto.setEnd(fechasHorarioCalculos.changeFecha(dto.getEnd()));
			dto.setText(text);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * Listamos el horario por modulo y actividad (non-Javadoc)
	 * 
	 * @see ve.org.bcv.services.HorarioService#findByModuloActividad(java.lang.String,
	 *      java.lang.String)
	 */
	public List<HorarioDto> findByModuloActividad(String nbModulo,String nbGrupoModulo,String nbSubGrupoModulo,String nbActividad,String nbHorario) {
		List<Horario> horarios = horarioLocal.findByModuloActividad(nbModulo,nbGrupoModulo,nbSubGrupoModulo, nbActividad,nbHorario);
		List<HorarioDto> dtos = new ArrayList<HorarioDto>();

		if (horarios != null && horarios.size() > 0) {
			HorarioDto dto = null;
			for (Horario dao : horarios) {
				dto = new HorarioDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					try {
						dto.setStart(fechasHorarioCalculos.changeFecha(dto.getStart()));
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
					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return dtos;
	}

	/**
	 * Listamos el horario por modulo y actividad (non-Javadoc)
	 * 
	 * @see ve.org.bcv.services.HorarioService#findByModuloActividad(java.lang.String,
	 *      java.lang.String)
	 */
//	@Deprecated
//	public List<HorarioDto> findByModuloActividadMoveDisabled(String nbModulo, String nbActividad) {
//		List<Horario> horarios =null;// horarioLocal.findByModuloActividad(nbModulo, nbActividad);
//		List<HorarioDto> dtos = new ArrayList<HorarioDto>();
//
//		if (horarios != null && horarios.size() > 0) {
//			HorarioDto dto = null;
//			for (Horario dao : horarios) {
//				dto = new HorarioDto();
//				try {
//					BeanUtils.copyProperties(dto, dao);
//					try {
//						dto.setStart(fechasHorarioCalculos.changeFecha(dto.getStart()));
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					try {
//						dto.setEnd(fechasHorarioCalculos.changeFecha(dto.getEnd()));
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					dto.setMoveDisabled(true);
//					dto.setResizeDisabled(true);
//					dto.setClickDisabled(true);
//
//					dtos.add(dto);
//
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//		}
//		return dtos;
//	}
	
	public List<HorarioDto> findByModuloActividad(String nbModulo, String nbGrupo, String nbSubGrupo,
			String nbActividad) {
		
		List<Horario> horarios = horarioLocal.findByModuloActividad(  nbModulo,   nbGrupo,   nbSubGrupo,   nbActividad);
		List<HorarioDto> dtos = new ArrayList<HorarioDto>();

		if (horarios != null && horarios.size() > 0) {
			HorarioDto dto = null;
			for (Horario dao : horarios) {
				dto = new HorarioDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					try {
						dto.setStart(fechasHorarioCalculos.changeFecha(dto.getStart()));
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

					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return dtos;
	}


	 
	public HorarioDto delete(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad, String nbHorario,
			String id, String text) throws Exception {
		HorarioDto dto = null;
		Horario dao= horarioLocal.find( nbModulo,nbGrupo, nbSubGrupo, nbActividad,
				 nbHorario,  id);
		if (dao != null) {
			 horarioLocal.remove(dao);
			 dto = new HorarioDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setStart(fechasHorarioCalculos.changeFecha(dto.getStart()));
					dto.setEnd(fechasHorarioCalculos.changeFecha(dto.getEnd()));
					dto.setText(text);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		
		return dto;
	}


}

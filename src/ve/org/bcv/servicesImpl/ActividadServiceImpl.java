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

import ve.org.bcv.dao.Actividad;
import ve.org.bcv.dao.ActividadPK;
import ve.org.bcv.dao.local.ActividadLocal;
import ve.org.bcv.dao.local.AtencionLocal;
import ve.org.bcv.dao.local.HorarioLocal;
import ve.org.bcv.dao.local.HorarioNombreLocal;
import ve.org.bcv.dao.local.MiembroHorarioLocal;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.dao.local.ParametroLocal;
import ve.org.bcv.dto.ActividadDto;
import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.services.ActividadService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 20/07/2016 11:01:42 2016 mail :
 *         oraclefedora@gmail.com
 */
@ActividadServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ActividadServiceImpl implements ActividadService {

	@Inject
	private ActividadLocal actividadLocal;
	@Inject
	private HorarioLocal horarioLocal;

	@Inject
	private HorarioNombreLocal horarioNombreLocal;;

	@Inject
	private AtencionLocal atencionLocal;

	@Inject
	private MiembroLocal miembroLocal;

	@Inject
	private MiembroHorarioLocal miembroHorarioLocal;

	@Inject
	private ParametroLocal parametroLocal;

	public DeleteYesDto isDelete(String nbModulo, String nbGrupo, String nbSubGrupoModulo, String actividad) {
		DeleteYesDto deleteYesDto = new DeleteYesDto();
		deleteYesDto.setDeleteObj(
				horarioNombreLocal.isDeleteActividadInHorario(nbModulo, nbGrupo, nbSubGrupoModulo, actividad));

		return deleteYesDto;
	}

	public ActividadDto save(String actividad, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo)
			throws Exception {
		ActividadDto dto = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && actividad != null
				&& actividad.length() > 0) {
			ActividadPK actividadPK = new ActividadPK();
			actividadPK.setNbModulo(nbModulo);
			actividadPK.setNbGrupo(nbGrupoModulo);
			actividadPK.setNbSubGrupo(nbSubGrupoModulo);
			actividadPK.setNbActividad(actividad);
			Actividad dao = new Actividad();
			dao.setId(actividadPK);
			dao = actividadLocal.find(actividadPK);
			if (dao == null) {
				dao = new Actividad();
				dao.setId(actividadPK);
				dao = actividadLocal.save(dao);
				try {
					dto = new ActividadDto();
					BeanUtils.copyProperties(dto, dao);
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return dto;
	}

	@Deprecated
	public ActividadDto save(String nbModulo, String nbActividad) throws Exception {
		ActividadDto dto = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbActividad != null && nbActividad.length() > 0) {
			nbModulo = nbModulo.toLowerCase();
			nbActividad = nbActividad;
			ActividadPK actividadPK = new ActividadPK();
			actividadPK.setNbModulo(nbModulo);
			actividadPK.setNbActividad(nbActividad);
			Actividad dao = new Actividad();
			dao.setId(actividadPK);
			dao = actividadLocal.find(actividadPK);
			if (dao == null) {
				dao = new Actividad();
				dao.setId(actividadPK);
				dao = actividadLocal.save(dao);
				try {
					dto = new ActividadDto();
					BeanUtils.copyProperties(dto, dao);
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return dto;
	}

	public ActividadDto update(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbActividadModif) throws Exception {
		ActividadDto dto = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0 && nbActividadModif != null && nbActividadModif.length() > 0) {
			/** Verificamos el dao a modificar opriginal */
			Actividad dao = new Actividad();
			ActividadPK actividadPK = new ActividadPK();
			actividadPK.setNbModulo(nbModulo);
			actividadPK.setNbGrupo(nbGrupoModulo);
			actividadPK.setNbSubGrupo(nbSubGrupoModulo);
			actividadPK.setNbActividad(nbActividad);
			dao = actividadLocal.find(actividadPK);
			/** Si existe */
			if (dao != null) {
				/**
				 * Verificamos que la actividad que se va a modiicar no exista
				 */
				Actividad daoVerifModf = null;
				if (nbActividadModif != null && nbActividadModif.length() > 0
						&& !nbActividadModif.equalsIgnoreCase(nbActividad)) {
					/** Verificamos con el nuevo que no exista */
					ActividadPK actividadModifPK = new ActividadPK();
					actividadModifPK.setNbModulo(nbModulo);
					actividadModifPK.setNbGrupo(nbGrupoModulo);
					actividadModifPK.setNbSubGrupo(nbSubGrupoModulo);
					actividadModifPK.setNbActividad(nbActividadModif);
					daoVerifModf = actividadLocal.find(actividadModifPK);
					if (daoVerifModf == null) {
						/**
						 * Ingresamos la actividad a modificar como nueva y
						 * pasamos toda la data de la vieja a la nueva y
						 * eliminamos la actividad vieja
						 */
						// List<Horario>
						// horarios=horarioLocal.findByModuloActividad(nbModulo,
						// nbActividad);
						// if (null!=horarios&& horarios.size()>0){
						// /**No tenemos problemas porqueno estamos modificando
						// un pk***/
						// for (Horario horario:horarios){
						// horario.setNbActividad(nbActividadModif);
						// horarioLocal.update(horario);
						// }
						// }

						/**
						 * Ingresamos la actividad a modificar como nueva y
						 * pasamos toda la data de la vieja a la nueva y
						 * eliminamos la actividad vieja
						 */
						// List<MiembroHorario>
						// objs=miembroHorarioLocal.findByModuloActividad(nbModulo,
						// nbActividad);
						// if (null!=objs&& objs.size()>0){
						// /**Aca estamos modificando un pk y debemos eliminar
						// el pk y crear uno nuevo con la misma data*/
						// MiembroHorario objNew=null;
						// for (MiembroHorario obj:objs){
						// /** eliminamos la actividad vieja y add new*/
						// miembroHorarioLocal.remove(obj);
						//
						// }
						// }

						/**
						 * Ingresamos la actividad a modificar como nueva y
						 * pasamos toda la data de la vieja a la nueva y
						 * eliminamos la actividad vieja
						 */
						// List<Miembro>
						// miembros=miembroLocal.findMiembrosByActMod(nbModulo,
						// nbActividad);
						// if (null!=miembros&& miembros.size()>0){
						// Miembro objNew=null;
						// for (Miembro obj:miembros){
						//
						// /**Aca estamos modificando un pk y debemos eliminar
						// el pk y crear uno nuevo con la misma data*/
						// miembroLocal.remove(obj);
						// }
						// }

						/**
						 * Ingresamos la actividad a modificar como nueva y
						 * pasamos toda la data de la vieja a la nueva y
						 * eliminamos la actividad vieja
						 */
						// List<Atencion>
						// atenciones=atencionLocal.findByModuloActivdadStadistica(nbModulo,
						// nbActividad);
						// if (null!=atenciones&& atenciones.size()>0){
						// Atencion objNew=null;
						// for (Atencion obj:atenciones){
						// /**Aca estamos modificando un pk y debemos eliminar
						// el pk y crear uno nuevo con la misma data*/
						// atencionLocal.remove(obj);
						//
						// }
						// }

						/**
						 * Ingresamos la actividad a modificar como nueva y
						 * pasamos toda la data de la vieja a la nueva y
						 * eliminamos la actividad vieja
						 */
						// List<Parametro>
						// objsP=parametroLocal.findAll(nbModulo, nbActividad);
						// if (null!=objsP&& objsP.size()>0){
						// Parametro objNew=null;
						// for (Parametro obj:objsP){
						// /**Aca estamos modificando un pk y debemos eliminar
						// el pk y crear uno nuevo con la misma data*/
						// parametroLocal.remove(obj);
						//
						//
						// }
						// }

						/** eliminamos la actividad vieja */
						actividadLocal.remove(dao);
						/** Agregamos la nueva actividad */
						daoVerifModf = new Actividad();
						actividadModifPK = new ActividadPK();

						actividadModifPK.setNbModulo(nbModulo);
						actividadModifPK.setNbGrupo(nbGrupoModulo);
						actividadModifPK.setNbSubGrupo(nbSubGrupoModulo);
						actividadModifPK.setNbActividad(nbActividadModif);
						daoVerifModf.setId(actividadModifPK);
						daoVerifModf = actividadLocal.save(daoVerifModf);

						try {
							dto = new ActividadDto();
							BeanUtils.copyProperties(dto, daoVerifModf);
							dto.setNbModulo(daoVerifModf.getId().getNbModulo());
							dto.setNbGrupo(daoVerifModf.getId().getNbGrupo());
							dto.setNbSubGrupo(daoVerifModf.getId().getNbSubGrupo());
							dto.setNbActividad(daoVerifModf.getId().getNbActividad());

						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}

					/** fin eliminamos la actividad vieja */

				}
			}
		}

		return dto;
	}

	public List<ActividadDto> findByActividadesByModulo(String nbModulo, String nbGrupoModulo,
			String nbSubGrupoModulo) {
		List<Actividad> actividades = actividadLocal.findByModulo(nbModulo, nbGrupoModulo, nbSubGrupoModulo);
		List<ActividadDto> dtos = new ArrayList<ActividadDto>();
		if (actividades != null && actividades.size() > 0) {
			ActividadDto dto = null;
			for (Actividad dao : actividades) {
				dto = new ActividadDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());

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

	@Deprecated
	public List<ActividadDto> findByActividadesByModulo(String nbModulo) {
		List<Actividad> actividades = actividadLocal.findByModulo(nbModulo);
		List<ActividadDto> dtos = new ArrayList<ActividadDto>();
		if (actividades != null && actividades.size() > 0) {
			ActividadDto dto = null;
			for (Actividad dao : actividades) {
				dto = new ActividadDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ve.org.bcv.services.ActividadService#delete(java.lang.String,
	 * java.lang.String)
	 */
	@Deprecated
	public ActividadDto delete(String nbModulo, String nbActividad) throws Exception {
		ActividadDto dto = new ActividadDto();

		/** Verificamos el dao a modificar opriginal */
		ActividadPK actividadPK = new ActividadPK();
		actividadPK.setNbModulo(nbModulo);
		actividadPK.setNbActividad(nbActividad);
		Actividad dao = new Actividad();
		dao.setId(actividadPK);

		dao = actividadLocal.find(actividadPK);
		/** Si existe */
		if (dao != null) {
			actividadLocal.remove(dao);
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbGrupo(dao.getId().getNbGrupo());
				dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
				dto.setNbActividad(dao.getId().getNbActividad());
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

	public ActividadDto delete(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad)
			throws Exception {
		ActividadDto dto = new ActividadDto();

		/** Verificamos el dao a modificar opriginal */
		ActividadPK actividadPK = new ActividadPK();
		actividadPK.setNbModulo(nbModulo);
		actividadPK.setNbGrupo(nbGrupoModulo);
		actividadPK.setNbSubGrupo(nbSubGrupoModulo);
		actividadPK.setNbActividad(nbActividad);
		Actividad dao = actividadLocal.find(actividadPK);
		/** Si existe */
		if (dao != null) {
			actividadLocal.remove(dao);
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbGrupo(dao.getId().getNbGrupo());
				dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
				dto.setNbActividad(dao.getId().getNbActividad());
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

	public Boolean noExiste(String actividad, String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo)
			throws Exception {
		/** Verificamos que no se repita el mismo nombre del grupo */
		ActividadPK id = new ActividadPK();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
		id.setNbActividad(actividad);
		Actividad dao = actividadLocal.findSameModificar(id);

		return (dao == null);
	}

}

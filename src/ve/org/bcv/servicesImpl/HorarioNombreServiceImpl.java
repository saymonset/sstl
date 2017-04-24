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
import ve.org.bcv.dao.Atencion;
import ve.org.bcv.dao.Horario;
import ve.org.bcv.dao.HorarioNombre;
import ve.org.bcv.dao.HorarioNombrePk;
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.MiembroHorario;
import ve.org.bcv.dao.Parametro;
import ve.org.bcv.dao.ParametroPk;
import ve.org.bcv.dao.local.AccesoDirectoLocal;
import ve.org.bcv.dao.local.ActividadLocal;
import ve.org.bcv.dao.local.AtencionLocal;
import ve.org.bcv.dao.local.HorarioLocal;
import ve.org.bcv.dao.local.HorarioNombreLocal;
import ve.org.bcv.dao.local.MiembroHorarioLocal;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.dao.local.ParametroLocal;
import ve.org.bcv.dto.DeleteYesDto;
import ve.org.bcv.dto.HorarioNombreDto;
import ve.org.bcv.services.HorarioNombreService;

@HorarioNombreServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HorarioNombreServiceImpl implements HorarioNombreService {

	@Inject
	private HorarioLocal horarioLocal;
	@Inject
	private HorarioNombreLocal horarioNombreLocal;

	@Inject
	private AccesoDirectoLocal accesoDirectoLocal;
	
	@Inject
	private AtencionLocal atencionLocal;

	@Inject
	private MiembroLocal miembroLocal;

	@Inject
	private MiembroHorarioLocal miembroHorarioLocal;

	@Inject
	private ActividadLocal actividadLocal;
	
	@Inject
	private ParametroLocal parametroLocal;
	

	public DeleteYesDto isDelete(String nbModulo, String nbGrupo, String nbSubGrupoModulo, String actividad,
			String nbHorario) {
		DeleteYesDto deleteYesDto = new DeleteYesDto();
		List<Atencion> atenciones = atencionLocal.findNbHorario(nbModulo, nbGrupo, nbSubGrupoModulo, actividad,
				nbHorario);
		List<Miembro> miembros = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupo, nbSubGrupoModulo, actividad,
				nbHorario);
		List<MiembroHorario> miembroHorarios = null;

		try {
			miembroHorarios = miembroHorarioLocal.findByModuloActividad(nbModulo, nbGrupo, nbSubGrupoModulo, actividad,
					nbHorario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ((miembroHorarios == null || miembroHorarios.size() == 0) && (atenciones == null || atenciones.size() == 0)
				&& (miembros == null || miembros.size() == 0)) {
			deleteYesDto.setDeleteObj(true);
		}
		return deleteYesDto;
	}

	public List<HorarioNombreDto> findByActividadesByModulo(String nbModulo, String nbGrupoModulo,
			String nbSubGrupoModulo, String actividad) {
		List<HorarioNombre> objs = horarioNombreLocal.findByModulo(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				actividad);
		List<HorarioNombreDto> dtos = new ArrayList<HorarioNombreDto>();
		if (objs != null && objs.size() > 0) {
			HorarioNombreDto dto = null;
			for (HorarioNombre dao : objs) {
				dto = new HorarioNombreDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbHorario(dao.getId().getNbHorario());

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

	public HorarioNombreDto delete(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario) throws Exception {
		HorarioNombreDto dto = new HorarioNombreDto();

		/** Verificamos el dao a modificar opriginal */
		HorarioNombrePk pk = new HorarioNombrePk();
		pk.setNbModulo(nbModulo);
		pk.setNbGrupo(nbGrupoModulo);
		pk.setNbSubGrupo(nbSubGrupoModulo);
		pk.setNbActividad(nbActividad);
		pk.setNbHorario(nbHorario);

		List<Horario> horarios = horarioLocal.findByModuloActividad(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad, nbHorario);
		if (null != horarios && horarios.size() > 0) {
			for (Horario horario : horarios) {
				horarioLocal.remove(horario);
			}
		}

	
		
		
		AccesoDirectoPK accesoDirectoPK = new AccesoDirectoPK();
		accesoDirectoPK.setNbModulo(nbModulo);
		accesoDirectoPK.setNbGrupo(nbGrupoModulo);
		accesoDirectoPK.setNbSubGrupo(nbSubGrupoModulo);
		accesoDirectoPK.setNbActividad(nbActividad);
		accesoDirectoPK.setNbHorario(nbHorario);

		/**Borramos el acceso directo*/
		AccesoDirecto accesoDirecto = accesoDirectoLocal.find(accesoDirectoPK);
		if (null!=accesoDirecto){
			accesoDirectoLocal.remove(accesoDirecto);
		}
		
		/**Borramos los parametros*/
		ParametroPk parametroPk = new ParametroPk();
		parametroPk.setNbModulo(nbModulo);
		parametroPk.setNbGrupo(nbGrupoModulo);
		parametroPk.setNbSubGrupo(nbSubGrupoModulo);
		parametroPk.setNbActividad(nbActividad);
		parametroPk.setNbHorario(nbHorario);
		List<Parametro> parametros = parametroLocal.find(parametroPk); 
		if (null!=parametros){
			for (Parametro param:parametros){
				parametroLocal.remove(param);		
			}
		}
		
		
		/**Borramos el nombre del horario*/
		HorarioNombre dao = horarioNombreLocal.find(pk);	
		/** Si existe */
		if (dao != null) {
			horarioNombreLocal.remove(dao);
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbGrupo(dao.getId().getNbGrupo());
				dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setNbHorario(dao.getId().getNbHorario());
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

	public HorarioNombreDto save(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String actividad,
			String nbHorario) throws Exception {
		HorarioNombreDto dto = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && actividad != null
				&& actividad.length() > 0 && nbHorario != null && nbHorario.length() > 0) {
			HorarioNombrePk pk = new HorarioNombrePk();
			pk.setNbModulo(nbModulo);
			pk.setNbGrupo(nbGrupoModulo);
			pk.setNbSubGrupo(nbSubGrupoModulo);
			pk.setNbActividad(actividad);
			pk.setNbHorario(nbHorario);
			HorarioNombre dao = horarioNombreLocal.find(pk);
			if (dao == null) {
				dao = new HorarioNombre();
				dao.setId(pk);
				dao = horarioNombreLocal.save(dao);
				try {
					dto = new HorarioNombreDto();
					BeanUtils.copyProperties(dto, dao);
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbHorario(dao.getId().getNbHorario());
					dto.setDelete(true);

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return dto;
	}

	public HorarioNombreDto update(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String nbHorarioModif) throws Exception {
		HorarioNombreDto dto = null;
		if (nbModulo != null && nbModulo.length() > 0 && nbGrupoModulo != null && nbGrupoModulo.length() > 0
				&& nbSubGrupoModulo != null && nbSubGrupoModulo.length() > 0 && nbActividad != null
				&& nbActividad.length() > 0 && nbHorario != null && nbHorario.length() > 0 && nbHorarioModif != null
				&& nbHorarioModif.length() > 0) {
			/** Verificamos el dao a modificar opriginal */
			HorarioNombrePk pk = new HorarioNombrePk();
			pk.setNbModulo(nbModulo);
			pk.setNbGrupo(nbGrupoModulo);
			pk.setNbSubGrupo(nbSubGrupoModulo);
			pk.setNbActividad(nbActividad);
			pk.setNbHorario(nbHorario);
			HorarioNombre dao = horarioNombreLocal.find(pk);
			/** Si existe */
			if (dao != null) {
				/**
				 * Verificamos que la actividad que se va a modiicar no exista
				 */
				HorarioNombre daoVerifModf = null;
				if (nbHorarioModif != null && nbHorarioModif.length() > 0
						&& !nbHorarioModif.equalsIgnoreCase(nbHorario)) {
					/** Verificamos con el nuevo que no exista */
					HorarioNombrePk pkModif = new HorarioNombrePk();
					pkModif.setNbModulo(nbModulo);
					pkModif.setNbGrupo(nbGrupoModulo);
					pkModif.setNbSubGrupo(nbSubGrupoModulo);
					pkModif.setNbActividad(nbActividad);
					pkModif.setNbHorario(nbHorarioModif);
					daoVerifModf = horarioNombreLocal.find(pkModif);
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
						horarioNombreLocal.remove(dao);

						/** Agregamos la nueva actividad */
						daoVerifModf = new HorarioNombre();
						pkModif = new HorarioNombrePk();

						pkModif = new HorarioNombrePk();
						pkModif.setNbModulo(nbModulo);
						pkModif.setNbGrupo(nbGrupoModulo);
						pkModif.setNbSubGrupo(nbSubGrupoModulo);
						pkModif.setNbActividad(nbActividad);
						pkModif.setNbHorario(nbHorarioModif);
						daoVerifModf.setId(pkModif);
						daoVerifModf = horarioNombreLocal.save(daoVerifModf);
						try {
							dto = new HorarioNombreDto();
							BeanUtils.copyProperties(dto, daoVerifModf);
							dto.setNbModulo(daoVerifModf.getId().getNbModulo());
							dto.setNbGrupo(daoVerifModf.getId().getNbGrupo());
							dto.setNbSubGrupo(daoVerifModf.getId().getNbSubGrupo());
							dto.setNbActividad(daoVerifModf.getId().getNbActividad());
							dto.setNbHorario(daoVerifModf.getId().getNbHorario());
							dto.setDelete(true);
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

	public Boolean noExiste(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String actividad,
			String nbHorario) throws Exception {
		/** Verificamos que no se repita el mismo nombre del grupo */
		HorarioNombrePk id = new HorarioNombrePk();
		id.setNbModulo(nbModulo);
		id.setNbGrupo(nbGrupoModulo);
		id.setNbSubGrupo(nbSubGrupoModulo);
		id.setNbActividad(actividad);
		id.setNbHorario(nbHorario);
		HorarioNombre dao = horarioNombreLocal.findSameModificar(id);

		return (dao == null);
	}

}

package ve.org.bcv.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ve.org.bcv.dao.AccesoDirecto;
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.MiembroPK;
import ve.org.bcv.dao.local.AccesoDirectoLocal;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.dto.AccesoDirectoDto;
import ve.org.bcv.dto.SeguridadDto;
import ve.org.bcv.services.AccesoDirectoService;
import ve.org.bcv.services.SeguridadService;

@AccesoDirectoServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccesoDirectoServiceImpl implements AccesoDirectoService {
	@Inject
	private AccesoDirectoLocal accesoDirectoLocal;

	@Inject
	@SeguridadServiceType1
	private SeguridadService seguridadService;
	@Inject
	private MiembroLocal miembroLocal;

	public List<AccesoDirectoDto> findAll(String cedula) {
		List<AccesoDirectoDto> moduloDtos = new ArrayList<AccesoDirectoDto>();
		List<AccesoDirecto> dao = accesoDirectoLocal.findAll(cedula);

		AccesoDirectoDto dto = null;
		if (null != dao) {
			MiembroPK miembroPK = null;
			SeguridadDto seguridadDto =  null;
			for (AccesoDirecto m : dao) {

				miembroPK = new MiembroPK();
				miembroPK.setNbModulo(m.getId().getNbModulo());
				miembroPK.setNbGrupo(m.getId().getNbGrupo());
				miembroPK.setNbSubGrupo(m.getId().getNbSubGrupo());
				miembroPK.setNbActividad(m.getId().getNbActividad());
				miembroPK.setNbHorario(m.getId().getNbHorario());
				miembroPK.setCedula(cedula);
				Miembro daoMiembro = miembroLocal.find(miembroPK);

				if (null == daoMiembro) {

					dto = new AccesoDirectoDto();
					dto.setNbModulo(m.getId().getNbModulo());
					dto.setNbGrupo(m.getId().getNbGrupo());
					dto.setNbSubGrupo(m.getId().getNbSubGrupo());
					dto.setNbHorario(m.getId().getNbHorario());
					dto.setNbActividad(m.getId().getNbActividad());
					dto.setNbAccesoDirecto(m.getNbAccesoDirecto());
					dto.setPersonalizarHorario(m.isPersonalizarHorario());
					dto.setStatus(m.isStatus());
					seguridadDto = seguridadService.find(dto.getNbModulo(), dto.getNbGrupo(),
							dto.getNbSubGrupo(), dto.getNbActividad(), dto.getNbHorario());
					if (seguridadDto.isInscripcionShow()) {
						moduloDtos.add(dto);
					}

				}
			}
		}

		return moduloDtos;
	}
}

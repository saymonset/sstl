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

import ve.org.bcv.dao.Modulo;
import ve.org.bcv.dao.local.ModuloLocal;
import ve.org.bcv.dto.ModuloDto;
import ve.org.bcv.services.ModuloService;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 17/06/2016 14:58:34
 * 2016
 * mail : oraclefedora@gmail.com
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ModuloServiceImpl implements ModuloService {
 
	@Inject
	private ModuloLocal moduloLocal;
	public ModuloDto save(String nb) {
		Modulo dao = new Modulo();
		dao.setNbModulo(nb);
		moduloLocal.save(dao);
		ModuloDto dto = new ModuloDto();
		try {
			System.out.println("Copying properties from fromBean to toBean");
			BeanUtils.copyProperties(dto, dao);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public List<ModuloDto> findAll() {
		List<ModuloDto> moduloDtos = new ArrayList<ModuloDto>(); 
		List<Modulo> modulos = moduloLocal.findAll();
		ModuloDto moduloDto =null;
		if (null!=modulos){
			for (Modulo m:modulos ){
				moduloDto = new ModuloDto();
				moduloDto.setNbModulo(m.getNbModulo());
				moduloDtos.add(moduloDto);
			}	
		}
		
		return moduloDtos;
	}
 

}

/**
 * 
 */
package ve.org.bcv.services;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ve.org.bcv.controller.AsistenciaCtrl;
import ve.org.bcv.controller.AtencionCtrl;
import ve.org.bcv.controller.EstadisticaCtrl;
import ve.org.bcv.servicesImpl.AccesoDirectoServiceResource;
import ve.org.bcv.servicesImpl.ActividadServiceResource;
import ve.org.bcv.servicesImpl.ConfiguracionServiceResource;
import ve.org.bcv.servicesImpl.GrupoModuloServiceResource;
import ve.org.bcv.servicesImpl.HorarioNombreServiceResource;
import ve.org.bcv.servicesImpl.HorarioServiceResource;
import ve.org.bcv.servicesImpl.LdapServicioResource;
import ve.org.bcv.servicesImpl.MiembroServiceResource;
import ve.org.bcv.servicesImpl.ModuloServiceResource;
import ve.org.bcv.servicesImpl.ParametroServiceResource;
import ve.org.bcv.servicesImpl.SeguridadServiceResource;
import ve.org.bcv.servicesImpl.SubGrupoModuloServiceResource;
/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 03/06/2016 14:50:32
 * 2016
 * mail : oraclefedora@gmail.com
 */
@ApplicationPath("/services")
public class RestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestApplication() {
		classes.add(HorarioServiceResource.class);
		classes.add(LdapServicioResource.class);
		classes.add(ActividadServiceResource.class);
		classes.add(MiembroServiceResource.class);
		classes.add(AtencionCtrl.class);
		classes.add(EstadisticaCtrl.class);
		classes.add(ConfiguracionServiceResource.class);
		classes.add(ParametroServiceResource.class);
		classes.add(SeguridadServiceResource.class);
		classes.add(AsistenciaCtrl.class);
		classes.add(GrupoModuloServiceResource.class);
		classes.add(SubGrupoModuloServiceResource.class);
		classes.add(HorarioNombreServiceResource.class);
		classes.add(ModuloServiceResource.class);
		classes.add(AccesoDirectoServiceResource.class);
		
		
	}

	  public Set<Class<?>> getClasses() {
		    return classes;
		  }
	  
	public Set<Object> getSingletons() {
		return singletons;
	}
}
 
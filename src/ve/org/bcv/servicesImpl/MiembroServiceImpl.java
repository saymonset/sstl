/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import ve.org.bcv.dao.Atencion;
import ve.org.bcv.dao.Horario;
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.MiembroPK;
import ve.org.bcv.dao.Parametro;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.dao.local.ParametroLocal;
import ve.org.bcv.dto.AsistenciaDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.DataChart;
import ve.org.bcv.dto.DatasetsChart;
import ve.org.bcv.dto.HorarioDto;
import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;
import ve.org.bcv.dto.MiembroDtoOrderByNombre;
import ve.org.bcv.dto.OptionsChart;
import ve.org.bcv.dto.ScalesChart;
import ve.org.bcv.dto.TicksChart;
import ve.org.bcv.dto.YAxesChart;
import ve.org.bcv.services.MiembroService;
import ve.org.bcv.util.DiaUtil;
import ve.org.bcv.util.ManejadorDB;
import ve.org.bcv.util.ParametrosConstantes;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 25/07/2016 10:09:13 2016 mail :
 *         oraclefedora@gmail.com
 */
@MiembroServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MiembroServiceImpl implements MiembroService {

	@Inject
	private ParametroLocal parametroLocal;
	@Inject
	private MiembroLocal miembroLocal;
	@Inject
	ManejadorDB manejadorDB;

	public List<MiembroDto> findMiembrosByActMod(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();// sdf.parse(fechaOld.toString());
		String fechaNow = sdf.format(date);

		List<Miembro> miembros = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad, nbHorario);

		List<MiembroDto> lista = new ArrayList<MiembroDto>();
		if (miembros != null && miembros.size() > 0) {
			MiembroDto dto = null;
			for (Miembro dao : miembros) {
				dto = new MiembroDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCedula(dao.getId().getCedula());
					Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
					if (null != miembro) {
						dto.setNombre(miembro.getNombre());
						dto.setTipoEmp(miembro.getTipoEmp());
					}
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbHorario(dao.getId().getNbHorario());
					if (dto.isRenovable() && dto.getFeRenovacion() != null
							&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
						dto.setRenovableVencido(true);
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

	public String fechaRenovacion(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario) {
		/** txNombreParametroRenovacion */
		Date fechaRenovacion = new Date();
		Parametro parametro = parametroLocal.find(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
				ParametrosConstantes.txNombreParametroRenovacion);
		if (parametro != null && parametro.getTxValorParametro() != null
				&& parametro.getTxValorParametro().length() > 0) {
			fechaRenovacion = movDias(new Date(), new Integer(parametro.getTxValorParametro()));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fechaStr = sdf.format(fechaRenovacion);

		return fechaStr;
	}

	public MiembroDto save(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String cedula) throws Exception {
		Miembro dao = miembroLocal.save(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario, cedula,
				"Nombre Temporal", "TiepoEmpleadoTemporal");
		MiembroDto dto = new MiembroDto();
		if (dao != null) {
			BeanUtils.copyProperties(dto, dao);
			dto.setCedula(dao.getId().getCedula());
			Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
			if (null != miembro) {
				dto.setNombre(miembro.getNombre());
				dto.setTipoEmp(miembro.getTipoEmp());
			}
			dto.setNbModulo(dao.getId().getNbModulo());
			dto.setNbGrupo(dao.getId().getNbGrupo());
			dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
			dto.setNbActividad(dao.getId().getNbActividad());
			dto.setNbHorario(dao.getId().getNbHorario());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// sdf.parse(fechaOld.toString());
			String fechaNow = sdf.format(date);
			if (dto.isRenovable() && dto.getFeRenovacion() != null
					&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
				dto.setRenovableVencido(true);
			}

		}

		return dto;
	}

	public MiembroDto delete(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String cedula) throws Exception {
		MiembroPK miembroPk = new MiembroPK(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario, cedula);
		MiembroDto dto = new MiembroDto();
		Miembro dao = miembroLocal.find(miembroPk);
		if (dao != null) {
			miembroLocal.remove(dao);

			try {
				BeanUtils.copyProperties(dto, dao);

				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbGrupo(dao.getId().getNbGrupo());
				dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setNbHorario(dao.getId().getNbHorario());
				dto.setCedula(dao.getId().getCedula());
				Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
				if (null != miembro) {
					dto.setNombre(miembro.getNombre());
					dto.setTipoEmp(miembro.getTipoEmp());
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

	public static Date movDias(Date date, int dias) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date); // Configuramos la fecha que se recibe

		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de d�as a
													// a�adir, o
													// restar en caso de
													// d�as<0

		return calendar.getTime();
	}

	public MiembroDto saveInscripcion(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula, String nombre, String tipoEmp, boolean renovable,
			boolean profesor, String fechaStr, List<HorarioDto> horarioDtos) throws Exception {
		Date fechaRenovacion = null;
		if (renovable && fechaStr != null && fechaStr.length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			fechaRenovacion = sdf.parse(fechaStr);
		}
		Miembro dao = miembroLocal.saveInscripcion(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
				cedula, nombre, tipoEmp, renovable, profesor, fechaRenovacion);
		MiembroDto dto = null;
		if (dao != null) {
			dto = new MiembroDto();
			BeanUtils.copyProperties(dto, dao);
			dto.setCedula(dao.getId().getCedula());
			Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
			if (null != miembro) {
				dto.setNombre(miembro.getNombre());
				dto.setTipoEmp(miembro.getTipoEmp());
			}

			dto.setNbModulo(dao.getId().getNbModulo());
			dto.setNbGrupo(dao.getId().getNbGrupo());
			dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
			dto.setNbActividad(dao.getId().getNbActividad());
			dto.setNbHorario(dao.getId().getNbHorario());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// sdf.parse(fechaOld.toString());
			String fechaNow = sdf.format(date);
			if (dto.isRenovable() && dto.getFeRenovacion() != null
					&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
				dto.setRenovableVencido(true);
			}

		}

		return dto;
	}

	public MiembroDto findMiembroByCedula(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula) {
		MiembroPK miembroPK = new MiembroPK();
		miembroPK.setNbModulo(nbModulo);
		miembroPK.setNbGrupo(nbGrupoModulo);
		miembroPK.setNbSubGrupo(nbSubGrupoModulo);
		miembroPK.setNbActividad(nbActividad);
		miembroPK.setNbHorario(nbHorario);
		miembroPK.setCedula(cedula);
		Miembro dao = miembroLocal.find(miembroPK);
		MiembroDto dto = new MiembroDto();
		if (dao != null) {
			try {
				BeanUtils.copyProperties(dto, dao);

				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbGrupo(dao.getId().getNbGrupo());
				dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setNbHorario(dao.getId().getNbHorario());
				dto.setCedula(dao.getId().getCedula());
				Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
				if (null != miembro) {
					dto.setNombre(miembro.getNombre());
					dto.setTipoEmp(miembro.getTipoEmp());
				}
				dto.setFeRegistro(dao.getFeRegistro());
				dto.setFeRenovacion(dao.getFeRenovacion());
				dto.setProfesor(dao.isProfesor());
				dto.setRenovable(dao.isRenovable());
				if (null != dto.getFeRenovacion()) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					dto.setFechaStr(sdf.format(dto.getFeRenovacion()));
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

	public MiembroDto update(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo, String nbActividad,
			String nbHorario, String cedula, String nombre, String tipoEmp, boolean renovable, boolean profesor,
			Date fechaRenovacion) {

		Miembro dao = miembroLocal.update(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario, cedula,
				"Nombre Temporal", "TiepoEmpleadoTemporal", renovable, profesor, fechaRenovacion);
		MiembroDto dto = null;
		if (dao != null) {
			dto = new MiembroDto();
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setCedula(dao.getId().getCedula());
				Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
				if (null != miembro) {
					dto.setNombre(miembro.getNombre());
					dto.setTipoEmp(miembro.getTipoEmp());
				}

				dto.setNbModulo(dao.getId().getNbModulo());
				dto.setNbGrupo(dao.getId().getNbGrupo());
				dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setNbHorario(dao.getId().getNbHorario());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();// sdf.parse(fechaOld.toString());
				String fechaNow = sdf.format(date);
				if (dto.isRenovable() && dto.getFeRenovacion() != null
						&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
					dto.setRenovableVencido(true);
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

	public List<MiembroDto> moduloActividadDesdeHasta(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, Date feDesdeStr, Date feHastaStr) {

		/**
		 * Buscamos por cada texto y sumamos por cada texto para saber cuantos
		 * tenemos de cada uno
		 */
		List<Miembro> miembros = null;
		/**
		 * Buscamos por cada texto y sumamos por cada texto para saber cuantos
		 * tenemos de cada uno
		 */
		miembros = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
				feDesdeStr, feHastaStr);

		List<MiembroDto> lista = new ArrayList<MiembroDto>();
		if (miembros != null && miembros.size() > 0) {
			MiembroDto dto = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// sdf.parse(fechaOld.toString());
			String fechaNow = sdf.format(date);
			for (Miembro dao : miembros) {
				dto = new MiembroDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCedula(dao.getId().getCedula());
					Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
					if (null != miembro) {
						dto.setNombre(miembro.getNombre());
						dto.setTipoEmp(miembro.getTipoEmp());
					}
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbHorario(dao.getId().getNbHorario());
					if (dto.isRenovable() && dto.getFeRenovacion() != null
							&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
						dto.setRenovableVencido(true);
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

	/*
	 * Tomamos las inscripciones.. (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#estadisticasGetMiembroChart(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.util.Date, java.util.Date)
	 */
	public MiembroChartDto estadisticasGetMiembroChart(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, Date feDesdeStr, Date feHastaStr) {
		MiembroChartDto out = new MiembroChartDto();
		List<MiembroDto> lista = new ArrayList<MiembroDto>();
		Map<String, MiembroDto> cedulaUnico = new HashMap<String, MiembroDto>();
		MiembroChartDto miembroChartDto = new MiembroChartDto();
		MiembroDto dto = null;
		List<MiembroDto> dtos = new ArrayList<MiembroDto>();
		ChartDto chartDto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new LinkedHashMap<String, Integer>();
		DiaUtil diaUtil = new DiaUtil();
		String[] strDays = diaUtil.getDiasSemana();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String fechaNow = sdf.format(date);

		for (String dia : strDays) {
			cuantosPorCadaUno.put(dia, 0);
		}

		/** saco todos los miembros filtrando por las fechas */
		List<Miembro> objs = null;
		try {
			objs = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
					feDesdeStr, feHastaStr);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (objs != null && objs.size() > 0) {
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			Calendar cal = Calendar.getInstance();
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = new Date();
			fechaNow = sdf.format(date);

			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */

			SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat("dd-MM-yyyy");
			Date dateDDMMYYYY = new Date();
			/** saco todos los miembros filtrando por la cedula */
			List<Miembro> miembros = null;
			for (Miembro obj1 : objs) {
				miembros = miembroLocal.findMiembrosByCedula(obj1.getId().getNbModulo(), obj1.getId().getNbGrupo(),
						obj1.getId().getNbSubGrupo(), obj1.getId().getNbActividad(), obj1.getId().getNbHorario(),
						obj1.getId().getCedula(), feDesdeStr, feHastaStr);

				if (miembros != null && miembros.size() > 0) {
					for (Miembro dao : miembros) {
						date = dao.getFeRegistro();
						cal.setTime(date);

						String diaName = strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
						int cont = cuantosPorCadaUno.get(diaName);
						cuantosPorCadaUno.put(diaName, ++cont);

						/** Si no existe dto con la cedula.. ***/
						if (!cedulaUnico.containsKey(dao.getId().getCedula())) {
							dto = new MiembroDto();
							try {
								BeanUtils.copyProperties(dto, dao);
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							dto.setCedula(dao.getId().getCedula());
							Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(),
									manejadorDB);
							if (null != miembro) {
								dto.setNombre(miembro.getNombre());
								dto.setTipoEmp(miembro.getTipoEmp());
							}
							dto.setNbModulo(dao.getId().getNbModulo());
							dto.setNbGrupo(dao.getId().getNbGrupo());
							dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
							dto.setNbActividad(dao.getId().getNbActividad());
							dto.setNbHorario(dao.getId().getNbHorario());
							if (dto.isRenovable() && dto.getFeRenovacion() != null
									&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
								dto.setRenovableVencido(true);
							}
							if (dto.getAsistenciaDtos() == null) {
								dto.setAsistenciaDtos(new ArrayList<AsistenciaDto>());
							}
							cedulaUnico.put(dao.getId().getCedula(), dto);
						}

						/** Recuperamos al usuario **/
						dto = cedulaUnico.get(dao.getId().getCedula());

						String fecha = sdfDDMMYYYY.format(dao.getFeRegistro());
						dto.setFechaStr(fecha);

						AsistenciaDto asistenciaDto = new AsistenciaDto();
						asistenciaDto.setNbModulo(dao.getId().getNbModulo());
						asistenciaDto.setNbGrupo(dao.getId().getNbGrupo());
						asistenciaDto.setNbSubGrupo(dao.getId().getNbSubGrupo());
						asistenciaDto.setNbActividad(dao.getId().getNbActividad());
						asistenciaDto.setNbHorario(dao.getId().getNbHorario());
						asistenciaDto.setFecha(fecha);
						asistenciaDto.setDia(diaName);
						dto.getAsistenciaDtos().add(asistenciaDto);
						cedulaUnico.put(dao.getId().getCedula(), dto);

					}
				}
			}
			// LLenamos los usuarios
			for (String key : cedulaUnico.keySet()) {
				dtos.add(cedulaUnico.get(key));
			}
		}

		DataChart data = new DataChart();
		List<String> labels = new ArrayList<String>();
		DatasetsChart datasetsChart = new DatasetsChart();
		List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
		datasetsChart.setLabel("Estadisticas");
		List<Integer> data1 = new ArrayList<Integer>();
		List<String> backgroundColor = new ArrayList<String>();
		StringBuilder color = null;
		List<String> borderColor = new ArrayList<String>();
		// Imprimimos el Map con un Iterador
		Iterator it = cuantosPorCadaUno.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			labels.add(key);
			data1.add(cuantosPorCadaUno.get(key));
			// to get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black

			Random rnd = new Random();
			;

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");
			backgroundColor.add(color.toString());

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");

			borderColor.add(color.toString());

		}
		data.setLabels(labels);
		datasetsChart.setData(data1);
		datasetsChart.setBorderWidth(1);
		datasetsChart.setBackgroundColor(backgroundColor);
		datasetsChart.setBorderColor(borderColor);
		datasetsCharts.add(datasetsChart);
		data.setDatasets(datasetsCharts);
		OptionsChart options = new OptionsChart();
		ScalesChart scales = new ScalesChart();
		List<YAxesChart> yAxesChartLst = new ArrayList<YAxesChart>();
		YAxesChart yAxesCharts = new YAxesChart();
		TicksChart ticks = new TicksChart();
		ticks.setBeginAtZero(true);
		yAxesCharts.setTicks(ticks);
		yAxesChartLst.add(yAxesCharts);
		scales.setyAxes(yAxesChartLst);
		options.setScales(scales);
		chartDto.setType("bar");
		chartDto.setData(data);
		chartDto.setOptions(options);

		miembroChartDto.setChartDto(chartDto);
		miembroChartDto.setMiembroDtos(dtos);
		return miembroChartDto;
	}

	/*
	 * Graficas Asistencia por modulos y actividad (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#estadisticasByModuloActividad(java.
	 * lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Deprecated
	public ChartDto estadisticasByModuloActividad(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, Date feDesdeStr, Date feHastaStr) {
		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();

		List<Miembro> objs = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad,
				nbHorario);

		if (objs != null && objs.size() > 0) {
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			List<Miembro> activObjs = null;
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			for (Miembro obj : objs) {
				activObjs = miembroLocal.findMiembrosByActMod(nbModulo, obj.getId().getNbGrupo(),
						obj.getId().getNbSubGrupo(), obj.getId().getNbActividad(), obj.getId().getNbHorario(),
						feDesdeStr, feHastaStr);
				cuantosPorCadaUno.put(obj.getId().getNbHorario(), activObjs == null ? 0 : activObjs.size());
			}
		}

		DataChart data = new DataChart();
		List<String> labels = new ArrayList<String>();
		DatasetsChart datasetsChart = new DatasetsChart();
		List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
		datasetsChart.setLabel("Estadisticas");
		List<Integer> data1 = new ArrayList<Integer>();
		List<String> backgroundColor = new ArrayList<String>();
		StringBuilder color = null;
		List<String> borderColor = new ArrayList<String>();
		// Imprimimos el Map con un Iterador
		Iterator it = cuantosPorCadaUno.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			labels.add(key);
			data1.add(cuantosPorCadaUno.get(key));

			// to get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black

			Random rnd = new Random();
			;

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");
			backgroundColor.add(color.toString());

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");

			borderColor.add(color.toString());

		}
		data.setLabels(labels);
		datasetsChart.setData(data1);
		datasetsChart.setBorderWidth(1);
		datasetsChart.setBackgroundColor(backgroundColor);
		datasetsChart.setBorderColor(borderColor);
		datasetsCharts.add(datasetsChart);
		data.setDatasets(datasetsCharts);
		OptionsChart options = new OptionsChart();
		ScalesChart scales = new ScalesChart();
		List<YAxesChart> yAxesChartLst = new ArrayList<YAxesChart>();
		YAxesChart yAxesCharts = new YAxesChart();
		TicksChart ticks = new TicksChart();
		ticks.setBeginAtZero(true);
		yAxesCharts.setTicks(ticks);
		yAxesChartLst.add(yAxesCharts);
		scales.setyAxes(yAxesChartLst);
		options.setScales(scales);
		dto.setType("bar");
		dto.setData(data);
		dto.setOptions(options);

		return dto;
	}

	/*
	 * Graficas Asistencia por modulos (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#findByEstadisticasByModulo(java.lang.
	 * String, java.util.Date, java.util.Date)
	 */
	public ChartDto findAsistenciaByModulo(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, Date feDesdeStr, Date feHastaStr) {
		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();
		List<Miembro> objs = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad);
		if (objs != null && objs.size() > 0) {
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			List<Miembro> activObjs = null;
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			for (Miembro obj : objs) {
				activObjs = miembroLocal.findMiembrosByActMod(nbModulo, obj.getId().getNbGrupo(),
						obj.getId().getNbSubGrupo(), obj.getId().getNbActividad(), obj.getId().getNbHorario(),
						feDesdeStr, feHastaStr);
				cuantosPorCadaUno.put(obj.getId().getNbActividad(), activObjs == null ? 0 : activObjs.size());
			}
		}

		DataChart data = new DataChart();
		List<String> labels = new ArrayList<String>();
		DatasetsChart datasetsChart = new DatasetsChart();
		List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
		datasetsChart.setLabel("Estadisticas");
		List<Integer> data1 = new ArrayList<Integer>();
		List<String> backgroundColor = new ArrayList<String>();
		StringBuilder color = null;
		List<String> borderColor = new ArrayList<String>();
		// Imprimimos el Map con un Iterador
		Iterator it = cuantosPorCadaUno.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			labels.add(key);
			data1.add(cuantosPorCadaUno.get(key));

			// to get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black

			Random rnd = new Random();
			;

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");
			backgroundColor.add(color.toString());

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");

			borderColor.add(color.toString());

		}
		data.setLabels(labels);
		datasetsChart.setData(data1);
		datasetsChart.setBorderWidth(1);
		datasetsChart.setBackgroundColor(backgroundColor);
		datasetsChart.setBorderColor(borderColor);
		datasetsCharts.add(datasetsChart);
		data.setDatasets(datasetsCharts);
		OptionsChart options = new OptionsChart();
		ScalesChart scales = new ScalesChart();
		List<YAxesChart> yAxesChartLst = new ArrayList<YAxesChart>();
		YAxesChart yAxesCharts = new YAxesChart();
		TicksChart ticks = new TicksChart();
		ticks.setBeginAtZero(true);
		yAxesCharts.setTicks(ticks);
		yAxesChartLst.add(yAxesCharts);
		scales.setyAxes(yAxesChartLst);
		options.setScales(scales);
		dto.setType("bar");
		dto.setData(data);
		dto.setOptions(options);

		return dto;
	}

	/*
	 * Graficas Asistencia por modulos (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#findByEstadisticasByModulo(java.lang.
	 * String, java.util.Date, java.util.Date)
	 */
	public ChartDto findByEstadisticasByModulo(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, Date feDesdeStr, Date feHastaStr) {
		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();
		List<Miembro> objs = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad);
		if (objs != null && objs.size() > 0) {
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			List<Miembro> activObjs = null;
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			for (Miembro obj : objs) {
				activObjs = miembroLocal.findMiembrosByActMod(nbModulo, obj.getId().getNbGrupo(),
						obj.getId().getNbSubGrupo(), obj.getId().getNbActividad(), obj.getId().getNbHorario(),
						feDesdeStr, feHastaStr);
				cuantosPorCadaUno.put(obj.getId().getNbActividad(), activObjs == null ? 0 : activObjs.size());
			}
		}

		DataChart data = new DataChart();
		List<String> labels = new ArrayList<String>();
		DatasetsChart datasetsChart = new DatasetsChart();
		List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
		datasetsChart.setLabel("Estadisticas");
		List<Integer> data1 = new ArrayList<Integer>();
		List<String> backgroundColor = new ArrayList<String>();
		StringBuilder color = null;
		List<String> borderColor = new ArrayList<String>();
		// Imprimimos el Map con un Iterador
		Iterator it = cuantosPorCadaUno.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			labels.add(key);
			data1.add(cuantosPorCadaUno.get(key));

			// to get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black

			Random rnd = new Random();
			;

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");
			backgroundColor.add(color.toString());

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");

			borderColor.add(color.toString());

		}
		data.setLabels(labels);
		datasetsChart.setData(data1);
		datasetsChart.setBorderWidth(1);
		datasetsChart.setBackgroundColor(backgroundColor);
		datasetsChart.setBorderColor(borderColor);
		datasetsCharts.add(datasetsChart);
		data.setDatasets(datasetsCharts);
		OptionsChart options = new OptionsChart();
		ScalesChart scales = new ScalesChart();
		List<YAxesChart> yAxesChartLst = new ArrayList<YAxesChart>();
		YAxesChart yAxesCharts = new YAxesChart();
		TicksChart ticks = new TicksChart();
		ticks.setBeginAtZero(true);
		yAxesCharts.setTicks(ticks);
		yAxesChartLst.add(yAxesCharts);
		scales.setyAxes(yAxesChartLst);
		options.setScales(scales);
		dto.setType("bar");
		dto.setData(data);
		dto.setOptions(options);

		return dto;
	}

	public List<MiembroDto> findMiembrosByActMod(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();// sdf.parse(fechaOld.toString());
		String fechaNow = sdf.format(date);
		List<Miembro> miembros = miembroLocal.findMiembrosByActMod(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad);
		List<MiembroDto> lista = new ArrayList<MiembroDto>();
		if (miembros != null && miembros.size() > 0) {
			MiembroDto dto = null;
			for (Miembro dao : miembros) {
				dto = new MiembroDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCedula(dao.getId().getCedula());
					Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
					if (null != miembro) {
						dto.setNombre(miembro.getNombre());
						dto.setTipoEmp(miembro.getTipoEmp());
					}
					dto.setNbModulo(dao.getId().getNbModulo());
					dto.setNbGrupo(dao.getId().getNbGrupo());
					dto.setNbSubGrupo(dao.getId().getNbSubGrupo());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbHorario(dao.getId().getNbHorario());

					if (dto.isRenovable() && dto.getFeRenovacion() != null
							&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
						dto.setRenovableVencido(true);
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

		Collections.sort(lista, new MiembroDtoOrderByNombre());

		return lista;
	}

	public MiembroDto findUserFromPersonalTdoEmpleados(String ceula) {
		Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(ceula, manejadorDB);
		MiembroDto miembroDto = null;
		if (null != miembro) {
			miembroDto = new MiembroDto();
			miembroDto.setCedula(ceula);
			miembroDto.setNombre(miembro.getNombre());
			miembroDto.setTipoEmp(miembro.getTipoEmp());
		}

		return miembroDto;
	}

}

package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.Parametro;
import ve.org.bcv.dao.local.AsistenciaLocal;
import ve.org.bcv.dao.local.AtencionLocal;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.dao.local.ParametroLocal;
import ve.org.bcv.dto.AsistenciaDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.DataChart;
import ve.org.bcv.dto.DatasetsChart;
import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;
import ve.org.bcv.dto.OptionsChart;
import ve.org.bcv.dto.ScalesChart;
import ve.org.bcv.dto.TicksChart;
import ve.org.bcv.dto.YAxesChart;
import ve.org.bcv.services.AsistenciaService;
import ve.org.bcv.util.DiaUtil;
import ve.org.bcv.util.FechasHorarioCalculos;
import ve.org.bcv.util.ManejadorDB;

@AsistenciaServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AsistenciaServiceImpl implements AsistenciaService {
	@Inject
	private AsistenciaLocal asistenciaLocal;
	@Inject
	private ParametroLocal parametroLocal;
	@Inject
	private MiembroLocal miembroLocal;
	@Inject
	private AtencionLocal atencionLocal;
	@Inject
	ManejadorDB manejadorDB;

	/*
	 * Graficas Atencion por modulos (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#findByEstadisticasByModulo(java.lang.
	 * String, java.util.Date, java.util.Date)
	 */
	public MiembroChartDto buscarByModuloCedula(String nbModulo, String nbGrupo, String nbSubGrupo, String nbActividad,
			String nbHorario, String cedula, Date feDesdeStr, Date feHastaStr) {
		Map<String, MiembroDto> cedulaUnico = new HashMap<String, MiembroDto>();
		MiembroChartDto miembroChartDto = new MiembroChartDto();
		MiembroDto dto = null;
		List<MiembroDto> dtos = new ArrayList<MiembroDto>();
		ChartDto chartDto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new LinkedHashMap<String, Integer>();
		DiaUtil diaUtil = new DiaUtil();
		String[] strDays = diaUtil.getDiasSemana();

		for (String dia : strDays) {
			cuantosPorCadaUno.put(dia, 0);
		}

		/** saco todos los miembros */
		List<Atencion> objs = null;
		try {
			objs = atencionLocal.asistencia(nbModulo, nbGrupo, nbSubGrupo, nbActividad, nbHorario, cedula, feDesdeStr,
					feHastaStr);
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String fechaNow = sdf.format(date);

			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */

			SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat("dd-MM-yyyy");
			Date dateDDMMYYYY = new Date();
			for (Atencion obj : objs) {

				try {
					date = sdf.parse(obj.getId().getStart().substring(0, obj.getId().getStart().indexOf("T")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cal.setTime(date);

				String diaName = strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];

				/**
				 * Solo una cedula de usuario por dia y fecha para saber si
				 * asistio o no
				 */
				int cont = cuantosPorCadaUno.get(diaName);
				cuantosPorCadaUno.put(diaName, ++cont);
				List<Miembro> miembrosCedula = miembroLocal.findMiembrosByCedula(obj.getId().getNbModulo(),
						obj.getId().getNbGrupo(), obj.getId().getNbSubGrupo(), obj.getId().getNbActividad(),
						obj.getId().getNbHorario(), obj.getId().getCedula());
				if (null != miembrosCedula && miembrosCedula.size() > 0) {
					Miembro dao = miembrosCedula.get(0);

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
						if (dto.getAsistenciaDtos() == null) {
							dto.setAsistenciaDtos(new ArrayList<AsistenciaDto>());
						}
						cedulaUnico.put(dao.getId().getCedula(), dto);
					}

					/** Recuperamos al usuario **/
					dto = cedulaUnico.get(dao.getId().getCedula());

					try {
						dateDDMMYYYY = sdf
								.parse(obj.getId().getStart().substring(0, obj.getId().getStart().indexOf("T")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String fecha = sdfDDMMYYYY.format(dateDDMMYYYY);
					dto.setFechaStr(fecha);

					AsistenciaDto asistenciaDto = new AsistenciaDto();
					asistenciaDto.setNbModulo(nbModulo);
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
		MiembroDto miembro = null;
		DecimalFormat df = new DecimalFormat("#.##");
		/*** Calculamos los porcentajes si cumplio o no cumplio */
		for (String key : cedulaUnico.keySet()) {
			miembro = cedulaUnico.get(key);
			Parametro parametrCantDiasAsistPorMes = parametroLocal.find(miembro.getNbModulo(), miembro.getNbGrupo(),
					miembro.getNbSubGrupo(), miembro.getNbActividad(), miembro.getNbHorario(), "cantDiasAsistPorMes");
			Parametro parametrPorcentajeAceptacion = parametroLocal.find(miembro.getNbModulo(), miembro.getNbGrupo(),
					miembro.getNbSubGrupo(), miembro.getNbActividad(), miembro.getNbHorario(), "porcentajeAceptacion");

			if (parametrCantDiasAsistPorMes != null && parametrCantDiasAsistPorMes.getTxValorParametro() != null
					&& parametrPorcentajeAceptacion != null
					&& parametrPorcentajeAceptacion.getTxValorParametro() != null) {
				miembro.setCantDiasAsistPorMes(parametrCantDiasAsistPorMes.getTxValorParametro());
				miembro.setPorcentajeAceptacion(parametrPorcentajeAceptacion.getTxValorParametro() + "%");
				miembro.setDiasTranscurridos(FechasHorarioCalculos.calcularDiasDosfechas(feDesdeStr,
					feHastaStr));
				if (miembro != null && miembro.getAsistenciaDtos() != null) {
					int asistencias = miembro.getAsistenciaDtos().size();

					double porcentaje = (asistencias * 100)
							/ Double.parseDouble(parametrCantDiasAsistPorMes.getTxValorParametro());
					
					      

					miembro.setPorcentajeCompletado(df.format(porcentaje) + "%");
					miembro.setMetaResultado("NO CUMPLIO");
					if (porcentaje >= Double.parseDouble(parametrPorcentajeAceptacion.getTxValorParametro())) {
						miembro.setResultado(true);
						miembro.setMetaResultado("CUMPLIO");
					}
				}

			}
			dtos.add(miembro);
		}

	 

		DataChart data = new DataChart();
		List<String> labels = new ArrayList<String>();
		DatasetsChart datasetsChart = new DatasetsChart();
		List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
		datasetsChart.setLabel("Asistencia");
		List<Integer> data1 = new ArrayList<Integer>();
		List<String> backgroundColor = new ArrayList<String>();
		StringBuilder color = null;
		List<String> borderColor = new ArrayList<String>();
		// Imprimimos el Map con un Iterador
		for (String key : cuantosPorCadaUno.keySet()) {
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

	public List<MiembroDto> findMiembrosAsistenciaByModulo(String nbModulo, String nbGrupo, String nbSubGrupo,
			String nbActividad, String nbHorario, String cedula, Date feDesdeStr, Date feHastaStr) {
		DiaUtil diaUtil = new DiaUtil();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MiembroDto dto = null;
		List<MiembroDto> dtos = new ArrayList<MiembroDto>();
		Date date = new Date();
		String[] strDays = diaUtil.getDiasSemana();
		String fechaNow = sdf.format(date);

		/** saco todos los miembros */
		List<Atencion> objs = null;
		try {
			objs = atencionLocal.byModuloCedulaAtenciones(nbModulo, nbGrupo, nbSubGrupo, nbActividad, nbHorario, cedula,
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
			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			Map<String, String> unicoByDiaByCedula = new LinkedHashMap<String, String>();
			for (Atencion obj : objs) {
				try {
					date = sdf.parse(obj.getId().getStart().substring(0, obj.getId().getStart().indexOf("T")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cal.setTime(date);
				/**
				 * Solo una cedula de usuario por dia y fecha para saber si
				 * asistio o no
				 */
				if (!unicoByDiaByCedula.containsKey(cal.getTime() + "" + strDays[cal.get(Calendar.DAY_OF_WEEK) - 1] + ""
						+ obj.getId().getCedula())) {
					unicoByDiaByCedula.put(
							cal.getTime() + "" + strDays[cal.get(Calendar.DAY_OF_WEEK) - 1] + ""
									+ obj.getId().getCedula(),
							strDays[cal.get(Calendar.DAY_OF_WEEK) - 1] + "" + obj.getId().getCedula());
					List<Miembro> miembrosCedula = miembroLocal.findMiembrosByCedula(nbModulo, nbGrupo, nbSubGrupo,
							nbActividad, nbHorario, obj.getId().getCedula());
					if (null != miembrosCedula && miembrosCedula.size() > 0) {
						Miembro dao = miembrosCedula.get(0);
						dto = new MiembroDto();

						SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat("dd-MM-yyyy");
						Date dateDDMMYYYY = new Date();// sdf.parse(fechaOld.toString());

						try {
							dateDDMMYYYY = sdf
									.parse(obj.getId().getStart().substring(0, obj.getId().getStart().indexOf("T")));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						String fecha = sdfDDMMYYYY.format(dateDDMMYYYY);
						dto.setFechaStr(fecha);
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
						Miembro miembro = miembroLocal.findUserFromPersonalTdoEmpleados(dto.getCedula(), manejadorDB);
						if (null != miembro) {
							dto.setNombre(miembro.getNombre());
							dto.setTipoEmp(miembro.getTipoEmp());
						}
						dto.setNbModulo(dao.getId().getNbModulo());
						dto.setNbActividad(dao.getId().getNbActividad());
						if (dto.isRenovable() && dto.getFeRenovacion() != null
								&& sdf.format(dto.getFeRenovacion()).compareTo(fechaNow) <= 0) {
							dto.setRenovableVencido(true);
						}
						dtos.add(dto);
					}
				}
			}
		}
		return dtos;
	}

	/*
	 * Graficas Atencion por modulos (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#findByEstadisticasByModulo(java.lang.
	 * String, java.util.Date, java.util.Date)
	 */
	// public List<MiembroDto> findMiembrosAsistenciaByModulo(String nbModulo,
	// String nbGrupo, String nbSubGrupo, String nbActividad,String nbHorario,
	// Date feDesdeStr, Date feHastaStr) {
	// String cedula="";
	// //(String nbModulo, String nbGrupo, String nbSubGrupo, String
	// nbActividad,String nbHorario,String cedula, Date feDesdeStr, Date
	// feHastaStr) ;
	// return findMiembrosAsistenciaByModulo( nbModulo, nbGrupo, nbSubGrupo,
	// nbActividad, nbHorario,cedula,feDesdeStr,feHastaStr) ;
	// }

	/*
	 * Graficas Atencion por modulos (non-Javadoc)
	 * 
	 * @see
	 * ve.org.bcv.services.MiembroService#findByEstadisticasByModulo(java.lang.
	 * String, java.util.Date, java.util.Date)
	 */
	// public ChartDto findAsistenciaByModulo(String nbModulo, String nbGrupo,
	// String nbSubGrupo, String nbActividad, Date feDesdeStr, Date feHastaStr)
	// {
	// ChartDto dto = new ChartDto();
	// Map<String, Integer> cuantosPorCadaUno = new LinkedHashMap<String,
	// Integer>();
	// DiaUtil diaUtil = new DiaUtil();
	// String[] strDays = diaUtil.getDiasSemana();
	// for (String dia : strDays) {
	// cuantosPorCadaUno.put(dia, 0);
	// }
	// String cedula = null;
	// /** saco todos los miembros */
	// List<Atencion> objs = null;
	// try {
	// objs = atencionLocal.byModuloCedulaAtenciones( nbModulo, nbGrupo,
	// nbSubGrupo, nbActividad, cedula, feDesdeStr, feHastaStr);
	// } catch (Exception e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// if (objs != null && objs.size() > 0) {
	// /**
	// * Buscamos por cada texto y sumamos por cada texto para saber
	// * cuantos tenemos de cada uno
	// */
	// Calendar cal = Calendar.getInstance();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// Date date = new Date();
	//
	// /**
	// * Buscamos por cada texto y sumamos por cada texto para saber
	// * cuantos tenemos de cada uno
	// */
	// Map<String, String> unicoByDiaByCedula = new LinkedHashMap<String,
	// String>();
	// for (Atencion obj : objs) {
	// try {
	// date = sdf.parse(obj.getId().getStart().substring(0,
	// obj.getId().getStart().indexOf("T")));
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// cal.setTime(date);
	// /**
	// * Solo una cedula de usuario por dia y fecha para saber si
	// * asistio o no
	// */
	// if (!unicoByDiaByCedula.containsKey(cal.getTime() + "" +
	// strDays[cal.get(Calendar.DAY_OF_WEEK) - 1] + ""
	// + obj.getId().getCedula())) {
	// unicoByDiaByCedula.put(
	// cal.getTime() + "" + strDays[cal.get(Calendar.DAY_OF_WEEK) - 1] + ""
	// + obj.getId().getCedula(),
	// strDays[cal.get(Calendar.DAY_OF_WEEK) - 1] + "" +
	// obj.getId().getCedula());
	// int cont = cuantosPorCadaUno.get(strDays[cal.get(Calendar.DAY_OF_WEEK) -
	// 1]);
	// cuantosPorCadaUno.put(strDays[cal.get(Calendar.DAY_OF_WEEK) - 1],
	// ++cont);
	// }
	// }
	// }
	//
	// DataChart data = new DataChart();
	// List<String> labels = new ArrayList<String>();
	// DatasetsChart datasetsChart = new DatasetsChart();
	// List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
	// datasetsChart.setLabel("Asistencia");
	// List<Integer> data1 = new ArrayList<Integer>();
	// List<String> backgroundColor = new ArrayList<String>();
	// StringBuilder color = null;
	// List<String> borderColor = new ArrayList<String>();
	// // Imprimimos el Map con un Iterador
	// for (String key : cuantosPorCadaUno.keySet()) {
	// labels.add(key);
	// data1.add(cuantosPorCadaUno.get(key));
	//
	// // to get rainbow, pastel colors
	// Random random = new Random();
	// final float hue = random.nextFloat();
	// final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
	// final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black
	//
	// Random rnd = new Random();
	// ;
	//
	// color = new StringBuilder("");
	// color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
	// .append(",").append(rnd.nextInt(256)).append(")");
	// backgroundColor.add(color.toString());
	//
	// color = new StringBuilder("");
	// color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
	// .append(",").append(rnd.nextInt(256)).append(")");
	//
	// borderColor.add(color.toString());
	//
	// }
	// data.setLabels(labels);
	// datasetsChart.setData(data1);
	// datasetsChart.setBorderWidth(1);
	// datasetsChart.setBackgroundColor(backgroundColor);
	// datasetsChart.setBorderColor(borderColor);
	// datasetsCharts.add(datasetsChart);
	// data.setDatasets(datasetsCharts);
	// OptionsChart options = new OptionsChart();
	// ScalesChart scales = new ScalesChart();
	// List<YAxesChart> yAxesChartLst = new ArrayList<YAxesChart>();
	// YAxesChart yAxesCharts = new YAxesChart();
	// TicksChart ticks = new TicksChart();
	// ticks.setBeginAtZero(true);
	// yAxesCharts.setTicks(ticks);
	// yAxesChartLst.add(yAxesCharts);
	// scales.setyAxes(yAxesChartLst);
	// options.setScales(scales);
	// dto.setType("bar");
	// dto.setData(data);
	// dto.setOptions(options);
	//
	// return dto;
	// }

}

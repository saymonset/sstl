/**
 * 
 */
package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import ve.org.bcv.dao.AtencionPK;
import ve.org.bcv.dao.Horario;
import ve.org.bcv.dao.Miembro;
import ve.org.bcv.dao.local.AtencionLocal;
import ve.org.bcv.dao.local.HorarioLocal;
import ve.org.bcv.dao.local.MiembroLocal;
import ve.org.bcv.dto.AsistenciaDto;
import ve.org.bcv.dto.AtencionDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.DataChart;
import ve.org.bcv.dto.DatasetsChart;
import ve.org.bcv.dto.MiembroChartDto;
import ve.org.bcv.dto.MiembroDto;
import ve.org.bcv.dto.OptionsChart;
import ve.org.bcv.dto.ScalesChart;
import ve.org.bcv.dto.TicksChart;
import ve.org.bcv.dto.YAxesChart;
import ve.org.bcv.services.AtencionService;
import ve.org.bcv.util.DiaUtil;
import ve.org.bcv.util.ManejadorDB;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 26/07/2016 11:35:53 2016 mail :
 *         oraclefedora@gmail.com
 */

@AtencionServicioType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AtencionServiceImpl implements AtencionService {

	@Inject
	private AtencionLocal atencionLocal;
	@Inject
	private HorarioLocal horarioLocal;
	@Inject
	private MiembroLocal miembroLocal;
	@Inject
	ManejadorDB manejadorDB;

	public AtencionDto save(String text, String id, String start, String end, String nbModulo, String nbGrupoModulo,
			String nbSubGrupoModulo, String nbActividad, String nbHorario, String cedula, String atencionRealizada)
			throws Exception {

		AtencionPK atencionPK = new AtencionPK(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario,
				cedula, start, end, id);
		Atencion dao = new Atencion();
		dao.setId(atencionPK);
		dao.setText(text);
		dao.setAtencionRealizada(atencionRealizada);
		dao = atencionLocal.save(dao);

		AtencionDto dto = new AtencionDto();
		if (dao != null) {
			try {
				BeanUtils.copyProperties(dto, dao);
				dto.setId(dao.getId().getId());
				dto.setCedula(dao.getId().getCedula());
				dto.setEnd(dao.getId().getEnd());
				dto.setStart(dao.getId().getStart());
				dto.setNbActividad(dao.getId().getNbActividad());
				dto.setNbModulo(dao.getId().getNbModulo());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}
	
	
	public List<AtencionDto> deleteAtencionByCedula(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula) {

		List<AtencionDto> atencionesDtos = new ArrayList<AtencionDto>();
		List<Atencion> atenciones = atencionLocal.atencionByCedula(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad, nbHorario, cedula);
		for (Atencion dao : atenciones) {
			atencionLocal.remove(dao);
			AtencionDto dto = new AtencionDto();
			if (dao != null) {
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setId(dao.getId().getId());
					dto.setCedula(dao.getId().getCedula());
					dto.setEnd(dao.getId().getEnd());
					dto.setStart(dao.getId().getStart());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbModulo(dao.getId().getNbModulo());
					atencionesDtos.add(dto);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return atencionesDtos;
	}

	public List<AtencionDto> atencionByCedula(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula) {

		List<AtencionDto> atencionesDtos = new ArrayList<AtencionDto>();
		List<Atencion> atenciones = atencionLocal.atencionByCedula(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad, nbHorario, cedula);
		for (Atencion dao : atenciones) {
			AtencionDto dto = new AtencionDto();
			if (dao != null) {
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setId(dao.getId().getId());
					dto.setCedula(dao.getId().getCedula());
					dto.setEnd(dao.getId().getEnd());
					dto.setStart(dao.getId().getStart());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbModulo(dao.getId().getNbModulo());
					atencionesDtos.add(dto);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return atencionesDtos;
	}

	public ChartDto findByModuloActivdadStadistica(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario) {

		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();
		List<Atencion> atenciones = atencionLocal.findByModuloActivdadStadistica(nbModulo, nbGrupoModulo,
				nbSubGrupoModulo, nbActividad, nbHorario);
		if (atenciones != null && atenciones.size() > 0) {

			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			for (Atencion atenc : atenciones) {
				if (!cuantosPorCadaUno.containsKey(atenc.getText())) {
					cuantosPorCadaUno.put(atenc.getText(), 0);
				}
				int cuantos = cuantosPorCadaUno.get(atenc.getText());
				cuantosPorCadaUno.put(atenc.getText(), ++cuantos);
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

			System.out.println("Clave: " + key + " -> Valor: " + cuantosPorCadaUno.get(key));
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

		// labels.add("Miercoles Desde las 2pm hasta las 04y 30");
		// labels.add("Yoga TRX");
		// labels.add("Lunes desde las 2 a las 4 y 30");
		// labels.add("Domingo");
		// labels.add("jajajja");
		// labels.add("Martes Desde las 2pm hasta las 04y 30");

		// data1.add(19);
		// data1.add(3);
		// data1.add(5);
		// data1.add(2);
		// data1.add(3);

		// backgroundColor.add("rgba(54, 162, 235, 0.2)");
		// backgroundColor.add("rgba(255, 206, 86, 0.2)");
		// backgroundColor.add("rgba(75, 192, 192, 0.2)");
		// backgroundColor.add("rgba(153, 102, 255, 0.2)");
		// backgroundColor.add("rgba(255, 159, 64, 0.2)");
		// datasetsChart.setBackgroundColor(backgroundColor);
		// List<String> borderColor = new ArrayList<String>();
		// borderColor.add("rgba(255,99,132,1)");
		// borderColor.add("rgba(54, 162, 235, 1)");
		// borderColor.add("rgba(255, 206, 86, 1)");
		// borderColor.add("rgba(75, 192, 192, 1)");
		// borderColor.add("rgba(153, 102, 255, 1)");
		// borderColor.add("rgba(255, 159, 64, 1)");
		// datasetsChart.setBorderColor(borderColor);

		// {
		// type: ,
		// data: {
		// labels: [, , , , , ],
		// datasets: [{
		// label: '',
		// data: [],
		// backgroundColor: [
		// '',
		// '',
		// '',
		// '',
		// '',
		// ''
		// ],
		// borderColor: [
		// '',
		// '',
		// '',
		// '',
		// '',
		// ''
		// ],
		// borderWidth:
		// }]
		// },
		// options: {
		// scales: {
		// yAxes: [{
		// ticks: {
		// beginAtZero:true
		// }
		// }]
		// }
		// }
		// }

		return dto;
	}

	public ChartDto findByModuloActivdadStadistica(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String cedula) {
		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();
		List<Atencion> atenciones = atencionLocal.atencionByCedula(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad, nbHorario, cedula);
		if (atenciones != null && atenciones.size() > 0) {

			/**
			 * Buscamos por cada texto y sumamos por cada texto para saber
			 * cuantos tenemos de cada uno
			 */
			for (Atencion atenc : atenciones) {
				if (!cuantosPorCadaUno.containsKey(atenc.getText())) {
					cuantosPorCadaUno.put(atenc.getText(), 0);
				}
				int cuantos = cuantosPorCadaUno.get(atenc.getText());
				cuantosPorCadaUno.put(atenc.getText(), ++cuantos);
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

			System.out.println("Clave: " + key + " -> Valor: " + cuantosPorCadaUno.get(key));
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

	public MiembroChartDto findByEstadisticasByModulo(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String nbHorario, String feDesdeStr, String feHastaStr) {
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

		/** saco todos los miembros */
		List<Horario> objs = null;
		try {
			objs = horarioLocal.atencionByModulo(nbModulo, nbGrupoModulo, nbSubGrupoModulo, nbActividad, nbHorario);
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
			for (Horario obj : objs) {
				/** Sacamos todas las atenciones para cada horario.. */
				List<Atencion> atenciones = atencionLocal.atencionByText(obj.getId().getNbModulo(),
						obj.getId().getNbGrupo(), obj.getId().getNbSubGrupo(), obj.getId().getNbActividad(),
						obj.getId().getNbHorario(), obj.getText(), obj.getId().getId(), feDesdeStr, feHastaStr);

				if (atenciones != null && atenciones.size() > 0) {
					for (Atencion atencion : atenciones) {
						try {
							date = sdf.parse(
									atencion.getId().getStart().substring(0, atencion.getId().getStart().indexOf("T")));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cal.setTime(date);

						String diaName = strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
						int cont = cuantosPorCadaUno.get(diaName);
						cuantosPorCadaUno.put(diaName, ++cont);
						List<Miembro> miembrosCedula = miembroLocal.findMiembrosByCedula(obj.getId().getNbModulo(),
								obj.getId().getNbGrupo(), obj.getId().getNbSubGrupo(), obj.getId().getNbActividad(),
								obj.getId().getNbHorario(), atencion.getId().getCedula());
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
                            String start="";
                            String end="";
							try {
								dateDDMMYYYY = sdf.parse(atencion.getId().getStart().substring(0,
										atencion.getId().getStart().indexOf("T")));
								
								start=atencion.getId().getStart().substring(
										atencion.getId().getStart().indexOf("T")+1);
								end=atencion.getId().getEnd().substring(
										atencion.getId().getStart().indexOf("T")+1);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							String fecha = sdfDDMMYYYY.format(dateDDMMYYYY);
							dto.setFechaStr(fecha);

							AsistenciaDto asistenciaDto = new AsistenciaDto();
							asistenciaDto.setNbModulo(dao.getId().getNbModulo());
							asistenciaDto.setNbGrupo(dao.getId().getNbGrupo());
							asistenciaDto.setNbSubGrupo(dao.getId().getNbSubGrupo());
							asistenciaDto.setNbActividad(dao.getId().getNbActividad());
							asistenciaDto.setNbHorario(dao.getId().getNbHorario());
							asistenciaDto.setFecha(fecha);
							asistenciaDto.setDia(diaName);
							asistenciaDto.setStart(start);
							asistenciaDto.setEnd(end);
							dto.getAsistenciaDtos().add(asistenciaDto);
							cedulaUnico.put(dao.getId().getCedula(), dto);

						}
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

	public List<AtencionDto> byModuloAtenciones(String nbModulo, String nbGrupoModulo, String nbSubGrupoModulo,
			String nbActividad, String horario, String cedula, Date feDesde, Date feHasta) throws Exception {
		List<Atencion> atenciones = atencionLocal.byModuloAtenciones(nbModulo, nbGrupoModulo, nbSubGrupoModulo,
				nbActividad, horario, cedula, feDesde, feHasta);
		List<AtencionDto> atencionesDtos = new ArrayList<AtencionDto>();

		for (Atencion dao : atenciones) {
			AtencionDto dto = new AtencionDto();
			if (dao != null) {
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setId(dao.getId().getId());
					dto.setCedula(dao.getId().getCedula());
					dto.setEnd(dao.getId().getEnd());
					dto.setStart(dao.getId().getStart());
					dto.setNbActividad(dao.getId().getNbActividad());
					dto.setNbModulo(dao.getId().getNbModulo());
					atencionesDtos.add(dto);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return atencionesDtos;
	}

}

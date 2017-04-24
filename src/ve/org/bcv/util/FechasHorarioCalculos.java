/**
 * 
 */
package ve.org.bcv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 10/08/2016 11:04:41
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class FechasHorarioCalculos {
	
	
	
	/**
	 * Recibimos la hora con este formato 2016-07-18T16:30:00
	 * @param fechaHora
	 * @return
	 * @throws ParseException
	 */
	public static boolean diaAnteriorAlActual(String fechaHora) throws ParseException {
        boolean diaPrevious=false;
		StringBuilder hora = new StringBuilder();
		/**Obtenemos la hora separada*/
		hora.append(fechaHora.substring(fechaHora.indexOf("T"),
				fechaHora.length()));
		StringBuilder fechaOld = new StringBuilder();
		/**Obtenemos la fecha separada*/
		fechaOld.append(fechaHora.substring(0, fechaHora.indexOf("T")));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(fechaOld.toString());
		Calendar calendarViejaFecha = Calendar.getInstance();
		calendarViejaFecha.setTime(date);
		// Sunday = 1
		// Monday = 2
		// Tuesday = 3
		// Wednesday = 4
		// Thursday = 5
		// Friday = 6
		// Saturday = 7

		/**Verificamos la fecha actual del sistema*/
		Calendar calendarFechaActual = Calendar.getInstance();
		calendarFechaActual.setTime(new Date());
		/**Numero del dia de la fecha actual*/
		int dayOfWeekToday = calendarFechaActual.get(Calendar.DAY_OF_WEEK);

		int dayOfWeekOld = calendarViejaFecha.get(Calendar.DAY_OF_WEEK);
		// domingo..  = 1
				if (dayOfWeekOld==1){
					diaPrevious=(dayOfWeekOld > dayOfWeekToday);
				}else{
					diaPrevious= (dayOfWeekOld < dayOfWeekToday);
				}
	   return diaPrevious;
	}
	
	/**
	 * Recibimos la hora con este formato 2016-07-18T16:30:00
	 * @param fechaHora
	 * @return
	 * @throws ParseException
	 */
	public static boolean diaNextAlActual(String fechaHora) throws ParseException {
        boolean diaNext=false;
		StringBuilder hora = new StringBuilder();
		/**Obtenemos la hora separada*/
		hora.append(fechaHora.substring(fechaHora.indexOf("T"),
				fechaHora.length()));
		StringBuilder fechaOld = new StringBuilder();
		/**Obtenemos la fecha separada*/
		fechaOld.append(fechaHora.substring(0, fechaHora.indexOf("T")));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(fechaOld.toString());
		Calendar calendarViejaFecha = Calendar.getInstance();
		calendarViejaFecha.setTime(date);
		// Sunday = 1
		// Monday = 2
		// Tuesday = 3
		// Wednesday = 4
		// Thursday = 5
		// Friday = 6
		// Saturday = 7

		/**Verificamos la fecha actual del sistema*/
		Calendar calendarFechaActual = Calendar.getInstance();
		calendarFechaActual.setTime(new Date());
		/**Numero del dia de la fecha actual*/
		int dayOfWeekToday = calendarFechaActual.get(Calendar.DAY_OF_WEEK);

		int dayOfWeekOld = calendarViejaFecha.get(Calendar.DAY_OF_WEEK);
		// domingo..  = 1
				if (dayOfWeekOld==1){
					diaNext=(dayOfWeekOld < dayOfWeekToday);
				}else{
					diaNext= (dayOfWeekOld > dayOfWeekToday);
				}
	   return diaNext;
	}
	
	
	/**
	 * Recibimos la hora con este formato 2016-07-18T16:30:00
	 * @param fechaHora
	 * @return
	 * @throws ParseException
	 */
	public static boolean diaPreviousAlActual(String fechaHora,boolean diaAnterior) throws ParseException {
        boolean diaPrevious=false;
		StringBuilder hora = new StringBuilder();
		/**Obtenemos la hora separada*/
		hora.append(fechaHora.substring(fechaHora.indexOf("T"),
				fechaHora.length()));
		StringBuilder fechaOld = new StringBuilder();
		/**Obtenemos la fecha separada*/
		fechaOld.append(fechaHora.substring(0, fechaHora.indexOf("T")));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(fechaOld.toString());
		Calendar calendarViejaFecha = Calendar.getInstance();
		calendarViejaFecha.setTime(date);
		// Sunday = 1
		// Monday = 2
		// Tuesday = 3
		// Wednesday = 4
		// Thursday = 5
		// Friday = 6
		// Saturday = 7

		/**Verificamos la fecha actual del sistema*/
		Calendar calendarFechaActual = Calendar.getInstance();
		calendarFechaActual.setTime(new Date());
		/**Numero del dia de la fecha actual*/
		int dayOfWeekToday = calendarFechaActual.get(Calendar.DAY_OF_WEEK);

		int dayOfWeekOld = calendarViejaFecha.get(Calendar.DAY_OF_WEEK);
		// domingo..  = 1
				if (dayOfWeekOld==1){
					diaPrevious=(dayOfWeekOld > dayOfWeekToday);
				}else{
					diaPrevious= (dayOfWeekOld < dayOfWeekToday);
				}
	   return diaPrevious;
	}

	/**
	 * Recibimos la hora con este formato 2016-07-18T16:30:00
	 * @param fechaHora
	 * @return
	 * @throws ParseException
	 */
	public static String changeFecha(String fechaHora) throws ParseException {

		StringBuilder hora = new StringBuilder();
		/**Obtenemos la hora separada*/
		hora.append(fechaHora.substring(fechaHora.indexOf("T"),
				fechaHora.length()));
		StringBuilder fechaOld = new StringBuilder();
		/**Obtenemos la fecha separada*/
		fechaOld.append(fechaHora.substring(0, fechaHora.indexOf("T")));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(fechaOld.toString());
		Calendar calendarViejaFecha = Calendar.getInstance();
		calendarViejaFecha.setTime(date);
		// Sunday = 1
		// Monday = 2
		// Tuesday = 3
		// Wednesday = 4
		// Thursday = 5
		// Friday = 6
		// Saturday = 7

		/**Verificamos la fecha actual del sistema*/
		Calendar calendarFechaActual = Calendar.getInstance();
		calendarFechaActual.setTime(new Date());
		/**Numero del dia de la fecha actual*/
		int dayOfWeekToday = calendarFechaActual.get(Calendar.DAY_OF_WEEK);

		int dayOfWeekOld = calendarViejaFecha.get(Calendar.DAY_OF_WEEK);
		/**Numero del dia de la fecha Vieja en bd*/
		int diasToSumOrRestar = 0;
		// domingo..  = 1
		if (dayOfWeekOld==1){
			diasToSumOrRestar = dayOfWeekOld - dayOfWeekToday;
			/**Nos vamos para el proximo domingo.. */
			diasToSumOrRestar+=7;
		}else{
			diasToSumOrRestar = dayOfWeekOld - dayOfWeekToday;	
		}

		
		/**Fecha a mostrar para sacarla en el calendario*/
		Date fechaShow = movDias(calendarFechaActual.getTime(),
				diasToSumOrRestar);
		Calendar calendarFechaAlterada= Calendar.getInstance();
		 calendarFechaAlterada.setTime(fechaShow);
		dayOfWeekToday = calendarFechaAlterada.get(Calendar.DAY_OF_WEEK);
	   return sdf.format(fechaShow)+hora.toString();
		//return fechaHora;
	}

	public static Date movDias(Date date, int dias) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date); // Configuramos la fecha que se recibe

		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de días a añadir, o
													// restar en caso de días<0

		return calendar.getTime();
	}
	
	public static int calcularDiasDosfechas(Date date1,Date date2){
		
		Calendar c = Calendar.getInstance();
		 
		//fecha inicio
		 
		Calendar fechaInicio = new GregorianCalendar();
		 
		fechaInicio.setTime(date1);
		 
		//fecha fin
		 
		Calendar fechaFin = new GregorianCalendar();
		 
		fechaFin.setTime(date2);
		 
		//restamos las fechas como se puede ver son de tipo Calendar,
		 
		//debemos obtener el valor long con getTime.getTime.
		 
		c.setTimeInMillis(
		 
		       fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());
		 
		//la resta provoca que guardamos este valor en c,
		 
		//los milisegundos corresponde al tiempo en dias
		 int numDias=0;
		//asi sabemos cuantos dias
		if (!date1.equals(date2)){
			numDias=c.get(Calendar.DAY_OF_YEAR);
		}
		 
		System.out.println("N. dias" + c.get(Calendar.DAY_OF_YEAR));
		return numDias+1;
		
	}

}

/**
 * 
 */
package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco 18/07/2016 14:23:48 2016 mail :
 *         oraclefedora@gmail.com
 */
public class MainTest {
	public static void main(String[] args) {
		String fecha = "2016-07-31T15:30:00";

		try {
			System.out.println(changeFecha(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Recibimos la hora con este formato 2016-07-18T16:30:00
	 * @param fechaHora
	 * @return
	 * @throws ParseException
	 */
	private static String changeFecha(String fechaHora) throws ParseException {

		StringBuilder hora = new StringBuilder();
		/**Obtenemos la hora separada*/
		hora.append(fechaHora.substring(fechaHora.indexOf("T"),
				fechaHora.length()));
		StringBuilder fechaOld = new StringBuilder();
		/**Obtenemos la fecha separada*/
		fechaOld.append(fechaHora.substring(0, fechaHora.indexOf("T")));
		System.out.println("Fecha Vieja es=" + fechaOld.toString());

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
		System.out.println("Fecha actual es="+ sdf.format(calendarFechaActual.getTime()));
		/**Numero del dia de la fecha actual*/
		int dayOfWeekToday = calendarFechaActual.get(Calendar.DAY_OF_WEEK);
		System.out.println("dayOfWeek today =" + dayOfWeekToday);

		int dayOfWeekOld = calendarViejaFecha.get(Calendar.DAY_OF_WEEK);
		/**Numero del dia de la fecha Vieja en bd*/
		System.out.println("dayOfWeek old =" + dayOfWeekOld);
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
		System.out.println("dayOfWeek today =" + dayOfWeekToday
				+ ",Fecha alterada=" + sdf.format(fechaShow));
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
}

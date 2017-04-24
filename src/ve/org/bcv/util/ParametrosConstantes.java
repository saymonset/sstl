/**
 * 
 */
package ve.org.bcv.util;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/08/2016 14:27:46
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface ParametrosConstantes {
	String txNombreParametroNumerocupos ="numerocupos";
	String txValorParametroNumerocupos = "100";
	String txObservacionesParametroNumerocupos ="numerocupos";
	String tipoParametroNumerocupos ="i"; // entero
	 

	String txNombreParametroRenovacion ="renovacionPorDias";
	String txValorParametroRenovacion = "30";
	String txObservacionesParametroRenovacion ="renovacionPorDias";
	String tipoParametroRenovacion ="i"; // i entero, c caracater, f fecha 
	/**
	 * 
	 * Corresponde a la cantidad de días a asistir por mes.
	 * */
	String txNombreParametroCantDiasAsistPorMes ="cantDiasAsistPorMes";
	String txValorParametroCantDiasAsistPorMes = "30";
	String txObservacionesParametroCantDiasAsistPorMes ="Corresponde a la cantidad de días a asistir por mes.";
	String tipoParametroCantDiasAsistPorMes ="i"; // i entero, c caracater, f fecha 

	/**
	 * 
	 * Corresponde a la cantidad de días a asistir por mes.
	 * */
	String txNombreParametroPorcentajeAceptacion ="porcentajeAceptacion";
	String txValorParametroPorcentajeAceptacion = "50";
	String txObservacionesParametroPorcentajeAceptacion="Porcentaje de Aceptaci�n";
	String tipoParametroPorcentajeAceptacion="i"; // i entero, c caracater, f fecha 
	
	
	/**
	 * 
	 * Acceso directo a inscripciones
	 * */
	String txNombreParametroAccesoDirectoInscripcion ="accesoDirectoInscripcion";
	String txValorParametroAccesoDirectoInscripcion = "NO";
	String txObservacionesParametroAccesoDirectoInscripcion = "";
	String tipoParametroAccesoDirectoInscripcion="b"; // Boolean 
}

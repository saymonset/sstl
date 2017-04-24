package ve.org.bcv.dto;

import java.util.Comparator;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   19 de ene. de 2017 3:00:22 p. m.
 *
 * mail: oraclefedora@gmail.com
 * 
 * Ordenamos por nombre la clase miembro dto 
 */
public class MiembroDtoOrderByNombre  implements Comparator<MiembroDto> {

	public int compare(MiembroDto a, MiembroDto b) {
		// TODO Auto-generated method stub
		if (a.getNombre()!=null && null !=b.getNombre()){
			   return a.getNombre().compareToIgnoreCase(b.getNombre());	
		}else{
			   return 0;	
		}
		
	}

}

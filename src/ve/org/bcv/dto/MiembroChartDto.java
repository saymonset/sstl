package ve.org.bcv.dto;

import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
   10 de feb. de 2017 8:31:33 a. m.
 *
 * mail: oraclefedora@gmail.com
 */
public class MiembroChartDto {
 private List<MiembroDto> miembroDtos;
 private ChartDto chartDto;

 public ChartDto getChartDto() {
	return chartDto;
}
public void setChartDto(ChartDto chartDto) {
	this.chartDto = chartDto;
}
public List<MiembroDto> getMiembroDtos() {
	return miembroDtos;
}
public void setMiembroDtos(List<MiembroDto> miembroDtos) {
	this.miembroDtos = miembroDtos;
}
}

package metodos2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Metodos2 {
	
	/**ArrayList a Vector
	 * 
	 * @param arrayList
	 * @return
	 */
	public static Vector<?> ALtoVector(ArrayList<?> arrayList) 
	{
		Vector<Object> v = new Vector<Object>(arrayList.size(),1);
		for (Object i: arrayList) {
			v.add(i);
		}
		return v;
	}
	/**Array a Vector
	 * 
	 * @param array
	 * @return
	 */
	public static Vector<?> ArtoVector(Object[] array) 
	{
		Vector<Object> v = new Vector<Object>(array.length,1);
		for (Object i: array) {
			v.add(i);
		}
		return v;
	}
	/**Array a ArrayList
	 * 
	 * @param array
	 * @return
	 */
	public static ArrayList<Object> arrayToArrayList(Object[] array){
		ArrayList<Object> A = new ArrayList<Object>();
		for (Object i: array) {
			A.add(i);
		}
		return A;
	}
	
	@SuppressWarnings("deprecation")
	public static LocalDate DateToLocalDate(Date fecha){
		LocalDate FechaF;
		//+1 mes porque Date trabaja con meses de 0 a 11 y LocalDate de 1 a 12
		FechaF = LocalDate.of(fecha.getYear(), fecha.getMonth()+1, fecha.getDate());
		return FechaF;
	}
	
	@SuppressWarnings("deprecation")
	public static Date LocalDateToDate(LocalDate fecha){
		//-1 mes porque Date trabaja con meses de 0 a 11 y LocalDate de 1 a 12
		Date FechaF = new Date(fecha.getYear(), fecha.getMonthValue()-1, fecha.getDayOfMonth());
		return FechaF;
	}
	@SuppressWarnings("deprecation")
	public static java.sql.Date DateToSQLDate(Date fecha){
		java.sql.Date FechaF = new java.sql.Date(fecha.getYear(), fecha.getMonth(), fecha.getDate());
		return FechaF;
	}
	public static java.sql.Date LocalDateToSQLDate(LocalDate fecha) {
		Date FechaF = LocalDateToDate(fecha);
		return DateToSQLDate(FechaF);
	}
	
	public static String LocalDateToString(LocalDate fecha) {
//		le sumammos 1900 a los años, porque date trata los años desde el 1900
//		ejmp: el año 2000, seria 100, para mostrarlo correctamente tenemos que sumarle 1900
		String Fecha = fecha.getDayOfMonth()+"/"+(fecha.getMonthValue())+"/"+(fecha.getYear()+1900);
		return Fecha;
	}
	
	@SuppressWarnings("deprecation")
	public static String DateToString(Date fecha) {
//		le sumammos 1900 a los años, porque date trata los años desde el 1900
//		ejmp: el año 2000, seria 100, para mostrarlo correctamente tenemos que sumarle 1900
		
		String mes = (fecha.getMonth()+1)+"";
		if (mes.length()==1)
			mes = "0"+mes;
		
		String Fecha = fecha.getDate()+"/"+mes+"/"+(fecha.getYear()+1900);
		return Fecha;
	}
}

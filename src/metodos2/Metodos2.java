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
		LocalDate FechaF = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDate()); 
		return FechaF;
	}
	
	@SuppressWarnings("deprecation")
	public static Date LocalDateToDate(LocalDate fecha){
		Date FechaF = new Date(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth());
		return FechaF;
	}
	@SuppressWarnings("deprecation")
	public static java.sql.Date DateToSQLDate(Date fecha){
		java.sql.Date FechaF = new java.sql.Date(fecha.getYear(), fecha.getMonth(), fecha.getDate());
		return FechaF;
	}
	public static java.sql.Date LocalDateToSQLDate(LocalDate fechaBaja) {
		Date FechaF = LocalDateToDate(fechaBaja);
		return DateToSQLDate(FechaF);
	}
	
	
}

package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

import clases.Empleado;
import clases.Oficina;
import excepciones.ParámetroInálido;
import gbd.Conexion;
import metodos2.Metodos2;

public class EmpleadoBD {
	public static Empleado leerEmpleado(String dni)
	{
		Empleado e = null;
		String sql = "SELECT P.*, E.* FROM persona P JOIN empleado E ON P.dni=E.dni_persona WHERE E.dni_persona =?";
		Connection cn;
		cn = Conexion.getConnOracle();
		dni = dni.toUpperCase();
		
		try {
			
			PreparedStatement st = cn.prepareStatement("alter session set NLS_DATE_FORMAT='DD/MM/YYYY'");
			
			st.executeUpdate();

			st = cn.prepareStatement(sql);
			
			st.setString(1, dni);
			
			ResultSet rs = st.executeQuery(); 

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String ap1 = rs.getString("ap1");
				String ap2 = rs.getString("ap2");
				Date fechaNac = rs.getDate("fecha_nac");
				Date fechaAlt = rs.getDate("fecha_alta");
				String codOfi =  rs.getString("cod_oficina");
				Date fechaBaj = rs.getDate("fecha_baja");
				
				LocalDate fN = Metodos2.DateToLocalDate(fechaNac);
				LocalDate fA = Metodos2.DateToLocalDate(fechaAlt);
				LocalDate fB = null;
				if (fechaBaj != null)
					fB = Metodos2.DateToLocalDate(fechaBaj);
				Oficina o = OficinaBD.leerOficina(codOfi);
				
				e = new Empleado(dni, nombre, ap1, ap2, fN, fA, o, fB);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return e;
	}
	/**
	 * 
	 * @param Comparador
	 * @param Comparacion
	 * @param OrderBy
	 * @param Order
	 * @return
	 * @throws ParámetroInálido 
	 */
	public static Vector<Empleado> leerEmpleados(String Comparador, String Comparacion, String OrderBy, boolean Ascendente) throws ParámetroInálido{
		Vector<Empleado> es = new Vector<Empleado>(0,1);
		Empleado e = null;
		String sql = null;
		Connection cn;
		if (Comparador == null || Comparacion == null) {
			throw new ParámetroInálido();
		}
		else
		{
//			Dependiendo del campo tendremos que añadirle un prefijo u otro
			switch (Comparador) {
				case "dni","nombre","ap1","ap2","fecha_nac": {
					Comparador = "P."+Comparador;
					break;
				}
				case "fecha_alta","cod_oficina","fecha_baja": {
					Comparador = "E."+Comparador;
					break;
				}
			}


			if (OrderBy != null) {
//				Dependiendo del campo tendremos que añadirle un prefijo u otro
				switch (OrderBy) {
					case "dni","nombre","ap1","ap2","fecha_nac": {
						OrderBy = "P."+OrderBy;
						break;
					}
					case "fecha_alta","cod_oficina","fecha_baja": {
						OrderBy = "E."+OrderBy;
						break;
					}
				}

				if (Ascendente) {

					sql = "SELECT P.*, E.* FROM persona P JOIN empleado E ON P.dni=E.dni_persona WHERE "+Comparador+" like ? ORDER BY "+OrderBy;
				} else {
					sql = "SELECT P.*, E.* FROM persona P JOIN empleado E ON P.dni=E.dni_persona WHERE "+Comparador+" like ? ORDER BY "+OrderBy+" DESC";
				}
			} 
			else{
				sql = "SELECT P.*, E.* FROM persona P JOIN empleado E ON P.dni=E.dni_persona WHERE "+Comparador+" like ?";
				
			}

			cn = Conexion.getConnOracle();

			try {
				PreparedStatement st = cn.prepareStatement("alter session set NLS_DATE_FORMAT='DD/MM/YYYY'");
				
				st.executeUpdate();

				st = cn.prepareStatement(sql);
				
				st.setString(1, "%"+Comparacion+"%");
				
				ResultSet rs = st.executeQuery(); 

				while (rs.next()) {
					String dni = rs.getString("dni");
					String nombre = rs.getString("nombre");
					String ap1 = rs.getString("ap1");
					String ap2 = rs.getString("ap2");
					Date fechaNac = rs.getDate("fecha_nac");
					Date fechaAlt = rs.getDate("fecha_alta");
					String codOfi =  rs.getString("cod_oficina");
					Date fechaBaj = rs.getDate("fecha_baja");
					
					LocalDate fN = Metodos2.DateToLocalDate(fechaNac);
					LocalDate fA = Metodos2.DateToLocalDate(fechaAlt);
					LocalDate fB = null;
					if (fechaBaj != null)
						fB = Metodos2.DateToLocalDate(fechaBaj);
					Oficina o = OficinaBD.leerOficina(codOfi);
					
					e = new Empleado(dni, nombre, ap1, ap2, fN, fA, o, fB);

					es.add(e);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}


		return es;
	}

	public static Vector<Empleado> leerEmpleados() throws ParámetroInálido{
		Vector<Empleado> os = leerEmpleados("1", "1", "dni", true);
		return os;
	}

	public static Vector<Empleado> leerEmpleados(String Comparador, String Comparacion) throws ParámetroInálido{
		Vector<Empleado> os = leerEmpleados(Comparador, Comparacion, null, true);
		return os;
	}

	public static Vector<Empleado> leerEmpleados(String Comparador, String Comparacion, String OrderBy) throws ParámetroInálido{
		Vector<Empleado> os = leerEmpleados(Comparador, Comparacion, OrderBy, true);
		return os;
	}

	public static void grabaEmpleado(Empleado e)
	{
		String sql = "INSERT INTO persona (dni,nombre,ap1,ap2,fecha_nac) VALUES (?,?,?,?,?)";
		Connection cn = Conexion.getConnOracle();
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);

			st.setString(1, e.getDNI().toUpperCase());
			st.setString(2, e.getNombre());
			st.setString(3, e.getAp1());
			st.setString(4, e.getAp2());
			st.setDate(5, Metodos2.LocalDateToSQLDate(e.getFechaNac()));

			st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		sql = "INSERT INTO empleado (fecha_Alta,cod_oficina,dni_persona,fecha_baja) VALUES (?,?,?,?)";
		try {
			st = cn.prepareStatement(sql);

			st.setDate(1, Metodos2.LocalDateToSQLDate(e.getFechaAlta()));
			st.setString(2, e.getOficinaActual().getCodigo());
			st.setString(3, e.getDNI().toUpperCase());
			if (e.getFechaBaja() != null)
				st.setDate(4, Metodos2.LocalDateToSQLDate(e.getFechaBaja()));
			else
				st.setDate(4, null);

			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static void modificarEmpleado(String dni, String nombre, String ap1, String ap2, Date fechaNac, Date fechaAlt, String codOfi, Date fechaBaj) {
		String sql = "UPDATE persona SET nombre = ?, ap1 = ?, ap2 = ?, fecha_nac = ? WHERE dni = ?";
		Connection cn = Conexion.getConnOracle();

		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, nombre);
			st.setString(2, ap1);
			st.setString(3, ap2);
			st.setDate(4, Metodos2.DateToSQLDate(fechaNac));
			st.setString(5, dni.toUpperCase());

			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "UPDATE empleado SET fecha_Alta = ?, cod_oficina = ?, fecha_baja = ? WHERE dni_persona = ?";
		try {
			st = cn.prepareStatement(sql);
			st.setDate(1, Metodos2.DateToSQLDate(fechaAlt));
			st.setString(2, codOfi);
			if (fechaBaj != null)
				st.setDate(3, Metodos2.DateToSQLDate(fechaBaj));
			else
				st.setDate(3, null);
			st.setString(4, dni.toUpperCase());

			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void eliminarEmpleado(String dni) {
		String sql = "DELETE FROM empleado WHERE dni_persona = ?";
		Connection cn = Conexion.getConnOracle();
		dni = dni.toUpperCase();

		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, dni);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "DELETE FROM persona WHERE dni = ?";
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, dni);

			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean empleadoExiste(String dni) {
		Empleado e = null;
		boolean existe = false;
		e = leerEmpleado(dni);

		if (e != null)
		{
			existe = true;
		}
		return existe;
	}
	/**Recibe un vector de Empleados y devuelve un vector de vectores de Empleados.
	 * 
	 * @param emples
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<Vector> GeneraVectorEmple(Vector<Empleado> emples){
		Vector<Vector> Empleados = new Vector<Vector>(0,1);
		for (int i = 0; i<emples.size(); i++) {
			Empleados.add(new Vector<Object>(0,1)); //Crea un nuevo vector y le añade todos los valores del empleado correspondiente
			Empleados.get(i).add(emples.get(i).getDNI());
			Empleados.get(i).add(emples.get(i).getNombre());
			Empleados.get(i).add(emples.get(i).getAp1());
			Empleados.get(i).add(emples.get(i).getAp2());
			Empleados.get(i).add((Metodos2.LocalDateToString(emples.get(i).getFechaNac())));
			Empleados.get(i).add(emples.get(i).getOficinaActual().getCodigo());
			Empleados.get(i).add(Metodos2.LocalDateToString(emples.get(i).getFechaAlta()));
			if (emples.get(i).getFechaBaja() != null)
				Empleados.get(i).add(Metodos2.LocalDateToString(emples.get(i).getFechaBaja()));
			else
				Empleados.get(i).add("(null)");
		}

		return Empleados;
	}
}

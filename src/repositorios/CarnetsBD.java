package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import clases.CarnetConducir;
import excepciones.ParámetroInálido;
import gbd.Conexion;

public class CarnetsBD {
	
	public static CarnetConducir leerCarnet(String codigo)
	{
		CarnetConducir c = null;
		String sql = "SELECT * FROM carnetconducir WHERE codigo =?";
		Connection cn;
		cn = Conexion.getConnOracle();

		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, codigo);

			ResultSet rs = st.executeQuery(); 
			
			if (rs.next()) {
				String descrp = rs.getString("descrp");
				c = new CarnetConducir(codigo, descrp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
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
	public static Vector<CarnetConducir> leerCarnets(String Comparador, String Comparacion, String OrderBy, boolean Ascendente) throws ParámetroInálido{
		Vector<CarnetConducir> cs = new Vector<CarnetConducir>(0,1);
		CarnetConducir c = null;
		String sql = null;
		Connection cn;
		if (Comparador == null || Comparacion == null) {
			throw new ParámetroInálido();
		}
		else
		{
			if (OrderBy != null) {
				if (Ascendente) {
					sql = "SELECT * FROM carnetconducir WHERE "+Comparador+" LIKE ? ORDER BY "+OrderBy;
				} else {
					sql = "SELECT * FROM carnetconducir WHERE "+Comparador+" LIKE ? ORDER BY "+OrderBy+" DESC";
				}
			} else
			{
				sql = "SELECT * FROM carnetconducir WHERE "+Comparador+" LIKE ?";
			}
			
			cn = Conexion.getConnOracle();

			try {
				PreparedStatement st = cn.prepareStatement(sql);

				st.setString(1, "%"+Comparacion+"%");
				

				ResultSet rs = st.executeQuery(); 
				
				while (rs.next()) {
					String codigo = rs.getString("codigo");
					String descrp = rs.getString("descrp");
					c = new CarnetConducir(codigo, descrp);
					
					cs.add(c);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		return cs;
	}

	public static Vector<CarnetConducir> leerCarnets() throws ParámetroInálido{
		Vector<CarnetConducir> cs = leerCarnets("1", "1", "codigo", true);
		return cs;
	}
	
	public static Vector<CarnetConducir> leerCarnets(String Comparador, String Comparacion) throws ParámetroInálido{
		Vector<CarnetConducir> cs = leerCarnets(Comparador, Comparacion, null, true);
		return cs;
	}
	
	public static Vector<CarnetConducir> leerCarnets(String Comparador, String Comparacion, String OrderBy) throws ParámetroInálido{
		Vector<CarnetConducir> cs = leerCarnets(Comparador, Comparacion, OrderBy, true);
		return cs;
	}
	
	public static void grabaCarnet(CarnetConducir o)
	{
		String sql = "INSERT INTO carnetconducir (codigo,descrp) VALUES (?,?)";
		Connection cn = Conexion.getConnOracle();
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);

			st.setString(1, o.getCodigo());
			st.setString(2, o.getDescrp());

			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void modificarCarnet(String codigo, String descrp) {
		String sql = "UPDATE carnetconducir SET descrp = ? WHERE codigo = ?";
		Connection cn = Conexion.getConnOracle();
		
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, descrp);
			st.setString(2, codigo);
			
			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarCarnet(String codigo) {
		String sql = "DELETE FROM carnetconducir WHERE codigo = ?";
		Connection cn = Conexion.getConnOracle();
		
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, codigo);
			
			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean CarnetExiste(String codigo) {
		CarnetConducir c = null;
		boolean existe = false;
		c = leerCarnet(codigo);
		
		if (c != null)
		{
			existe = true;
		}
		return existe;
	}
	/**Recibe un vector de Carnets y devuelve un vector de vectores de CarnetConducir.
	 * 
	 * @param ofis
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<Vector> GeneraVectorcarnet(Vector<CarnetConducir> carnets){
		Vector<Vector> Carnets = new Vector<Vector>(0,1);
		for (int i = 0; i<carnets.size(); i++) {
			Carnets.add(new Vector<Object>(0,1)); //Crea un nuevo vector y le añade todos los valores del carnet correspondiente
			Carnets.get(i).add(carnets.get(i).getCodigo());
			Carnets.get(i).add(carnets.get(i).getDescrp());
		}
		
		return Carnets;
	}

}

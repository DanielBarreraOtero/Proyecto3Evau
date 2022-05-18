package repositorios;

import java.sql.*;
import java.util.Vector;

import clases.Oficina;
import excepciones.ParámetroInálido;
import gbd.Conexion;

public class OficinaBD {
	
	public static Oficina leerOficina(String codigo)
	{
		Oficina o = null;
		String sql = "SELECT * FROM oficina WHERE codigo =?";
		Connection cn;
		cn = Conexion.getConnOracle();

		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, codigo);

			ResultSet rs = st.executeQuery(); 
			
			if (rs.next()) {
				String descrp = rs.getString("descrp");
				String localidad = rs.getString("localidad");
				String provincia =  rs.getString("provincia");
				boolean esAeropuerto =  rs.getBoolean("esaeropuerto");
				o = new Oficina(codigo, descrp, localidad, provincia, esAeropuerto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return o;
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
	public static Vector<Oficina> leerOficinas(String Comparador, String Comparacion, String OrderBy, boolean Ascendente) throws ParámetroInálido{
		Vector<Oficina> os = new Vector<Oficina>(0,1);
		Oficina o = null;
		String sql = null;
		Connection cn;
		if (Comparador == null || Comparacion == null) {
			throw new ParámetroInálido();
		}
		else
		{
			if (OrderBy != null) {
				if (Ascendente) {
					sql = "SELECT * FROM oficina WHERE "+Comparador+" LIKE ? ORDER BY "+OrderBy;
				} else {
					sql = "SELECT * FROM oficina WHERE "+Comparador+" LIKE ? ORDER BY "+OrderBy+" DESC";
				}
			} else
			{
				sql = "SELECT * FROM oficina WHERE "+Comparador+" LIKE ?";
			}
			
			cn = Conexion.getConnOracle();

			try {
				PreparedStatement st = cn.prepareStatement(sql);
				

				if (Comparacion.equals("true")) {
					st.setInt(1, 1);
				}
				else if (Comparacion.equals("false")) 
				{
					st.setInt(1, 0);
				}
				else {
					st.setString(1, "%"+Comparacion+"%");
				}
				

				ResultSet rs = st.executeQuery(); 
				
				while (rs.next()) {
					String codigo = rs.getString("codigo");
					String descrp = rs.getString("descrp");
					String localidad = rs.getString("localidad");
					String provincia =  rs.getString("provincia");
					boolean esAeropuerto =  rs.getBoolean("esaeropuerto");
					o = new Oficina(codigo, descrp, localidad, provincia, esAeropuerto);
					
					os.add(o);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		return os;
	}

	public static Vector<Oficina> leerOficinas() throws ParámetroInálido{
		Vector<Oficina> os = leerOficinas("1", "1", "codigo", true);
		return os;
	}
	
	public static Vector<Oficina> leerOficinas(String Comparador, String Comparacion) throws ParámetroInálido{
		Vector<Oficina> os = leerOficinas(Comparador, Comparacion, null, true);
		return os;
	}
	
	public static Vector<Oficina> leerOficinas(String Comparador, String Comparacion, String OrderBy) throws ParámetroInálido{
		Vector<Oficina> os = leerOficinas(Comparador, Comparacion, OrderBy, true);
		return os;
	}
	
	public static void grabaOficina(Oficina o)
	{
		String sql = "INSERT INTO oficina (codigo,descrp,provincia,localidad,esaeropuerto) VALUES (?,?,?,?,?)";
		Connection cn = Conexion.getConnOracle();
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);

			st.setString(1, o.getCodigo());
			st.setString(2, o.getDescrp());
			st.setString(3, o.getProvincia());
			st.setString(4, o.getLocalidad());
			st.setInt(5, 0);
			if(o.isAeropuerto()) {
				st.setInt(5, 1);
			}

			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void modificarOficina(String codigo, String descrp, String provincia, String localidad, boolean esaeropuerto) {
		String sql = "UPDATE oficina SET descrp = ?, provincia = ?, localidad = ?, esaeropuerto = ? WHERE codigo = ?";
		Connection cn = Conexion.getConnOracle();
		
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, descrp);
			st.setString(2, provincia);
			st.setString(3, localidad);
			st.setInt(4, 0);
			if(esaeropuerto) {
				st.setInt(4, 1);
			}
			st.setString(5, codigo);
			
			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarOficina(String codigo) {
		String sql = "DELETE FROM oficina WHERE codigo = ?";
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

	public static boolean OficinaExiste(String codigo) {
		Oficina o = null;
		boolean existe = false;
		o = leerOficina(codigo);
		
		if (o != null)
		{
			existe = true;
		}
		return existe;
	}
	/**Recibe un vector de oficinas y devuelve un vector de vectores de Oficina.
	 * 
	 * @param ofis
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<Vector> GeneraVectorOfi(Vector<Oficina> ofis){
		Vector<Vector> Oficinas = new Vector<Vector>(0,1);
		for (int i = 0; i<ofis.size(); i++) {
			Oficinas.add(new Vector<Object>(0,1)); //Crea un nuevo vector y le añade todos los valores de la oficina correspondiente
			Oficinas.get(i).add(ofis.get(i).getCodigo());
			Oficinas.get(i).add(ofis.get(i).getDescrp());
			Oficinas.get(i).add(ofis.get(i).getLocalidad());
			Oficinas.get(i).add(ofis.get(i).getProvincia());
			Oficinas.get(i).add(ofis.get(i).isAeropuerto());
		}
		
		return Oficinas;
	}

}

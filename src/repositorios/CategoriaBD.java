package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import clases.Categoria;
import clases.Oficina;
import excepciones.ParámetroInálido;
import gbd.Conexion;

public class CategoriaBD {

	public static Categoria leerCategoria(String codigo)
	{
		Categoria c = null;
		String sql = "SELECT * FROM categoria WHERE codigo =?";
		Connection cn;
		cn = Conexion.getConnOracle();

		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, codigo);

			ResultSet rs = st.executeQuery(); 
			
			if (rs.next()) {
				String descrp = rs.getString("descrp");
				double recargo = rs.getDouble("recargo");
				c = new Categoria(codigo, descrp, recargo);
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
	public static Vector<Categoria> leerCategorias(String Comparador, String Comparacion, String OrderBy, boolean Ascendente) throws ParámetroInálido{
		Vector<Categoria> cs = new Vector<Categoria>(0,1);
		Categoria c = null;
		String sql = null;
		Connection cn;
		if (Comparador == null || Comparacion == null) {
			throw new ParámetroInálido();
		}
		else
		{
			if (OrderBy != null) {
				if (Ascendente) {
					sql = "SELECT * FROM categoria WHERE "+Comparador+" LIKE ? ORDER BY "+OrderBy;
				} else {
					sql = "SELECT * FROM categoria WHERE "+Comparador+" LIKE ? ORDER BY "+OrderBy+" DESC";
				}
			} else
			{
				sql = "SELECT * FROM categoria WHERE "+Comparador+" LIKE ?";
			}
			
			cn = Conexion.getConnOracle();

			try {
				PreparedStatement st = cn.prepareStatement(sql);
				

				if (Comparador.equals("recargo")) {
					
					try {
						Double Comp = Double.parseDouble(Comparacion);
						st.setDouble(1, Comp);
					} catch (Exception e) {
//						TODO CONSEGUIR QUE SALGA UNA EXCEPCION EN CASO DE ERROR
//						JOptionPane.showInternalMessageDialog(,"Valor de recargo inválido.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					st.setString(1, "%"+Comparacion+"%");
				}
				

				ResultSet rs = st.executeQuery(); 
				
				while (rs.next()) {
					String codigo = rs.getString("codigo");
					String descrp = rs.getString("descrp");
					double recargo = rs.getDouble("recargo");
					c = new Categoria(codigo, descrp, recargo);
					
					cs.add(c);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		return cs;
	}

	public static Vector<Categoria> leerCategorias() throws ParámetroInálido{
		Vector<Categoria> cs = leerCategorias("1", "1", "codigo", true);
		return cs;
	}
	
	public static Vector<Categoria> leerCategorias(String Comparador, String Comparacion) throws ParámetroInálido{
		Vector<Categoria> cs = leerCategorias(Comparador, Comparacion, null, true);
		return cs;
	}
	
	public static Vector<Categoria> leerCategorias(String Comparador, String Comparacion, String OrderBy) throws ParámetroInálido{
		Vector<Categoria> cs = leerCategorias(Comparador, Comparacion, OrderBy, true);
		return cs;
	}
	
	public static void grabaCategoria(Categoria c)
	{
		String sql = "INSERT INTO categoria (codigo,descrp,recargo) VALUES (?,?,?)";
		Connection cn = Conexion.getConnOracle();
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);

			st.setString(1, c.getCodigo());
			st.setString(2, c.getDescripcion());
			st.setDouble(3, c.getRecargo());

			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void modificarCategoria(String codigo, String descrp, double recargo) {
		String sql = "UPDATE categoria SET descrp = ?, recargo = ? WHERE codigo = ?";
		Connection cn = Conexion.getConnOracle();
		
		PreparedStatement st;
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, descrp);
			st.setDouble(2, recargo);
			st.setString(3, codigo);
			
			st.executeUpdate();
			st.executeUpdate("commit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarCategoria(String codigo) {
		String sql = "DELETE FROM categoria WHERE codigo = ?";
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

	public static boolean CategoriaExiste(String codigo) {
		Categoria c = null;
		boolean existe = false;
		c = leerCategoria(codigo);
		
		if (c != null)
		{
			existe = true;
		}
		return existe;
	}
	/**Recibe un vector de categorias y devuelve un vector de vectores de Categorias.
	 * 
	 * @param ofis
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<Vector> GeneraVectorCate(Vector<Categoria> categs){
		Vector<Vector> Categorias = new Vector<Vector>(0,1);
		for (int i = 0; i<categs.size(); i++) {
			Categorias.add(new Vector<Object>(0,1)); //Crea un nuevo vector y le añade todos los valores de la categoria correspondiente
			Categorias.get(i).add(categs.get(i).getCodigo());
			Categorias.get(i).add(categs.get(i).getDescripcion());
			Categorias.get(i).add(categs.get(i).getRecargo());
		}
		
		return Categorias;
	}
	
}

package gbd;

import java.sql.*;

public class Conexion {
	
	private static Connection conexion;

	public static Connection getConnOracle()
	{
		if (conexion == null) 
		{
			Connection cn;
			try {
				cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","AlquilerVehiculos","12345");
				conexion=cn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conexion;
	}
}

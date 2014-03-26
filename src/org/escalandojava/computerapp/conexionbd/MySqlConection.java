package org.escalandojava.computerapp.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 *  Es la clase que te permite crear conexiones, es decir es que
 *  va tocar la puerta a la base de datos y obtendra la llave de
 *  ingreso
 */
public class MySqlConection {

	private static ResourceBundle rb = ResourceBundle.getBundle(
			"database");
	
	static {
		try {
			//Reflexion: Instancia la clase Driver
			Class.forName(rb.getString("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConection() {
		try {
			return DriverManager.getConnection(rb.getString("url"), 
																		 rb.getString("username"),
																		 rb.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Pruebas del acceso a la bd
	public static void main(String[] args) {
			@SuppressWarnings("unused")
			Connection conn = new MySqlConection().getConection();
	}

}

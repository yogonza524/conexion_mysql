package com.ministerio.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseApplicationTests {

	@Test
	public void connectionTest() throws Exception {
		Connection con = getConnection();

		//Insert some data
		createAltaComplejidad(new Random().nextInt(100000),"Gonza","Pellegriini 820", "Migoya", 11, "Kinesiologia", "2");

		//Get data
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ops.alta_complejidad");

		while (rs.next()) {
			Integer id = rs.getInt("id_alta_complejidad");
			String nombre = rs.getString("nombre");
			String domicilio = rs.getString("domicilio");
			String director = rs.getString("director");
			String camas = rs.getString("camas");

			System.out.println("Nombre: " + nombre + ", Domicilio: " + domicilio + ", ID: " + id + ", Camas: " + camas);
			System.out.println("-----------------------------------------------");
		}
	}

	public Connection getConnection() throws Exception {

		// Establece el nombre del driver a utilizar
		String dbDriver = "com.mysql.cj.jdbc.Driver";

		// Establece la conexion a utilizar contra la base de datos
		String dbConnString = "jdbc:mysql://localhost:3310/ops";

		// Establece el usuario de la base de datos
		String dbUser = "test";

		// Establece la contrase�a de la base de datos
		String dbPassword = "W8uYnyh6";

		// Establece el driver de conexion
		Class.forName(dbDriver).newInstance();

		// Retorna la conexion
		return DriverManager.getConnection(dbConnString, dbUser, dbPassword);
	}

	public void createAltaComplejidad(int id, String nombre, String domicilio, String director,
									  int capacidad, String especialidad,String camas) throws Exception {

		// the mysql insert statement
		String query = " insert into alta_complejidad (id_alta_complejidad, nombre, domicilio, director, capacidad, especialidad, camas)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = getConnection().prepareStatement(query);
		preparedStmt.setInt(1, id);
		preparedStmt.setString(2, nombre);
		preparedStmt.setString(3, domicilio);
		preparedStmt.setString(4, director);
		preparedStmt.setInt(5, capacidad);
		preparedStmt.setString(6,especialidad);
		preparedStmt.setString(7,camas);

		// execute the preparedstatement
		preparedStmt.execute();
	}

}

package data;

import java.sql.*;
import java.util.ArrayList;
import utils.ApplicationException;

import entidades.Ataque;
import entidades.Personaje;
import entidades.Usuario;

public class DataAtaque {
	public DataAtaque() {

	}

	public ArrayList<Ataque> getAllAtaques() {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Ataque> lista = new ArrayList<Ataque>();

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from ataque",
					PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated
			// on the db
			// by the autoincrement column. Otherwise don't use it

			// after executing the insert use the following lines to retrieve the id
			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {
					Ataque a = new Ataque();
					a.setId_ataque(rs.getInt("id_ataque"));
					a.setEnergia_requerida(rs.getInt("energia_requerida"));
					a.setNombre_ataque(rs.getString("nombre_ataque"));
					lista.add(a);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return lista;
	}

	public void add(Ataque ataque) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into ataque(id_ataque,nombre_ataque,energia_requerida)" + " values(null,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated
			// on the db
			// by the autoincrement column. Otherwise don't use it

			stmt.setString(1, ataque.getNombre_ataque());
			stmt.setInt(2, ataque.getEnergia_requerida());
			stmt.execute();

			// after executing the insert use the following lines to retrieve the id
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				ataque.setId_ataque(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Ataque get(int id) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Ataque ataqueEdit = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from ataque where id_ataque = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				ataqueEdit = new Ataque();
				ataqueEdit.setId_ataque(rs.getInt("id_ataque"));
				ataqueEdit.setNombre_ataque(rs.getString("nombre_ataque"));
				ataqueEdit.setEnergia_requerida(rs.getInt("energia_requerida"));

			} else {
				System.out.println("Ataque no encontrado");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ataqueEdit;
	}

	public void edit(Ataque ataque) throws ApplicationException {
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update ataque set nombre_ataque = ?, energia_requerida = ? where id_ataque = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println(ataque.getId_ataque() + "\n");
			System.out.println(ataque.getNombre_ataque() + "\n");
			System.out.println(ataque.getEnergia_requerida() + "\n");
			stmt.setString(1, ataque.getNombre_ataque());
			stmt.setInt(2, ataque.getEnergia_requerida());
			stmt.setInt(3, ataque.getId_ataque());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

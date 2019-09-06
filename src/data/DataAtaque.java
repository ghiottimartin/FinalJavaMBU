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

	public ArrayList<Ataque> getAllAtaques() throws ApplicationException {
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
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al obtener los ataques.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al obtener los ataques.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
			}
		}

		return lista;
	}

	public ArrayList<Ataque> getAllAtaquesByEnergy(int max_energy) throws ApplicationException {
		ArrayList<Ataque> ataques = new ArrayList<Ataque>();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from ataque where energia_requerida <= ?;", PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, max_energy);

			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {
					Ataque a = new Ataque();
					a.setId_ataque(rs.getInt("id_ataque"));
					a.setEnergia_requerida(rs.getInt("energia_requerida"));
					a.setNombre_ataque(rs.getString("nombre_ataque"));
					ataques.add(a);
				}
			}

		} catch (SQLException e) {
			throw new ApplicationException(e, "Error en la consulta al obtener los ataques.");
		} catch (ApplicationException e) {
			throw new ApplicationException(e, "Error al obtener los ataques.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
			}
		}

		return ataques;
	}

	public ArrayList<Ataque> getNewAtaquesByEnergy(int max_energy, int id_personaje) throws ApplicationException {
		ArrayList<Ataque> ataques = new ArrayList<Ataque>();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from ataque where energia_requerida <= ? and id_ataque not in(select id_ataque from personaje_ataque where id_personaje = ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, max_energy);
			stmt.setInt(2, id_personaje);

			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {
					Ataque a = new Ataque();
					a.setId_ataque(rs.getInt("id_ataque"));
					a.setEnergia_requerida(rs.getInt("energia_requerida"));
					a.setNombre_ataque(rs.getString("nombre_ataque"));
					ataques.add(a);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al obtener los ataques.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al obtener los ataques.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
			}
		}

		return ataques;
	}

	public void add(Ataque ataque) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into ataque(id_ataque,nombre_ataque,energia_requerida) values(default,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, ataque.getNombre_ataque());
			stmt.setInt(2, ataque.getEnergia_requerida());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Lo siento, hubo un error en la consulta crear el ataque.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Lo siento, hubo un error al crear el ataque.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
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
				throw new ApplicationException(new Exception(), "No se encontro el ataque.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar el ataque.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al buscar el ataque.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
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
			stmt.setString(1, ataque.getNombre_ataque());
			stmt.setInt(2, ataque.getEnergia_requerida());
			stmt.setInt(3, ataque.getId_ataque());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al editar el ataque.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al editar el ataque.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
			}
		}
	}

	public void delete(int id) throws ApplicationException {
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("delete from ataque  where id_ataque = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al borrar el ataque.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al borrar el ataque.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Perdone por los inconvenientes causados");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(e, "Error al desconectarse de la base de datos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(ex, "Error al desconectarse de la base de datos.");
			}
		}
	}
}

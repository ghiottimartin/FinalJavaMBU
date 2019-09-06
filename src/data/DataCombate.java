package data;

import java.sql.*;
import java.util.ArrayList;

import entidades.Ataque;
import entidades.Combate;
import entidades.Personaje;
import utils.ApplicationException;

public class DataCombate {

	public DataCombate() {

	}

	public Combate getCombate(int id) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Combate c = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from combate where id_combate = ?", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			// System.out.println(rs.next());
			if (rs != null && rs.next()) {
				c = new Combate();
				c.setId(rs.getInt("id_combate"));
				c.setIdEnemigo(rs.getInt("id_enemigo"));
				c.setExperiencia(rs.getInt("experiencia"));
			} else {
				throw new ApplicationException(new Exception(), "Combate no encontrado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar el combate.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al buscar el combate.");
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
		return c;
	}

	public ArrayList<Combate> getCombates() throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Combate> lista = new ArrayList<Combate>();
		Combate c;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from combate",
					PreparedStatement.RETURN_GENERATED_KEYS);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					c = new Combate();
					c.setId(rs.getInt("id_combate"));
					c.setIdEnemigo(rs.getInt("id_enemigo"));
					c.setExperiencia(rs.getInt("experiencia"));

					lista.add(c);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar los combates.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al buscar los combates.");
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

	public ArrayList<Ataque> getAtaquesOfPersonaje(int id_personaje) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Ataque> lista = new ArrayList<Ataque>();
		Ataque att;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select ata.* from personaje_ataque pa inner join ataque ata on pa.id_ataque = ata.id_ataque where pa.id_personaje = ?;",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id_personaje);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					att = new Ataque();
					att.setId_ataque(rs.getInt("id_ataque"));
					att.setEnergia_requerida(rs.getInt("energia_requerida"));
					att.setNombre_ataque(rs.getString("nombre_ataque"));

					lista.add(att);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar los ataques del personaje.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al buscar los ataques del personaje.");
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

	public ArrayList<Ataque> getAtaquesOfPersonajeByEnergia(int id_personaje, int energia) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Ataque> lista = new ArrayList<Ataque>();
		Ataque att;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select ata.* from personaje_ataque pa inner join ataque ata on pa.id_ataque = ata.id_ataque where pa.id_personaje = ? and energia_requerida <= ? ;",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id_personaje);
			stmt.setInt(2, energia);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					att = new Ataque();
					att.setId_ataque(rs.getInt("id_ataque"));
					att.setEnergia_requerida(rs.getInt("energia_requerida"));
					att.setNombre_ataque(rs.getString("nombre_ataque"));

					lista.add(att);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar los ataques posibles del personaje.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al buscar los ataques posibles del personaje.");
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

	public int getExperienciaFromCombate(int id_combate) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int experiencia = 0;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select experiencia from combate where id_combate = ?;", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id_combate);
			rs = stmt.executeQuery();
			// System.out.println(rs.next());
			if (rs != null && rs.next()) {
				experiencia = rs.getInt("experiencia");
			} else {
				throw new ApplicationException(new Exception(), "Combate no encontrado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar la experiencia del combate.");
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error al buscar la experiencia del combate.");
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
		return experiencia;
	}

}

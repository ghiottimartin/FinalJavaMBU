package data;

import java.sql.*;
import java.util.ArrayList;

import entidades.*;
import utils.ApplicationException;

public class DataPersonaje {
	public DataPersonaje() {

	}

	public int add(Personaje p) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into db_tp_java.personaje (id_personaje,nombre,vida,energia,defensa,evasion,experiencia,id_nivel,id_tipo_personaje)"
							+ " values(null,?,?,?,?,?,0,1,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated
			// on the db
			// by the autoincrement column. Otherwise don't use it

			stmt.setString(1, p.getNombre());
			stmt.setInt(2, p.getVida());
			stmt.setInt(3, p.getEnergia());
			stmt.setInt(4, p.getDefensa());
			stmt.setInt(5, p.getEvasion());
			stmt.setInt(6, p.getId_rol());
			stmt.execute();
			System.out.println(p.getEvasion());
			// after executing the insert use the following lines to retrieve the id
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				p.setId(rs.getInt(1));
				System.out.println(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al crear el personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al crear el personaje.");
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
		return p.getId();
	}

	public void update(Personaje p) throws ApplicationException {
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update personaje set id_personaje=?,nombre=?,vida=?,energia=?,defensa=?,evasion=?,experiencia=?, id_nivel=?, id_tipo_personaje=?"
							+ " where id=?");

			stmt.setInt(1, p.getId());
			stmt.setString(2, p.getNombre());
			stmt.setInt(3, p.getVida());
			stmt.setInt(4, p.getEnergia());
			stmt.setInt(5, p.getDefensa());
			stmt.setInt(6, p.getEvasion());
			stmt.setInt(7, p.getExperiencia());
			stmt.setInt(8, p.getId_nivel());
			stmt.setInt(9, p.getId_rol());
			stmt.setInt(10, p.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al modificar el personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al modificar el personaje.");
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

	public void updatePuntos(Personaje p) throws ApplicationException {
		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("update personajes set nivel=?" + " where id=?");

			stmt.setInt(1, 1);
			stmt.setInt(2, p.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al modificar el nivel del personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al modificar el nivel del personaje.");
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

	public Personaje getById(int id_personaje) throws ApplicationException {
		Personaje p = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_personaje,nombre,vida,energia,defensa,evasion,experiencia,id_nivel,id_tipo_personaje from personaje where id_personaje=?");
			stmt.setInt(1, id_personaje);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Personaje();
				p.setId(rs.getInt("id_personaje"));
				p.setNombre(rs.getString("nombre"));
				p.setVida(rs.getInt("vida"));
				p.setEnergia(rs.getInt("energia"));
				p.setDefensa(rs.getInt("defensa"));
				p.setEvasion(rs.getInt("evasion"));
				p.setExperiencia(rs.getInt("experiencia"));
				p.setId_nivel(rs.getInt("id_nivel"));
				p.setId_rol(rs.getInt("id_tipo_personaje"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar el personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar el personaje.");
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

		return p;
	}

	public Personaje getByName(String nombre) throws ApplicationException {
		Personaje p = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_personaje,nombre,vida,energia,defensa,evasion,experiencia, id_nivel,id_tipo_personaje from personaje where nombre=?");
			stmt.setString(1, nombre);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Personaje();
				p.setId(rs.getInt("id_personaje"));
				p.setNombre(rs.getString("nombre"));
				p.setVida(rs.getInt("vida"));
				p.setEnergia(rs.getInt("energia"));
				p.setDefensa(rs.getInt("defensa"));
				p.setEvasion(rs.getInt("evasion"));
				p.setExperiencia(rs.getInt("experiencia"));
				p.setExperiencia(rs.getInt("id_nivel"));
				p.setId_rol(rs.getInt("id_tipo_personaje"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar el personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar el personaje.");
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

		return p;
	}

	public int consultarMax() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int idMax = 0;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select max(id) from personaje");
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				idMax = ((Number) rs.getObject(1)).intValue();

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar maximo id de personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar maximo id de personaje.");
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

		return idMax + 1;

	}

	public void delete(Personaje p) throws ApplicationException {

		PreparedStatement stmt = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("delete from personaje where id=?");
			stmt.setInt(1, p.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al borrar el personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al borrar el personaje.");
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

	public boolean coincideNombre(Personaje p) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean coincide = false;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select nombre from personaje where nombre like ?");
			stmt.setString(1, p.getNombre());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				if (((String) rs.getObject(1)).matches(p.getNombre())) {
					coincide = true;
				} else {
					coincide = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar si el nombre es repetido.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar si el nombre es repetido.");
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

		return coincide;

	}

	public ArrayList<Personaje> getPersonajes() throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Personaje> lista = new ArrayList<Personaje>();

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from personaje",
					PreparedStatement.RETURN_GENERATED_KEYS);
			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {
					Personaje p = new Personaje();

					p.setId(rs.getInt("id_personaje"));
					p.setNombre(rs.getString("nombre"));
					p.setVida(rs.getInt("vida"));
					p.setEnergia(rs.getInt("energia"));
					p.setDefensa(rs.getInt("defensa"));
					p.setEvasion(rs.getInt("evasion"));
					p.setExperiencia(rs.getInt("experiencia"));
					p.setId_nivel(rs.getInt("id_nivel"));
					p.setId_rol(rs.getInt("id_tipo_personaje"));

					lista.add(p);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar los personajes.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar los personajes.");
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

	public void addPersonajeAtaque(int id_personaje, int id_ataque) throws ApplicationException {

		int insert = 0;
		PreparedStatement stmtPersAtaque = null;
		Connection conn = null;

		try {
			conn = FactoryConexion.getInstancia().getConn();
			conn.setAutoCommit(false);

			stmtPersAtaque = conn.prepareStatement(
					"insert into personaje_ataque(`id_personaje_ataque`,`id_personaje`,`id_ataque`) values(null,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmtPersAtaque.setInt(1, id_personaje);
			stmtPersAtaque.setInt(2, id_ataque);

			insert = stmtPersAtaque.executeUpdate();
			if (insert > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta agregar ataque al personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al agregar ataque al personaje.");
		} finally {
			try {
				if (stmtPersAtaque != null)
					stmtPersAtaque.close();
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

	public void addPersonajeUsuario(int id_personaje, int id_usuario) throws ApplicationException {
		int insert = 0;
		PreparedStatement stmtPersUsuario = null;
		Connection conn = null;

		try {
			conn = FactoryConexion.getInstancia().getConn();
			conn.setAutoCommit(false);

			stmtPersUsuario = conn.prepareStatement(
					"insert into usuario_personaje(`id_usuario_personaje`,`id_usuario`,`id_personaje`) values(null,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmtPersUsuario.setInt(1, id_usuario);
			stmtPersUsuario.setInt(2, id_personaje);
			insert = stmtPersUsuario.executeUpdate();
			if (insert > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al agregar personaje al usuario.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al agregar personaje al usuario.");
		} finally {
			try {
				if (stmtPersUsuario != null)
					stmtPersUsuario.close();
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

	public ArrayList<Personaje> getByUser(int id_usuario) throws ApplicationException {
		ArrayList<Personaje> personajes = new ArrayList<Personaje>();
		Personaje p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select per.* from usuario_personaje up inner join personaje per on up.id_personaje = per.id_personaje where up.id_usuario = ?;");
			stmt.setInt(1, id_usuario);
			rs = stmt.executeQuery();
			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {
					p = new Personaje();
					p.setId(rs.getInt("id_personaje"));
					p.setNombre(rs.getString("nombre"));
					p.setVida(rs.getInt("vida"));
					p.setEnergia(rs.getInt("energia"));
					p.setDefensa(rs.getInt("defensa"));
					p.setEvasion(rs.getInt("evasion"));
					p.setExperiencia(rs.getInt("experiencia"));
					p.setId_nivel(rs.getInt("id_nivel"));
					p.setId_rol(rs.getInt("id_tipo_personaje"));

					personajes.add(p);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar personajes.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar personajes.");
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

		return personajes;
	}

	public void updateExperienciaPersonaje(int id_personaje, int cant_experiencia) throws ApplicationException {
		PreparedStatement stmt = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update personaje " + "set experiencia = experiencia + ? " + "where id_personaje = ?;");
			stmt.setInt(1, cant_experiencia);
			stmt.setInt(2, id_personaje);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al modificar personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al modificar personaje.");
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

	public void levelUpPersonaje(AtributosRolNivel atr, int id_personaje, int id_next_nivel)
			throws ApplicationException {
		PreparedStatement stmt = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("update personaje "
					+ "set vida = ?, energia = ?, defensa = ?, evasion = ?, id_nivel = ? " + "where id_personaje = ?;");
			stmt.setInt(1, atr.getVida());
			stmt.setInt(2, atr.getEnergia());
			stmt.setInt(3, atr.getDefensa());
			stmt.setInt(4, atr.getEvasion());
			stmt.setInt(5, id_next_nivel);
			stmt.setInt(6, id_personaje);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al modificar personaje.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al modificar personaje.");
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

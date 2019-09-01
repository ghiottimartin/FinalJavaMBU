package data;

import java.sql.*;
import java.util.ArrayList;
import utils.ApplicationException;
import entidades.Ataque;
import entidades.Partida;

public class DataPartida {
	
	public DataPartida(){
		
	}
	
	public void savePartida(Partida par) throws ApplicationException{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"INSERT INTO partida VALUES (null,?,current_timestamp(),?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			// <{id_partida: }>,<{descripcion: }>,<{fecha_creacion: }>,<{id_torneo: }>,<{id_usuario: }>
			
			stmt.setString(1, par.getDescripcion());
			stmt.setInt(2, par.getId_torneo());
			stmt.setInt(3, par.getId_usuario());
			stmt.execute();

			// after executing the insert use the following lines to retrieve the id
			/*rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				ataque.setId_ataque(rs.getInt("id"));
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al guardar la partida.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al guardar la partida.");
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
	
	public ArrayList<Partida> getAllPartidasFromUser(int id_usuario) throws ApplicationException{
		ArrayList<Partida> partidas = new ArrayList<Partida>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from partida where id_usuario = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id_usuario);
			rs = stmt.executeQuery();
			if (rs != null) {

				while (rs.next()) {
					Partida par = new Partida();
					par.setId_partida(rs.getInt("id_partida"));
					par.setId_torneo(rs.getInt("id_torneo"));
					par.setId_usuario(rs.getInt("id_usuario"));
					par.setDescripcion(rs.getString("descripcion"));
					par.setFecha_creacion(rs.getDate("fecha_creacion"));
					partidas.add(par);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al buscar las partidas del usuario.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al buscar las partidas del usuario.");
		}  finally {
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
		System.out.println(partidas);
		return partidas;
	}
	
	public int getIdUsuarioPersonajeFromTorneo(int id_partida) throws ApplicationException{
		int id_usuario_personaje = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select t.id_usuario_personaje from partida par inner join torneo t on par.id_torneo = t.id_torneo where par.id_partida = ?;",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id_partida);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				id_usuario_personaje = rs.getInt("id_usuario_personaje");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al cargar la partida.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al cargar la partida.");
		}  finally {
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
		
		return id_usuario_personaje;
	}
	
	public int getIdTorneo(int id_partida) throws ApplicationException{
		int id_torneo = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select id_torneo from partida where id_partida = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id_partida);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				id_torneo = rs.getInt("id_torneo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, "Error en la consulta al cargar la partida.");
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			throw new ApplicationException(ex, "Error al cargar la partida.");
		}  finally {
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
		
		return id_torneo;
	}

}

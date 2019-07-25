package data;

import java.sql.*;
import java.util.ArrayList;

import entidades.Torneo;
import entidades.TorneoCombate;
import data.DataCombate;
import utils.ApplicationException;

public class DataTorneo {

	public DataTorneo() {
			
	}
	
	public Torneo getTorneo(int id) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Torneo t = null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from torneo where id_torneo = ?",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,id);
			rs= stmt.executeQuery();
			//System.out.println(rs.next());
			if(rs!=null && rs.next()){
				t = new Torneo();
				t.setId(rs.getInt("id_torneo"));
				t.setIdUsuarioPersonaje(rs.getInt("id_usuario_personaje"));
			} else {
				System.out.println("Torneo no encontrado");
			}			
		} catch(SQLException e) {
		    throw new ApplicationException(e,"Error en el sql al buscar el torneo");
		} catch(ApplicationException ae) {
			throw new ApplicationException(ae, "Torneo no econtrado");
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return t;
	}
	
	public Torneo add(Torneo t) throws ApplicationException{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into torneo(id_torneo,id_usuario_personaje)"+
					" values(null,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
			
			stmt.setInt(1, t.getIdUsuarioPersonaje());
			stmt.execute();
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();

			if(rs!=null && rs.next()){
				t.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public int getIdUsuarioPersonaje(int idUsuario, int idPersonaje) throws ApplicationException{
	
		int idUsuarioPersonaje = 0;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_usuario_personaje from usuario_personaje where id_usuario=? and id_personaje =? ");
			stmt.setInt(1, idUsuario);
			stmt.setInt(2, idPersonaje);
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				idUsuarioPersonaje = rs.getInt("id_usuario_personaje");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException(e,"Error en el sql al buscar el Personaje o Usuario");
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException(ae, "Personaje y/o Usuario no encontrado");
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return idUsuarioPersonaje;
	}
	
	public int getIdPersonajeByIdUsuarioPersonaje(int idUsuarioPersonaje) throws ApplicationException{
	
		int idPersonaje = 0;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_personaje from usuario_personaje where id_usuario_personaje=? ");
			stmt.setInt(1, idUsuarioPersonaje);
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				idPersonaje = rs.getInt("id_personaje");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException(e,"Error en el sql al buscar el Personaje");
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException(ae, "Personaje no encontrado");
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return idPersonaje;
	}
	
	
	
	
	public int getIdCombateActivo(int idTorneo) throws ApplicationException{
	
		int idCombate = 0;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_combate from torneo_combate where id_torneo=? and combate_activo =1 ");
			stmt.setInt(1, idTorneo);
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				idCombate = rs.getInt("id_combate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException(e,"Error en el sql al buscar el Personaje o Usuario");
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException(ae, "Personaje y/o Usuario no encontrado");
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return idCombate;
	}
	
	//select max(id_torneo_combate) from torneo_combate;
	
	public int getIdMaxTorneoCombate() throws ApplicationException{
		
		int idTorneoCombate = 0;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select max(id_torneo_combate) 'id_torneo_combate' from torneo_combate;");
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				idTorneoCombate = rs.getInt("id_torneo_combate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException(e,"Error en el sql al traer el maximo de los torneo-combate");
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException(ae, "Personaje y/o Usuario no encontrado");
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return idTorneoCombate;
	}
	
	public void addTorneoCombates(ArrayList<TorneoCombate> torneoCombates) throws ApplicationException {
		int sizeTorneoCombate = torneoCombates.size();
		System.out.println("aca esta data"+sizeTorneoCombate);
		for(int i = (sizeTorneoCombate-1); i >= 0; i--){
		//for(int i = 2; i < 0; i--){
		System.out.println(torneoCombates.get(i).getCombate_activo());
		System.out.println("Esto es  i =   "+i);
		PreparedStatement stmt=null;
		ResultSet rs=null;
		//String insertQuery = "";
		
		try {
				stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
						" INSERT INTO `db_tp_java`.`torneo_combate`(`id_torneo_combate`,`id_torneo`,`id_combate`,`combate_activo`,`id_siguiente_combate`)"+
						" values(null,?,?,?,null)");
				// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
				// by the autoincrement column. Otherwise don't use it
				
				stmt.setInt(1, torneoCombates.get(i).getId_torneo());
				stmt.setInt(2, torneoCombates.get(i).getId_combate());
				stmt.setInt(3, torneoCombates.get(i).getCombate_activo());
				System.out.println(torneoCombates.get(i).getCombate_activo());
				/*if(torneoCombates.get(i).getId_siguiente_combate() == null){
					stmt.setInt(4,0);
				}
				else
				{
					stmt.setInt(4, torneoCombates.get(i).getId_siguiente_combate());
				}*/
				
				stmt.execute();				
				
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException(e,"Error en el sql al crear torneo combates");
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException(ae, "No se creo el torneoCombate");
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  }
	}
		
}


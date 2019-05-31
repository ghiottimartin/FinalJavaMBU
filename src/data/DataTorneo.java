package data;

import java.sql.*;

import entidades.Torneo;
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
	    throw new ApplicationException("Error en el sql al buscar el torneo",e);
	} catch(ApplicationException ae) {
		throw new ApplicationException("Torneo no econtrado");
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

public void create(Torneo t) throws ApplicationException{
	ResultSet rs = null;
	PreparedStatement stmt = null;
	PreparedStatement stmt2 = null;
	
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
			t.setId(rs.getInt("id_torneo"));
			
		//Despues de crear el torneo se deberian crear las relaciones del torneo con combates
		stmt2=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into torneo_combate(id_torneo_combate,id_torneo,id_combate,combate_activo,id_siguiente_combate)"+
					" values(null,?,1,1,null)",PreparedStatement.RETURN_GENERATED_KEYS);
			
		stmt2.setInt(1, t.getId());
		stmt2.execute();	
			
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
}


}


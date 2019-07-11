package data;

import java.sql.*;

import entidades.Combate;
import utils.ApplicationException;

public class DataCombate {
	
public DataCombate() {
		
	}
public Combate getCombate(int id) throws ApplicationException {
	ResultSet rs = null;
	PreparedStatement stmt = null;
	Combate c = null;
	
	try {
		stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
				"select * from combate where id_combate = ?",PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setInt(1,id);
		rs= stmt.executeQuery();
		//System.out.println(rs.next());
		if(rs!=null && rs.next()){
			c = new Combate();
			c.setId(rs.getInt("id_combate"));
			c.setIdEnemigo(rs.getInt("id_enemigo"));
			c.setExperiencia(rs.getInt("experiencia"));
		} else {
			System.out.println("Combate no encontrado");
		}			
	} catch(SQLException e) {
	    throw new ApplicationException("Error en el sql al buscar el combate",e);
	} catch(ApplicationException ae) {
		throw new ApplicationException("Combate no econtrado");
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
	return c;
}
	
	
}

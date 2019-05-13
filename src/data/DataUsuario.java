package data;

import java.sql.*;
import java.util.ArrayList;

import utils.ApplicationException;
import entidades.Usuario;

public class DataUsuario {
	
	public DataUsuario() {
		
	}
	
	public Usuario getUsuario(String nombreUsuario,String pass) throws ApplicationException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Usuario u = null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id, nombreUsuario, password from usuarios where nombreUsuario = ? and password = ?",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,nombreUsuario);
			stmt.setString(2,pass);
			rs= stmt.executeQuery();
			//System.out.println(rs.next());
			if(rs!=null && rs.next()){
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombreUsuario"));
				u.setPassword(rs.getString("password"));
				//System.out.println(u.getPassword());
			} else {
				System.out.println("Usuario no encontrado");
			}			
		} catch(SQLException e) {
		    throw new ApplicationException("Error en el sql al buscar el usuario",e);
		} catch(ApplicationException ae) {
			throw new ApplicationException("Usuario no econtrado");
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
		
		return u;
	}
	
	public void add(Usuario u) throws ApplicationException{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
	}

}

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
		PreparedStatement stmt = null;
		
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into usuarios(id,nombreUsuario,password,email,fechaCreacion)"+
					" values(null,?,?,?,curdate())",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
			
			stmt.setString(1, u.getNombreUsuario());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getEmail());
			stmt.execute();
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				u.setId(rs.getInt("id"));
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

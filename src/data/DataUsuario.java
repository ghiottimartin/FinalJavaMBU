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
					"select * from usuario where usuario = ? and password = ?",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,nombreUsuario);
			stmt.setString(2,pass);
			rs= stmt.executeQuery();
			//System.out.println(rs.next());
			if(rs!=null && rs.next()){
				u = new Usuario();
				u.setId(rs.getInt("id_usuario"));
				u.setNombreUsuario(rs.getString("usuario"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setRol(rs.getString("rol"));
				//System.out.println(u.getPassword());
			} else {
				System.out.println("Usuario no encontrado");
			}			
		} catch(SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error en el sql al buscar el usuario.\n"+e.getSQLState()+":"+e.getMessage());
			throw ade;
		} catch(ApplicationException ae) {
			ApplicationException ade=new ApplicationException(ae, ae.getMessage());
			throw ade;
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
					"insert into usuario(id_usuario,usuario,password,nombre,apellido,email,fechaCreacion,rol)"+
					" values(null,?,?,?,?,?,current_timestamp(),'usuario')",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
			
			stmt.setString(1, u.getNombreUsuario());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getNombre());
			stmt.setString(4, u.getApellido());
			stmt.setString(5, u.getEmail());
			stmt.execute();
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();

			if(rs!=null && rs.next()){
				u.setId(rs.getInt(1));
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

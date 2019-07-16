package data;

import java.sql.*;
import java.util.ArrayList;
import utils.ApplicationException;

import entidades.Rol;

public class DataRol {
	
	public DataRol(){
		
	}
	
	public ArrayList<Rol> getAllRoles() throws ApplicationException{
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<Rol> roles = new ArrayList<Rol>();
		System.out.println("LLEGO HASTA ACA");
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from tipo_personaje",
					PreparedStatement.RETURN_GENERATED_KEYS);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Rol rol = new Rol();
					rol.setId_rol(rs.getInt("id_tipo_personaje"));
					rol.setDescripcion_rol(rs.getString("descripcion_tipo_personaje"));
					rol.setCantidad_vida(rs.getFloat("porcentaje_vida"));
					rol.setCantidad_energia(rs.getFloat("porcentaje_energia"));
					rol.setCantidad_defensa(rs.getFloat("porcentaje_defensa"));
					rol.setCantidad_evasion(rs.getFloat("porcentaje_evasion"));
					
					System.out.println(rol.getDescripcion_rol());
					
					roles.add(rol);
				}
			}

		} catch (SQLException e) {
			throw new ApplicationException(e, "Error en el SQL");
		} catch (ApplicationException ae) {
			throw new ApplicationException(ae, "No existen roles");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return roles;
	}
	
	public Rol getOneRoleById(int id_rol) throws ApplicationException{
		Rol rol = new Rol();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from tipo_personaje where id_tipo_personaje = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,id_rol);
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
					rol.setId_rol(rs.getInt("id_tipo_personaje"));
					rol.setDescripcion_rol(rs.getString("descripcion_tipo_personaje"));
					rol.setCantidad_vida(rs.getFloat("porcentaje_vida"));
					rol.setCantidad_energia(rs.getFloat("porcentaje_energia"));
					rol.setCantidad_defensa(rs.getFloat("porcentaje_defensa"));
					rol.setCantidad_evasion(rs.getFloat("porcentaje_evasion"));

			}

		} catch (SQLException e) {
			throw new ApplicationException(e, "Error en el SQL");
		} catch (ApplicationException ae) {
			throw new ApplicationException(ae, "No existen roles");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rol;
	}
}

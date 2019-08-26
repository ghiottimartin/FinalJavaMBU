package data;


import java.sql.*;
import java.util.ArrayList;
import utils.ApplicationException;

import entidades.Nivel;

public class DataNivel {
	
	public Nivel getOneLevelById(int id_nivel) throws ApplicationException{
		Nivel nivel = new Nivel();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from nivel where id_nivel = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,id_nivel);
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				nivel.setId_nivel(rs.getInt("id_nivel"));
				nivel.setNro_nivel(rs.getInt("nro_nivel"));
				nivel.setExperiencia_minima(rs.getInt("experiencia_minima"));
				nivel.setPuntos_totales(rs.getInt("puntos_totales"));
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
		
		return nivel;
	}
	
	public int getOneLevelByExperience(int experiencia) throws ApplicationException{
		int id_nivel = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select max(id_nivel) 'id_nivel' from nivel where experiencia_minima <= ?;",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,experiencia);
			rs = stmt.executeQuery();
			if (rs!=null && rs.next()) {
				id_nivel = rs.getInt("id_nivel");
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
		return id_nivel;
	}
}

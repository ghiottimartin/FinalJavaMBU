package data;

import java.sql.*;
import java.util.ArrayList;
import utils.ApplicationException;

import entidades.Ataque;
import entidades.Personaje;

public class DataAtaque {
	public DataAtaque() {
		
	}
	
	public ArrayList<Ataque> getAllAtaques() {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		ArrayList<Ataque> lista = new ArrayList<Ataque>();
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from ataque",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						

			
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.executeQuery();
			if(rs!=null){
										
			while(rs.next())
			{
				Ataque a = new Ataque();
				a.setId_ataque(rs.getInt("id_ataque"));
				a.setEnergia_requerida(rs.getInt("energia_requerida"));
				a.setNombre_ataque(rs.getString("nombre_ataque"));
				lista.add(a);
			}
			}		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return lista;
	}
}

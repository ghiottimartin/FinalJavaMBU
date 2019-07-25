package data;

import java.sql.*;
import java.util.ArrayList;

import entidades.Combate;
import entidades.Personaje;
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
		    throw new ApplicationException(e,"Error en el sql al buscar el combate");
		} catch(ApplicationException ae) {
			throw new ApplicationException(ae, "Combate no econtrado");
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
	
	public ArrayList<Combate> getCombates() throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		ArrayList<Combate> lista = new ArrayList<Combate>();
		Combate c;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from combate",PreparedStatement.RETURN_GENERATED_KEYS);
			rs=stmt.executeQuery();
			if(rs!=null){							
				while(rs.next())
				{
					c = new Combate();
					c.setId(rs.getInt("id_combate"));
					c.setIdEnemigo(rs.getInt("id_enemigo"));
					c.setExperiencia(rs.getInt("experiencia"));
					
					lista.add(c);
				}
			}		
			
			
		} catch (SQLException e) {
			ApplicationException ade=new ApplicationException(e, "Error al recuperar persoajes.\n"+e.getMessage());
			throw ade;
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();;
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

package data;

import java.sql.*;
import java.util.ArrayList;

import entidades.*;
import utils.ApplicationException;

public class DataPersonaje {
	public DataPersonaje()
	{
		
	}
	
	public int add(Personaje p) throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into personaje(id_personaje,nombre,vida,energia,defensa,evasion,experiencia,id_nivel)"+
					" values(null,?,?,?,?,?,0,1)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setString(1, p.getNombre());
			stmt.setInt(2, p.getVida());
			stmt.setInt(3, p.getEnergia());
			stmt.setInt(4, p.getDefensa());
			stmt.setInt(5, p.getEvasion());
			stmt.execute();
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				p.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error en el sql al crear personaje",e);
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
		return p.getId();
	}
	
	public void update(Personaje p) throws ApplicationException{
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"update personaje set id_personaje=?,nombre=?,vida=?,energia=?,defensa=?,evasion=?,experiencia=?, id_nivel=?"+
					" where id=?");
			
			stmt.setInt(1, p.getId());
			stmt.setString(2, p.getNombre());
			stmt.setInt(3, p.getVida());
			stmt.setInt(4, p.getEnergia());
			stmt.setInt(5, p.getDefensa());
			stmt.setInt(6, p.getEvasion());
			stmt.setInt(7, p.getExperiencia());
			stmt.setInt(8, p.getId_nivel());
			stmt.execute();
			
			
		} catch (SQLException e) {
			throw new ApplicationException("Error en el sql al modificar el personaje",e);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
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
	
	
	public Personaje getById(Personaje per) throws ApplicationException{
		Personaje p=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id,nombre,vida,energia,defensa,evasion,experiencia,id_nivel from personaje where id=?");
			stmt.setInt(1, per.getId());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=new Personaje();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setVida(rs.getInt("vida"));
				p.setEnergia(rs.getInt("energia"));
				p.setDefensa(rs.getInt("defensa"));
				p.setEvasion(rs.getInt("evasion"));
				p.setExperiencia(rs.getInt("experiencia"));
				p.setId_nivel(rs.getInt("id_nivel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException("Error en el sql al buscar el personaje",e);
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Personaje no encontrado");
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
		
		return p;
	}
	
	public Personaje getByName(String nombre) throws ApplicationException{
		Personaje p=null;
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_personaje,nombre,vida,energia,defensa,evasion,experiencia, id_nivel from personaje where nombre=?");
			stmt.setString(1, nombre);
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=new Personaje();
				p.setId(rs.getInt("id_personaje"));
				p.setNombre(rs.getString("nombre"));
				p.setVida(rs.getInt("vida"));
				p.setEnergia(rs.getInt("energia"));
				p.setDefensa(rs.getInt("defensa"));
				p.setEvasion(rs.getInt("evasion"));
				p.setExperiencia(rs.getInt("experiencia"));
				p.setExperiencia(rs.getInt("id_nivel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    throw new ApplicationException("Error en el sql al buscar el personaje",e);
		} catch (ApplicationException ae) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Personaje no econtrado");
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
		
		return p;
	}

	public int consultarMax() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int idMax = 0;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select max(id) from personaje");
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				idMax =  ((Number) rs.getObject(1)).intValue();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return idMax + 1;
		
	}

	public void delete(Personaje p) {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from personaje where id=?");
			stmt.setInt(1, p.getId());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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

	public boolean coincideNombre(Personaje p) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean coincide = false;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select nombre from personaje where nombre like ?");
			stmt.setString(1, p.getNombre());
			rs= stmt.executeQuery();
			if(rs!=null && rs.next()){
				if( ((String) rs.getObject(1)).matches(p.getNombre()) ) 
				{
					coincide =  true;
				}
				else { coincide = false; }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		return coincide;
		
	}
	public ArrayList<Personaje> getPersonajes() throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		ArrayList<Personaje> lista = new ArrayList<Personaje>();
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from personaje",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						

			
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.executeQuery();
			if(rs!=null){
										
			while(rs.next())
			{
				Personaje p = new Personaje();
			
				p.setId(rs.getInt("id_personaje"));
				p.setNombre(rs.getString("nombre"));
				p.setVida(rs.getInt("vida"));
				p.setEnergia(rs.getInt("energia"));
				p.setDefensa(rs.getInt("defensa"));
				p.setEvasion(rs.getInt("evasion"));
				p.setExperiencia(rs.getInt("experiencia"));
				p.setId_nivel(rs.getInt("id_nivel"));
				
				lista.add(p);
			}
			}		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Error al recuperar persoajes",e);
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
	
	public int addPersonajeAtaque(int id_personaje, int id_ataque){
		int id_personaje_ataque = 0;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into personaje_ataque(id_personaje_ataque,id_personaje,id_ataque) values(null,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setInt(1, id_personaje);
			stmt.setInt(2, id_ataque);
			
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				id_personaje_ataque = rs.getInt(1);
			}		
			
			
		} catch (SQLException e) {
			//throw new ApplicationException("Error en el sql al crear personaje",e);
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
		
		return id_personaje_ataque;
	}
	
	
	public int addUsuarioPersonaje(int id_usuario,int id_personaje){
		int id_usuario_personaje = 0;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into usuario_personaje(id_usuario_personaje,id_usuario,id_personaje) values(null,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setInt(1, id_usuario);
			stmt.setInt(2, id_personaje);
			
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				id_usuario_personaje = rs.getInt(1);
			}		
			
			
		} catch (SQLException e) {
			//throw new ApplicationException("Error en el sql al crear personaje",e);
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
		
		return id_usuario_personaje;
		
	}
	
	

}

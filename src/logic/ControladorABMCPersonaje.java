package logic;

import entidades.Personaje;

import java.util.*;
import data.DataPersonaje;
import utils.ApplicationException;

public class ControladorABMCPersonaje {
	private Personaje pers;
	ArrayList<Personaje>personajes = new ArrayList<Personaje>();
	
private data.DataPersonaje dataPer;
	
	
	public ControladorABMCPersonaje() 
	{
		personajes = new ArrayList<Personaje>();
		dataPer = new DataPersonaje();
	}
	
	public int agregarPersonaje(Personaje p) throws ApplicationException
	{
		int id_personaje = dataPer.add(p);
		return id_personaje;
	}
	
	public int recuperarID()
	{
		return dataPer.consultarMax();
	}
	
	public Personaje busca(int id_personaje) throws ApplicationException
	{
		Personaje per = dataPer.getById(id_personaje);
		return per;
	}
	public void borrarPersonaje(Personaje p)
	{
		dataPer.delete(p);
	}

	public void modificar(Personaje p) {
		try {
			dataPer.update(p);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Personaje busca(String nombre) throws ApplicationException
	{
		Personaje per = dataPer.getByName(nombre);
		return per;
	}
	
	public ArrayList<Personaje> getAll()
	{
		try {
			personajes = dataPer.getPersonajes();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personajes;
	}
	
	public void insertarPersonajeAtaque(int id_personaje, int id_ataque){
		dataPer.addPersonajeAtaque(id_personaje, id_ataque);
	}
	
	public void insertarPersonajeUsuario(int id_personaje, int id_usuario) {
		dataPer.addPersonajeUsuario(id_personaje, id_usuario);
	}
	
	public ArrayList<Personaje> recuperarPersonajesDeUsuario(int id_usuario) throws ApplicationException {
		ArrayList<Personaje> pers = dataPer.getByUser(id_usuario);
		return pers;
	}
}

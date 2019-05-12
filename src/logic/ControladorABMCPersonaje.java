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
	
	public void agregarPersonaje(Personaje p) throws ApplicationException
	{
		//En vez de agredarlo directamente hago un metodo para tirar la exception
		if(!dataPer.coincideNombre(p)) {
			dataPer.add(p);
		} else
		{
			throw new ApplicationException("Ya existe un personaje con ese nombre");
		}
	}
	
	public int recuperarID()
	{
		return dataPer.consultarMax();
	}
	
	public Personaje busca(Personaje p) throws ApplicationException
	{
		Personaje per = dataPer.getById(p);
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
}

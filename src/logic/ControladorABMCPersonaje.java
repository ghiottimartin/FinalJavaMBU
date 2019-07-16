package logic;

import entidades.Personaje;
import entidades.Rol;
import entidades.Nivel;
import entidades.AtributosRolNivel;
import data.DataRol;

import java.util.*;
import data.DataPersonaje;
import data.DataNivel;
import utils.ApplicationException;

public class ControladorABMCPersonaje {
	private Personaje pers;
	ArrayList<Personaje>personajes = new ArrayList<Personaje>();
	
	private data.DataPersonaje dataPer;
	
	private data.DataRol dataRol;
	private data.DataNivel dataNivel;
	
	
	public ControladorABMCPersonaje() 
	{
		personajes = new ArrayList<Personaje>();
		dataPer = new DataPersonaje();
		dataRol = new DataRol();
		dataNivel = new DataNivel();
	}
	
	public int agregarPersonaje(Personaje p) throws ApplicationException
	{
		//En vez de agredarlo directamente hago un metodo para tirar la exception
		if(!dataPer.coincideNombre(p)) {
			return dataPer.add(p);
		} else
		{
			Exception e = new Exception();
			throw new ApplicationException(e, "Ya existe un personaje con ese nombre");
		}
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


	public Personaje getById(int idPersonaje) {
		Personaje personaje = new Personaje();
		DataPersonaje dataPersonaje = new DataPersonaje();
		try {
			personaje = dataPersonaje.getById(idPersonaje);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personaje;
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
	
	public ArrayList<Rol> getAllRoles(){
		ArrayList<Rol> roles = new ArrayList<Rol>();
		try {
			roles = dataRol.getAllRoles();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Roles " + roles);
		return roles;
	}
	
	public Rol getOneRoleById(int id_rol){
		Rol rol = new Rol();
		try {
			rol = dataRol.getOneRoleById(id_rol);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rol;
	}
	
	public Nivel getOneLevelById(int id_nivel){
		Nivel nivel = new Nivel();
		try {
			nivel = dataNivel.getOneLevelById(id_nivel);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nivel;
	}
	
	public AtributosRolNivel puntosTotalesSegunRolNivel(int id_rol,int id_nivel){
		Rol role = this.getOneRoleById(id_rol);
		Nivel level = this.getOneLevelById(id_nivel);
		
		
		
		int currentPoints = level.getPuntos_totales();
		
		AtributosRolNivel atr = new AtributosRolNivel();
		atr.setVida(Math.round(currentPoints * role.getCantidad_vida()));
		atr.setEnergia(Math.round(currentPoints * role.getCantidad_energia()));
		atr.setDefensa(Math.round(currentPoints * role.getCantidad_defensa()));
		atr.setEvasion(Math.round(currentPoints * role.getCantidad_evasion()));
		
		
		return atr;
	}
}

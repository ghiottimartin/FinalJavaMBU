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
	ArrayList<Personaje> personajes = new ArrayList<Personaje>();

	private data.DataPersonaje dataPer;

	private data.DataRol dataRol;
	private data.DataNivel dataNivel;

	public ControladorABMCPersonaje() {
		personajes = new ArrayList<Personaje>();
		dataPer = new DataPersonaje();
		dataRol = new DataRol();
		dataNivel = new DataNivel();
	}

	public int agregarPersonaje(Personaje p) throws ApplicationException {
		if (!dataPer.coincideNombre(p)) {
			return dataPer.add(p);
		} else {
			throw new ApplicationException(new Exception(), "Ya existe un personaje con ese nombre");
		}
	}

	public int recuperarID() throws ApplicationException {
		return dataPer.consultarMax();
	}

	public Personaje busca(int id_personaje) throws ApplicationException {
		return dataPer.getById(id_personaje);
	}

	public void borrarPersonaje(Personaje p) throws ApplicationException {
		dataPer.delete(p);
	}

	public void modificar(Personaje p) throws ApplicationException {
		dataPer.update(p);
	}

	public Personaje busca(String nombre) throws ApplicationException {
		return dataPer.getByName(nombre);
	}

	public ArrayList<Personaje> getAll() throws ApplicationException {
		return dataPer.getPersonajes();
	}

	public Personaje getById(int idPersonaje) throws ApplicationException {
		return dataPer.getById(idPersonaje);
	}

	public void insertarPersonajeAtaque(int id_personaje, int id_ataque) throws ApplicationException {
		dataPer.addPersonajeAtaque(id_personaje, id_ataque);
	}

	public void insertarPersonajeUsuario(int id_personaje, int id_usuario) throws ApplicationException {
		dataPer.addPersonajeUsuario(id_personaje, id_usuario);
	}

	public ArrayList<Personaje> recuperarPersonajesDeUsuario(int id_usuario) throws ApplicationException {
		return dataPer.getByUser(id_usuario);
	}

	public ArrayList<Rol> getAllRoles() throws ApplicationException {
		ArrayList<Rol> roles = new ArrayList<Rol>();
		roles = dataRol.getAllRoles();
		return roles;
	}

	public Rol getOneRoleById(int id_rol) throws ApplicationException {
		return dataRol.getOneRoleById(id_rol);
	}

	public Nivel getOneLevelById(int id_nivel) throws ApplicationException {
		return dataNivel.getOneLevelById(id_nivel);
	}

	public AtributosRolNivel puntosTotalesSegunRolNivel(int id_rol, int id_nivel) throws ApplicationException {
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

	public void updateExperienciaPersonaje(int id_personaje, int cant_experiencia) throws ApplicationException {
		dataPer.updateExperienciaPersonaje(id_personaje, cant_experiencia);
	}

	public void levelUpPersonaje(Personaje per, int id_next_nivel) throws ApplicationException {
		AtributosRolNivel atr = puntosTotalesSegunRolNivel(per.getId_rol(), id_next_nivel);
		dataPer.levelUpPersonaje(atr, per.getId(), id_next_nivel);
	}
}

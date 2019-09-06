package logic;

import java.util.*;
import data.DataAtaque;
import utils.ApplicationException;
import entidades.Ataque;

public class ControladorABMAtaque {
	private DataAtaque dataAtaque = new DataAtaque();

	public ControladorABMAtaque() {

	}

	public ArrayList<Ataque> getAll() throws ApplicationException {
		return this.dataAtaque.getAllAtaques();
	}

	public ArrayList<Ataque> getAllByEnergy(int max_energy) throws ApplicationException {
		return this.dataAtaque.getAllAtaquesByEnergy(max_energy);
	}

	public ArrayList<Ataque> getNewByEnergy(int max_energy, int id_personaje) throws ApplicationException {
		return this.dataAtaque.getNewAtaquesByEnergy(max_energy, id_personaje);
	}

	public void add(String nombre, String energia) throws ApplicationException {
		this.validarAtaque(nombre, energia);
		Ataque ataque = new Ataque(String.valueOf(nombre),Integer.valueOf(energia));
		this.dataAtaque.add(ataque);
	}

	public Ataque get(int id) throws ApplicationException {
		return this.dataAtaque.get(id);
	}

	public void edit(Ataque ataque, String nombre, String energia) throws ApplicationException {
		this.validarAtaque(nombre, energia);
		ataque.setNombre_ataque(String.valueOf(nombre));
		ataque.setEnergia_requerida(Integer.parseInt(energia));
		this.dataAtaque.edit(ataque);
	}

	public void delete(int id) throws ApplicationException {
		this.dataAtaque.delete(id);
	}

	public void validarAtaque(String nombre, String energia) throws ApplicationException {
		if (String.valueOf(nombre) == "")
			throw new ApplicationException(new Exception(), "El nombre del ataque no puede estar vacío");
		if (String.valueOf(energia) == "" || Integer.parseInt(energia) <= 0)
			throw new ApplicationException(new Exception(),
					"El ataque debe tener una cantidad de energia mayor a cero");
	}
}

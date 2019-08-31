package logic;

import java.util.*;
import data.DataAtaque;
import utils.ApplicationException;
import entidades.Ataque;


public class ControladorABMAtaque {
	private DataAtaque dataAtaque = new DataAtaque();
	
	public ControladorABMAtaque() {
		
	}
	
	public ArrayList<Ataque> getAll() throws ApplicationException{
		return  this.dataAtaque.getAllAtaques();
	}
	
	public ArrayList<Ataque> getAllByEnergy(int max_energy) throws ApplicationException{
		return  this.dataAtaque.getAllAtaquesByEnergy(max_energy);
	}
	
	public ArrayList<Ataque> getNewByEnergy(int max_energy, int id_personaje) throws ApplicationException{
		return this.dataAtaque.getNewAtaquesByEnergy(max_energy,id_personaje);
	}
	
	public void add(Ataque ataque) throws ApplicationException {
		this.dataAtaque.add(ataque);
	}
	
	public Ataque get(int id) throws ApplicationException {
		return this.dataAtaque.get(id);
	}
	
	public void edit(Ataque ataque) throws ApplicationException {
		this.dataAtaque.edit(ataque);
	}
	
	public void delete(int id) throws ApplicationException {
		this.dataAtaque.delete(id);
	}
}

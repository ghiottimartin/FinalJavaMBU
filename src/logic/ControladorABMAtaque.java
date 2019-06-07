package logic;

import java.util.*;
import data.DataAtaque;
import utils.ApplicationException;
import entidades.Ataque;


public class ControladorABMAtaque {
	private DataAtaque dataAtaque = new DataAtaque();
	
	public ControladorABMAtaque() {
		
	}
	
	public ArrayList<Ataque> getAll(){
		ArrayList<Ataque> ataques = new ArrayList<Ataque>();
		try{
			ataques = this.dataAtaque.getAllAtaques();
		} catch (Exception e){
			
		}
		return ataques;
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

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
}

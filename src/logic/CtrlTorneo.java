package logic;

import java.util.ArrayList;

import data.DataCombate;
import data.DataTorneo;
import entidades.Torneo;
import entidades.Personaje;
import entidades.Combate;
import entidades.TorneoCombate;
import utils.ApplicationException;

public class CtrlTorneo {
	private Torneo torneo;
	private DataTorneo dataTorneo;
	private DataCombate dataCombate;
	
	public CtrlTorneo(){
		dataCombate = new DataCombate();
		dataTorneo = new DataTorneo();
	}
	
	public void create(Torneo t) throws ApplicationException {
		Torneo tor = dataTorneo.add(t);
		int id_torneo = tor.getId();
		System.out.print(id_torneo);
		ArrayList<TorneoCombate> tcArray = this.getTorneoCombateToInsert(id_torneo);
		System.out.println(tcArray);
		System.out.println(tcArray.size());
		System.out.println(tcArray.get(1).getCombate_activo());
		dataTorneo.addTorneoCombates(tcArray);
	}
	
	public Personaje getEnemigo(int idTorneo) 
	{	
		Personaje p = new Personaje();
		try {
			int idCombateActivo = dataTorneo.getIdCombateActivo(idTorneo);
			Combate combate = dataCombate.getCombate(idCombateActivo);
			int idPersonaje = combate.getIdEnemigo();
			ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
		    p = ctrlPersonaje.getById(idPersonaje);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public int getIdUsuarioPersonaje(int idUsuario, int idPersonaje)
	{
		int id = 0;
		try {
			id = dataTorneo.getIdUsuarioPersonaje(idUsuario, idPersonaje);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public int getIdPersonaje(int idUsuarioPersonaje){
		int idPersonaje = 0;
		try {
			idPersonaje = dataTorneo.getIdPersonajeByIdUsuarioPersonaje(idUsuarioPersonaje);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idPersonaje;
	}
	
	public Personaje getpersonaje(int idUsuarioPersonaje){
		int idPersonaje = getIdPersonaje(idUsuarioPersonaje);
		Personaje p = new Personaje();
		ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
		//falta implementar el codigo de getById de controlador Personaje
		p = ctrlPersonaje.getById(idPersonaje);
		return p;
	}
	
	
	
	public ArrayList<TorneoCombate> getTorneoCombateToInsert(int id_torneo){
		ArrayList<TorneoCombate> torneoCombates = new ArrayList<TorneoCombate>();
		try {
			ArrayList<Combate> allCombates = dataCombate.getCombates();
			int count = allCombates.size();
			int lastAddedTorneoCombate = dataTorneo.getIdMaxTorneoCombate();
			int total = count + lastAddedTorneoCombate;
			for(int i = 0; i < count; i++){
				TorneoCombate tc = new TorneoCombate();
				tc.id_torneo = id_torneo;
				tc.id_combate = allCombates.get(i).getId();
				if(i == 0){
					tc.combate_activo = 1;
				} else {
					tc.combate_activo = 0;
				}
				
				if(i == count-1){
					tc.id_siguiente_combate = null;
				} else {
					tc.id_siguiente_combate = total - (i + 1);
				}
				
				torneoCombates.add(tc);
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return torneoCombates;
	}
	
	public int updateCombateActivo(int idTorneo){
		int id_next_combate = 0;
		int id_current_combate= 0;
		System.out.println("Entro a updatecombateactivo");
		System.out.println(idTorneo);
		try {
			id_current_combate = dataTorneo.getIdTorneoCombateActivo(idTorneo);
			System.out.println(id_current_combate);
		} catch (ApplicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			id_next_combate = dataTorneo.getNextCombate(id_current_combate);
			System.out.println(id_next_combate);
			if(id_next_combate != 0){
				dataTorneo.updateCombateActivo(id_current_combate, id_next_combate);
			} 
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id_next_combate;
	}
}

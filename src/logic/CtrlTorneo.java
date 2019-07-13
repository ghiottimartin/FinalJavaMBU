package logic;

import data.DataCombate;
import data.DataTorneo;
import entidades.Torneo;
import entidades.Personaje;
import entidades.Combate;
import utils.ApplicationException;

public class CtrlTorneo {
	private Torneo torneo;
	private DataTorneo dataTorneo = new DataTorneo();
	private DataCombate dataCombate = new DataCombate();
	
	public void create(Torneo t) throws ApplicationException {
		dataTorneo.add(t);
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
	

}

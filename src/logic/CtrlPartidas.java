package logic;

import java.util.ArrayList;
import utils.ApplicationException;
import entidades.Partida;
import data.DataPartida;

public class CtrlPartidas {
	private DataPartida dataPar;
	
	public CtrlPartidas(){
		dataPar = new DataPartida();
	}
	
	public void guardarPartida(Partida par) throws ApplicationException{
		dataPar.savePartida(par);
	}
	
	public ArrayList<Partida> getAllFromUser(int id_usuario) throws ApplicationException{
		return dataPar.getAllPartidasFromUser(id_usuario);
	}
	
	public int getIdUsuarioPersonajeFromTorneo(int id_partida) throws ApplicationException{
		return dataPar.getIdUsuarioPersonajeFromTorneo(id_partida);
	}
	
	public int getIdTorneo(int id_partida) throws ApplicationException{
		return dataPar.getIdTorneo(id_partida);
	}
	
}

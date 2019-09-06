package logic;

import java.util.ArrayList;
import utils.ApplicationException;
import entidades.Partida;
import entidades.Torneo;
import data.DataPartida;

public class CtrlPartidas {
	private DataPartida dataPar;
	
	public CtrlPartidas(){
		dataPar = new DataPartida();
	}
	
	public void guardarPartida(Torneo torneo,String descripcion,int idUsuario) throws ApplicationException{
		Partida par = new Partida();
		par.setDescripcion(descripcion);
		par.setId_torneo(torneo.getId());
		par.setId_usuario(idUsuario);
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

package logic;

import data.DataTorneo;
import entidades.Torneo;
import utils.ApplicationException;

public class CtrlTorneo {
	private Torneo torneo;
	private DataTorneo dataTorneo = new DataTorneo();
	
	public void create(Torneo t) throws ApplicationException {
		dataTorneo.add(t);
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
}

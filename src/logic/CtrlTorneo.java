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
}

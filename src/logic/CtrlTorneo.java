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

	public CtrlTorneo() {
		dataCombate = new DataCombate();
		dataTorneo = new DataTorneo();
	}

	public void create(Torneo t) throws ApplicationException {
		Torneo tor = dataTorneo.add(t);
		int id_torneo = tor.getId();
		System.out.print(id_torneo);
		ArrayList<TorneoCombate> tcArray = this.getTorneoCombateToInsert(id_torneo);
//		System.out.println(tcArray);
//		System.out.println(tcArray.size());
//		System.out.println(tcArray.get(1).getCombate_activo());
		dataTorneo.addTorneoCombates(tcArray);
	}

	public Personaje getEnemigo(int idTorneo) throws ApplicationException {
		int idCombateActivo = dataTorneo.getIdCombateActivo(idTorneo);
		Combate combate = dataCombate.getCombate(idCombateActivo);
		int idPersonaje = combate.getIdEnemigo();
		ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
		return ctrlPersonaje.getById(idPersonaje);
	}

	public int getIdUsuarioPersonaje(int idUsuario, int idPersonaje) throws ApplicationException {
		int id = 0;
		id = dataTorneo.getIdUsuarioPersonaje(idUsuario, idPersonaje);
		return id;
	}

	public int getIdPersonaje(int idUsuarioPersonaje) throws ApplicationException {
		int idPersonaje = 0;
		idPersonaje = dataTorneo.getIdPersonajeByIdUsuarioPersonaje(idUsuarioPersonaje);
		return idPersonaje;
	}

	public Personaje getpersonaje(int idUsuarioPersonaje) throws ApplicationException {
		int idPersonaje = getIdPersonaje(idUsuarioPersonaje);
		ControladorABMCPersonaje ctrlPersonaje = new ControladorABMCPersonaje();
		// falta implementar el codigo de getById de controlador Personaje
		return ctrlPersonaje.getById(idPersonaje);
	}

	public ArrayList<TorneoCombate> getTorneoCombateToInsert(int id_torneo) throws ApplicationException {
		ArrayList<TorneoCombate> torneoCombates = new ArrayList<TorneoCombate>();
		ArrayList<Combate> allCombates = dataCombate.getCombates();
		int count = allCombates.size();
		int lastAddedTorneoCombate = dataTorneo.getIdMaxTorneoCombate();
		int total = count + lastAddedTorneoCombate;
		for (int i = 0; i < count; i++) {
			TorneoCombate tc = new TorneoCombate();
			tc.id_torneo = id_torneo;
			tc.id_combate = allCombates.get(i).getId();
			if (i == 0) {
				tc.combate_activo = 1;
			} else {
				tc.combate_activo = 0;
			}

			if (i == count - 1) {
				tc.id_siguiente_combate = null;
			} else {
				tc.id_siguiente_combate = total - (i + 1);
			}

			torneoCombates.add(tc);
		}

		return torneoCombates;
	}

	public int updateCombateActivo(int idTorneo) throws ApplicationException {
		int id_next_combate = 0;
		int id_current_combate = 0;
		System.out.println("Entro a updatecombateactivo");
		System.out.println(idTorneo);

		id_current_combate = dataTorneo.getIdTorneoCombateActivo(idTorneo);
		System.out.println(id_current_combate);
		id_next_combate = dataTorneo.getNextCombate(id_current_combate);
		System.out.println(id_next_combate);
		if (id_next_combate != 0) {
			dataTorneo.updateCombateActivo(id_current_combate, id_next_combate);
		}
		return id_next_combate;
	}

	public int getIdCombateActivo(int id_torneo) throws ApplicationException {
		int id_combate_activo = 0;
		id_combate_activo = dataTorneo.getIdCombateActivo(id_torneo);
		return id_combate_activo;
	}
}

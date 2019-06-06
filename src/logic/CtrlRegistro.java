package logic;

import data.DataUsuario;
import entidades.Usuario;
import utils.ApplicationException;

public class CtrlRegistro {
	private Usuario usuarioLogueado;
	private DataUsuario dataUsuario = new DataUsuario();
	
	public void register(Usuario u) throws ApplicationException {
		dataUsuario.add(u);
	}
}

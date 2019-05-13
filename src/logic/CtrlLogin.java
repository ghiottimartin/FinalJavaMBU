package logic;

import data.DataUsuario;
import entidades.Usuario;
import utils.ApplicationException;

public class CtrlLogin {
	private Usuario usuarioLogueado;
	private DataUsuario dataUsuario = new DataUsuario();
	
	public Usuario login(String usuario) throws ApplicationException {
		return dataUsuario.getUsuario(usuario);
	}

}

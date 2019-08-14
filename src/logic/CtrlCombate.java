package logic;
import entidades.Ataque;
import entidades.Personaje;
import utils.ApplicationException;
import utils.SuperLogger;
import data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.log4j.Level;


public class CtrlCombate {
	private Personaje pers1, pers2;
	private DataPersonaje dataPer = new DataPersonaje();
	private DataCombate dataCombate = new DataCombate();
	private DataNivel dataNivel = new DataNivel();
	private int vidaP1, vidaP2, energiaP1, energiaP2;
	private String perTurno;
	private String ganador;
	private boolean evadido = false;
	private static Random rand;
	

	public boolean isEvadido() {
		return evadido;
	}

	public void setEvadido(boolean evadido) {
		this.evadido = evadido;
	}

	public String getPerTurno() {
		return perTurno;
	}

	public void setPerTurno(int turno) {
		if (turno == 1) 
		{
			perTurno = pers2.getNombre();
		}
		else 
		{
			perTurno = pers1.getNombre();
		}
		
	}
	
	public int getVidaP1() {
		return vidaP1;
	}

	public void setVidaP1(int vidaP1) {
		this.vidaP1 = vidaP1;
	}

	public int getVidaP2() {
		return vidaP2;
	}

	public void setVidaP2(int vidaP2) {
		this.vidaP2 = vidaP2;
	}

	public int getEnergiaP1() {
		return energiaP1;
	}

	public void setEnergiaP1(int energiaP1) {
		this.energiaP1 = energiaP1;
	}

	public int getEnergiaP2() {
		return energiaP2;
	}

	public void setEnergiaP2(int energiaP2) {
		this.energiaP2 = energiaP2;
	}
	
	public int getEnergiabyTurno(int turno) {
		if (turno==1)
		{
		return energiaP1;
		}
		else 
		{
		return energiaP2;
		}
	}
	
	public String getGanador() {
		return ganador;
	}

	public void setGanador(int turno) {
		if (turno==1)
		{
		this.ganador = pers1.getNombre();
		}
		else 
		{
		  this.ganador = pers2.getNombre();
		}
	}
	
	public void seteaPer(Personaje p1, Personaje p2) {
		pers1 = p1;
		pers2 = p2;
		this.setVidaP1(pers1.getVida());
		this.setVidaP2(pers2.getVida());
		this.setEnergiaP1(pers1.getEnergia());
		this.setEnergiaP2(pers2.getEnergia());
		perTurno = p1.getNombre();
		
	}








	
	//Metodos de ataque

	
	public boolean ataque(int energia, int turno) throws ApplicationException {
	boolean	gano = false;
	
	if(this.validaEnergia(energia, turno)){
		
	
		this.atacar(energia, turno);
		if(this.validarPartida(turno)) 
		{
			gano=true;
			setGanador(turno);
		}
		else
		{
		 this.setPerTurno(turno);
		}
		return gano;
	}
else { throw new ApplicationException(null, ganador);} }
	
	
	

	public boolean validaEnergia(int energia, int turno) {
		boolean valido=false;
		if (turno == 1)
		{
			if(energia <= energiaP1){ valido = true;}		
		}
		else 
		{
			if(energia <= energiaP2){ valido = true;}
		}
		
		if(energia<0 || energia == 0) 
		{
			valido=false;
		}
		
		return valido;
	}
	

public void atacar(int energia,int turno)
{ 
		 if(!this.evadir(turno))
	     {
			 this.quitaVida(energia,turno);
		 }
		 else 
		{
			evadido = true;
		}
		 this.quitaEnergia(energia,turno);

		
}


public boolean evadir(int turno) {
	Random rnd = new Random();
	float numAle = rnd.nextFloat();

	boolean evade = false;
	if (turno == 1)
	{
		//(numAleatorio*100)>puntosDeEvasion
		if ((numAle*100)<pers2.getEvasion())
		{
			evade = true;
		}
	}
	
	else
	{
		//(numAleatorio*100)>puntosDeEvasion
		if ((numAle*100)<pers1.getEvasion())
		{
			evade = true;
		}
	}
	
	return evade;
}


public void quitaVida( int energia, int turno) {
	if (turno == 1)
	{
		vidaP2 = vidaP2 - energia ;
		if(vidaP2 <= 0) { vidaP2 = 0;}
	}
	else 
	{
		vidaP1 = vidaP1 - energia ;
		if(vidaP1 <= 0) { vidaP1 = 0;}
	}
}
	


public void quitaEnergia( int energia,int turno) {
	if (turno == 1)
	{
		energiaP1 = energiaP1 -  energia ;
	
	}
	else 
	{
		energiaP2 = energiaP2 -  energia ;
	}

}


public boolean validarPartida(int turno) {
	boolean valido = false;
	try {
		valido = false;
		if(turno==1) 
		{
			if(vidaP2 <= 0) 
			{
				valido = true;
				//dataPer.updatePuntos(pers1);
			}
		}
		else {
			if(vidaP1 <= 0) 
			{
				valido = true;
				//dataPer.updatePuntos(pers2);
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return valido;
}

// fin de metodos de ataque




//metodos de Defensa



public void defensa(int turno) {
	this.recuperaEnergia(turno);
	this.recuperaVida(turno);
	this.setPerTurno(turno);
	
}

public void recuperaEnergia(int turno) {
	if (turno ==1)
	{

		energiaP1 = energiaP1 + (pers1.getEnergia() * pers1.getDefensa())/100;
	
		if (energiaP1 > pers1.getEnergia())
		{
			energiaP1 = pers1.getEnergia();
			
		}
	}
	else
	{

		energiaP2 = energiaP2 + (pers2.getEnergia() * pers2.getDefensa())/100;
		
		if (energiaP2 > pers2.getEnergia())
		{
			energiaP2 = pers2.getEnergia();
		}
	}

}

public void recuperaVida(int turno) {
	if (turno ==1)
	{
		//vidaARecuperar = vidaOriginal * defensa / 250
		vidaP1 = vidaP1 + (pers1.getVida() * pers1.getDefensa())/250;
		if (vidaP1 > pers1.getVida())
		{
			vidaP1 = pers1.getVida();
		}
	}
	else
	{
	
		vidaP2 = vidaP2 + (pers2.getVida() * pers2.getDefensa())/250;
		if (vidaP2 > pers2.getVida())
		{
			vidaP2 = pers2.getVida();
		}
	}
}




//fin de metodos de Defensa




//Metodos de ataque-energia
public int getEnergiaFromAtaque(int idAtaque)
{
	//revisar este metodo
	ControladorABMAtaque ctrlAtaque = new ControladorABMAtaque();
	try {
		Ataque ataque = ctrlAtaque.get(idAtaque);
		return ataque.getEnergia_requerida();
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}

};


// Fin de metodos Ataque-Energia


private void notifyUser(String mensaje) {
	JOptionPane.showMessageDialog(null, mensaje, "Warning!", JOptionPane.INFORMATION_MESSAGE);
}

private void notifyUser(String mensaje, Exception e, org.apache.logging.log4j.Level l) {
	notifyUser(mensaje);
	SuperLogger.logger.log(l, mensaje, e);
}

public ArrayList<Ataque> getAtaquesOfPersonaje(int id_personaje) {
	ArrayList<Ataque> ataques_personaje = new ArrayList<Ataque>();
	try {
		ataques_personaje = dataCombate.getAtaquesOfPersonaje(id_personaje);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ataques_personaje;
}

public ArrayList<Ataque> getAtaquesOfPersonajeByEnergia(int id_personaje, int turno) {
	ArrayList<Ataque> ataques_personaje = new ArrayList<Ataque>();
	int energia = getEnergiabyTurno(turno);
	try {
		ataques_personaje = dataCombate.getAtaquesOfPersonajeByEnergia(id_personaje, energia);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ataques_personaje;
}

public int getExperienciaFromCombate(int id_combate){
	int experiencia = 0;
	try {
		experiencia = dataCombate.getExperienciaFromCombate(id_combate);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return experiencia;
}

public int getNivelByExperiencia(int experiencia) {
	int id_nivel = 0;
	try {
		id_nivel = dataNivel.getOneLevelByExperience(experiencia);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return id_nivel;
}

public int getIdPersonajeTurno(int turno) {
	if (turno==1)
	{
	return pers1.getId();
	}
	else 
	{
	return pers2.getId();
	}
}

public static Ataque randomAtaque(List<Ataque> ataques) {
rand = new Random();
Ataque ataque = ataques.get(rand.nextInt(ataques.size()));
return ataque;
}

}

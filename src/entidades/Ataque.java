package entidades;

public class Ataque {
	private int id_ataque, energia_requerida;
	private String nombre_ataque;
	
	public Ataque(String nombre_ataque, int energia_requerida) {
		this.nombre_ataque = nombre_ataque;
		this.energia_requerida = energia_requerida;
	}
	public Ataque() {
		
	}
	public int getId_ataque() {
		return id_ataque;
	}
	public void setId_ataque(int id_ataque) {
		this.id_ataque = id_ataque;
	}
	public int getEnergia_requerida() {
		return energia_requerida;
	}
	public void setEnergia_requerida(int energia_requerida) {
		this.energia_requerida = energia_requerida;
	}
	public String getNombre_ataque() {
		return nombre_ataque;
	}
	public void setNombre_ataque(String nombre_ataque) {
		this.nombre_ataque = nombre_ataque;
	}
}

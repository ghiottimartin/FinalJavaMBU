package entidades;

public class Rol {
	private int id_rol, cantidad_vida, cantidad_energia, cantidad_defensa, cantidad_evasion;
	private String descripcion_rol;
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public int getCantidad_vida() {
		return cantidad_vida;
	}
	public void setCantidad_vida(int cantidad_vida) {
		this.cantidad_vida = cantidad_vida;
	}
	public int getCantidad_energia() {
		return cantidad_energia;
	}
	public void setCantidad_energia(int cantidad_energia) {
		this.cantidad_energia = cantidad_energia;
	}
	public int getCantidad_defensa() {
		return cantidad_defensa;
	}
	public void setCantidad_defensa(int cantidad_defensa) {
		this.cantidad_defensa = cantidad_defensa;
	}
	public int getCantidad_evasion() {
		return cantidad_evasion;
	}
	public void setCantidad_evasion(int cantidad_evasion) {
		this.cantidad_evasion = cantidad_evasion;
	}
	public String getDescripcion_rol() {
		return descripcion_rol;
	}
	public void setDescripcion_rol(String descripcion_rol) {
		this.descripcion_rol = descripcion_rol;
	}
}

package entidades;

public class Rol {
	private int id_rol;
	private float cantidad_vida, cantidad_energia, cantidad_defensa, cantidad_evasion;
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public float getCantidad_vida() {
		return cantidad_vida;
	}
	public void setCantidad_vida(float cantidad_vida) {
		this.cantidad_vida = cantidad_vida;
	}
	public float getCantidad_energia() {
		return cantidad_energia;
	}
	public void setCantidad_energia(float cantidad_energia) {
		this.cantidad_energia = cantidad_energia;
	}
	public float getCantidad_defensa() {
		return cantidad_defensa;
	}
	public void setCantidad_defensa(float cantidad_defensa) {
		this.cantidad_defensa = cantidad_defensa;
	}
	public float getCantidad_evasion() {
		return cantidad_evasion;
	}
	public void setCantidad_evasion(float cantidad_evasion) {
		this.cantidad_evasion = cantidad_evasion;
	}
	public String getDescripcion_rol() {
		return descripcion_rol;
	}
	public void setDescripcion_rol(String descripcion_rol) {
		this.descripcion_rol = descripcion_rol;
	}
	private String descripcion_rol;
	
}

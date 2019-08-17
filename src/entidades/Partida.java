package entidades;

import java.sql.Date;

public class Partida {
	public int id_partida, id_torneo, id_usuario;
	public String descripcion;
	public Date fecha_creacion;
	public int getId_partida() {
		return id_partida;
	}
	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}
	public int getId_torneo() {
		return id_torneo;
	}
	public void setId_torneo(int id_torneo) {
		this.id_torneo = id_torneo;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
}

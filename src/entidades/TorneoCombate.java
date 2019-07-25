package entidades;

public class TorneoCombate {
	public Integer id_torneo_combate, id_torneo, id_combate, combate_activo, id_siguiente_combate ;

	public Integer getId_torneo_combate() {
		return id_torneo_combate;
	}

	public void setId_torneo_combate(Integer id_torneo_combate) {
		this.id_torneo_combate = id_torneo_combate;
	}

	public Integer getId_torneo() {
		return id_torneo;
	}

	public void setId_torneo(Integer id_torneo) {
		this.id_torneo = id_torneo;
	}

	public Integer getId_combate() {
		return id_combate;
	}

	public void setId_combate(Integer id_combate) {
		this.id_combate = id_combate;
	}

	public Integer getCombate_activo() {
		return combate_activo;
	}

	public void setCombate_activo(Integer combate_activo) {
		this.combate_activo = combate_activo;
	}

	public Integer getId_siguiente_combate() {
		return id_siguiente_combate;
	}

	public void setId_siguiente_combate(Integer id_siguiente_combate) {
		this.id_siguiente_combate = id_siguiente_combate;
	}

	
}

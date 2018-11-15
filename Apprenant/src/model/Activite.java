package model;

import java.io.Serializable;

public class Activite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id_activite;
	private String code_activite;
	private String nom_activite;

	public Activite() {super();}
	
	public Activite(int id_activite, String code_activite, String nom_activite) {
		super();
		this.id_activite = id_activite;
		this.code_activite = code_activite;
		this.nom_activite = nom_activite;
	}

	public int getId_activite() {
		return id_activite;
	}

	public void setId_activite(int id_activite) {
		this.id_activite = id_activite;
	}

	public String getCode_activite() {
		return code_activite;
	}

	public void setCode_activite(String code_activite) {
		this.code_activite = code_activite;
	}

	public String getNom_activite() {
		return nom_activite;
	}

	public void setNom_activite(String nom_activite) {
		this.nom_activite = nom_activite;
	}

	@Override
	public String toString() {
		return "Activite [id_activite=" + id_activite + ", code_activite=" + code_activite + ", nom_activite="
				+ nom_activite + "]";
	}
	
}

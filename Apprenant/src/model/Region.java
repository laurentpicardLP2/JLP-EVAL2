package model;

import java.io.Serializable;
import java.sql.Time;


public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id_region;
	private String nom_region;
	
	public Region() {
		super();
		}
	
	

	public Region(int id_region, String nom_region) {
		super();
		this.id_region = id_region;
		this.nom_region = nom_region;
	}



	public int getId_region() {
		return id_region;
	}



	public void setId_region(int id_region) {
		this.id_region = id_region;
	}



	public String getNom_region() {
		return nom_region;
	}



	public void setNom_region(String nom_region) {
		this.nom_region = nom_region;
	}



	@Override
	public String toString() {
		return "Region [id_region=" + id_region + ", nom_region=" + nom_region + "]";
	}
	
	
}
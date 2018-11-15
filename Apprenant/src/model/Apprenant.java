package model;

import java.util.Date;

public class Apprenant {

	private int id_apprenant;
	private String nom;
	private String prenom;
	private Date date;
	private int id_region;
	private String email;
	private Object photo;


	public Apprenant() {
		super();
	}

	public Apprenant(String nom, String prenom, Date date, String email, int id_region) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.id_region = id_region;
		this.email = email;
	}


	public Apprenant(int id_apprenant, String nom, String prenom, Date date, int id_region, String email, Object photo) {
		super();
		this.id_apprenant = id_apprenant;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.id_region = id_region;
		this.email = email;
		this.photo = photo;
	}

	public int getId_apprenant() {
		return id_apprenant;
	}

	public void setId_apprenant(int id_apprenant) {
		this.id_apprenant = id_apprenant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId_region() {
		return id_region;
	}

	public void setId_region(int id_region) {
		this.id_region = id_region;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object getPhoto() {
		return photo;
	}

	public void setPhoto(Object photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		if(nom.length()<=7)
			return "id=" + id_apprenant + "\t" + nom + "\t\t" + prenom ;
		else
			return "id=" + id_apprenant + "\t" + nom + "\t" + prenom;

	}

	public String toStringListeByArea() {
		return "\n\t" + prenom + " " + nom ;

	}	

}

package model;


public class PeutAvoir {
	private int id_apprenant;
	private int id_activite;
	
	
	public PeutAvoir() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PeutAvoir(int id_apprenant, int id_activite) {
		super();
		this.id_apprenant = id_apprenant;
		this.id_activite = id_activite;
	}
	public int getId_apprenant() {
		return id_apprenant;
	}
	public void setId_apprenant(int id_apprenant) {
		this.id_apprenant = id_apprenant;
	}
	public int getId_activite() {
		return id_activite;
	}
	public void setId_activite(int id_activite) {
		this.id_activite = id_activite;
	}
	
	
	@Override
	public String toString() {
		return "PeutAvoir [id_apprenant=" + id_apprenant + ", id_activite=" + id_activite + "]";
	}
	
}



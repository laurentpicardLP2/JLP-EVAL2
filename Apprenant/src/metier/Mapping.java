package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Apprenant;
import model.PeutAvoir;
import model.Activite;
import model.Region;


public class Mapping {

	public Mapping() {
		
	}
	
	/**
	 * Méthode pour instancier un objet region
	 */
	public static Region mapperRegion(ResultSet resultat) throws SQLException, ClassNotFoundException{
		Region region = new Region();
		region.setId_region(resultat.getInt("id_region"));
		region.setNom_region(resultat.getString("nom_region"));
		return region;
	}
	
	/**
	 * Méthode pour instancier un objet activite
	 */
	public static Activite mapperActivite(ResultSet resultat) throws SQLException, ClassNotFoundException{
		Activite activite = new Activite();
		activite.setId_activite(resultat.getInt("id_activite"));
		activite.setCode_activite(resultat.getString("code_activite"));
		activite.setNom_activite(resultat.getString("nom_activite"));
		return activite;
	}
	
	/**
	 * Méthode pour instancier un objet peutAvoir
	 */
	public static PeutAvoir mapperPeutAvoir(ResultSet resultat) throws SQLException, ClassNotFoundException{
		PeutAvoir peutAvoir = new PeutAvoir();
		peutAvoir.setId_activite(resultat.getInt("id_activite"));
		peutAvoir.setId_apprenant(resultat.getInt("id_apprenant"));
		return peutAvoir;
	}

	/**
	 * Méthode pour instancier un objet Apprenant
	 */
	public static Apprenant mapperApprenant(ResultSet resultat) throws SQLException, ClassNotFoundException{
		Apprenant apprenant = new Apprenant();
		apprenant.setId_apprenant(resultat.getInt("id_apprenant"));
		apprenant.setNom(resultat.getString("nom"));
		apprenant.setPrenom(resultat.getString("prenom"));
		apprenant.setDate(resultat.getDate("dateNaissance"));
		apprenant.setEmail(resultat.getString("email"));
		apprenant.setPhoto(resultat.getObject("photo"));
		apprenant.setId_region(resultat.getInt("id_region"));
		return apprenant;
	}

}

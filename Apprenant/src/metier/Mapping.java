package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Apprenant;
import model.PeutAvoir;
import model.Activite;
import model.Region;

/**
 * @author Philippe
 * Mapping par Programmation
 */
public class Mapping {

	/**
	 * 
	 */
	public Mapping() {
		
	}
	
	/**
	 * Méthode pour instancier un objet Avion
	 * @param resultat
	 * @return
	 * @throws SQLException
	 */
	/*public static Avion mapperAvion(ResultSet resultat) throws SQLException
	{
		
		Avion avion = new Avion();
		avion.setId(resultat.getInt("AV_ID"));
		avion.setConstructeur(resultat.getString("AV_CONST"));
		avion.setModele(resultat.getString("AV_MODELE"));
		avion.setCapacite(resultat.getInt("AV_CAPACITE"));
		avion.setSite(resultat.getString("AV_SITE"));
		
		return avion;
	
	}*/
	
	/**
	 * Méthode pour instancier un objet Pilote
	 * @param resultat
	 * @return
	 * @throws SQLException
	 */
	/*public static Pilote mapperPilote(ResultSet resultat) throws SQLException
	{
		Pilote pilote = new Pilote();
		pilote.setId(resultat.getInt("PI_ID"));
		pilote.setNom(resultat.getString("PI_NOM"));
		pilote.setSite(resultat.getString("PI_SITE"));
		
		return pilote;
		
	}*/
	
	/**
	 * Méthode pour instancier un objet Region
	 * @param resultat
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Region mapperRegion(ResultSet resultat) throws SQLException, ClassNotFoundException{
		Region region = new Region();
		region.setId_region(resultat.getInt("id_region"));
		region.setNom_region(resultat.getString("nom_region"));
		return region;
	}
	
	public static Activite mapperActivite(ResultSet resultat) throws SQLException, ClassNotFoundException{
		Activite activite = new Activite();
		activite.setId_activite(resultat.getInt("id_activite"));
		activite.setCode_activite(resultat.getString("code_activite"));
		activite.setNom_activite(resultat.getString("nom_activite"));
		return activite;
	}
	
	
	public static PeutAvoir mapperPeutAvoir(ResultSet resultat) throws SQLException, ClassNotFoundException{
		PeutAvoir peutAvoir = new PeutAvoir();
		peutAvoir.setId_activite(resultat.getInt("id_activite"));
		peutAvoir.setId_apprenant(resultat.getInt("id_apprenant"));
		return peutAvoir;
	}

	/**
	 * Méthode pour instancier un objet Apprenant
	 * @param resultat
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
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

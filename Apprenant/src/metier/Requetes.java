package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import connection.AccesBD;
import model.Apprenant;
import model.Region;

public class Requetes {
	
	public static ArrayList<Apprenant> getAllApprenants() throws ClassNotFoundException, SQLException

	{
		ArrayList<Apprenant>  apprenants = new ArrayList<Apprenant>();
		String requete	= "SELECT * FROM Apprenant";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while(resultat.next())
		{
			Apprenant p = Mapping.mapperApprenant(resultat);
			apprenants.add(p);
		}
		return apprenants;
		
	}
	
	
	/*public static HashMap<String, ArrayList<Apprenant>> apprenantsByArea() throws ClassNotFoundException, SQLException {
		
		return
	}*/
	
public static HashMap<String, ArrayList<Apprenant>> apprenantsByArea() throws ClassNotFoundException, SQLException {
	HashMap<String, ArrayList<Apprenant>> listeApprenantByArea = new HashMap<String, ArrayList<Apprenant>>();
	
	String requete	= "SELECT distinct COUNT(id_region) from region";
	ResultSet rs = AccesBD.executerQuery(requete);
	PreparedStatement prepareStatement;
	String region;
	ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
	
	rs.next();
	int nbRegion = rs.getInt(1);
	
	for (int i = 0; i<nbRegion; i++) {
		apprenants.clear();
		prepareStatement = AccesBD.getConnection()
				.prepareStatement("SELECT nom_region from region WHERE id_region=?");
		prepareStatement.setInt(1, (i+1));
		rs = prepareStatement.executeQuery();
		rs.next();
		region = rs.getString("nom_region");
		//System.out.println(region);
		
		
		prepareStatement = AccesBD.getConnection()
				.prepareStatement("SELECT id_apprenant, prenom, nom, dateNaissance, email, photo, apprenant.id_region, region.id_region, nom_region from apprenant JOIN region ON region.id_region = apprenant.id_region WHERE region.nom_region=?");
		prepareStatement.setString(1, region);
		//System.out.println(region);
		rs = prepareStatement.executeQuery();
		
		prepareStatement.clearParameters();
		
		while (rs.next()) {
			Apprenant p = Mapping.mapperApprenant(rs);
			apprenants.add(p);
            //int id_apprenant = rs.getInt("id_apprenant");
            //String userid = rs.getString("id_apprenant");
            

           //System.out.println("userid : " + id_apprenant);
            //System.out.println("username : " + username);

        }
		
		listeApprenantByArea.put(region, apprenants);
		System.out.println(apprenants);
		
		
		//Apprenant p = Mapping.mapperApprenant(resultat);
		//System.out.println(p);
	
		
	}
	return listeApprenantByArea;
	
}
		


		/*System.out.println("\n\nRequêtes n°2 : (requête préparées) Liste des avions ayant une capacité supérieure à 300 passagers\n");
		PreparedStatement aPreparedStatement = AccesBD.getConnection().prepareStatement("SELECT * FROM avion where AV_CAPACITE > ?");

		aPreparedStatement.setInt(1,300);
		resultat = aPreparedStatement.executeQuery();
		while(resultat.next())
			{
				System.out.println("Avion : "+resultat.getInt("AV_ID")+" "+
											  resultat.getString("AV_CONST")+" "+
											  resultat.getString("AV_MODELE")+" "+
											  resultat.getInt("AV_CAPACITE")+" "+
											  resultat.getString("AV_SITE"));
			}*/

	}
	
	
	/**
	 * Méthode ajouter un nouveau pilote
	 * @param pilote
	 * @throws SQLException
	 */
	/*public static void ajouterPilote(Pilote pilote) throws SQLException
	{
		PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("INSERT INTO `pilote` VALUES( ? , ? , ? )");
		prepareStatement.setInt(1,pilote.getId());
		prepareStatement.setString(2,pilote.getNom());
		prepareStatement.setString(3,pilote.getSite());
		prepareStatement.executeUpdate();
		
	}*/
	
	/**
	 * méthode pour modifier un pilote
	 * @param pilote
	 * @throws SQLException
	 */
	/*public static void modifierPilote(Pilote pilote) throws SQLException
	{
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("UPDATE pilote SET PI_NOM = 'Line' WHERE PI_ID = ? ");
			prepareStatement.setInt(1,pilote.getId());
			prepareStatement.executeUpdate();
			System.out.println("Modification effectuée pour le pilote : "+pilote);

		}
		catch(SQLException e){
			System.out.println("Erreur lors de la modification !");
		}
	}
	
	public static void supprimerPilote(Pilote pilote) throws SQLException
	{
		Statement statement = null;

		try {
			statement = AccesBD.getConnection().createStatement();
			String sql = "DELETE FROM pilote WHERE PI_ID="+pilote.getId();
			statement.executeUpdate(sql);
			System.out.println("Suppression du pilote "+ pilote+ " effectuée");
		}
		catch(SQLException e){
			System.out.println("Erreur lors de la suppression du pilote !");
		}
	}*/
	
	/**
	 * Méthode pour retourner tous les pilotes dans un tableau
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/*public static ArrayList<Pilote> getAllPilotes() throws ClassNotFoundException, SQLException

	{
		ArrayList<Pilote>  pilotes = new ArrayList<Pilote>();
		String requete	= "SELECT * FROM pilote ORDER BY PI_ID";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while(resultat.next())
		{
			Pilote p = Mapping.mapperPilote(resultat);
			pilotes.add(p);
		}
		return pilotes;
	}*/
	
	// faites la même chose avec AVION
	/*public static void ajouterAvion(Avion avion) throws SQLException {
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("INSERT INTO avion ( AV_ID, AV_CONST, AV_MODELE, AV_CAPACITE, AV_SITE ) VALUES( ?, ?, ?, ?, ? )");
		prepareStatement.setInt(1, avion.getId());
		prepareStatement.setString(2, avion.getConstructeur());
		prepareStatement.setString(3, avion.getModele());
		prepareStatement.setInt(4, avion.getCapacite());
		prepareStatement.setString(5, avion.getSite());
		prepareStatement.executeUpdate();		
		
	}
	// faites la même chose avec Vol (attention, un peu compliqué !!!)
	public static void ajouterVol(Vol vol)  throws SQLException{
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("INSERT INTO vol ( VO_ID, VO_AVION, VO_PILOTE ,VO_SITE_DEPART, VO_SITE_ARRIVEE, VO_HEURE_DEPART, VO_HEURE_ARRIVEE ) VALUES( ?, ?, ?, ?, ?, ?, ? )");
		prepareStatement.setString(1, vol.getId());
		prepareStatement.setInt(2, vol.getAvion().getId());
		prepareStatement.setInt(3, vol.getPilote().getId());
		prepareStatement.setString(4, vol.getSiteDepart());
		prepareStatement.setString(5, vol.getSiteArrivee());
		prepareStatement.setTime(6, vol.getHeureDepart());
		prepareStatement.setTime(7, vol.getHeureArrivee());
		prepareStatement.executeUpdate();		
		
	}
	
	// faites la même chose que pour modifierPilote()
	public static void modifierAvion(Avion avion) throws SQLException {
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("UPDATE Avion SET AV_SITE =  ?  WHERE AV_ID = ?");
		prepareStatement.setString(1, "GAP");
		prepareStatement.setInt(2, avion.getId());
		prepareStatement.executeUpdate();
	}
	
	



	public static ArrayList<Vol> getAllVols() throws ClassNotFoundException, SQLException
	{
		ArrayList<Vol> vols = new ArrayList<Vol>();
		String requete = "SELECT * FROM vol";
		ResultSet resultat = AccesBD.executerQuery(requete);
	
		while(resultat.next())
		{
			Vol vol = Mapping.mapperVol(resultat);
			vols.add(vol);
		}
		
		return vols;
	}
	*/
	/**
	 * Méthode qui retourne une liste d'objets de type Avion
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/*public static ArrayList<Avion> getAllAvions() throws ClassNotFoundException, SQLException

	{
		ArrayList<Avion>  avions = new ArrayList<Avion>();
		String requete	= "SELECT * FROM avion ORDER BY AV_ID";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while(resultat.next())
		{
			Avion avion = new Avion();
			avion.setId(resultat.getInt("AV_ID"));
			avion.setConstructeur(resultat.getString("AV_CONST"));
			avion.setModele(resultat.getString("AV_MODELE"));
			avion.setCapacite(resultat.getInt("AV_CAPACITE"));
			avion.setSite(resultat.getString("AV_SITE"));
			avions.add(avion);
			
		}
		return avions;
	}*/
	
	/**
	 * Méthode qui retourne un objet de type Avion en fonction de son identifiant
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/*public static Avion getAvionById(int id) throws ClassNotFoundException, SQLException

	{
		
		Avion avion = new Avion();
		
		PreparedStatement aPreparedStatement = AccesBD.getConnection().prepareStatement("SELECT * FROM avion WHERE AV_ID = ?");
		aPreparedStatement.setInt(1,id);
		ResultSet resultat = aPreparedStatement.executeQuery();
		resultat.next();
		avion.setId(resultat.getInt("AV_ID"));
		avion.setConstructeur(resultat.getString("AV_CONST"));
		avion.setModele(resultat.getString("AV_MODELE"));
		avion.setCapacite(resultat.getInt("AV_CAPACITE"));
		avion.setSite(resultat.getString("AV_SITE"));
		return avion;
			
		}*/
	
	/**
	 * Méthode idem que ci-dessus avec Mapping délégué à une méthode qui retourne un objet de type Avion en fonction de son identifiant
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/*public static Avion getAvionByIdAvecMapping(int id) throws ClassNotFoundException, SQLException

	{
		PreparedStatement aPreparedStatement = AccesBD.getConnection().prepareStatement("SELECT * FROM avion WHERE AV_ID = ?");
		aPreparedStatement.setInt(1,id);
		ResultSet resultat = aPreparedStatement.executeQuery();
		resultat.next();
		
		return Mapping.mapperAvion(resultat);
	}
	*/
	
	
	/**
	 * Méthode qui retourne un objet de type Pilote en fonction de son identifiant
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/*public static Pilote getPiloteById(int id) throws ClassNotFoundException, SQLException

	{
		Pilote pilote = new Pilote();
		String requete	= "SELECT * FROM pilote WHERE PI_ID="+id;
		ResultSet resultat = AccesBD.executerQuery(requete);
		resultat.next();
		pilote.setId(resultat.getInt("PI_ID"));
		pilote.setNom(resultat.getString("PI_NOM"));
		pilote.setSite(resultat.getString("PI_SITE"));
		return pilote;
			
		}*/
	
	/*public static int getNombreDePilote() throws SQLException
	{
		 ResultSet resultat = AccesBD.getConnection().createStatement().executeQuery("SELECT count(*) FROM pilote");
		 resultat.next();
		 return resultat.getInt(1);
	}
	*/
	
	/**
	 * Démonstration d'une méthode qui récupère des résultats sans les stocker dans des objets.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	
	//================================= Votre partie : Categorie =======================================

package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import connection.AccesBD;
import model.Activite;
import model.Apprenant;
import model.Region;

public class Requetes {
	
	public static void getAllApprenants() throws ClassNotFoundException, SQLException

	{
		ArrayList<Apprenant>  apprenants = new ArrayList<Apprenant>();
		String requete	= "SELECT * FROM Apprenant";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while(resultat.next())
		{
			Apprenant p = Mapping.mapperApprenant(resultat);
			apprenants.add(p);
		}
		
		
		for (Apprenant apprenant : apprenants) {
            System.out.println(apprenant);
        }
	}
	
	
	/*public static HashMap<String, ArrayList<Apprenant>> apprenantsByArea() throws ClassNotFoundException, SQLException {
		
		return
	}*/
	
public static void apprenantsByArea() throws ClassNotFoundException, SQLException {
	HashMap<String, ArrayList<Apprenant>> listeApprenantByArea = new HashMap<String, ArrayList<Apprenant>>();
	
	String requete	= "SELECT distinct COUNT(id_region) from region";
	ResultSet rs = AccesBD.executerQuery(requete);
	PreparedStatement prepareStatement;
	String region;
	ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
	
	rs.next();
	int nbRegion = rs.getInt(1);
	for (int i = 0; i<nbRegion; i++) {
		prepareStatement = AccesBD.getConnection()
				.prepareStatement("SELECT nom_region from region WHERE id_region=?");
		prepareStatement.setInt(1, (i+1));
		rs = prepareStatement.executeQuery();
		rs.next();
		region = rs.getString("nom_region");
		
		prepareStatement = AccesBD.getConnection()
				.prepareStatement("SELECT id_apprenant, prenom, nom, dateNaissance, email, photo, apprenant.id_region, region.id_region, nom_region from apprenant JOIN region ON region.id_region = apprenant.id_region WHERE region.nom_region=?");
		prepareStatement.setString(1, region);
		rs = prepareStatement.executeQuery();
		//prepareStatement.clearParameters();
		
		while (rs.next()) {
			Apprenant p = Mapping.mapperApprenant(rs);
			apprenants.add(p);
        }
		ArrayList<Apprenant> a = (ArrayList<Apprenant>) apprenants.clone();
		listeApprenantByArea.put(region, a);
		apprenants.clear();	
	}	
	for (String key : listeApprenantByArea.keySet() ) {
	    System.out.println(key + " (" + listeApprenantByArea.get(key).size() + ") " + showArray(listeApprenantByArea.get(key)) + "\n");
	}
	
}

public static String showArray(ArrayList<Apprenant> apprenants) {
	String s = new String("");
	for(Apprenant a : apprenants) s+= a.toStringListeByArea();
	return s;
}


public static void ListeActiviteForApprenant() throws ClassNotFoundException, SQLException {
	ArrayList<Activite> activites = new ArrayList<Activite>();
	PreparedStatement prepareStatement;
	ResultSet rs;
	Scanner scanner = new Scanner(System.in);
	System.out.print("Veuillez saisir le nom d'un apprenant : ");
	String nomApprenant = scanner.nextLine();
	
	prepareStatement = AccesBD.getConnection()
			.prepareStatement("SELECT COUNT(nom) from apprenant WHERE nom=?");
	prepareStatement.setString(1, nomApprenant);
	rs = prepareStatement.executeQuery();
	rs.next();
	if( rs.getInt(1) > 0) {
		
		prepareStatement = AccesBD.getConnection()
				.prepareStatement("SELECT id_apprenant from apprenant WHERE nom=?");
		prepareStatement.setString(1, nomApprenant);
		rs = prepareStatement.executeQuery();
		rs.next();
		int id_apprenant = rs.getInt(1);
		
		
		prepareStatement = AccesBD.getConnection()
				.prepareStatement("SELECT code_activite, activite.id_activite, id_apprenant, nom_activite FROM peutavoir JOIN activite ON peutavoir.id_activite = activite.id_activite WHERE peutavoir.id_apprenant=?");
		prepareStatement.setInt(1, id_apprenant);
		rs = prepareStatement.executeQuery();
		//prepareStatement.clearParameters();
		
		while (rs.next()) {
			Activite activite = Mapping.mapperActivite(rs);
			activites.add(activite);
        }
		
		String s = new String("");
		for(Activite activite : activites) {
			s += "\n\t" + activite.getNom_activite();
		}
		System.out.println("Activites de " + nomApprenant + " : " + s);
	}
	
	
}

public static void initilialiseBase() throws ClassNotFoundException, SQLException {
	String requete	= "DROP TABLE IF EXISTS peutavoir ;";
	AccesBD.executerUpdate(requete);
	requete	= "DROP TABLE IF EXISTS apprenant;";
	AccesBD.executerUpdate(requete);
	requete	= "DROP TABLE IF EXISTS activite ;";
	AccesBD.executerUpdate(requete);
	requete	= "DROP TABLE IF EXISTS region ;";
	AccesBD.executerUpdate(requete);
	
	

	requete = "CREATE TABLE Region(id_region  Int  Auto_increment  NOT NULL ,nom_region Varchar (50) NOT NULL,CONSTRAINT Region_PK PRIMARY KEY (id_region))ENGINE=InnoDB;";
	AccesBD.executerUpdate(requete);
	
	requete	= "CREATE TABLE Activite(\r\n" + 
			"        id_activite   Int  Auto_increment  NOT NULL ,\r\n" + 
			"        code_activite Char (3) NOT NULL ,\r\n" + 
			"        nom_activite  Varchar (50) NOT NULL\r\n" + 
			"	,CONSTRAINT Activite_PK PRIMARY KEY (id_activite)\r\n" + 
			")ENGINE=InnoDB;";
	AccesBD.executerUpdate(requete);
	
	requete = "CREATE TABLE Apprenant(id_apprenant  Int  Auto_increment  NOT NULL ,nom Varchar (50) NOT NULL , prenom Varchar (50) NOT NULL , dateNaissance Date NULL , email Varchar (50) NOT NULL ,photo Varchar (50) NULL ,id_region Int NOT NULL ,CONSTRAINT Apprenant_PK PRIMARY KEY (id_apprenant) ,CONSTRAINT Apprenant_Region_FK FOREIGN KEY (id_region) REFERENCES Region(id_region))ENGINE=InnoDB;";
	AccesBD.executerUpdate(requete);
	
	requete = "CREATE TABLE peutAvoir(id_activite  Int NOT NULL ,id_apprenant Int NOT NULL,CONSTRAINT peutAvoir_PK PRIMARY KEY (id_activite,id_apprenant),CONSTRAINT peutAvoir_Activite_FK FOREIGN KEY (id_activite) REFERENCES Activite(id_activite),CONSTRAINT peutAvoir_Apprenant0_FK FOREIGN KEY (id_apprenant) REFERENCES Apprenant(id_apprenant))ENGINE=InnoDB;";
	AccesBD.executerUpdate(requete);
	
	requete = "INSERT INTO `region` (`nom_region`) VALUES ('Ile de France'), ('Pays de Loire'), ('Aquitaine');";
	AccesBD.executerUpdate(requete);
	
	requete = "INSERT INTO `activite` (`code_activite`, `nom_activite`) VALUES ('001', 'Programmer en java'),('002', 'Ecouter les mouches'),('003', 'Jouer au bilboquet'),('004', 'Dormir pendant le cours'),('005', 'Chercher un stage � Haiti'),('006', 'Attendre les vacances'),('007', 'Prendre le go�ter'),('008', 'Caresser le chat'),('009', 'Ecouter de la musique'),('010', 'Rien faire'),('011', 'Jouer � Angular'),('012', 'R�ver'),('013', 'Travailler jour et nuit');";
	AccesBD.executerUpdate(requete);

	requete = "INSERT INTO Apprenant (nom, prenom,dateNaissance, email, id_region) VALUES ('Autrique', 'G�raldine', '1970-12-27', 'geraldine.autrique@laposte.fr', 3 ),('Filine', 'Nicolas', '1986-08-07', 'nicolas.filine@laposte.fr', 1),('Gorce', 'Pierre', '1976-01-05', 'pierrexgorce@gmail.com', 1 ),('Joblon', 'Samuel', '1973-10-18', 'samuel.joblon@gmail.com', 1),('Kamvongsa', 'Phoneprasong', null, 'pomlao@hotmail.com', 2 ),('Lebegue', 'Vincent', '1986-08-13', 'vincent.lebegue@labanquepostale.fr', 1 ),('Londeix', 'Matthieu', '1981-05-19', 'matthieu.londeix@laposte.fr', 2),('Longueville', 'Thomas', '1972-04-26', 'thomas.longueville@laposte.fr', 2 ),('M�tivier', 'Christine', '1980-04-29', 'christine.pereira@laposte.fr', 1 ),('Picard', 'Laurent', '1972-03-22', 'laurent2.picard@laposte.fr', 1 ),('Pouline', 'David', '1982-07-07', 'david.pouline@facteo.fr', 3 ),('Prodhomme', 'Julien', '1990-08-30', 'prodhomme.julien@gmail.com', 1 ),('Sabot', 'Samuel', '1980-04-10', 'samuel.sabot@facteo.fr', 3),('Sancesario', 'Salvatore', '1975-05-10', 'salvatore.sancesario@facteo.fr', 1),('Sylvestre', 'David', '1986-07-06', 'david.sylvestre@mfacteur.fr', 2),('Tressous', 'C�dric', '1984-08-08', 'cedric.tressous@gmail.com', 2),('Z�butruc', 'Z�bulon', '1977-03-13', 'zebulonzeb@free.fr', 2);";
	AccesBD.executerUpdate(requete);
	
	requete = "INSERT INTO peutAvoir (id_activite, id_apprenant) VALUES (1, 1),(3, 1),(5, 1),(2, 2),(4, 2),(6, 2),(7, 3),(8, 4),(10, 4),(2, 5),(4, 5),(6, 5),(1, 6),(3, 6),(5, 6),(2, 7),(4, 7),(6, 7),(10, 8),(9, 8),(8, 8),(1, 9),(3, 9),(5,9),(11, 10),(2, 11),(4, 11),(6, 11),(1,12),(3, 12),(5, 12),(8, 13 ),(10, 13),(1, 14 ),(3, 14),(5, 14),(8, 15),(11, 16);";
	AccesBD.executerUpdate(requete);
}
		


		/*System.out.println("\n\nRequ�tes n�2 : (requ�te pr�par�es) Liste des avions ayant une capacit� sup�rieure � 300 passagers\n");
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
	 * M�thode ajouter un nouveau pilote
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
	 * m�thode pour modifier un pilote
	 * @param pilote
	 * @throws SQLException
	 */
	/*public static void modifierPilote(Pilote pilote) throws SQLException
	{
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("UPDATE pilote SET PI_NOM = 'Line' WHERE PI_ID = ? ");
			prepareStatement.setInt(1,pilote.getId());
			prepareStatement.executeUpdate();
			System.out.println("Modification effectu�e pour le pilote : "+pilote);

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
			System.out.println("Suppression du pilote "+ pilote+ " effectu�e");
		}
		catch(SQLException e){
			System.out.println("Erreur lors de la suppression du pilote !");
		}
	}*/
	
	/**
	 * M�thode pour retourner tous les pilotes dans un tableau
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
	
	// faites la m�me chose avec AVION
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
	// faites la m�me chose avec Vol (attention, un peu compliqu� !!!)
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
	
	// faites la m�me chose que pour modifierPilote()
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
	 * M�thode qui retourne une liste d'objets de type Avion
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
	 * M�thode qui retourne un objet de type Avion en fonction de son identifiant
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
	 * M�thode idem que ci-dessus avec Mapping d�l�gu� � une m�thode qui retourne un objet de type Avion en fonction de son identifiant
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
	 * M�thode qui retourne un objet de type Pilote en fonction de son identifiant
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
	 * D�monstration d'une m�thode qui r�cup�re des r�sultats sans les stocker dans des objets.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	
	//================================= Votre partie : Categorie =======================================

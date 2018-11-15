package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Apprenant;
import metier.Requetes;

public class Application {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			/*scanner = new Scanner(System.in);
			System.out.print("Saisir un chiffre : ");
			String saisie = scanner.nextLine();
			if(saisie.matches("[0-3]")) {
				int choix = Integer.parseInt(saisie);
				System.out.println(choix);*/
			
			//Requete pour initialiser les tables de la bd
			//Requetes.initilialiseBase();

			
			//Affichez les noms et prénoms de tous les apprenant(e)s.
			//Requetes.getAllApprenants();
			
			//Affichez la liste des apprenants pour chaque région (Ile France, Pays de Loire et Aquitaine).
			//Requetes.apprenantsByArea();
			
			//Recherchez un apprenant(e) (par son nom) et afficher la liste des activités qu’il ou qu’elle pratique.
			Requetes.ListeActiviteForApprenant();
			
			//Read : Ecrire une requête qui permet d’afficher la liste des activités que personne ne fait.
			//SELECT nom_activite FROM activite LEFT JOIN peutavoir ON peutavoir.id_activite = activite.id_activite WHERE peutavoir.id_activite IS NULL;
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

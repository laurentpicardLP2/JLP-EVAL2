package main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import menu.Menu;
import metier.Requetes;

public class Application {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			int choix;
			do {
				choix = Menu.showMenu();
				switch (choix) {
				case 1 : Requetes.getAllApprenants();
					break;
				case 2 : Requetes.apprenantsByArea();
					break;
				case 3 : Requetes.ListeActiviteForApprenant();
					break;
				case 4 : Requetes.ListeApprenantDoActivite();
					break;
				case 5 : Requetes.AddApprenant();
					break;
				case 6 : Requetes.AddCaresser();
					break;
				case 7 : Requetes.ListeActivitesNull();
					break;
				case 8 : Requetes.UpdateNomApprenant();
					break;
				case 9 : Requetes.DeleteApprenant();
					break;
				case 10 : Requetes.initilialiseBase();
					break;
					default : break;
				}
			}while(choix!=11);
			
			//Requete pour initialiser les tables de la bd
			//Requetes.initilialiseBase();

			
			//Affichez les noms et prénoms de tous les apprenant(e)s.
			//Requetes.getAllApprenants();
			
			//Affichez la liste des apprenants pour chaque région (Ile France, Pays de Loire et Aquitaine).
			//Requetes.apprenantsByArea();
			
			//Recherchez un apprenant(e) (par son nom) et afficher la liste des activités qu’il ou qu’elle pratique.
			//Requetes.ListeActiviteForApprenant();
			
			//Recherchez les apprenants qui pratiquent une activité passée en paramètre d’une méthode..
			//Requetes.ListeApprenantDoActivite();
			
			//Read : Ecrire une requête qui permet d’afficher la liste des activités que personne ne fait.7
			//SELECT nom_activite FROM activite LEFT JOIN peutavoir ON peutavoir.id_activite = activite.id_activite WHERE peutavoir.id_activite IS NULL;
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

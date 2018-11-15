package main;

import java.sql.SQLException;
import menu.Menu;
import metier.Requetes;

public class Application {
	
	public static void main(String[] args) {
		
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
				case 10 : Requetes.initialiseBase();
					break;
					default : break;
				}
				
			} while (choix!=11);
						
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

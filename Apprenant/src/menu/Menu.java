package menu;

import java.util.Scanner;

public class Menu {
	public static int showMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean bSaisie;
		
		bSaisie = false;
		do {
			System.out.println("1. Affichez les noms et prénoms de tous les apprenant(e)s.");
			System.out.println("2. Affichez la liste des apprenants pour chaque région (Ile France, Pays de Loire et Aquitaine).");
			System.out.println("3. Recherchez un apprenant(e) (par son nom) et afficher la liste des activités qu’il ou qu’elle pratique.");
			System.out.println("4. Recherchez les apprenants qui pratiquent une activité passée en paramètre d’une méthode.");
			System.out.println("5. Ajouter un(e) nouvel(le) apprenant(e).");
			System.out.println("6. Affecter 2 activités (Caresser le chat et Ecouter de la musique) à l’apprenant(e) que vous venez d’ajouter");
			System.out.println("7. Afficher la liste des activités que personne ne fait.");
			System.out.println("8. Mise à jour du nom d’un(e) apprenant(e).");
			System.out.println("9. Détruire un(e) apprenant(e) en fonction de l’objet Apprenant passé en paramètre.");
			System.out.println("10. Initialiser la base.");
			
			System.out.println("11. Quitter.");
			System.out.print("Saisir un chiffre : ");
			String saisie = scanner.nextLine();
			
			if(saisie.matches("[1-9]+")) {
				int choix = Integer.parseInt(saisie);
				return choix;
			}
		}while (!bSaisie);
		return 0;
		
	}
}

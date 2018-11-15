package menu;

import java.util.Scanner;

public class Menu {
	public static int showMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean bSaisie;
		
		bSaisie = false;
		do {
			System.out.println("1. Affichez les noms et pr�noms de tous les apprenant(e)s.");
			System.out.println("2. Affichez la liste des apprenants pour chaque r�gion (Ile France, Pays de Loire et Aquitaine).");
			System.out.println("3. Recherchez un apprenant(e) (par son nom) et afficher la liste des activit�s qu�il ou qu�elle pratique.");
			System.out.println("4. Recherchez les apprenants qui pratiquent une activit� pass�e en param�tre d�une m�thode.");
			System.out.println("5. Ajouter un(e) nouvel(le) apprenant(e).");
			System.out.println("6. Affecter 2 activit�s (Caresser le chat et Ecouter de la musique) � l�apprenant(e) que vous venez d�ajouter");
			System.out.println("7. Afficher la liste des activit�s que personne ne fait.");
			System.out.println("8. Mise � jour du nom d�un(e) apprenant(e).");
			System.out.println("9. D�truire un(e) apprenant(e) en fonction de l�objet Apprenant pass� en param�tre.");
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

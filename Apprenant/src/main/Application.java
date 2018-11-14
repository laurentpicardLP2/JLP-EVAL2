package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Apprenant;
import metier.Requetes;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			/*List<Apprenant> apprenants = Requetes.getAllApprenants();
			
			for (Apprenant apprenant : apprenants) {
	            System.out.println(apprenant);
	        }*/
			
			HashMap<String, ArrayList<Apprenant>> listeApprenantByArea = Requetes.apprenantsByArea();
			
			for (String key : listeApprenantByArea.keySet() ) {
			    //System.out.println(key + " " + listeApprenantByArea.get(key));
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

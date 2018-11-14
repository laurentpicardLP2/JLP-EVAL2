package main;

import java.sql.SQLException;
import java.util.List;

import model.Apprenant;
import metier.Requetes;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			List<Apprenant> apprenants = Requetes.getAllApprenants();
			
			for (Apprenant apprenant : apprenants) {
	            System.out.println(apprenant);
	        }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

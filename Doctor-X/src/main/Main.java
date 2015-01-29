package main;

import javax.swing.UIManager;

import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

public class Main
{

	public static void main(String[] args)
	{	
		try 
	    {
	      UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }
		
		System.out.println("sasdd");
		System.out.println("sadsad");
		
//		//Instanciranje glavnog modela Application koji se proslijedjuje View-u i Controlleru.
//		Application model = new Application();
//		
//		//Instanciranje View-a kome se proslijedjuje Model.
//		View view = new View(model);
//		
//		//Instanciranje kontrolera
//		new Controller(model, view);
	}

}

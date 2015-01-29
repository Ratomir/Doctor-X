package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.components.AtomComponent;
import parser.XMLreader;
import view.StateEditor;


/**
 * 
 * Klasa {@link Application} kreira aplikaciju.
 * 
 * @author Grupa 1
 *
 */
public class Application
{
	public static List<DocumentModel> documents = new ArrayList<DocumentModel>();	
	public static int IDcounter;
	public static String workspace;
	public static List<AtomComponent> clipboard = new ArrayList<AtomComponent>(); 
	public static int theme;
	
	public static StateEditor state = StateEditor.Normal;
	
	/**
	 * Inicijalni konstruktor za aplikaciju.
	 */
	public Application()
	{				
		IDcounter = 0;
		//citanje i kreiranje (ukoliko ne postoji) default workspace-a
		XMLreader reader = new XMLreader();
		File workspaceLoc;
				
		try
		{
			workspaceLoc = new File(reader.defaultWorkspace());
			if(!workspaceLoc.exists())
			{
				workspaceLoc = new File("C:/DoctorX");
				if(!workspaceLoc.exists())
					workspaceLoc.mkdir();			
				workspaceLoc = new File("C:/DoctorX/DefaultWorkspace");
				if(!workspaceLoc.exists())
					workspaceLoc.mkdir();
				
			}
		}
		catch(Exception e)
		{
			workspaceLoc = new File("C:/DoctorX");
			if(!workspaceLoc.exists())
				workspaceLoc.mkdir();			
			workspaceLoc = new File("C:/DoctorX/DefaultWorkspace");
			if(!workspaceLoc.exists())
				workspaceLoc.mkdir();				
		}
		workspace = workspaceLoc.getAbsolutePath();
		
		
		//ovde pitamo korisnika za workspace
		Object[] options1 = { "OK", "Browse", "Cancel" };
		JPanel panel = new JPanel();
		panel.add(new JLabel("Select workspace:"));
		JTextField textField = new JTextField(30);
		textField.setText(workspace);
		panel.add(textField);
		int result = JOptionPane.showOptionDialog(null, panel, "Choose workspace",
		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
		if (result == JOptionPane.NO_OPTION)
		{
			JFileChooser fileCh = new JFileChooser("C:/DoctorX/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "xml");
			fileCh.setFileFilter(filter);
			fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileCh.showOpenDialog(null);
			fileCh.setAcceptAllFileFilterUsed(false);
			if(option == JFileChooser.APPROVE_OPTION)
			{			
				Application.workspace = fileCh.getSelectedFile().toString();							
			}
		}
		else if(result == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		
	}
	
	/**
	 * Metoda koja kreira dokument.
	 */
	public void createDocument()
	{
		//Ime dokumenta se proslijedjuje kroz string. Trebamo skontati nacin imenovanja.
		DocumentModel dm = new DocumentModel("Document", "");
		documents.add(dm);		
	}
	
	/**
	 * Metoda koja vraca listu dokumenata unutar Aplikacije.
	 */
	public List<DocumentModel> getDocuments()
	{
		return documents;
	}
	
	/**
	 * Metoda koja postavlja dokumente.
	 */
	public void setDocuments(List<DocumentModel> documents)
	{
		Application.documents = documents;
	}

	public static int getIDcounter() {
		return IDcounter;
	}

	public static void setIDcounter(int iDcounter) {
		IDcounter = iDcounter;
	}

	/**
	 * @return the state
	 */
	public static StateEditor getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public static void setState(StateEditor state)
	{
		Application.state = state;
	}
}

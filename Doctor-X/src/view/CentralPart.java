package view;

import java.awt.Button;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import model.Application;
import model.DocumentModel;

public class CentralPart extends JTabbedPane
{

	/**
	 * Klasa koja predstavlja centralni dio za dodavanje canvasa.
	 */
	private static final long serialVersionUID = 1L;
	
	Application application = null;
	public List<Canvas> canvasList = new ArrayList<Canvas>();
	public JScrollPane scroll_1;	
		
		
	public CentralPart(Application application)
	{
		this.application = application;		
	}

	
	
	public void addNewDocument(DocumentModel docModel)
	{	
		boolean contains = false;
		for (DocumentModel doc : application.documents)
		{
			if(doc.name.equalsIgnoreCase(docModel.name) && doc.location.equalsIgnoreCase(docModel.location))
				contains = true;			
		}		
		if(!contains)
		{
			application.documents.add(docModel);
			canvasList.add(new Canvas(application));			
			JScrollPane scrollPane = new JScrollPane(canvasList.get(canvasList.size()-1), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			this.add(docModel.name, scrollPane);
			this.setSelectedIndex(this.getTabCount()-1);
						
			application.setIDcounter(docModel.IDdocument+1);
		}
	}
	
	/**
	 * Metoda koja radi repaint za svaki canvas.
	 */
	public void repaintAll()
	{
		for (int i = 0; i < canvasList.size(); i++)
		{
			canvasList.get(i).repaint();
		}
	}
}

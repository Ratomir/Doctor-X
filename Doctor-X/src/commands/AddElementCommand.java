package commands;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;
import view.Canvas;
import view.View;

/**
 * Klasa pomocu koje se implementira komanda dodavanja elementa na kanvas.
 * @author Ilija Divljan
 *
 */
public class AddElementCommand implements Command
{
	public Application model = null;	
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public View view = null;	
	
	public List<AtomComponent> previousComponents;
	public List<AtomComponent> nextComponents;
	
	public AddElementCommand(Application application, View view)
	{
		this.model = application;		
		this.listModel = application.getDocuments();
		this.view = view;
		
		previousComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
	}
	
	@Override
	public void execute(AtomComponent element) 
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		int canvasId = ((Canvas)viewport.getView()).IDcanvas; 
		
		//Deselektovanje svih selektovanih elemenata
		AtomComponent.selectNone(listModel.get(canvasId).nodes);
		
		//Iscrtavanje selektovane komponente
		element.setSelected(true);
		
		//Dodavanje komponente u doc model
		listModel.get(canvasId).nodes.add(element);	
		
		nextComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
	}

	@Override
	public void undo() 
	{
		listModel.get(selectedCanvas(view)).setElements(previousComponents);
		
	}

	@Override
	public void redo() 
	{
		listModel.get(selectedCanvas(view)).setElements(nextComponents);
		
	}

	
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas;	
	}
}

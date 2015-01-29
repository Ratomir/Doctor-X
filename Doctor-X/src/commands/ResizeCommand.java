package commands;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

/**
 * Klasa pomocu koje se implementira komanda promjene velicine elemenata.
 * @author Ilija Divljan
 *
 */
public class ResizeCommand implements Command 
{
	public Application model = null;	
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public View view = null;
	
	public List<AtomComponent> previousComponents;
	public List<AtomComponent> nextComponents;
	
	public int previousRadius = 0;
	public int nextRadius = 0;

	public ResizeCommand(Application application, View view)
	{
		this.model = application;		
		this.listModel = application.getDocuments();
		this.view = view;
		
		previousComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
		previousRadius = listModel.get(selectedCanvas(this.view)).radius;
	}	
	
	
	@Override
	public void execute(AtomComponent element) 
	{
		AtomComponent.updateRadius(listModel.get(view.centralPart.getSelectedIndex()).nodes, listModel.get(view.centralPart.getSelectedIndex()).radius);
		nextComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
		nextRadius = listModel.get(selectedCanvas(this.view)).radius;
	}

	@Override
	public void undo() 
	{
		listModel.get(selectedCanvas(view)).setElements(previousComponents);
		AtomComponent.updateRadius(listModel.get(view.centralPart.getSelectedIndex()).nodes, previousRadius);
	}

	@Override
	public void redo() 
	{
		listModel.get(selectedCanvas(view)).setElements(nextComponents);
		AtomComponent.updateRadius(listModel.get(view.centralPart.getSelectedIndex()).nodes, nextRadius);

	}
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}

}

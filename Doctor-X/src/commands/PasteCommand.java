package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

public class PasteCommand implements Command
{
	public Application model = null;	
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public View view = null;
	//public List<AtomComponent> copiedElements = new ArrayList<AtomComponent>();
	public List<AtomComponent> previousComponents;
	public List<AtomComponent> nextComponents;
	
	public PasteCommand(Application application, View view)
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
		
		ListIterator<AtomComponent> iter = Application.clipboard.listIterator();
		AtomComponent.selectNone(listModel.get(canvasId).nodes);
		while(iter.hasNext())
		{
			AtomComponent n = iter.next();			
			AtomComponent s = n.copy(n);					
			s.setSelected(true);
			listModel.get(canvasId).nodes.add(s);
		}
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

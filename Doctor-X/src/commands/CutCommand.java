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
import model.components.Edge;

public class CutCommand implements Command
{

	public Application model = null;	
	public List<DocumentModel> listModels = new ArrayList<DocumentModel>();
	public View view = null;
	
	public CutCommand(Application application, View view)
	{
		this.model = application;		
		this.listModels = application.getDocuments();
		this.view = view;
	}	

	@Override
	public void execute(AtomComponent element)
	{
		Application.clipboard.clear();
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		int canvasId = ((Canvas)viewport.getView()).IDcanvas;
		
		ListIterator<AtomComponent> iter = listModels.get(canvasId).nodes.listIterator();		
		while(iter.hasNext())
		{
			AtomComponent n = iter.next();						
			if(n.isSelected())
			{
				Application.clipboard.add(n);
				deleteEdges(n);
				iter.remove();
			}
		}
	}

	@Override
	public void undo()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void redo()
	{
		// TODO Auto-generated method stub

	}
	
	private void deleteEdges(AtomComponent n)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		int canvasId = ((Canvas)viewport.getView()).IDcanvas; 
		
		ListIterator<Edge> iter = listModels.get(canvasId).edges.listIterator();
		while(iter.hasNext())
		{
			Edge e = iter.next();
			if(e.n1 == n || e.n2 == n)
			{
				iter.remove();
			}
		}
	}

}

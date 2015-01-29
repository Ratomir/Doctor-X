package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.View;
import model.components.Edge;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

/**
 * Klasa pomocu koje se implementira komanda brisanja elemenata sa kanvasa.
 * @author Ilija Divljan
 *
 */
public class DeleteElementCommand implements Command 
{

	public Application model = null;
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public View view = null;
	
	public List<AtomComponent> previousComponents;
	public List<AtomComponent> nextComponents;
	
	public DeleteElementCommand(Application application, View view) 
	{
		this.model = application;
		this.view = view;
		listModel = application.getDocuments();
		
		previousComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
	}
	
	@Override
	public void execute(AtomComponent element) 
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		int canvasId = ((Canvas)viewport.getView()).IDcanvas; 
		
		//Cisto konverzija u iterativnu listu radi performansi.
		ListIterator<AtomComponent> iter = listModel.get(canvasId).nodes.listIterator();
		
		while(iter.hasNext())
		{
			AtomComponent n = iter.next();
			if(n.isSelected())
			{
				//Dakle kod selektovanih elemenata se brisu sve njihove veze a potom i oni sami.
				deleteEdges(n);
				iter.remove();
			}
		}
		nextComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
	}
	
	/**
	 * Metoda za brisanje veza.
	 */
	private void deleteEdges(AtomComponent n)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		int canvasId = ((Canvas)viewport.getView()).IDcanvas; 
		
		ListIterator<Edge> iter = listModel.get(canvasId).edges.listIterator();
		while(iter.hasNext())
		{
			Edge e = iter.next();
			if(e.n1 == n || e.n2 == n)
			{
				iter.remove();
			}
		}
		
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

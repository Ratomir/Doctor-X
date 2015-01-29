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
import model.components.Edge;
import model.components.EdgeKind;

/**
 * Klasa pomocu koje se implementira komanda povezivanja elemenata na kanvasu.
 * @author Ilija Divljan
 *
 */
public class ConnectCommand implements Command 
{

	public Application model = null;	
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public View view = null;
	EdgeKind kind =  null;
	
	public List<Edge> previousEdges;
	public List<Edge> nextEdges;

	public ConnectCommand(Application application, View view, EdgeKind kind)
	{
		this.model = application;		
		this.listModel = application.getDocuments();
		this.view = view;
		this.kind = kind;
		
		previousEdges = new ArrayList<Edge>(listModel.get(selectedCanvas(this.view)).edges);
	}	
	
	@Override
	public void execute(AtomComponent element) 
	{
		AtomComponent.getSelected(listModel.get(view.centralPart.getSelectedIndex()).nodes, listModel.get(view.centralPart.getSelectedIndex()).selected);
		
		int n = listModel.get(view.centralPart.getSelectedIndex()).selected.size();
		if(n > 1)
		{
			for(int i = 0; i < n-1; i++)
			{
				AtomComponent n1 = listModel.get(view.centralPart.getSelectedIndex()).selected.get(i);
				AtomComponent n2 = listModel.get(view.centralPart.getSelectedIndex()).selected.get(i+1);
				
				listModel.get(view.centralPart.getSelectedIndex()).edges.add(new Edge(n1, n2, kind));
			}
		}
		nextEdges = new ArrayList<Edge>(listModel.get(selectedCanvas(this.view)).edges);
	}

	@Override
	public void undo() 
	{
		listModel.get(selectedCanvas(this.view)).setListaKonekcija(previousEdges);

	}

	@Override
	public void redo() 
	{
		listModel.get(selectedCanvas(this.view)).setListaKonekcija(nextEdges);

	}
	
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}

}

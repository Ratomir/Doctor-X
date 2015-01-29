package commands;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.View;
import model.Application;
import model.components.AtomComponent;

public class CloseDocumentCommand implements Command
{	
	private Application model;
	private View view;
	
	public CloseDocumentCommand(Application model, View view)
	{
		this.model = model;
		this.view = view;
	}
	

	@Override
	public void execute(AtomComponent element)
	{
		if(view.centralPart.getSelectedIndex() != -1)
		{
			//ovde vrsimo odredjivanje koji je canvas selektovan
			JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
			JViewport viewport = selectedTab.getViewport();
			int canvasId = ((Canvas)viewport.getView()).IDcanvas;
			view.centralPart.remove(view.centralPart.getSelectedIndex());
			//ne brisemo iz spiska documenata jer bi kasnije izasli iz indeksa niza
			(model.documents.get(canvasId)).name = "none";
			(model.documents.get(canvasId)).nodes = null;
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

}

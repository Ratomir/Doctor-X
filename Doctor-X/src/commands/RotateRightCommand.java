package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

/**
 * Klasa koja implementira komandu za rotiranje elemenata u desno.
 * @author Ilija Divljan
 *
 */
public class RotateRightCommand implements Command 
{
	
	public Application model = null;	
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public View view = null;
	
	
	Vector<Integer> selektovaniIndeksi = new Vector<Integer>();
	
	public RotateRightCommand(Application application, View view)
	{
		this.model = application;		
		this.listModel = application.getDocuments();
		this.view = view;
		
		procitajIndekse();
	}	

	@Override
	public void execute(AtomComponent element) 
	{
		AtomComponent.Rotate(listModel.get(selectedCanvas(this.view)).nodes, 45);	

	}

	@Override
	public void undo() 
	{
		
		setSelected();
		AtomComponent.Rotate(listModel.get(selectedCanvas(this.view)).nodes, -45);
		

	}

	@Override
	public void redo() 
	{
		setSelected();
		AtomComponent.Rotate(listModel.get(selectedCanvas(this.view)).nodes, 45);

	}


	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}
	
	public void procitajIndekse()
	{
		for(int i = 0; i < listModel.get(selectedCanvas(this.view)).nodes.size(); i++)
		{
			if(listModel.get(selectedCanvas(this.view)).nodes.get(i).isSelected())
			{
				selektovaniIndeksi.add(i);
			}
		}
	}
	
	public void setSelected()
	{
		AtomComponent.selectNone(listModel.get(selectedCanvas(this.view)).nodes);
		for(Integer indeks : selektovaniIndeksi)
		{
			listModel.get(selectedCanvas(this.view)).nodes.get(indeks).setSelected(true);
		}
	}
}

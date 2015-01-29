package commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.Toolbar;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

/**
 * Klasa koja implementira komandu za bojenje komponenti i njenu undo i redo metodu.
 * @author Ilija Divljan
 *
 */
public class PaintCommand implements Command {

	public Application model = null;
	public List<DocumentModel> listModel = new ArrayList<DocumentModel>();
	public Toolbar toolbar = null;
	public View view;
	
	public Color previousColor;
	public List<AtomComponent> previousComponents;
	public List<AtomComponent> nextComponents;
	
	public Vector<Color> colorListPrevious = new Vector<Color>();
	public Vector<Color> colorListNext = new Vector<Color>();
	
	public PaintCommand(Application model, View view) 
	{
		this.model = model;
		listModel = model.documents;
		this.toolbar = view.toolbar;
		this.view = view;
		
		previousComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
		
		readColors(colorListPrevious, previousComponents);
	}
	
	@Override
	public void execute(AtomComponent element) 
	{
		
		//Ovo kupimo sa one Labele na Toolbaru.
		Color color = toolbar.hueIcon.getColor();
		color = JColorChooser.showDialog(null, "Chose a color", color);
		
		if(color != null)
		{

			AtomComponent.updateColor(listModel.get(selectedCanvas(view)).nodes, color);
			toolbar.hueIcon.setColor(color);
			toolbar.repaint();
		}
		
		nextComponents = new ArrayList<AtomComponent>(listModel.get(selectedCanvas(this.view)).nodes);
		readColors(colorListNext, nextComponents);

	}

	@Override
	public void undo() 
	{
		listModel.get(selectedCanvas(view)).setElements(previousComponents);
		int n = listModel.get(selectedCanvas(view)).nodes.size();
		for(int i = 0; i < n; i++)
		{
			AtomComponent.updateColorSingle(listModel.get(selectedCanvas(view)).nodes.get(i), colorListPrevious.get(i));	
		}
		
		toolbar.repaint();
	}

	@Override
	public void redo() 
	{
		listModel.get(selectedCanvas(view)).setElements(nextComponents);
		int n = listModel.get(selectedCanvas(view)).nodes.size();
		for(int i = 0; i < n; i++)
		{
			AtomComponent.updateColorSingle(listModel.get(selectedCanvas(view)).nodes.get(i), colorListNext.get(i));
			
		}
		
		toolbar.repaint();

	}
	
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}

	
	/**
	 * Ova metoda je potrebna da se procitaju boje jer u Javi se objekti prosljedjuju po referencama a ne vrijednostima.
	 * @param colors
	 * @param components
	 */
	public void readColors(Vector<Color> colors, List<AtomComponent> components)
	{
		for(int i = 0; i < components.size(); i++)
		{
			Color clr = new Color(0,0,0);
			clr = components.get(i).color;
			colors.add(clr);
		}
	}
}

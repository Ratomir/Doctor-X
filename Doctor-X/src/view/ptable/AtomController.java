/**
 * 
 */
package view.ptable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.StateEditor;
import view.View;
import view.ptable.jtable.CustomTableModel;

/**
 * @author Ratomir
 *
 */
public class AtomController extends MouseAdapter
{
	private Table table = null;
	
	public AtomController(Table table)
	{
		this.table = table;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getClickCount() == 2)
		{
			Atom tempAtom = (Atom)e.getSource();
			mouseExit(tempAtom);
			
			int index = selectedCanvas(this.table.modelView);
			if(index == -1)
			{
				this.getTable().dispose();
				return;
			}
			
			this.table.modelApplication.documents.get(index).atom = new Atom(tempAtom.getOrdinalNumber(), tempAtom.getSign(), tempAtom.getName(), tempAtom.getNumberOfRings(), tempAtom.getWeight(), tempAtom.getType());
			this.table.modelApplication.state = StateEditor.ReadyPaint;
			this.getTable().dispose();
		}
		else
		{
			Atom tempAtom = (Atom)e.getSource();
		
			CustomTableModel model = this.getTable().getCustomTable().getModel();
			model.setValueAt(tempAtom.getOrdinalNumber(), 0, 1);
			model.setValueAt(tempAtom.getSign(), 1, 1);
			model.setValueAt(tempAtom.getName(), 2, 1);
			model.setValueAt(tempAtom.getWeight(), 3, 1);
			model.setValueAt(tempAtom.getNumberOfRings().length, 4, 1);
			model.setValueAt(tempAtom.atomsPerRingToString(), 5, 1);
		
			this.getTable().getCustomTable().updateTable();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		Atom tempAtom = (Atom)e.getSource();
		tempAtom.setSelected(true);
		tempAtom.repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		mouseExit((Atom)e.getSource());
	}
	
	private void mouseExit(Atom atom)
	{
		atom.setSelected(false);
		atom.repaint();
	}

	/**
	 * @return the table
	 */
	public Table getTable()
	{
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table)
	{
		this.table = table;
	}
	
	//metoda sluzi za odredjivanje id selektovanog canvasa
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();
		if(selectedTab != null)
		{
			JViewport viewport = selectedTab.getViewport();
			return ((Canvas)viewport.getView()).IDcanvas; 
		}
		
		return -1;
	}

}

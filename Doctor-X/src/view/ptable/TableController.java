package view.ptable;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import parser.XMLwriter;

/**
 * @author Ratomir Vukadin
 *
 */
public class TableController extends WindowAdapter
{
	@Override
	public void windowClosing(WindowEvent e)
	{
		XMLwriter writer = new XMLwriter();
	}
	
	@Override
	public void windowClosed(WindowEvent e)
	{
	}
}

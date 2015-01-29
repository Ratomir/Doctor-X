package commands;

import model.components.AtomComponent;


/**
 * Interfejs kojeg implementiraju sve command klase
 * @author Ilija Divljan
 *
 */
public interface Command 
{
	public void execute(AtomComponent element);
	
	public void undo();
	
	public void redo();
	
	//public void setElementPoint(int x, int y);
}

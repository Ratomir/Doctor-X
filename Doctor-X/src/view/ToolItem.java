package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolItem extends JButton
{
	/**
	 * Klasa za definisanje toolItem-a pri cemu se parametri salju preko konstruktora.
	 * Takodje je u nastavku potrebno ubaciti i koje akcije se izvrsavaju na klik ali to tek kad se kreiraju te funkcije.
	 */
	private static final long serialVersionUID = 1L;

	public ToolItem(ImageIcon icon, String text, String actionCommand)
	{
		this.setIcon(icon);
		this.setToolTipText(text);
		this.setActionCommand(actionCommand);
	}
	

}

package view;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MenuItem extends JMenuItem
{
	/**
	 * Klasa za definisanje menuItem-a pri cemu se parametri salju preko konstruktora.
	 * Takodje je u nastavku potrebno ubaciti i koje akcije se izvrsavaju na klik ali to tek kad se kreiraju te funkcije.
	 */
	private static final long serialVersionUID = 1L;

	public MenuItem(int mnemKey, int mnemMask, ImageIcon icon, String toolTip, String text)
	{
		this.setText(text);		
		this.setIcon(icon);
		this.setToolTipText(toolTip);	
		this.setMnemonic(mnemKey);
		this.setAccelerator(KeyStroke.getKeyStroke(mnemKey, mnemMask));
	}
	
	public MenuItem(ImageIcon icon, String toolTip, String text)
	{
		this.setText(text);		
		this.setIcon(icon);
		this.setToolTipText(toolTip);	
	}

}

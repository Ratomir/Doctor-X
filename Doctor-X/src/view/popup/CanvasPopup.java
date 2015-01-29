package view.popup;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Klasa koja definise popup za elemente na kanvasu.w
 * @author Ilija Divljan
 *
 */
public class CanvasPopup extends JPopupMenu 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JMenuItem copyItm, pasteItm, cutItm, selectAllItm, deleteItm, rotateLeftItm, rotateRightItm;
	public JMenuItem singleConnectionItm, doubleConnectionItm, tripleConnectionItm;
	public JMenuItem theme1, theme2, theme3, color;
	
	public CanvasPopup()
	{
		
		
		cutItm = new JMenuItem("Cut", new ImageIcon("image/popup/cut-16.png"));
		cutItm.setActionCommand("Cut");
		this.add(cutItm);
		
		copyItm = new JMenuItem("Copy", new ImageIcon("image/popup/copy-16.png"));
		copyItm.setActionCommand("Copy");
		this.add(copyItm);
		
		pasteItm = new JMenuItem("Paste", new ImageIcon("image/popup/paste-16.png"));
		pasteItm.setActionCommand("Paste");
		this.add(pasteItm);
		
		this.addSeparator();
		
		selectAllItm = new JMenuItem("Select all", new ImageIcon("image/popup/select-all-16.png"));
		selectAllItm.setActionCommand("SellectAll");
		this.add(selectAllItm);
		
		deleteItm = new JMenuItem("Delete", new ImageIcon("image/popup/delete-16.png"));
		deleteItm.setActionCommand("Delete");
		this.add(deleteItm);
		
		this.addSeparator();
		
		
		//Submenu
		JMenu subMenu = new JMenu("Connection");
		
		singleConnectionItm = new JMenuItem("Single");
		singleConnectionItm.setActionCommand("Single");
		subMenu.add(singleConnectionItm);
		
		doubleConnectionItm = new JMenuItem("Double");
		doubleConnectionItm.setActionCommand("Double");
		subMenu.add(doubleConnectionItm);
		
		tripleConnectionItm = new JMenuItem("Triple");
		tripleConnectionItm.setActionCommand("Triple");
		subMenu.add(tripleConnectionItm);
		
		this.add(subMenu);
		
		rotateRightItm = new JMenuItem("Rotate Right", new ImageIcon("image/popup/rotate-right-16.png"));
		rotateRightItm.setActionCommand("RotateRight");
		this.add(rotateRightItm);
		
		rotateLeftItm = new JMenuItem("Rotate Left", new ImageIcon("image/popup/rotate-left-16.png"));
		rotateLeftItm.setActionCommand("RotateLeft");
		this.add(rotateLeftItm);
		
		this.addSeparator();
		
		
		color = new JMenuItem("Color");
		color.setActionCommand("Color");
		this.add(color);
		
		
		//potrebno definisati za teme i da se odavde mogu postaviti.
		JMenu subMenuTheme = new JMenu("Theme");
		
		theme1 = new JMenuItem("Tema 1");
		subMenuTheme.add(theme1);
		
		theme2 = new JMenuItem("Tema 2");
		subMenuTheme.add(theme2);
		
		theme3 = new JMenuItem("Tema 3");
		subMenuTheme.add(theme3);
		
		
		//this.add(subMenuTheme);
	}
	
	
	public void setElementListeners(ActionListener al)
	{
		copyItm.addActionListener(al);
		pasteItm.addActionListener(al);
		cutItm.addActionListener(al);
		selectAllItm.addActionListener(al);
		deleteItm.addActionListener(al);
		rotateLeftItm.addActionListener(al);
		rotateRightItm.addActionListener(al);
		singleConnectionItm.addActionListener(al);
		doubleConnectionItm.addActionListener(al);
		tripleConnectionItm.addActionListener(al);
		//theme1
		//theme2
		//theme3
		color.addActionListener(al);
	}
	

}

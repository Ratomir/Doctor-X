package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import localization.Localization;


public class Toolbar extends JToolBar
{
	
	
	/**
	 * Kreiranje toolbara.
	 */
	private static final long serialVersionUID = 1L;
	
	private Localization localization = Localization.getInstance();

	public ToolItem newItm, openItm, saveItm, saveAsItm, undoItm, redoItm, cutItm;
	public ToolItem copyItm, pasteItm, zoomInItm, zoomOutItm, selectAllItm, deleteItm, settingsItm;
	public ToolItem rotateRight, rotateLeft;
	
	//Ovo se kasnije moze prebaciti u ToolItem.
	
	public JButton  addElement, color, connect;
	public ColorIcon hueIcon = new ColorIcon(Color.blue);
	
	//JSpinner omogucava + i - opcije za resizovanje elemenata
	JSpinner js = null;
	
	public Toolbar()
	{						
		setRollover(true);
		//setFloatable(true);
		
		
		newItm = new ToolItem(new ImageIcon("image/menuIcons/new.png"), this.localization.getString("toolbox.new"), "New");
		this.localization.registerComponent("toolbox.new", newItm);
		add(newItm);
		openItm = new ToolItem(new ImageIcon("image/menuIcons/open.png"), this.localization.getString("toolbox.open"), "Open");
		this.localization.registerComponent("toolbox.open", openItm);
		add(openItm);	
		addSeparator();
		saveItm = new ToolItem(new ImageIcon("image/menuIcons/save.png"), this.localization.getString("toolbox.save"), "Save");
		this.localization.registerComponent("toolbox.save", saveItm);
		add(saveItm);
		saveAsItm = new ToolItem(new ImageIcon("image/menuIcons/saveAs.png"), this.localization.getString("toolbox.saveAs"), "SaveAs");
		this.localization.registerComponent("toolbox.saveAs", saveAsItm);
		add(saveAsItm);		
			
		//add(toolitem);	
		addSeparator();
		undoItm = new ToolItem(new ImageIcon("image/menuIcons/undo.png"), this.localization.getString("toolbox.undo"), "Undo");
		this.localization.registerComponent("toolbox.undo", undoItm);
		add(undoItm);
		//add(redoItm);		
		redoItm = new ToolItem(new ImageIcon("image/menuIcons/redo.png"), this.localization.getString("toolbox.redo"), "Redo");
		this.localization.registerComponent("toolbox.redo", redoItm);
		add(redoItm);
		addSeparator();
			
		cutItm = new ToolItem(new ImageIcon("image/menuIcons/cut.png"), this.localization.getString("toolbox.cut"), "Cut");
		this.localization.registerComponent("toolbox.cut", cutItm);
		add(cutItm);	
		copyItm = new ToolItem(new ImageIcon("image/menuIcons/copy.png"), this.localization.getString("toolbox.copy"), "Copy");
		this.localization.registerComponent("toolbox.copy", copyItm);
		add(copyItm);	
		pasteItm = new ToolItem(new ImageIcon("image/menuIcons/paste.png"), this.localization.getString("toolbox.paste"), "Paste");
		this.localization.registerComponent("toolbox.paste", pasteItm);
		add(pasteItm);		
		addSeparator();
			
			
		zoomInItm = new ToolItem(new ImageIcon("image/menuIcons/zoomIn.png"), this.localization.getString("toolbox.zoomIn"), "ZoomIn");
		this.localization.registerComponent("toolbox.zoomIn", zoomInItm);
		add(zoomInItm);		
		zoomOutItm = new ToolItem(new ImageIcon("image/menuIcons/zoomOut.png"), this.localization.getString("toolbox.zoomOut"), "ZoomOut");
		this.localization.registerComponent("toolbox.zoomOut", zoomOutItm);
		add(zoomOutItm);		
		addSeparator();
		
		selectAllItm = new ToolItem(new ImageIcon("image/menuIcons/select all.png"), this.localization.getString("toolbox.selectAll"), "SelectAll");
		this.localization.registerComponent("toolbox.selectAll", selectAllItm);
		add(selectAllItm);	
		deleteItm = new ToolItem(new ImageIcon("image/menuIcons/delete.png"), this.localization.getString("toolbox.delete"), "Delete");
		this.localization.registerComponent("toolbox.delete", deleteItm);
		add(deleteItm);		
		addSeparator();
		
		/*addElement = new JButton("Add");
		addElement.setActionCommand("Add");
		this.add(addElement);
		*/
		
		color = new JButton("Color");
		color.setActionCommand("Color");
		this.add(color);
		this.add(new JLabel(hueIcon));
		
		this.addSeparator(new Dimension(10, 0));
		//Dodavanje JSpinnera moze se iskoristiti sa Resize opciju elemenata.
		js = new JSpinner();
		//Konstruktor(PocetnaVrijednost, Minim, Maksimum, Korak rasta)
		js.setModel(new SpinnerNumberModel(20, 20, 100, 5));
		this.add(new JLabel("Size: "));
		js.setMaximumSize(new Dimension(50, 25));
		
		
		this.add(js);
		
		addSeparator();
		
		/*connect = new JButton("Connect");
		connect.setActionCommand("Connect");
		this.add(connect);*/
		
		
		
		//Potrebno postaviti ovu lokalizaciju za rotate left i rotate right.
		
		rotateLeft = new ToolItem(new ImageIcon("image/menuIcons/rotateLeft.png"), this.localization.getString("toolbox.rotateLeft"), "RotateLeft");
		this.localization.registerComponent("toolbox.rotateLeft", rotateLeft);
		add(rotateLeft);	
		
		rotateRight = new ToolItem(new ImageIcon("image/menuIcons/rotateRight.png"), this.localization.getString("toolbox.rotateRight"), "RotateRight");
		this.localization.registerComponent("toolbox.rotateRight", rotateRight);
		add(rotateRight);	
		
		//this.addSeparator();
		
		settingsItm = new ToolItem(new ImageIcon("image/menuIcons/settings.png"), this.localization.getString("toolbox.settings"), "Settings");
		this.localization.registerComponent("toolbox.settings", settingsItm);
		//add(settingsItm);
		
		
	}
	
	
	/**
	 * Postavljanje osluskivaca akcija na sve ToolIteme unutar Toolbara.
	 *
	 */
	public void setListeners(ActionListener al)
	{
		newItm.addActionListener(al);
		openItm.addActionListener(al);
		saveItm.addActionListener(al);
		saveAsItm.addActionListener(al);
		undoItm.addActionListener(al);
		redoItm.addActionListener(al);
		cutItm.addActionListener(al);
		copyItm.addActionListener(al);
		pasteItm.addActionListener(al);
		zoomInItm.addActionListener(al);
		zoomOutItm.addActionListener(al);
		selectAllItm.addActionListener(al);
		deleteItm.addActionListener(al);
		settingsItm.addActionListener(al);
		rotateLeft.addActionListener(al);
		rotateRight.addActionListener(al);
		
		//buttons
		//addElement.addActionListener(al);
		color.addActionListener(al);
		//connect.addActionListener(al);
	}
	
	public void setChangeListener(ChangeListener cl)
	{
		js.addChangeListener(cl);
	}
	
	
	/**
	 * Klasa ColorIcon je tu cisto da napravimo labelu za Color piker.
	 */
	public static class ColorIcon implements Icon 
	{

        private static final int WIDE = 15;
        private static final int HIGH = 15;
        private Color color;

        public ColorIcon(Color color) 
        {
            this.color = color;
        }

        public Color getColor() 
        {
            return color;
        }

        public void setColor(Color color) 
        {
            this.color = color;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) 
        {
            g.setColor(color);
            g.fillRect(x, y, WIDE, HIGH);
        }

        public int getIconWidth() 
        {
            return WIDE;
        }

        public int getIconHeight() 
        {
            return HIGH;
        }
    }
}

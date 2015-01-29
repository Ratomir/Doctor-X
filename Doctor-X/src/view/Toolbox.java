package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import model.components.AtomType;
import view.ptable.Atom;
import view.splitpane.SplitPanel;

/**
 * 
 * @author Ratomir
 *
 */
public class Toolbox extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String indexOfElement;
	
	private JButton tempButton = null;
	private ArrayList<JButton> listButtons = null;
	
	public Toolbox()
	{
		super(new BorderLayout());
		
		indexOfElement = "";
		
		this.setBackground(Color.ORANGE);
		
		listButtons = new ArrayList<JButton>();
		
		//################################
		
		SplitPanel splitPanelStandard = new SplitPanel("Standard", new ImageIcon("image/slidepane/bullet.png"));
		
		JPanel standardComponents = new JPanel();
		
		tempButton = new JButton(new ImageIcon("image/slidepane/standard/pointer.png"));
		tempButton.setBorder(UIManager.getBorder("Button.border"));
		tempButton.setBorderPainted(true);
		tempButton.setContentAreaFilled(false);
		tempButton.setOpaque(false);
		tempButton.setActionCommand("pointer");
		tempButton.setFocusable(true);
		standardComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		tempButton = new JButton(new ImageIcon("image/slidepane/standard/grabber.png"));
		nullBoreder(tempButton);
		tempButton.setActionCommand("grabber");
		standardComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		tempButton = new JButton(new ImageIcon("image/slidepane/standard/zoomin.png"));
		nullBoreder(tempButton);
		tempButton.setActionCommand("zoomIn");
		standardComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		splitPanelStandard.addComponent(standardComponents);
		
		tempButton = new JButton(new ImageIcon("image/slidepane/standard/zoomout.png"));
		nullBoreder(tempButton);
		tempButton.setActionCommand("zoomOut");
		standardComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		splitPanelStandard.addComponent(standardComponents);
		
		//################################
		
		SplitPanel splitPanelElements = new SplitPanel("Elements", new ImageIcon("image/slidepane/bullet.png"));
		
		JPanel elemntsComponents = new JPanel();
		
		tempButton = new Button(AtomType.Metalloids);
		tempButton.setActionCommand("metalloids");
		elemntsComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		tempButton = new Button(AtomType.Nonmetals);
		tempButton.setActionCommand("nonmetal");
		elemntsComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		tempButton = new Button(AtomType.AlkaliEarth);
		tempButton.setActionCommand("alkaliearth");
		elemntsComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		
		tempButton = new Button(AtomType.AlkalMetals);
		tempButton.setActionCommand("alkalimetals");
		elemntsComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		tempButton = new Button(AtomType.Halogens);
		tempButton.setActionCommand("halogens");
		elemntsComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		tempButton = new Button(AtomType.NobleGases);
		tempButton.setActionCommand("noblegases");
		elemntsComponents.add(tempButton);
		
		listButtons.add(tempButton);
		
		this.setMinimumSize(new Dimension(200, 200));
		this.setLayout(new BorderLayout());
		
		splitPanelElements.addComponent(elemntsComponents);
		
		//################################
		
		this.add(splitPanelStandard, BorderLayout.NORTH);
		this.add(splitPanelElements, BorderLayout.CENTER);
	}

	public Toolbox(LayoutManager layout)
	{
		super(layout);
	}
	
	public void nullBoreder(JButton button)
	{
		button.setBorder(null);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
	}
	

	/**
	 * @return the indexOfElement
	 */
	public String getIndexOfElement()
	{
		return indexOfElement;
	}

	/**
	 * @param indexOfElement the indexOfElement to set
	 */
	public void setIndexOfElement(String indexOfElement)
	{
		this.indexOfElement = indexOfElement;
	}

	/**
	 * @return the tempButton
	 */
	public JButton getTempButton()
	{
		return tempButton;
	}

	/**
	 * @param tempButton the tempButton to set
	 */
	public void setTempButton(JButton tempButton)
	{
		this.tempButton = tempButton;
	}

	/**
	 * @return the listButtons
	 */
	public ArrayList<JButton> getListButtons()
	{
		return listButtons;
	}

	/**
	 * @param listButtons the listButtons to set
	 */
	public void setListButtons(ArrayList<JButton> listButtons)
	{
		this.listButtons = listButtons;
	}
}

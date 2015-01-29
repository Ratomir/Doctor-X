/**
 * 
 */
package view.ptable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import model.Application;
import model.components.AtomType;
import view.View;
import view.ptable.jtable.CustomTable;

/**
 * @author Ratomir Vukadin
 *
 */
public class Table extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static List<Atom> listOfAtoms = Arrays.asList(
			
			//nemetali
			
			new Atom(1,  "H", "Hydrogen", new int[] {1},    1.008f,  AtomType.Nonmetals),
			new Atom(6,  "C", "Carbon",   new int[] {2, 4}, 12.011f, AtomType.Nonmetals),
			new Atom(7,  "N", "Nitrogen", new int[] {2, 5}, 14.007f, AtomType.Nonmetals),
			new Atom(8,  "O", "Oxygen",   new int[] {2, 6}, 15.999f, AtomType.Nonmetals),
			new Atom(15, "P", "Phosphorus", new int[] {2, 8, 5}, 30.9737f, AtomType.Nonmetals),
			new Atom(16, "S", "Sulfur",   new int[] {2, 8, 6}, 32.06f, AtomType.Nonmetals),
			
			//metaloidi
			
			new Atom(5,  "B",  "Boron",     new int[] {2, 3},       10.81f, AtomType.Metalloids),
			new Atom(14, "Si", "Silicon",   new int[] {2, 8, 4},    28.085f, AtomType.Metalloids),
			new Atom(32, "Ge", "Germanium", new int[] {2, 8, 18, 4},78.63f, AtomType.Metalloids),
			new Atom(33, "As", "Arsenic",   new int[] {2, 8, 18,5}, 74.921f, AtomType.Metalloids),
			new Atom(51, "Sb",  "Antimony", new int[] {2, 8, 18, 18, 5},    121.760f, AtomType.Metalloids),
			new Atom(52, "Te", "Tellerium", new int[] {2, 8, 18,18, 6}, 127.60f, AtomType.Metalloids),
			new Atom(84, "Po", "Polonium",  new int[] {2, 8, 18,32, 18, 6}, 209f, AtomType.Metalloids),
			
			//zemljani alkaliEarth
			
			new Atom(4,  "Be", "Beryllium", new int[] {2,2},    9.012f,  AtomType.AlkaliEarth),
			new Atom(12, "Mg", "Magnesium", new int[] {2, 8, 2}, 24.305f, AtomType.AlkaliEarth),
			new Atom(20, "Ca", "Calcium",   new int[] {2, 8, 8, 2}, 40.078f, AtomType.AlkaliEarth),
			new Atom(38, "Sr", "Strontium", new int[] {2, 8, 18, 8, 5}, 87.62f, AtomType.AlkaliEarth),
			new Atom(56, "Ba", "Barium",    new int[] {2, 8, 18, 18, 8, 2}, 137.327f, AtomType.AlkaliEarth),
			new Atom(88, "Ra", "Radium",    new int[] {2, 8, 18, 32, 18, 8, 2}, 226f, AtomType.AlkaliEarth),

			//metalni alkali metals
			
			new Atom(3,  "Li", "Lithium",  new int[] {2,1},    6.94f,  AtomType.AlkalMetals),
			new Atom(11, "Na", "Sodium",   new int[] {2, 8, 1}, 22.989f, AtomType.AlkalMetals),
			new Atom(19, "K",  "Podassium",new int[] {2, 8, 8, 1}, 39.0983f, AtomType.AlkalMetals),
			new Atom(37, "Rb", "Rubifium", new int[] {2, 8, 18, 8, 1}, 85.4578f, AtomType.AlkalMetals),
			new Atom(55, "Cs", "Caesium",  new int[] {2, 8, 18, 18, 8, 1}, 132.92f, AtomType.AlkalMetals),
			new Atom(87, "Fr", "Francium", new int[] {2, 8, 18, 32, 18, 8, 1}, 223f, AtomType.AlkalMetals),
			
			//halogeni
			
			new Atom(9,  "F", "Fluorine",      new int[] {2,7},         18.998f,  AtomType.Halogens),
			new Atom(17, "Cl", "Chlorine",     new int[] {2, 8, 7},     35.45f, AtomType.Halogens),
			new Atom(35, "Br", "Bromine",      new int[] {2, 8, 18, 7}, 79.904f, AtomType.Halogens),
			new Atom(53, "I", "Iodine",        new int[] {2, 8, 18, 18, 7}, 126.90f, AtomType.Halogens),
			new Atom(85, "At", "Astatine",     new int[] {2, 8, 18, 32, 18, 7}, 210f, AtomType.Halogens),
			new Atom(117, "Uus", "Ununseptium",new int[] {2, 8, 18, 32, 32, 18, 7}, 294f, AtomType.Halogens),

			//noble gases
			
			new Atom(2,   "He", "Hellium",    new int[] {2},    4.002602f,  AtomType.NobleGases),
			new Atom(10,  "Ne", "Neon",       new int[] {2, 8}, 20.1797f, AtomType.NobleGases),
			new Atom(18,  "Ar", "Argon",      new int[] {2, 8, 8}, 39.948f, AtomType.NobleGases),
			new Atom(36,  "Kr", "Krypton",    new int[] {2, 8, 18, 8}, 83.798f, AtomType.NobleGases),
			new Atom(54,  "Xe", "Yenon",      new int[] {2, 8, 18, 18, 8}, 131.293f, AtomType.NobleGases),
			new Atom(86,  "Rn", "Radon",      new int[] {2, 8, 18, 32, 18, 8}, 222f, AtomType.NobleGases),
			new Atom(118, "Uuo", "Ununodium", new int[] {2, 8, 18, 32, 32, 18, 8}, 294f, AtomType.NobleGases)
			
			
			);
	
	public View modelView = null;
	public Application modelApplication = null;
	
	private JPanel metalloids = null;
	private JPanel nonmetals = null;
	private JPanel alkaliEarth = null;
	private JPanel alkaliMetals = null;
	private JPanel halognes = null;
	private JPanel nobleGases = null;
	
	private CustomTable customTable = null;
	
	public Table(String command, View view, Application application)
	{
		this.modelView = view;
		this.modelApplication = application;
		
		this.customTable = new CustomTable();
		
		AtomController atomController = new AtomController(this);
		
		for (Atom atom : listOfAtoms)
		{
			atom.addMouseListener(atomController);
		}
		
		this.setLayout(new BorderLayout());
		
		createStateElemPanel();
		createCentralPanel(command);
		
		this.addWindowListener(new TableController());
		
		this.setTitle("Elements");
		
		this.setSize(700, 400);
		
		this.setLocationRelativeTo(this.modelView);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setType(Type.UTILITY);

		this.setIconImage(new ImageIcon("image/icon64x64.png").getImage());
		this.setVisible(true);
	}
	
	private void createCentralPanel(String tab)
	{
		JTabbedPane tabPanel = new JTabbedPane();
		
		metalloids = new JPanel();
		nonmetals = new JPanel();
		alkaliEarth = new JPanel();
		alkaliMetals = new JPanel();
		halognes = new JPanel();
		nobleGases = new JPanel();
		
		for (Atom atom : listOfAtoms)
		{
			AtomType type = atom.getType();
			
			if(type == AtomType.Nonmetals)
			{
				nonmetals.add(atom);
			}
			else if(type == AtomType.Metalloids)
			{
				metalloids.add(atom);
			}
			else if(type == AtomType.AlkaliEarth)
			{
				alkaliEarth.add(atom);
			}
			else if(type == AtomType.AlkalMetals)
			{
				alkaliMetals.add(atom);
			}
			else if(type == AtomType.Halogens)
			{
				halognes.add(atom);
			}
			else if(type == AtomType.NobleGases)
			{
				nobleGases.add(atom);
			}
				
		}
		
		tabPanel.addTab("<html><body><p style=\"font-size: 1.25em; \">Metalloids</p></body></html>",   new ImageIcon("image/tabIcon.png"), metalloids);
		tabPanel.addTab("<html><body><p style=\"font-size: 1.25em; \">Nonmetals</p></body></html>",    new ImageIcon("image/tabIcon.png"), nonmetals);
		tabPanel.addTab("<html><body><p style=\"font-size: 1.25em; \">Alkal earth</p></body></html>",  new ImageIcon("image/tabIcon.png"), alkaliEarth);
		tabPanel.addTab("<html><body><p style=\"font-size: 1.25em; \">Alkal metals</p></body></html>", new ImageIcon("image/tabIcon.png"), alkaliMetals);
		tabPanel.addTab("<html><body><p style=\"font-size: 1.25em; \">Halogens</p></body></html>",     new ImageIcon("image/tabIcon.png"), halognes);
		tabPanel.addTab("<html><body><p style=\"font-size: 1.25em; \">Noble gases</p></body></html>",  new ImageIcon("image/tabIcon.png"), nobleGases);
		
		if(tab == "metalloids")
		{
			tabPanel.setSelectedIndex(0);
		}
		else if(tab == "nonmetal")
		{
			tabPanel.setSelectedIndex(1);
		}
		else if(tab == "alkaliearth")
		{
			tabPanel.setSelectedIndex(2);
		}
		else if(tab == "alkalimetals")
		{
			tabPanel.setSelectedIndex(3);
		}
		else if(tab == "halogens")
		{
			tabPanel.setSelectedIndex(4);
		}
		else if(tab == "noblegases")
		{
			tabPanel.setSelectedIndex(5);
		}
		
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.getCustomTable() , tabPanel);
		splitPanel.setResizeWeight(0.4d);
		
		this.add(splitPanel, BorderLayout.CENTER);
	}
	
	private void createStateElemPanel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.add(new JLabel(new ImageIcon("image/solid.png")));
		panel.add(new JLabel(new ImageIcon("image/liquid.png")));
		panel.add(new JLabel(new ImageIcon("image/gas.png")));
		panel.add(new JLabel(new ImageIcon("image/unknow.png")));
		this.add(panel, BorderLayout.NORTH);
	}
	
	/**
	 * @return the listOfAtoms
	 */
	public static List<Atom> getListOfAtoms()
	{
		return listOfAtoms;
	}

	/**
	 * @param listOfAtoms the listOfAtoms to set
	 */
	public static void setListOfAtoms(List<Atom> listOfAtoms)
	{
		Table.listOfAtoms = listOfAtoms;
	}

	/**
	 * @return the customTable
	 */
	public CustomTable getCustomTable()
	{
		return customTable;
	}

	/**
	 * @param customTable the customTable to set
	 */
	public void setCustomTable(CustomTable customTable)
	{
		this.customTable = customTable;
	}

}

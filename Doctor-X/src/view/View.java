package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import parser.XMLreader;
import localization.Localization;
import model.Application;
import controller.WindowController;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Klasa {@link View}
	 */
	
	public Application model = null;
	
	private JPanel mainPanel;
	private JSplitPane splitPane;
	private JSplitPane rightSplitPane;
	
	public ProjectExplorer projectExplorer = null;
	public CentralPart centralPart = null;
	public Toolbox toolbox = null;
	public Menu menuBar = null;
	public StatusBar statusBar = null;
	public Toolbar toolbar = null;
	
	private Localization localization = null;
	
	public View(Application application)
	{
		
		this.model = application;
		
		//poziv konstruktora cije klase formiraju prikaz editora
		
		
		
		mainPanel = new JPanel(new BorderLayout());
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		//TODO lijevi split pane, treba postaviti ono za max
		splitPane.setResizeWeight(0.2);
		SplitPaneUI ui = splitPane.getUI();
		
		ui = splitPane.getUI();
		
		if(ui instanceof BasicSplitPaneUI)
		{
			((BasicSplitPaneUI)ui).getDivider().setBorder(null);
		}
		
		rightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		ui = rightSplitPane.getUI();
		
		if(ui instanceof BasicSplitPaneUI)
		{
			((BasicSplitPaneUI)ui).getDivider().setBorder(null);
		}
		
		
		try 
		{
			XMLreader reader = new XMLreader();
			String theme = "theme" + reader.defaultTheme();
			if(theme.equals("theme1"))
			{
				UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
			}
			else if(theme.equals("theme2"))
			{
				UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
			}
			else if(theme.equals("theme3"))
			{
				UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
			}
			else if(theme.equals("theme4"))
			{
				UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
			}
			else if(theme.equals("theme5"))
			{
				UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
			}
			else if(theme.equals("theme6"))
			{
				UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
			}
			else if(theme.equals("theme7"))
			{
				UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		/**
		 * Inicijalizacija ProjectExplorer, CentralPart, Toolbox
		 */
		projectExplorer = new ProjectExplorer(new FlowLayout(), Application.workspace);
		centralPart = new CentralPart(application);
		toolbox = new Toolbox();
				
		//citanje iz xml-a za lokalizaciju
		XMLreader reader = new XMLreader();
		this.localization = Localization.getInstance("en", "US");
		menuBar = new Menu(reader.ReadWindowParameters(this));
		statusBar = new StatusBar(application, this);
		toolbar = new Toolbar();
		
		//DODAJEMO NA SPLIT
		
		//TODO desni split pane provjeriti ovo za max razvlacenje
		
		
		rightSplitPane.setResizeWeight(1d);
		
		
		rightSplitPane.setLeftComponent(centralPart);
		rightSplitPane.setRightComponent(toolbox);
		rightSplitPane.setDividerLocation(this.getWidth()-this.toolbox.getWidth()-650);
		
		splitPane.setLeftComponent(projectExplorer);
		splitPane.setRightComponent(rightSplitPane);
		
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		mainPanel.add(statusBar, BorderLayout.SOUTH);
		
		this.setTitle(localization.getString("title"));
		this.localization.registerComponent("title", this);
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(true);
		this.setType(Type.NORMAL);

		this.addWindowListener(new WindowController(this));

		this.add(mainPanel);
		
		this.setJMenuBar(menuBar);
		this.add(toolbar, BorderLayout.NORTH);					
		this.setIconImage(new ImageIcon("image/icon64x64.png").getImage());
		this.setVisible(true);
	}

}

package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

import controller.ItemController;
import localization.Localization;

public class Menu extends JMenuBar
{
	/**
	 * Kreiranje menija
	 */
	private static final long serialVersionUID = 1L;

	private JMenu file;
	private JMenu edit;
	private JMenu options;
	private JMenu view;
	private JMenu help;

	private Localization localization = null;
	private ItemController itemController = null;
	
	private JCheckBoxMenuItem langEn   = null;
	private JCheckBoxMenuItem langSrBa = null;
	private JCheckBoxMenuItem langSrRs = null;
	
	private JCheckBoxMenuItem themeCheckBox = null;
	
	//lista menuitem potrebna je za implementaciju listenera
	public List<MenuItem> lm = new ArrayList<MenuItem>();

	/**
	 * Konstruktor vrsi kreiranje menija.
	 * 
	 * @param lang parametar po kojem se prepoznaje koji je jezik zadnji koriscen
	 */
	public Menu(String lang)
	{
		// Kreiranje menija File
		this.localization = Localization.getInstance();
		
		this.itemController = new ItemController();

		file = new JMenu(this.localization.getString("menufile"));
		this.localization.registerComponent("menufile", file);
				
		/*MenuItem menuItem = new MenuItem(KeyEvent.VK_N, ActionEvent.ALT_MASK, new ImageIcon("image/menuIcons/new.png"),
				"Creates New Document", this.localization.getString("menufile.new"));
		this.localization.registerComponent("menufile.new", menuItem);
		file.add(menuItem); lm.add(menuItem);*/
		
		//dodavanje podmenija
		JMenu newf = new JMenu(this.localization.getString("menufile.new"));
		this.localization.registerComponent("menufile.new", newf);
		MenuItem menuItem = new MenuItem(null,"Creates New Document", this.localization.getString("menufile.newDoc"));
		this.localization.registerComponent("menufile.newDoc", menuItem);
		newf.add(menuItem); lm.add(menuItem);
		menuItem = new MenuItem(null, "Creates New Project", this.localization.getString("menufile.newProj"));
		this.localization.registerComponent("menufile.newProj", menuItem);
		newf.add(menuItem); lm.add(menuItem);
		
		menuItem = new MenuItem(null, "Creates New Workspace", this.localization.getString("menufile.newWork"));
		this.localization.registerComponent("menufile.newWork", menuItem);
		newf.add(menuItem); lm.add(menuItem);
		file.add(newf);
		//********
		

		menuItem = new MenuItem(KeyEvent.VK_O, ActionEvent.ALT_MASK, new ImageIcon("image/menuIcons/open.png"),
				"Opens Existing Document", this.localization.getString("menufile.open"));
		this.localization.registerComponent("menufile.open", menuItem);
		file.add(menuItem); lm.add(menuItem);
		
		//switch workspace
		menuItem = new MenuItem(KeyEvent.VK_W, ActionEvent.ALT_MASK, new ImageIcon("image/menuIcons/open.png"),
				"Switch Workspace", this.localization.getString("menufile.switch"));
		this.localization.registerComponent("menufile.switch", menuItem);
		file.add(menuItem); lm.add(menuItem);

		menuItem = new MenuItem(KeyEvent.VK_C, ActionEvent.ALT_MASK, new ImageIcon("image/menuIcons/cancel.png"),
				"Close Document", this.localization.getString("menufile.close"));
		this.localization.registerComponent("menufile.close", menuItem);
		file.add(menuItem); lm.add(menuItem);

		file.add(new JSeparator(JSeparator.HORIZONTAL), 4);

		menuItem = new MenuItem(KeyEvent.VK_S, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/save.png"),
				"Save Document", this.localization.getString("menufile.save"));
		this.localization.registerComponent("menufile.save", menuItem);
		file.add(menuItem); lm.add(menuItem);

		menuItem = new MenuItem(new ImageIcon("image/menuIcons/saveAs.png"), "Save Document As", this.localization.getString("menufile.saveAs"));
		this.localization.registerComponent("menufile.saveAs", menuItem);
		file.add(menuItem); lm.add(menuItem);

		file.add(new JSeparator(JSeparator.HORIZONTAL), 7);

		menuItem = new MenuItem(KeyEvent.VK_E, ActionEvent.ALT_MASK, new ImageIcon("image/menuIcons/exit.png"),
				"Exit Program", this.localization.getString("menufile.exit"));
		this.localization.registerComponent("menufile.exit", menuItem);
		file.add(menuItem); lm.add(menuItem);

		this.add(file);

		// Kreiranje menija Edit
		edit = new JMenu(this.localization.getString("menuedit"));
		this.localization.registerComponent("menuedit", edit);

		menuItem = new MenuItem(KeyEvent.VK_Z, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/undo.png"), "",
				this.localization.getString("menuedit.undo"));
		this.localization.registerComponent("menuedit.undo", menuItem);
		edit.add(menuItem); lm.add(menuItem);
		
		
		menuItem = new MenuItem(KeyEvent.VK_Y, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/redo.png"), "",
				this.localization.getString("menuedit.redo"));
		this.localization.registerComponent("menuedit.redo", menuItem);
		edit.add(menuItem); lm.add(menuItem);
		
		
		menuItem = new MenuItem(KeyEvent.VK_X, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/cut.png"),
				"Cut Element", this.localization.getString("menuedit.cut"));
		this.localization.registerComponent("menuedit.cut", menuItem);
		edit.add(menuItem); lm.add(menuItem);
		
		edit.add(new JSeparator(JSeparator.HORIZONTAL), 2);
		
		menuItem = new MenuItem(KeyEvent.VK_C, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/copy.png"),
				"Copy Element", this.localization.getString("menuedit.copy"));
		this.localization.registerComponent("menuedit.copy", menuItem);
		edit.add(menuItem); lm.add(menuItem);
		
		menuItem = new MenuItem(KeyEvent.VK_V, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/paste.png"),
				"Paste Element", this.localization.getString("menuedit.paste"));
		this.localization.registerComponent("menuedit.paste", menuItem);
		edit.add(menuItem); lm.add(menuItem);
		
		edit.add(new JSeparator(JSeparator.HORIZONTAL), 6);
		
		menuItem = new MenuItem(KeyEvent.VK_DELETE, 0, new ImageIcon("image/menuIcons/delete.png"), "Delete Element",
				this.localization.getString("menuedit.delete"));
		this.localization.registerComponent("menuedit.delete", menuItem);
		edit.add(menuItem); lm.add(menuItem);
		
		menuItem = new MenuItem(KeyEvent.VK_A, ActionEvent.CTRL_MASK, new ImageIcon("image/menuIcons/select all.png"),
				"Selects All Document", this.localization.getString("toolbox.selectAll"));
		this.localization.registerComponent("toolbox.selectAll", menuItem);
		edit.add(menuItem); lm.add(menuItem);

		this.add(edit);

		// Kreiranje menija View
		view = new JMenu("View");
		this.localization.registerComponent("menuview", view);

		menuItem = new MenuItem(new ImageIcon("image/menuIcons/zoomIn.png"), "", this.localization.getString("menuview.zoomIn"));
		this.localization.registerComponent("menuview.zoomIn", menuItem);
		view.add(menuItem); lm.add(menuItem);
		
		menuItem = new MenuItem(new ImageIcon("image/menuIcons/zoomOut.png"), "", this.localization.getString("menuview.zoomOut"));
		this.localization.registerComponent("menuview.zoomOut", menuItem);
		view.add(menuItem); lm.add(menuItem);

		this.add(view);

		// Kreiranje menija Options
		options = new JMenu(this.localization.getString("menuoptions"));
		this.localization.registerComponent("menuoptions", options);

		JMenu settings = new JMenu(this.localization.getString("menuoptions.settings"));
		this.localization.registerComponent("menuoptions.settings", settings);
		
		JMenu languange = new JMenu(this.localization.getString("menuoptions.languange"));
		this.localization.registerComponent("menuoptions.languange", languange);
		
		ButtonGroup checkGroup = new ButtonGroup();
		
		//###############################
		
		langEn = new JCheckBoxMenuItem(this.localization.getString("menuoptions.languange.en_US"));
		this.localization.registerComponent("menuoptions.languange.en_US", langEn);
		
		langEn.setActionCommand("en_US");
		checkGroup.add(langEn);
		languange.add(langEn);
		langEn.addItemListener(itemController);
		
		langSrBa = new JCheckBoxMenuItem(this.localization.getString("menuoptions.languange.sr_BA"));
		this.localization.registerComponent("menuoptions.languange.sr_BA", langSrBa);
		
		langSrBa.setActionCommand("sr_BA");
		checkGroup.add(langSrBa);
		languange.add(langSrBa);
		langSrBa.addItemListener(itemController);
		
		langSrRs = new JCheckBoxMenuItem(this.localization.getString("menuoptions.languange.sr_RS"));
		this.localization.registerComponent("menuoptions.languange.sr_RS", langSrRs);
		
		langSrRs.setActionCommand("sr_RS");
		checkGroup.add(langSrRs);
		languange.add(langSrRs);
		langSrRs.addItemListener(itemController);
		
		//################################
		
		if(lang == "en_US")
		{
			langEn.setSelected(true);
		}
		else if(lang == "sr_BA")
		{
			langSrBa.setSelected(true);
		}
		else
		{
			langSrRs.setSelected(true);
		}
		
		settings.add(languange);
		options.add(settings);
		
		JMenu theme = new JMenu(this.localization.getString("menuoptions.theme"));
		this.localization.registerComponent("menuoptions.theme", theme);
		
		checkGroup = new ButtonGroup();
		
		//###############################
		
		//####### theme 1 ######
		
		for(int i=1; i<=7; i++)
		{
			themeCheckBox = new JCheckBoxMenuItem(this.localization.getString("menuoptions.theme.t" + i));
			this.localization.registerComponent("menuoptions.theme.t" + i, themeCheckBox);
			
			themeCheckBox.setActionCommand("theme" + i);
			checkGroup.add(themeCheckBox);
			theme.add(themeCheckBox);
			themeCheckBox.addItemListener(itemController);
		}
		
		options.add(theme);		
		this.add(options);

		// Kreiranje menija Help
		help = new JMenu(this.localization.getString("menuhelp"));
		this.localization.registerComponent("menuhelp", help);

		menuItem = new MenuItem(new ImageIcon("image/menuIcons/help.png"), "", this.localization.getString("menuhelp.help"));
		this.localization.registerComponent("menuhelp.help", menuItem);
		help.add(menuItem); lm.add(menuItem);
		
		menuItem = new MenuItem(new ImageIcon("image/menuIcons/about.png"), "", this.localization.getString("menuhelp.about"));
		this.localization.registerComponent("menuhelp.about", menuItem);
		help.add(menuItem); lm.add(menuItem);

		this.add(help);

		// Po potrebi dodavanje novih menija....

	}		
}
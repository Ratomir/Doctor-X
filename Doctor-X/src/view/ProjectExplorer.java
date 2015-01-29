package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;


import localization.Localization;
import model.Application;

public class ProjectExplorer extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;		
	public JPanel panel;
	public DefaultTreeModel theTreeModel;
	public JTree theTree;
	public DefaultMutableTreeNode fileSystem;
	
	public JMenuItem add, remove, rename;
	private Localization localization = Localization.getInstance();
	public JPopupMenu popupMenu;

	public ProjectExplorer()
	{		
	}

	public ProjectExplorer(LayoutManager layout, String workspaceLocation)
	{
		super(layout);
		this.setBackground(new Color(240, 240, 240));	
		
		//Postavljanje popup prozora na desni klik
		add = new JMenuItem(this.localization.getString("menufile.newDoc"));
		remove = new JMenuItem(this.localization.getString("menuedit.delete"));
		rename = new JMenuItem(this.localization.getString("menufile.rename"));		
		this.localization.registerComponent("menufile.newDoc", add);
		this.localization.registerComponent("menuedit.delete", remove);
		this.localization.registerComponent("menuedit.copy", rename);
		popupMenu = new JPopupMenu();	
		
		
		//******
		
		//Postavljanje object browsera	
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(new Color(240, 240, 240));				
		this.add(panel);	
		
		File dir = new File(workspaceLocation);
		fileSystem = new DefaultMutableTreeNode(dir.getName());
		theTreeModel = new DefaultTreeModel(fileSystem);
		theTree = new JTree(theTreeModel);		  	    
	    
		theTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		theTree.setVisibleRowCount(8);	
		theTree.setBackground(new Color(240, 240, 240));		
		theTree.setCellRenderer(new TreeRenderer());
				
		//dodavanje elemenata u stablo	
		listFiles(dir, fileSystem);
		
		
		panel.add(theTree);
	}
	
	public File listFiles(File dir, DefaultMutableTreeNode parent)
	{
		File temp;
		String[] children = dir.list();
		if (children !=null)
		{
		    for (int i=0; i<children.length; i++)
		    {
		        temp = new File(dir, children[i]);		        
		        DefaultMutableTreeNode element = new DefaultMutableTreeNode(children[i]);
		        parent.add(element);
		        listFiles(temp.getAbsoluteFile(), element);		        		        	
		    }
		}		
		return dir;
	}
	
	public void reloadTree()
	{
		File dir = new File(Application.workspace);		
		fileSystem.setUserObject(dir.getName());
		fileSystem.removeAllChildren();		
		listFiles(new File(Application.workspace), fileSystem);
		theTreeModel.reload();
	}
		
	public void showPopUp(int x, int y, Boolean file)
	{	
		for (java.awt.Component item : popupMenu.getComponents()) {
			popupMenu.remove(item);
		}
		
		popupMenu.add(add);
		popupMenu.add(remove);
		if(file)
			popupMenu.add(rename);
		
		popupMenu.show(theTree, x, y);
	}
}

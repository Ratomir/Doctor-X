package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import commands.Command;
import parser.XMLreader;
import view.Canvas;
import model.Application;
import model.DocumentModel;

public class ProjectExplorerController
{
	public Application appModel = null;
	public view.View appView = null;
	public HashMap<String, Command> commands = null;
	
	public ProjectExplorerController(Application model, view.View view, HashMap<String, Command> commands)
	{
		this.appModel = model;
		this.appView = view;		
		this.commands = commands;
		appView.projectExplorer.theTree.addMouseListener(new MouseHandler());
		
		appView.projectExplorer.add.addActionListener(rcl);
		appView.projectExplorer.remove.addActionListener(rcl);
		appView.projectExplorer.rename.addActionListener(rcl);		
	}
	
	RightMouseHandler rcl = new RightMouseHandler();
	
	public class MouseHandler extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2)
			{
				JTree selectedNod = (JTree) e.getSource();				
				TreePath path = selectedNod.getPathForLocation(e.getX(), e.getY());							
	                                         
	            if(path.toString().contains(".xml"))
	            {
	              	//regex da konvertuje treepath u putanju na disku
	            	//putanja ne smije imati prazan karakter..
	               	String filePath = path.toString().replaceAll("\\]|\\[|", "").replaceAll(", ", "/");	
	               	XMLreader reader = new XMLreader();
	               	File path1 = new File(Application.workspace);
	               	File path2 = new File(""); path2 = path1.getParentFile();	               	
	               	DocumentModel doc = reader.ReadDocument(path2 + "/" + filePath);
	               	appView.centralPart.addNewDocument(doc);
	            }	            				
				super.mouseClicked(e);
			}
			
			if(SwingUtilities.isRightMouseButton(e))
			{
				//ovaj dio sluzi da izuzme workspace od popup-a
				TreePath path = ((JTree) e.getSource()).getPathForLocation(e.getX(), e.getY());
				String filePath =path.toString().replaceAll("\\]|\\[|", "").replaceAll(", ", "/");
				File path1 = new File(Application.workspace);
               	File path2 = new File(""); path2 = path1.getParentFile();
				filePath = path2 + "\\" + filePath;				
				if(!filePath.equals(Application.workspace))
				{
					appView.projectExplorer.theTree.setSelectionPath(path);
					if(filePath.contains(".xml"))
						appView.projectExplorer.showPopUp(e.getX(), e.getY(), true);
					else
						appView.projectExplorer.showPopUp(e.getX(), e.getY(), false);
				}
		    }
		}
	}
	
	
	
	public class RightMouseHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			TreePath path = appView.projectExplorer.theTree.getSelectionPath();	
			String filePath =path.toString().replaceAll("\\]|\\[|", "").replaceAll(", ", "/");
			File path1 = new File(Application.workspace);
           	File path2 = new File(""); path2 = path1.getParentFile();
			filePath = path2 + "\\" + filePath;	
			switch (actionCommand) 
			{					
				//potrebno je dodati jos cirilicnu lokalizaciju							
				case "New Document": case "Novi dokument":
					//ovde se vracamo na projekat
					if(filePath.contains(".xml"))
					{
						File file = new File(filePath);						
						filePath = file.getParentFile().toString();
					}
					filePath = filePath + "/NewDocument" + (Application.documents.size()+1) + ".xml";					
					//snimanje dokumenta
					DocumentModel newDocument = new DocumentModel("NewDocument" + (Application.documents.size()+1), filePath);
					appView.centralPart.addNewDocument(newDocument);
					commands.get("save").execute(null);
					appView.projectExplorer.reloadTree();
					break;
										
				case "Rename": case "Preimenuj":					
					File oldFile = new File(filePath);
					String name = JOptionPane.showInputDialog("New name:");
					try {
					if(!name.isEmpty())
					{
						String newFilePath = oldFile.getAbsolutePath().replace(oldFile.getName(), "") + name + ".xml";
						DocumentModel newDoc = new DocumentModel(name, newFilePath);						
						appView.centralPart.addNewDocument(newDoc);					
						for (DocumentModel item : Application.documents)
						{												
							if(item.location.contains(oldFile.getName()))
							{
								int index = item.IDdocument;
								newDoc.nodes = item.nodes;
								newDoc.edges = item.edges;
								int totalTabs = appView.centralPart.getTabCount();
								for(int i = 0; i < totalTabs; i++)
								{
									JScrollPane selectedTab = (JScrollPane) appView.centralPart.getComponentAt(i);
									JViewport viewport = selectedTab.getViewport();
									int canvasId = ((Canvas)viewport.getView()).IDcanvas;
									if(index == canvasId)
									{
										appView.centralPart.remove(appView.centralPart.getComponentAt(i));
										i--;
										totalTabs--;
									}
								}							
							}							
						}	
						commands.get("save").execute(null);
						oldFile.delete();
						appView.projectExplorer.reloadTree();
					}
					}catch (Exception e1) {}					
					break;	
					
				case "Delete": case "Pobri\u0161i":					
					File file = new File(filePath);
					//da ga izbrise iz aplikacije					
					if(file.isDirectory())
					{
						for (DocumentModel item : Application.documents)
						{												
							if(item.location.contains(file.getName()))
							{
								int index = item.IDdocument;
								int totalTabs = appView.centralPart.getTabCount();
								for(int i = 0; i < totalTabs; i++)
								{
									JScrollPane selectedTab = (JScrollPane) appView.centralPart.getComponentAt(i);
									JViewport viewport = selectedTab.getViewport();
									int canvasId = ((Canvas)viewport.getView()).IDcanvas;
									if(index == canvasId)
									{
										appView.centralPart.remove(appView.centralPart.getComponentAt(i));
										i--;
										totalTabs--;
									}
								}							
							}
						}		
						String[] entries = file.list();
						for(String s: entries){
							File currentFile = new File(file.getPath(),s);
							currentFile.delete();
						}
					}
					else
					{
						for (DocumentModel item : Application.documents)
						{												
							if(item.location.contains(file.getName()))
							{
								int index = item.IDdocument;
								int totalTabs = appView.centralPart.getTabCount();
								for(int i = 0; i < totalTabs; i++)
								{
									JScrollPane selectedTab = (JScrollPane) appView.centralPart.getComponentAt(i);
									JViewport viewport = selectedTab.getViewport();
									int canvasId = ((Canvas)viewport.getView()).IDcanvas;
									if(index == canvasId)
									{
										appView.centralPart.remove(appView.centralPart.getComponentAt(i));
										i--;
										totalTabs--;
									}
								}							
							}	
						}
					}
					file.delete();
					appView.projectExplorer.reloadTree();
					break;
				default:					
					break;
			}
		}
		
	}
	

}

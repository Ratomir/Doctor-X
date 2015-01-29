package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;
import view.Canvas;
import view.CentralPart;
import view.Menu;
import view.MenuItem;
import view.View;
import commands.Command;
import commands.CopyCommand;
import commands.CutCommand;
import commands.DeleteElementCommand;
import commands.PasteCommand;
import commands.ZoomInCommand;
import commands.ZoomOutCommand;

public class MenubarController
{
	public Application appModel = null;
	public View view = null;
	public CentralPart centralPart = null;
	public Menu menubar = null;	
	
	public HashMap<String, Command> commands = null;
	
	public MenubarController(Application model, View view, HashMap<String, Command> commands)
	{
		this.appModel = model;
		this.centralPart = view.centralPart;
		this.menubar = view.menuBar;
		this.commands = commands;
		this.view = view;
		
		for (MenuItem item : menubar.lm)
		{
			item.addActionListener(menulistener);
		}		
	}
	
	MenuListener menulistener = new MenuListener();
	
	public class MenuListener implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent e)
		{			
			String actionCommand = e.getActionCommand();
			
			switch (actionCommand) 
			{	
				//potrebno je dodati jos cirilicnu lokalizaciju
				case "Open": case "Otvori": case "\u041E\u0442\u0432\u043E\u0440\u0438":
					commands.get("open").execute(null);					
					break;
				case "Close": case "Zatvori": case "\u0417\u0430\u0442\u0432\u043E\u0440\u0438":
					int result = JOptionPane.showConfirmDialog(null, "Do you want to save document? ", "Save", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION)
					{
						commands.get("save").execute(null);
						commands.get("close").execute(null);
					}
					commands.get("close").execute(null);
					break;
				case "Save": case "Snimi":					
					commands.get("save").execute(null);
					break;
				case "Save As": case "Snimi kao":
					commands.get("saveAs").execute(null);
					break;
				case "Exit": case "Izlaz":	
					System.exit(0);
					break;
				case "Undo":
					if(!appModel.documents.get(selectedCanvas(view)).UndoStackEmpty())
					{
						Command undo = appModel.documents.get(selectedCanvas(view)).PopUndoCommand();
						undo.undo();
						appModel.documents.get(selectedCanvas(view)).PushRedoCommand(undo);
						centralPart.repaintAll();
					}	
					break;					
				case "Redo":
					if((!appModel.documents.get(selectedCanvas(view)).RedoStackEmpty()))
					{
						Command redo = appModel.documents.get(selectedCanvas(view)).PopRedoCommand();
						redo.redo();
						appModel.documents.get(selectedCanvas(view)).PushUndoCommand(redo);
						centralPart.repaintAll();
					}
					break;
				case "Cut": case "Isjeci":
					Command cut = new CutCommand(appModel, view);
					cut.execute(null);
					centralPart.repaintAll();
					break;
				case "Copy": case "Kopiraj":
					Command copy = new CopyCommand(appModel, view);
					copy.execute(null);
					break;
				case "Paste": case "Zaljepi":
					Command paste = new PasteCommand(appModel, view);
					paste.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(paste);
					centralPart.repaintAll();
					break;
				case "Delete": case "Pobri\u0161i":
					Command delte = new DeleteElementCommand(appModel, view);
					delte.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(delte);
					centralPart.repaintAll();
					break;
				case "Select All": case "Selektuj sve":
					AtomComponent.selectAll(appModel.getDocuments().get(view.centralPart.getSelectedIndex()).nodes);
					centralPart.repaintAll();
					break;
				case "Zoom In": case "Zumiraj":
					Command zoomin = new ZoomInCommand(appModel, view);
					zoomin.execute(null);
					centralPart.repaintAll();
					break;
				case "Zoom Out": case "Odzumiraj":
					Command zoomout = new ZoomOutCommand(appModel, view);
					zoomout.execute(null);
					centralPart.repaintAll();
					break;				
				case "Settings":
					break;
				case "Help": case "Pomo\u0107":
					File htmlFile = new File("files/FullObjectReport.html");
				try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					break;
				case "About": case "O nama":
					JOptionPane.showMessageDialog(null, "           DoctorX\nProjektovanje softvera, Januar 2015.\nProfesor: Branko Perisic\nVisi Asistent: Vladimir Vujovic\n"
							+ "Studenti:\n\tIlija Divljan   Miljan Skipina\n\tRatomir Vukadin   Nenad Plemic", "About us", JOptionPane.INFORMATION_MESSAGE);	
					
					break;
				case "New Document": case "Novi dokument":
					DocumentModel newDocument = new DocumentModel("New Document" + (Application.documents.size()+1), "");
					view.centralPart.addNewDocument(newDocument);
					break;
				case "New Project": case "Novi projekat":					
					String name = JOptionPane.showInputDialog("Project name:");
					try {
					if(!name.isEmpty())
					{
						File project = new File(Application.workspace + "/" + name);
						project.mkdir();
					}
					} catch (Exception e1) {}
					view.projectExplorer.reloadTree();
					break;	
				case "New Workspace": case "Novi workspace":					
					name = JOptionPane.showInputDialog("Workspace name:");
					try {
					if(!name.isEmpty())
					{
						File project = new File("C:/DoctorX/" + name);
						project.mkdir();
						Application.workspace = "C:/DoctorX/" + name;
					}
					} catch (Exception e1) {}
					view.projectExplorer.reloadTree();
					break;
				case "Switch Workspace": case "Promjeni workspace":
					commands.get("switch").execute(null);
					break;
				default:
					break;
			}
						
		}
		
	}
	
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}
	

}

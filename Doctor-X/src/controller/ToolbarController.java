package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import commands.Command;
import commands.ConnectCommand;
import commands.CopyCommand;
import commands.CutCommand;
import commands.DeleteElementCommand;
import commands.PaintCommand;
import commands.PasteCommand;
import commands.ResizeCommand;
import commands.RotateLeftCommand;
import commands.RotateRightCommand;
import commands.ZoomInCommand;
import commands.ZoomOutCommand;
import view.Canvas;
import view.CentralPart;
import view.Toolbar;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;
import model.components.EdgeKind;

/**
 * Klasa ToolbarController sadrzi akcije i osluskivace koji se koriste za rad sa toolbarom.
 * @author Ilija Divljan
 *
 */
public class ToolbarController 
{
	public Application appModel = null;
	public View view = null;
	public CentralPart centralPart = null;
	public Toolbar toolbar = null;
	
	public HashMap<String, Command> commands = null;
	
	public ToolbarController(Application model, View view, HashMap<String, Command> commands)
	{
		this.appModel = model;
		this.centralPart = view.centralPart;
		this.toolbar = view.toolbar;
		this.commands = commands;
		this.view = view;
		
		this.toolbar.setListeners(al);
		this.toolbar.setChangeListener(cl);
	}
	
	ActionListener al = new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand();
			
			switch (actionCommand) 
			{
				case "New":
					DocumentModel newDocument = new DocumentModel("New Document" + (Application.documents.size()+1), "");
					view.centralPart.addNewDocument(newDocument);
					break;
				case "Open":
					commands.get("open").execute(null);	
					break;
				case "Save":
					commands.get("save").execute(null);
					break;
				case "SaveAs":
					commands.get("saveAs").execute(null);
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
				case "Cut":
					Command cut = new CutCommand(appModel, view);
					cut.execute(null);
					centralPart.repaintAll();
					break;
				case "Copy":
					Command copy = new CopyCommand(appModel, view);
					copy.execute(null);
					break;
				case "Paste":
					Command paste = new PasteCommand(appModel, view);
					paste.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(paste);
					centralPart.repaintAll();
					break;
				case "ZoomIn":
					Command zoomin = new ZoomInCommand(appModel, view);
					zoomin.execute(null);
					centralPart.repaintAll();
					break;
				case "ZoomOut":
					Command zoomout = new ZoomOutCommand(appModel, view);
					zoomout.execute(null);
					centralPart.repaintAll();
					break;
				case "Delete":
					Command delte = new DeleteElementCommand(appModel, view);
					delte.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(delte);
					centralPart.repaintAll();
					break;
				case "SelectAll":
					AtomComponent.selectAll(appModel.getDocuments().get(view.centralPart.getSelectedIndex()).nodes);
					centralPart.repaintAll();
					break;
				case "Settings":
					break;
				case "Add":
					commands.get("add").execute(null);
					centralPart.repaintAll();
					break;
				case "Color":
					Command paint = new PaintCommand(appModel, view);
					paint.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(paint);
					centralPart.repaintAll();
					break;
				case "Connect":
					Command cmd = new ConnectCommand(appModel, view, EdgeKind.Single);
					cmd.execute(null);
					centralPart.repaintAll();
					break;
				case "RotateLeft":
					Command rotateL = new RotateLeftCommand(appModel, view);
					rotateL.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(rotateL);
					view.centralPart.repaintAll();
					break;
				case "RotateRight":
					Command rotateR = new RotateRightCommand(appModel, view);
					rotateR.execute(null);
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(rotateR);
					view.centralPart.repaintAll();
					break;
				default:
					break;
			}
			
		}
	};
	
	ChangeListener cl = new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			JSpinner s = (JSpinner) e.getSource();
			Command resize = new ResizeCommand(appModel, view);
			appModel.getDocuments().get(view.centralPart.getSelectedIndex()).radius = (Integer) s.getValue();
			resize.execute(null);
			appModel.documents.get(selectedCanvas(view)).PushUndoCommand(resize);
			centralPart.repaintAll();
		}
	};
	
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}
}

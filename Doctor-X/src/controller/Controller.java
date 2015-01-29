package controller;

import java.util.HashMap;

import commands.AddElementCommand;
import commands.CloseDocumentCommand;
import commands.Command;
import commands.CopyCommand;
import commands.CutCommand;
import commands.DeleteElementCommand;
import commands.PaintCommand;
import commands.OpenDocumentCommand;
import commands.PasteCommand;
import commands.SaveAsDocumentCommand;
import commands.SaveDocumentCommand;
import commands.SwitchWorkspaceCommand;
import commands.ZoomInCommand;
import commands.ZoomOutCommand;
import view.View;
import model.Application;

/**
 * Klasa koja predstavlja glavni kontroler koji inicijalizuje sve ostale.
 * 
 * @author Ilija Divljan
 *
 */
public class Controller 
{
	/*
	 * Deklarisanje kontrolera.
	 */
	private CanvasController canvasController = null;
	private ProjectExplorerController projectExplorerController = null;
	private ToolbarController toolbarController = null;
	private MenubarController menubarController = null;
	
	private ToolboxController toolboxController = null;
	
	public Controller(Application model, View view)
	{
		HashMap<String, Command> commands = new HashMap<String, Command>();
		
		/*
		 * Inicijalizacija komandi
		 */
		//Command addElementCmd = new AddElementCommand(model, view);
		//Command deleteElement = new DeleteElementCommand(model, view);
		//Command paintCommand = new PaintCommand(model, view);
		Command openDocument = new OpenDocumentCommand(model, view);
		Command closeDocument = new CloseDocumentCommand(model, view);
		Command saveDocument = new SaveDocumentCommand(model, view);
		Command saveAsDocument = new SaveAsDocumentCommand(model, view);
		Command copyCommand = new CopyCommand(model, view);
		//Command pasteCommand = new PasteCommand(model, view);
		Command cutCommand = new CutCommand(model, view);
		Command zoominCommand = new ZoomInCommand(model, view);
		Command zoomoutCommand = new ZoomOutCommand(model, view);
		Command switchCommand = new SwitchWorkspaceCommand(model, view);
		
		//commands.put("add", addElementCmd);
		//commands.put("delete", deleteElement);
		//commands.put("paint", paintCommand);
		commands.put("open", openDocument);
		commands.put("close", closeDocument);
		commands.put("save", saveDocument);
		commands.put("saveAs", saveAsDocument);
		commands.put("copy", copyCommand);
		//commands.put("paste", pasteCommand);
		commands.put("cut", cutCommand);
		commands.put("zoomIn", zoominCommand);
		commands.put("zoomOut", zoomoutCommand);
		commands.put("switch", switchCommand);
		
		/*
		 * Inicijalizacija kontrolera.
		 */
		canvasController = new CanvasController(model, view, commands);
		projectExplorerController = new ProjectExplorerController(model, view, commands);
		toolbarController = new ToolbarController(model, view, commands);
		menubarController = new MenubarController(model, view, commands);
		toolboxController = new ToolboxController(model, view);
	}
}

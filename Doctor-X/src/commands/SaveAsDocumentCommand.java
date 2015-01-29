package commands;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;
import parser.XMLwriter;
import view.Canvas;
import view.View;

public class SaveAsDocumentCommand implements Command
{
	private Application model;
	private View view;
	
	public SaveAsDocumentCommand(Application model, View view)
	{
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute(AtomComponent element)
	{
		if(view.centralPart.getSelectedIndex() != -1)
		{
			//ovde vrsimo odredjivanje koji je canvas selektovan
			JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
			JViewport viewport = selectedTab.getViewport();
			int canvasId = ((Canvas)viewport.getView()).IDcanvas;
			XMLwriter writer = new XMLwriter();
			
			JFileChooser fileCh = new JFileChooser(Application.workspace);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "xml");
			fileCh.setFileFilter(filter);
			int option = fileCh.showSaveDialog(null);
			if(option == JFileChooser.APPROVE_OPTION)
			{
				DocumentModel newDoc = new DocumentModel("", "");
				newDoc.nodes = Application.documents.get(canvasId).nodes;
				newDoc.edges = Application.documents.get(canvasId).edges;				
				newDoc.location = (fileCh.getSelectedFile()).toString() + ".xml";
				newDoc.name = new File((fileCh.getSelectedFile()).toString()).getName();			
				writer.writeDocumentParametars(newDoc);
								
				view.projectExplorer.reloadTree();
				//brisemo i otvaramo novi
				view.centralPart.remove(view.centralPart.getSelectedIndex());					
				(model.documents.get(canvasId)).name = "none";
				(model.documents.get(canvasId)).nodes = null;
				view.centralPart.addNewDocument(newDoc);
			}
		}
	}		
	
	@Override
	public void undo()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void redo()
	{
		// TODO Auto-generated method stub

	}

}

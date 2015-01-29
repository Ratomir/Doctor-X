package commands;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import parser.XMLreader;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

public class OpenDocumentCommand implements Command
{
	private Application model;
	private View view;
	
	public OpenDocumentCommand(Application model, View view)
	{
		this.model = model;
		this.view = view;
	}
	
		
	@Override
	public void execute(AtomComponent element)
	{
		JFileChooser fileCh = new JFileChooser(Application.workspace);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "xml");
		fileCh.setFileFilter(filter);
		int option = fileCh.showOpenDialog(null);
		if(option == JFileChooser.APPROVE_OPTION)
		{
			XMLreader reader = new XMLreader();
			DocumentModel doc = reader.ReadDocument(fileCh.getSelectedFile().getAbsolutePath());
           	view.centralPart.addNewDocument(doc);			
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

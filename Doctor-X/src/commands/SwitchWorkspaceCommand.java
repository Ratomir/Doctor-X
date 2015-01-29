package commands;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import parser.XMLreader;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

public class SwitchWorkspaceCommand implements Command
{
	private Application model;
	private View view;
	
	public SwitchWorkspaceCommand(Application model, View view)
	{
		this.model = model;
		this.view = view;
	}
	
		
	@Override
	public void execute(AtomComponent element)
	{
		JFileChooser fileCh = new JFileChooser("C:/DoctorX/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "xml");
		fileCh.setFileFilter(filter);
		fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = fileCh.showOpenDialog(null);
		fileCh.setAcceptAllFileFilterUsed(false);
		if(option == JFileChooser.APPROVE_OPTION)
		{			
			Application.workspace = fileCh.getSelectedFile().toString();
			view.projectExplorer.reloadTree();			
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

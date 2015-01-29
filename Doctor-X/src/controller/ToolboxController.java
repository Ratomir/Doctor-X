package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.UIManager;

import model.Application;
import view.Canvas;
import view.StateEditor;
import view.View;
import view.ptable.Table;

public class ToolboxController extends MouseAdapter implements ActionListener
{
	private Application modelApplication = null;
	private View modelView = null;
	
	private JButton lastButton = new JButton();
	
	public ToolboxController(Application model, View view)
	{
		this.modelApplication = model;
		this.modelView = view;
		
		ArrayList<JButton> listAtoms = this.modelView.toolbox.getListButtons();
		
		for (JButton jButton : listAtoms)
		{
			jButton.addActionListener(this);
			jButton.addMouseListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "pointer":
			modelApplication.state = StateEditor.Normal;
			modelView.statusBar.setStateEditor(StateEditor.Select);
			break;
		case "grabber":
			modelApplication.state = StateEditor.Select;
			modelView.statusBar.setStateEditor(StateEditor.Select);
			break;
		case "zoomIn":
			modelApplication.state = StateEditor.ZoomIn;
			break;
		case "zoomOut":
			modelApplication.state = StateEditor.ZoomOut;
			break;
			
		case "metalloids":
		case "nonmetal":
		case "alkaliearth":
		case "alkalimetals":
		case "halogens":
		case "noblegases":
		{
			Table periodicTable = new Table(e.getActionCommand(), this.modelView, this.modelApplication);
			break;
		}
		default:
			break;
		}
		
		JButton tempButton = (JButton)e.getSource();
		
		this.nullBorder(lastButton);
		
		this.lastButton = tempButton;
		
		this.setButtonBorder(tempButton);
		
		this.modelView.toolbox.setIndexOfElement(e.getActionCommand());
		
		if(selectedCanvas(this.modelView) != -1)
		{
			this.modelApplication.documents.get(selectedCanvas(this.modelView)).atom = null;
		}
		
	}
	
	//metoda sluzi za odredjivanje id selektovanog canvasa
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();
		if(selectedTab != null)
		{
			JViewport viewport = selectedTab.getViewport();
			return ((Canvas)viewport.getView()).IDcanvas; 
		}
		
		return -1;
	}
	
	private void nullBorder(JButton button)
	{
		button.setBorder(null);
		button.setBorderPainted(false);
	}
	
	private void setButtonBorder(JButton button)
	{
		button.setBorder(UIManager.getBorder("Button.border"));
		button.setBorderPainted(true);
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.modelView.setCursor(Cursor.HAND_CURSOR);
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		this.modelView.setCursor(Cursor.DEFAULT_CURSOR);
	}

}

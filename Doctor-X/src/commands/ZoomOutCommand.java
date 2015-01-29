package commands;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.Canvas;
import view.View;
import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;

public class ZoomOutCommand implements Command
{
	public Application model = null;	
	public List<DocumentModel> listModels = new ArrayList<DocumentModel>();
	public View view = null;
	
	public ZoomOutCommand(Application application, View view)
	{
		this.model = application;		
		this.listModels = application.getDocuments();
		this.view = view;
	}

	@Override
	public void execute(AtomComponent element)
	{
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		int canvasId = ((Canvas)viewport.getView()).IDcanvas;
		
		if(model.getDocuments().get(canvasId).zoomFactor > 0.5)
		{
			model.getDocuments().get(canvasId).zoomFactor -= 0.2;
			Double zoomfactor = model.getDocuments().get(canvasId).zoomFactor;				
			zoomfactor = (double)((Math.round(zoomfactor*100)));			
			zoomfactor = (double)zoomfactor/100;
			if (zoomfactor == 1)	{
				view.statusBar.zoomSlider.setValue(0);
			} else if (zoomfactor == 1.2) {
				view.statusBar.zoomSlider.setValue(1);
			} else if (zoomfactor == 1.4) {
				view.statusBar.zoomSlider.setValue(2);
			} else if (zoomfactor == 1.6) {
				view.statusBar.zoomSlider.setValue(3);
			} else if (zoomfactor == 0.8) {
				view.statusBar.zoomSlider.setValue(-1);
			} else if (zoomfactor == 0.6) {
				view.statusBar.zoomSlider.setValue(-2);
			} else if (zoomfactor == 0.4) {
				view.statusBar.zoomSlider.setValue(-3);		
			}
			
			/*view.centralPart.canvasList.get(canvasId).setPreferredSize(new Dimension(
				(int)Math.round(view.centralPart.canvasList.get(canvasId).getPreferredSize().height*model.getDocuments().get(canvasId).zoomFactor),
				(int)Math.round(view.centralPart.canvasList.get(canvasId).getPreferredSize().width*model.getDocuments().get(canvasId).zoomFactor)));*/
			
									
						
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

}

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Application;
import localization.Localization;


public class StatusBar extends JPanel implements ChangeListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel state = null;
	private JLabel cursorState = null;
	private JLabel info = null;
	private StateEditor stateEditor = null;
	
	private Localization localization = null;
	public JSlider zoomSlider = null;
	private Application app = null;
	private View view = null;
	
	public StatusBar(Application app, View view)
	{
		this.localization = Localization.getInstance();
		this.app = app;
		this.view = view;
		this.setLayout(new GridLayout(1, 3, 30, 0));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		stateEditor = StateEditor.Normal;
		state = new JLabel(this.localization.getString("state.normal"));
		this.localization.registerComponent("state.normal", state);
		state.setFont(state.getFont().deriveFont(15f));
		
		this.add(state);
		
		cursorState = new JLabel(this.localization.getString("cursor"));
		this.localization.registerComponent("cursor", cursorState);
		cursorState.setFont(cursorState.getFont().deriveFont(15f));
		this.add(cursorState);
		
		//zoom slider
		zoomSlider = new JSlider(-3, 3, 0);		
		zoomSlider.setMinorTickSpacing(1);
		zoomSlider.setMajorTickSpacing(1);
		zoomSlider.setPaintLabels(true);
		zoomSlider.setPaintTicks(true);
		zoomSlider.setSnapToTicks(true);
		Border b = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY.brighter(), 1),"Zoom slider");
		zoomSlider.setBorder(b);
		zoomSlider.addChangeListener(this);
		zoomSlider.setEnabled(true);
		zoomSlider.setPreferredSize(new Dimension(50,60));
		
		this.add(zoomSlider);
//		info = new JLabel(this.localization.getString("cursor"));
//		this.localization.registerComponent("cursor", cursorState);
//		cursorState.setFont(cursorState.getFont().deriveFont(15f));
//		this.add(cursorState);
	}

	/**
	 * @return the state
	 */
	public JLabel getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(JLabel state)
	{
		this.state = state;
	}

	/**
	 * @return the cursorState
	 */
	public JLabel getCursorState()
	{
		return cursorState;
	}

	/**
	 * @param cursorState the cursorState to set
	 */
	public void setCursorState(String cursorState)
	{
		this.cursorState.setText(this.localization.getString("cursor") + " " + cursorState);
	}

	/**
	 * @return the stateEditor
	 */
	public StateEditor getStateEditor()
	{
		return stateEditor;
	}

	/**
	 * @param stateEditor the stateEditor to set
	 */
	public void setStateEditor(StateEditor stateEditor)
	{
		this.stateEditor = stateEditor;
		
		if(stateEditor == StateEditor.Select)
		{
			this.state.setText(this.localization.getString("state.select"));
		}
		else if(stateEditor == StateEditor.Edit)
		{
			this.state.setText(this.localization.getString("state.edit"));
		}
		else if(stateEditor == StateEditor.ZoomIn)
		{
			this.state.setText(this.localization.getString("state.zoomin"));
		}
		else if(stateEditor == StateEditor.ZoomOut)
		{
			this.state.setText(this.localization.getString("state.out"));
		}
		else if(stateEditor == StateEditor.Normal)
		{
			this.state.setText(this.localization.getString("state.normal"));
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		JSlider slider = (JSlider) e.getSource();
				
		if (view.centralPart.getTabCount() > 0)
		{
			//odredjivanje selektovanog taba
			JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();
			JViewport viewport = selectedTab.getViewport();
			int canvasId = ((Canvas) viewport.getView()).IDcanvas;
			double zoomfactor = app.getDocuments().get(canvasId).zoomFactor;			
			if (slider.getValue() == -3)	{
				zoomfactor = 0.4;
			} else if (slider.getValue() == -2) {
				zoomfactor = 0.6;
			} else if (slider.getValue() == -1) {
				zoomfactor = 0.8;
			} else if (slider.getValue() == 0) {
				zoomfactor = 1;
			} else if (slider.getValue() == 1) {
				zoomfactor = 1.2;
			} else if (slider.getValue() == 2) {
				zoomfactor = 1.4;
			} else if (slider.getValue() == 3) {
				zoomfactor = 1.6;			
			}
			app.getDocuments().get(canvasId).zoomFactor = zoomfactor;
			view.centralPart.repaintAll();
		}
		
	}

}

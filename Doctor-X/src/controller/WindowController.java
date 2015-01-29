package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import model.Application;
import parser.XMLwriter;

public class WindowController implements WindowListener
{
	private JFrame application = null;

	public WindowController(JFrame frame)
	{
		this.setApplication(frame);
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		XMLwriter xml = new XMLwriter();
		//ovde je potrebno proslijediti vrijednost default workspace ako se promjeni
		//to kasnije u kodu kada kreiramo potrebne klase
		xml.writeWindowParametars(this.application, Application.workspace);		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
	}

	/**
	 * @return the application
	 */
	public JFrame getApplication()
	{
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(JFrame application)
	{
		this.application = application;
	}

}

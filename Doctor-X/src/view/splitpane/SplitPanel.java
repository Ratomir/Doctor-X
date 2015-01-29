package view.splitpane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SplitPanel extends JPanel
{

	private TitlePanel titlePanel = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SplitPanel(String title, ImageIcon icon)
	{
		super(new BorderLayout());
		
		titlePanel = new TitlePanel(title, icon);
		
		this.add(titlePanel, BorderLayout.NORTH);
	}
	
	public void addComponent(Component component)
	{
		this.add(component, BorderLayout.CENTER);
	}
	
	public JPanel getSplitPanel()
	{
		return this;
	}

	/**
	 * @return the titlePanel
	 */
	public TitlePanel getTitlePanel()
	{
		return titlePanel;
	}

	/**
	 * @param titlePanel the titlePanel to set
	 */
	public void setTitlePanel(TitlePanel titlePanel)
	{
		this.titlePanel = titlePanel;
	}

}

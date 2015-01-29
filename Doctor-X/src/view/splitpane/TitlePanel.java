package view.splitpane;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TitlePanel(String title, ImageIcon icon)
	{
		super(new FlowLayout(FlowLayout.LEFT));
		
		this.add(new JLabel(icon));
		
		this.add(new JLabel(title));
	}
	
	public JPanel getTitlePanel()
	{
		return this;
	}

}

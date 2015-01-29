package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.UIManager;

import localization.Localization;
import model.Application;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
/**
 * 
 * Klasa {@link ItemController} hvata sve dogadjaje koji se dese za bilo koji check box 
 * koji se klikne na meniju.
 * Potrebno je dodati uslove u funkciji {@link ItemController#itemStateChanged(ItemEvent)}
 * 
 * @author Grupa 1
 *
 */
public class ItemController implements ItemListener
{

	private Localization localization = Localization.getInstance();
	
	/**
	 * Kostruktor je ostavljen radi neki buducih stavki koje ce se mozda trebati inicijalizovati.
	 */
	public ItemController()
	{
	}
	
	@Override
	public void itemStateChanged(ItemEvent source)
	{
		AbstractButton ab = (AbstractButton) source.getSource();
		if (source.getStateChange() == ItemEvent.SELECTED)
		{
			if (ab.getActionCommand().equals("en_US"))
			{
				localization.setLocal("en", "US");
				localization.updateAll();
			}
			else if (ab.getActionCommand().equals("sr_RS"))
			{
				localization.setLocal("sr", "RS");
				localization.updateAll();
			}
			else if (ab.getActionCommand().equals("sr_BA"))
			{
				localization.setLocal("sr", "BA");
				localization.updateAll();
			}
			else if(ab.getActionCommand().contains("theme"))
			{
				String theme = ab.getActionCommand();
				try 
				{
					if(theme.equals("theme1"))
					{
						UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
						Application.theme = 1; 
					}
					else if(theme.equals("theme2"))
					{
						UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
						Application.theme = 2; 
					}
					else if(theme.equals("theme3"))
					{
						UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
						Application.theme = 3; 
					}
					else if(theme.equals("theme4"))
					{
						UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
						Application.theme = 4; 
					}
					else if(theme.equals("theme5"))
					{
						UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
						Application.theme = 5; 
					}
					else if(theme.equals("theme6"))
					{
						UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
						Application.theme = 6; 
					}
					else if(theme.equals("theme7"))
					{
						UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
						Application.theme = 7; 
					}

				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			
		}
	}

}

package view.ptable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.components.AtomType;

public class Atom extends JPanel
{

	/**
	 * Ratomir
	 */
	private static final long serialVersionUID = 1L;
	
	private int ordinalNumber;
	private String sign = null;
	private String name;
	private float weight;
	
	private AtomType type;
	
	private int [] numberOfRings;
	
	private Boolean selected = false;

	public Atom(int number, String sign, String name, int [] numberOfRings, float weight, AtomType type)
	{
		this.ordinalNumber = number;
		this.sign = sign;
		this.name = name;
		this.numberOfRings = numberOfRings;
		this.weight = weight;
		
		this.type = type;
		
		this.setSize(80, 80);
		this.setPreferredSize(new Dimension(80, 80));
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		Map<Key, Object> hints = new HashMap<>();
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //VALUE_ANTIALIAS_ON | VALUE_ANTIALIAS_OFF | VALUE_ANTIALIAS_DEFAULT
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); //VALUE_ALPHA_INTERPOLATION_QUALITY | VALUE_ALPHA_INTERPOLATION_SPEED | VALUE_ALPHA_INTERPOLATION_DEFAULT
		hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY); //VALUE_COLOR_RENDER_QUALITY | VALUE_COLOR_RENDER_SPEED | VALUE_COLOR_RENDER_DEFAULT
		hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE); //VALUE_DITHER_DISABLE | VALUE_DITHER_ENABLE | VALUE_DITHER_DEFAULT
		hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON); //VALUE_FRACTIONALMETRICS_ON | VALUE_FRACTIONALMETRICS_OFF | VALUE_FRACTIONALMETRICS_DEFAULT
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR); //VALUE_INTERPOLATION_BICUBIC | VALUE_INTERPOLATION_BILINEAR | VALUE_INTERPOLATION_NEAREST_NEIGHBOR
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); //VALUE_RENDER_QUALITY | VALUE_RENDER_SPEED | VALUE_RENDER_DEFAULT
		hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); //VALUE_STROKE_NORMALIZE | VALUE_STROKE_DEFAULT | VALUE_STROKE_PURE
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP); //VALUE_TEXT_ANTIALIAS_ON | VALUE_TEXT_ANTIALIAS_OFF | VALUE_TEXT_ANTIALIAS_DEFAULT | VALUE_TEXT_ANTIALIAS_GASP | VALUE_TEXT_ANTIALIAS_LCD_HRGB | VALUE_TEXT_ANTIALIAS_LCD_HBGR | VALUE_TEXT_ANTIALIAS_LCD_VRGB | VALUE_TEXT_ANTIALIAS_LCD_VBGR

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHints(hints);
		
		RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(1,1,78,78, 10, 10);
		if(this.selected)
		{
			g2d.setColor(Color.WHITE);
		}
		g2d.setStroke(new BasicStroke(2f));
		
		RoundRectangle2D.Float rect = new RoundRectangle2D.Float(2,2,76,76,10,10);
		
		g2d.draw(roundRect);
		
		AtomType type = this.getType();
		
		if(type == AtomType.Nonmetals)
		{
			g2d.setColor(Color.decode("#22FF22"));
		}
		else if(type == AtomType.Metalloids)
		{
			g2d.setColor(Color.decode("#77DD88"));
		}
		else if(type == AtomType.AlkaliEarth)
		{
			g2d.setColor(Color.decode("#FFFF44"));
		}
		else if(type == AtomType.AlkalMetals)
		{
			g2d.setColor(Color.decode("#FFCC33"));
		}
		else if(type == AtomType.Halogens)
		{
			g2d.setColor(Color.decode("#22EECC"));
		}
		else if(type == AtomType.NobleGases)
		{
			g2d.setColor(Color.decode("#77CCFF"));
		}
		
		g2d.draw(rect);
		g2d.fill(rect);
		
		if(this.selected)
		{
			g2d.setColor(Color.WHITE);
		}
		else
		{
			g2d.setColor(Color.BLACK);
		}
		
		g2d.setFont(getFont().deriveFont(20f));
		FontMetrics fm = g2d.getFontMetrics();
		g2d.drawString(this.getOrdinalNumber() + "", 40 - fm.stringWidth(this.getOrdinalNumber() + "")/2, 20);
		
		g2d.setFont(getFont().deriveFont(Font.BOLD, 20f));
		fm = g2d.getFontMetrics();
		g2d.drawString(this.getSign().toString(), 40 - fm.stringWidth(this.getSign())/2, 45);
		
		g2d.setFont(getFont().deriveFont(Font.PLAIN, 15f));
		fm = g2d.getFontMetrics();
		g2d.drawString(this.getName(), 40 - fm.stringWidth(this.getName())/2, 60);
	}
	
	public String atomsPerRingToString()
	{
		if(numberOfRings.length > 0)
		{
			String temp = "";
		
			for (int i = 0; i < numberOfRings.length -1; i++)
			{
				temp += numberOfRings[i] + ", ";
			}
		
			temp += numberOfRings[numberOfRings.length-1] + "";
			
			return temp;
		}
		
		return "";
	}
	
	/**
	 * @return the ordinalNumber
	 */
	public int getOrdinalNumber()
	{
		return ordinalNumber;
	}

	/**
	 * @param ordinalNumber the ordinalNumber to set
	 */
	public void setOrdinalNumber(int ordinalNumber)
	{
		this.ordinalNumber = ordinalNumber;
	}

	/**
	 * @return the sign
	 */
	public String getSign()
	{
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign)
	{
		this.sign = sign;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the numberOfRings
	 */
	public int[] getNumberOfRings()
	{
		return numberOfRings;
	}

	/**
	 * @param numberOfRings the numberOfRings to set
	 */
	public void setNumberOfRings(int[] numberOfRings)
	{
		this.numberOfRings = numberOfRings;
	}

	/**
	 * @return the type
	 */
	public AtomType getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AtomType type)
	{
		this.type = type;
	}

	/**
	 * @return the weight
	 */
	public float getWeight()
	{
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight)
	{
		this.weight = weight;
	}

	/**
	 * @return the selected
	 */
	public Boolean getSelected()
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Boolean selected)
	{
		this.selected = selected;
	}

}

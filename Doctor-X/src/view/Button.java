package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.components.AtomType;

public class Button extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean selected = false;
	private AtomType buttonType = null;
	public static int SIZE = 60;
	
	
	public Button(AtomType type)
	{
		this.buttonType = type;
		
		this.setSize(SIZE, SIZE);
		this.setPreferredSize(new Dimension(SIZE, SIZE));
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
		
		RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(1,1,SIZE-2,SIZE-2, 10, 10);
		if(this.selected)
		{
			g2d.setColor(Color.WHITE);
		}
		g2d.setStroke(new BasicStroke(2f));
		
		RoundRectangle2D.Float rect = new RoundRectangle2D.Float(2,2,SIZE-4,SIZE-4,10,10);
		
		g2d.draw(roundRect);
		
		AtomType type = this.getButtonType();
		
		g2d.setFont(getFont().deriveFont(20f));
		FontMetrics fm = g2d.getFontMetrics();
		JLabel labela1 = new JLabel("");
		
		if(type == AtomType.Nonmetals)
		{
			g2d.setColor(Color.decode("#22FF22"));
			
			g2d.draw(rect);
			g2d.fill(rect);
			
			g2d.setColor(Color.BLACK);
			
			labela1.setText("NM");
			this.setToolTipText("Nonmetals");
			g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
			g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
		}
		else if(type == AtomType.Metalloids)
		{
			g2d.setColor(Color.decode("#77DD88"));
			
			g2d.draw(rect);
			g2d.fill(rect);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
			labela1.setText("M");
			this.setToolTipText("Nonmetals");
			g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
		}
		else if(type == AtomType.AlkaliEarth)
		{
			g2d.setColor(Color.decode("#FFFF44"));
			
			g2d.draw(rect);
			g2d.fill(rect);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
			labela1.setText("AE");
			this.setToolTipText("Alkal earth");
			g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
		}
		else if(type == AtomType.AlkalMetals)
		{
			g2d.setColor(Color.decode("#FFCC33"));
			
			g2d.draw(rect);
			g2d.fill(rect);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
			labela1.setText("AM");
			this.setToolTipText("Alkal metals");
			g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
		}
		else if(type == AtomType.Halogens)
		{
			g2d.setColor(Color.decode("#22EECC"));
			
			g2d.draw(rect);
			g2d.fill(rect);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
			labela1.setText("H");
			this.setToolTipText("Halogens");
			g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
		}
		else if(type == AtomType.NobleGases)
		{
			g2d.setColor(Color.decode("#77CCFF"));
			
			g2d.draw(rect);
			g2d.fill(rect);
			
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
			this.setToolTipText("Noble gases");
			labela1.setText("NG");
			g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
		}
	}

	/**
	 * @return the buttonType
	 */
	public AtomType getButtonType()
	{
		return buttonType;
	}

	/**
	 * @param buttonType the buttonType to set
	 */
	public void setButtonType(AtomType buttonType)
	{
		this.buttonType = buttonType;
	}

}

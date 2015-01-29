package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;
import model.components.Edge;

/**
 * Canvas view klasa.
 * @author Ilija Divljan
 *
 */

public class Canvas extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Application application = null;	
	public int IDcanvas;
	
	public Canvas(Application application) 
	{
		this.application = application;
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setBackground(Color.lightGray);
		
		
		this.setOpaque(true);
		IDcanvas = Application.IDcounter;		
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
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHints(hints);
		g2.scale(application.documents.get(IDcanvas).zoomFactor, application.documents.get(IDcanvas).zoomFactor);		
		
		for(Edge e: application.documents.get(IDcanvas).edges)
		{
			e.draw(g2);
		}
		for(AtomComponent n : application.documents.get(IDcanvas).nodes)
		{
			n.draw(g2);
		}
		if(application.documents.get(IDcanvas).selecting)
		{
			g2.setColor(Color.darkGray);
			g2.drawRect(application.documents.get(IDcanvas).mouseRect.x, application.documents.get(IDcanvas).mouseRect.y, application.documents.get(IDcanvas).mouseRect.width, application.documents.get(IDcanvas).mouseRect.height);
		}
		
	}
}

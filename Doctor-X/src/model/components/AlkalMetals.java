package model.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class AlkalMetals extends AtomComponent
{

	public AlkalMetals(Point p, int r, Color color, int number, String sign, String name, int [] numberOfRings, float weight, AtomType type)
	{
		super(number, sign, name, numberOfRings, weight, type);
		this.p = p;
		this.r = r;
		this.color = color;
		this.kind = Kind.Square;
		
		this.sign = sign;
		this.typeAtom = type;
		
		//metoda setBoundary iscrtava pravougaonik oko komponente kada je ta komp. selektovana.
		setBoundary(b);
	}

	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform currentAt = g2.getTransform();
		int xpoly[] = {b.x+b.width/2, b.x + b.width + b.width/2, b.x-b.width/2};
		int ypoly[] = {b.y, b.y+b.height, b.y+b.height};
		
		g2.rotate(Math.toRadians(rotateAngle), b.x + r, b.y + r);
		
		Polygon pentagon = new Polygon(xpoly, ypoly, 3);
		g2.setColor(color);
		g2.fill(pentagon);
		g2.draw(pentagon);
		
		
		
		
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(20f));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(this.getSign(), b.x + b.width/2 - fm.stringWidth(this.getSign())/2, b.y + 3*b.height/4 + fm.getDescent());
		if(selected)
		{
			g.setColor(Color.darkGray);
			g.drawRoundRect(b.x-b.width/2-1, b.y-1, b.width+b.width/2 + b.width/2+2, b.height+2, 5, 5);
		}
		
		g2.setTransform(currentAt);
	}
	
	public float getLabelHeight(Graphics2D g2, String label) {
	    FontRenderContext frc = g2.getFontRenderContext();
	    return g2.getFont().getLineMetrics(label, frc).getHeight();
	}

}

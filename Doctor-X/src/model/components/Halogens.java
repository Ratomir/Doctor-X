package model.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Halogens extends AtomComponent
{

	public Halogens(Point p, int r, Color color, int number, String sign, String name, int[] numberOfRings, float weight, AtomType type)
	{
		super(number, sign, name, numberOfRings, weight, type);
		this.p = p;
		this.r = r;
		this.color = color;
		this.kind = Kind.Circular;
		
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
		
		int xpoly[] = {b.x, b.x+r,          b.x+2*r, b.x + r + r/2,  b.x + r/2};
		int ypoly[] = {b.y, b.y-b.height/2, b.y,     b.y+b.height/2, b.y+b.height/2};
		
		g2.rotate(Math.toRadians(rotateAngle), b.x + b.width/2, b.y + b.height/8);
		
		
		Polygon pentagon = new Polygon(xpoly, ypoly, 5);
		
		g2.setColor(this.color);
		g2.fill(pentagon);
		g2.draw(pentagon);
		
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(20f));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(this.getSign(), b.x+ r - fm.stringWidth(this.getSign())/2, b.y + r/4 + fm.getDescent());
		
		if(selected) 
		{
			g.setColor(Color.darkGray);
			g.drawRoundRect(b.x-1, b.y-b.height/2-1, 2*r+1, b.height+1, 5, 5);
		}
		g2.setTransform(currentAt);
	}

}

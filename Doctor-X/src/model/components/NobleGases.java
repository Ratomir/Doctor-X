package model.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class NobleGases extends AtomComponent
{

	public NobleGases(Point p, int r, Color color, int number, String sign, String name, int[] numberOfRings, float weight, AtomType type)
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
		
		int xpoly[] = {b.x, b.x,                        b.x+b.width/2,            b.x+b.width,                b.x+b.width,  b.x+b.width/2};
		int ypoly[] = {b.y, b.width + b.y + b.height/2, b.width + b.y + b.height, b.width + b.y + b.height/2, b.y,          b.y-b.height/2};
		
		
		g2.rotate(Math.toRadians(rotateAngle), b.x + r, b.y + r);
		Polygon pentagon = new Polygon(xpoly, ypoly, 6);
		
		g2.setColor(this.color);
		g2.fill(pentagon);
		g2.draw(pentagon);
		
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(20f));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(this.getSign(), b.x + b.width/2 - fm.stringWidth(this.getSign())/2, b.y + b.height/2 + fm.getDescent());
		
		if(selected)
		{
			g.setColor(Color.darkGray);
			g.drawRoundRect(b.x, b.y-b.height/2, b.width, 2*b.height + b.height/2, 5, 5);
		}
		
		g2.setTransform(currentAt);
	}

}

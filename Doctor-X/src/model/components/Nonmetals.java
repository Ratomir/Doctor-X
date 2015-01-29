package model.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Klasa Nonmetals, naslijedjuje klasu {@ AtomComponent} i implementira metodu draw(Graphics g)
 * 
 * @author Ilija Divljan
 *
 */
public class Nonmetals extends AtomComponent {

	public Nonmetals(Point p, int r, Color color, int number, String sign, String name, int [] numberOfRings, float weight, AtomType type) 
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
		
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform currentAt = g2.getTransform();
		
		
		g2.rotate(Math.toRadians(rotateAngle), b.x + r, b.y + r);
		
		g2.setColor(this.color);
		g2.fillOval(b.x, b.y, b.width, b.height);
		
		g2.setColor(Color.black);
		g2.setFont(g.getFont().deriveFont(20f));
		FontMetrics fm = g.getFontMetrics();
		g2.drawString(this.getSign(), b.x + b.width/2 - fm.stringWidth(this.getSign())/2, b.y+b.height/2 + 10);
		
		if(selected)
		{
			g.setColor(Color.darkGray);
			g.drawRoundRect(b.x-1, b.y-1, b.width+2, b.height+2, 5 ,5);
		}
		g2.setTransform(currentAt);
	}

	/**
	 * @return the p
	 */
	public Point getP()
	{
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Point p)
	{
		this.p = p;
	}

}

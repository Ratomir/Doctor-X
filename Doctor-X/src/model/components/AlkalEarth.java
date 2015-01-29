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
public class AlkalEarth extends AtomComponent {

	public AlkalEarth(Point p, int r, Color color, int number, String sign, String name, int [] numberOfRings, float weight, AtomType type) 
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
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		AffineTransform currentAt = g2.getTransform();
		
		g2.rotate(Math.toRadians(rotateAngle), b.x + r, b.y + r);
		
		g2.setColor(this.color);
		g2.fillRect(b.x, b.y, b.width, b.height);
	
		g2.setColor(Color.BLACK);
		g2.setFont(g.getFont().deriveFont(20f));
		FontMetrics fm = g.getFontMetrics();
		g2.drawString(this.getSign(), b.x + b.width/2 - fm.stringWidth(this.getSign())/2, b.y + b.height/2 +10);
		
		
		
		if(selected)
		{
			g2.setColor(Color.darkGray);
			g2.drawRoundRect(b.x, b.y, b.width, b.height,5 ,5 );
		}
		g2.setTransform(currentAt);
	}

}

package model.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



/**
 * Klasa Edge se koristi sa iscrtavanje veza izmedju elemenata.
 * 
 * @author Ilija Divljan
 *
 */
public class Edge 
{
	public AtomComponent n1;
	public AtomComponent n2;
	public EdgeKind kind = EdgeKind.Single;
	
	public Edge(AtomComponent n1, AtomComponent n2, EdgeKind kind)
	{
		this.n1 = n1;
		this.n2 = n2;
		this.kind = kind;
	}
	public void draw(Graphics g)
	{
		Point p1 = n1.getLocation();
		Point p2 = n2.getLocation();
		
		if(kind == EdgeKind.Single)
		{
			g.setColor(Color.darkGray);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
		else if(kind == EdgeKind.Double)
		{
			g.setColor(Color.darkGray);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			
			Point p3 = new Point(n1.getLocation().x + 4, n1.getLocation().y + 4);
			Point p4 = new Point(n2.getLocation().x + 4, n2.getLocation().y + 4);
			
			g.drawLine(p3.x, p3.y, p4.x, p4.y);
		}
		else if(kind == EdgeKind.Triple)
		{
			g.setColor(Color.darkGray);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			
			Point p3 = new Point(n1.getLocation().x + 4, n1.getLocation().y + 4);
			Point p4 = new Point(n2.getLocation().x + 4, n2.getLocation().y + 4);
			
			g.drawLine(p3.x, p3.y, p4.x, p4.y);
			
			Point p5 = new Point(n1.getLocation().x - 4, n1.getLocation().y - 4);
			Point p6 = new Point(n2.getLocation().x - 4, n2.getLocation().y - 4);
			
			g.drawLine(p5.x, p5.y, p6.x, p6.y);
		}
	}
}

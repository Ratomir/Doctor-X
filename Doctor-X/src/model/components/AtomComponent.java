package model.components;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import model.components.interfaces.IDrawable;

/**
 * Apstraktna klasa koja definise svojstva i metode elemenata za iscrtavanje.
 * 
 * @author Ilija Divljan
 *
 */
public abstract class AtomComponent implements IDrawable 
{
	protected Point p;
	
	//r je kao poluprecnik komponente. moze se modifikovati.
	protected int r;
	public Color color;
	protected Kind kind;
	protected boolean selected = false;
	protected Rectangle b = new Rectangle();
	
	protected AtomType typeAtom;
	protected String sign = null;
	protected int ordinalNumber;
	protected String name;
	protected float weight;
	protected int [] numberOfRings;
	public double rotateAngle = 0;
	
	

	public AtomComponent(int number, String sign, String name, int [] numberOfRings, float weight, AtomType type)
	{
		this.ordinalNumber = number;
		this.sign = sign;
		this.name = name;
		this.numberOfRings = numberOfRings;
		this.weight = weight;
		
		this.typeAtom = type;
	}
	
	//metoda za kopiranje
	public AtomComponent copy(AtomComponent comp)
	{
		AtomComponent copy = null;
		if(Nonmetals.class.isInstance(comp))
		{
			copy = new Nonmetals(new Point(comp.p.x+20, comp.p.y+20), comp.r, comp.color, comp.ordinalNumber, comp.sign, comp.name, comp.numberOfRings, comp.weight, comp.typeAtom);
		}
		else if(AlkalEarth.class.isInstance(comp))
		{
			copy = new AlkalEarth(new Point(comp.p.x+20, comp.p.y+20), comp.r, comp.color, comp.ordinalNumber, comp.sign, comp.name, comp.numberOfRings, comp.weight, comp.typeAtom);
		}
		else if(Metalloids.class.isInstance(comp))
		{
			copy = new Metalloids(new Point(comp.p.x+20, comp.p.y+20), comp.r, comp.color, comp.ordinalNumber, comp.sign, comp.name, comp.numberOfRings, comp.weight, comp.typeAtom);
		}
		else if(AlkalMetals.class.isInstance(comp))
		{
			copy = new AlkalMetals(new Point(comp.p.x+20, comp.p.y+20), comp.r, comp.color, comp.ordinalNumber, comp.sign, comp.name, comp.numberOfRings, comp.weight, comp.typeAtom);
		}
		else if(Halogens.class.isInstance(comp))
		{
			copy = new Halogens(new Point(comp.p.x+20, comp.p.y+20), comp.r, comp.color, comp.ordinalNumber, comp.sign, comp.name, comp.numberOfRings, comp.weight, comp.typeAtom);
		}
		else if(NobleGases.class.isInstance(comp))
		{
			copy = new NobleGases(new Point(comp.p.x+20, comp.p.y+20), comp.r, comp.color, comp.ordinalNumber, comp.sign, comp.name, comp.numberOfRings, comp.weight, comp.typeAtom);
		}
		else if(Edge.class.isInstance(comp))
		{
			//copy = new Edge(comp, comp);
		}
		return copy;
	}

	//Racunanje okvira elementa
	public void setBoundary(Rectangle b)
	{
		b.setBounds(p.x - r, p.y - r, 2*r, 2*r);
	}

	//Return this node's Location.
	public Point getLocation()
	{
		return p;
	}
	
	//Set's this node's Location.
	public void setLocation(Point p)
	{
		this.p = p;
	}
	
	//Return true if this node contains p.
	public boolean contains(Point p)
	{
		return b.contains(p);
	}
	
	//Return true if selected
	public boolean isSelected()
	{
		return selected;
	}
			
	//Mark this node as selected.
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
			
	//Collect all the selected nodes in list.
	public static void getSelected(List<AtomComponent> list, List<AtomComponent> selected)
	{
		selected.clear();
		for(AtomComponent n : list)
		{
			if(n.isSelected())
			{
				selected.add(n);
			}
		}
				
	}
			
	//Select no nodes. Deselktovanje.
	public static void selectNone(List<AtomComponent> list)
	{
		for(AtomComponent n : list)
		{
			n.setSelected(false);
		}
	}
	
	//Select all nodes.
	public static void selectAll(List<AtomComponent> list)
	{
		for(AtomComponent n : list)
		{
			n.setSelected(true);
		}
	}
			
	//Selekt a singe node; return true if not already selected.
	public static boolean selectOne(List<AtomComponent> list, Point p)
	{
		for(AtomComponent n : list)
		{
			if(n.contains(p))
			{
				if(!n.isSelected())
				{
					AtomComponent.selectNone(list);
					n.setSelected(true);
				}
				return true;
			}
		}
		return false;
	}
			
			
			
	//Select each node in r.
	public static void selectRect(List<AtomComponent> list, Rectangle r)
	{
		for(AtomComponent n : list)
		{
			//Dakle prilikom razvlacenja pravougaonika ako pravougaonik sadrzi tacku P cvora on se selektuje.
			//U ovom slucaju je tacka p (x,y) = (0,0)
			n.setSelected(r.contains(n.p));
		}
	}
			
			
	//Toogle selectet state of each node containing p
	public static void selectToogle(List<AtomComponent> list, Point p)
	{
		for(AtomComponent n : list)
		{
			if(n.contains(p))
			{
				n.setSelected(!n.isSelected());
			}
		}
	}
			
	//Update each node's position by d (delta)
	public static void updatePosition(List<AtomComponent> list, Point d)
	{
		for(AtomComponent n : list)
		{
			n.p.x += d.x;
			n.p.y += d.y;
			n.setBoundary(n.b);
		}
	}
			
	//Update each node's radius r.
	public static void updateRadius(List<AtomComponent> list, int r)
	{
		for(AtomComponent n : list)
		{
			if(n.isSelected())
			{
				n.r = r;
				n.setBoundary(n.b);
			}
		}
	}
			
	//Update each node's color
	public static void updateColor(List<AtomComponent> list, Color color)
	{
		for(AtomComponent n : list)
		{
			if(n.isSelected())
			{
				n.color = color;
			}
		}
	}
	
	public static void updateColorSingle(AtomComponent atom, Color color)
	{
		
		atom.color = color;
		
	}
			
	//Update each node's kind
	public static void updateKind(List<AtomComponent> list, Kind kind) 
	{
	    for (AtomComponent n : list) 
	    {
	            	
	        if (n.isSelected()) 
	        {
	             n.kind = kind;
	        }
	    }
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Rectangle getB() {
		return b;
	}

	public void setB(Rectangle b) {
		this.b = b;
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
	 * @return the typeAtom
	 */
	public AtomType getTypeAtom()
	{
		return typeAtom;
	}

	/**
	 * @param typeAtom the typeAtom to set
	 */
	public void setTypeAtom(AtomType typeAtom)
	{
		this.typeAtom = typeAtom;
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
	 * @return rotateAngle ugao rotacije
	 */
	public double getRotateAngle() {
		return rotateAngle;
	}

	/**
	 * 
	 * @param rotateAngle ugao rotacije
	 */
	public void setRotateAngle(double rotateAngle) {
		this.rotateAngle = rotateAngle;
	}
	
	/**
	 * Funkcija koja obavlja rotiranje selektovanih komponenti.
	 * @param atomi
	 * @param angle
	 */
	public static void Rotate(List<AtomComponent> atomi, double angle)
	{
		for(AtomComponent o : atomi)
		{
			if(o.isSelected())
			{
				o.setRotateAngle(o.getRotateAngle() + angle);
			}
		}
	}

}

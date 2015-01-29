package parser;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.DocumentModel;
import model.components.AlkalEarth;
import model.components.AlkalMetals;
import model.components.AtomComponent;
import model.components.AtomType;
import model.components.Edge;
import model.components.EdgeKind;
import model.components.Halogens;
import model.components.NobleGases;
import model.components.Nonmetals;
import model.components.Metalloids;

import org.xml.sax.SAXException;

public class XMLreader
{
		
	public String ReadWindowParameters(JFrame application)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try
		{
			// Kreiranje novog parsera
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("xmlfiles/windowProp.xml", handler);

			application.setSize(Integer.parseInt(handler.getParameters().get(0)), Integer.parseInt(handler.getParameters().get(1)));
			application.setLocation(Integer.parseInt(handler.getParameters().get(2)), Integer.parseInt(handler.getParameters().get(3)));
			
			//kod za citanje i vracanje lokalizacije iz xml-a
			if((handler.getParameters().get(4)).contains("en"))
			{				
				return "en_US";
			}
			else if((handler.getParameters().get(4)).contains("bh"))
			{				
				return "sr_BA";
			}
			else
			{				
				return "sr_RS";
			}						
		}		
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public String defaultWorkspace()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();		
		try
		{			
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("xmlfiles/windowProp.xml", handler);
						
			return handler.getParameters().get(5);		
		}		
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";	
	}
	
	public String defaultTheme()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();		
		try
		{			
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("xmlfiles/windowProp.xml", handler);
						
			return handler.getParameters().get(6);		
		}		
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";	
	}
	
	public DocumentModel ReadDocument(String location)
	{		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		DocumentModel doc = new DocumentModel("Unknown document", "");
		try
		{			
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			handler.getParameters().clear();
			parser.parse(location, handler);
			
			doc.name = handler.getParameters().get(0);
			doc.location = handler.getParameters().get(1);
			
			//citanje i upis elemenata u dokument
			int i = 0;
			AtomComponent n = null;	
			int sum = 2;			
			while( 9*i+sum < handler.getParameters().size())
			{
				switch(handler.getParameters().get(9*i+sum))
				{
				case"Nonmetals":
					int[] niz = new int[15];					
					
					n = new Nonmetals(new Point(Integer.parseInt(handler.getParameters().get(9*i+sum+1)), Integer.parseInt(handler.getParameters().get(9*i+sum+2))),
							Integer.parseInt(handler.getParameters().get(9*i+sum+3)), new Color(Integer.parseInt(handler.getParameters().get(9*i+sum+4))),
									Integer.parseInt(handler.getParameters().get(9*i+sum+5)), (handler.getParameters().get(9*i+sum+6)),
									(handler.getParameters().get(9*i+sum+7)), niz, (float)Double.parseDouble(handler.getParameters().get(9*i+sum+8)), AtomType.Nonmetals);
					int[] niz1 = new int[Integer.parseInt(handler.getParameters().get(9*i+sum+9))];
					n.setNumberOfRings(niz1);
					for(int j=0; j<niz1.length; j++)
					{
						n.getNumberOfRings()[j] = Integer.parseInt(handler.getParameters().get(9*i+sum+9+j+1));
					}
					sum+=niz1.length+1;
					break;					
				case"AlkaliEarth":	
					niz = new int[15];					
					
					n = new AlkalEarth(new Point(Integer.parseInt(handler.getParameters().get(9*i+sum+1)), Integer.parseInt(handler.getParameters().get(9*i+sum+2))),
							Integer.parseInt(handler.getParameters().get(9*i+sum+3)), new Color(Integer.parseInt(handler.getParameters().get(9*i+sum+4))),
									Integer.parseInt(handler.getParameters().get(9*i+sum+5)), (handler.getParameters().get(9*i+sum+6)),
									(handler.getParameters().get(9*i+sum+7)), niz, (float)Double.parseDouble(handler.getParameters().get(9*i+sum+8)), AtomType.AlkaliEarth);
					niz1 = new int[Integer.parseInt(handler.getParameters().get(9*i+sum+9))];
					n.setNumberOfRings(niz1);
					for(int j=0; j<niz1.length; j++)
					{
						n.getNumberOfRings()[j] = Integer.parseInt(handler.getParameters().get(9*i+sum+9+j+1));
					}
					sum+=niz1.length+1;
					break;
				case"Metalloids":
					niz = new int[15];					
					
					n = new Metalloids(new Point(Integer.parseInt(handler.getParameters().get(9*i+sum+1)), Integer.parseInt(handler.getParameters().get(9*i+sum+2))),
							Integer.parseInt(handler.getParameters().get(9*i+sum+3)), new Color(Integer.parseInt(handler.getParameters().get(9*i+sum+4))),
									Integer.parseInt(handler.getParameters().get(9*i+sum+5)), (handler.getParameters().get(9*i+sum+6)),
									(handler.getParameters().get(9*i+sum+7)), niz, (float)Double.parseDouble(handler.getParameters().get(9*i+sum+8)), AtomType.Metalloids);
					niz1 = new int[Integer.parseInt(handler.getParameters().get(9*i+sum+9))];
					n.setNumberOfRings(niz1);
					for(int j=0; j<niz1.length; j++)
					{
						n.getNumberOfRings()[j] = Integer.parseInt(handler.getParameters().get(9*i+sum+9+j+1));
					}
					sum+=niz1.length+1;
					break;
				case"AlkalMetals":	
					niz = new int[15];			
					
					n = new AlkalMetals(new Point(Integer.parseInt(handler.getParameters().get(9*i+sum+1)), Integer.parseInt(handler.getParameters().get(9*i+sum+2))),
							Integer.parseInt(handler.getParameters().get(9*i+sum+3)), new Color(Integer.parseInt(handler.getParameters().get(9*i+sum+4))),
									Integer.parseInt(handler.getParameters().get(9*i+sum+5)), (handler.getParameters().get(9*i+sum+6)),
									(handler.getParameters().get(9*i+sum+7)), niz, (float)Double.parseDouble(handler.getParameters().get(9*i+sum+8)), AtomType.AlkalMetals);
					niz1 = new int[Integer.parseInt(handler.getParameters().get(9*i+sum+9))];
					n.setNumberOfRings(niz1);
					for(int j=0; j<niz1.length; j++)
					{
						n.getNumberOfRings()[j] = Integer.parseInt(handler.getParameters().get(9*i+sum+9+j+1));
					}
					sum+=niz1.length+1;					
					break;
				case"Halogens":	
					niz = new int[15];					
					
					n = new Halogens(new Point(Integer.parseInt(handler.getParameters().get(9*i+sum+1)), Integer.parseInt(handler.getParameters().get(9*i+sum+2))),
							Integer.parseInt(handler.getParameters().get(9*i+sum+3)), new Color(Integer.parseInt(handler.getParameters().get(9*i+sum+4))),
									Integer.parseInt(handler.getParameters().get(9*i+sum+5)), (handler.getParameters().get(9*i+sum+6)),
									(handler.getParameters().get(9*i+sum+7)), niz, (float)Double.parseDouble(handler.getParameters().get(9*i+sum+8)), AtomType.Halogens);
					niz1 = new int[Integer.parseInt(handler.getParameters().get(9*i+sum+9))];
					n.setNumberOfRings(niz1);
					for(int j=0; j<niz1.length; j++)
					{
						n.getNumberOfRings()[j] = Integer.parseInt(handler.getParameters().get(9*i+sum+9+j+1));
					}
					sum+=niz1.length+1;					
					break;
				case"NobleGases":	
					niz = new int[15];					
					
					n = new NobleGases(new Point(Integer.parseInt(handler.getParameters().get(9*i+sum+1)), Integer.parseInt(handler.getParameters().get(9*i+sum+2))),
							Integer.parseInt(handler.getParameters().get(9*i+sum+3)), new Color(Integer.parseInt(handler.getParameters().get(9*i+sum+4))),
									Integer.parseInt(handler.getParameters().get(9*i+sum+5)), (handler.getParameters().get(9*i+sum+6)),
									(handler.getParameters().get(9*i+sum+7)), niz, (float)Double.parseDouble(handler.getParameters().get(9*i+sum+8)), AtomType.NobleGases);
					niz1 = new int[Integer.parseInt(handler.getParameters().get(9*i+sum+9))];
					n.setNumberOfRings(niz1);
					for(int j=0; j<niz1.length; j++)
					{
						n.getNumberOfRings()[j] = Integer.parseInt(handler.getParameters().get(9*i+sum+9+j+1));
					}
					sum+=niz1.length+1;
					break;
				default:
					break;
				}				
				doc.nodes.add(n);
				i++;				
			}		
			
			
			//citanje veza
			i = 0;
			Edge ed = null;	
			AtomComponent n1 = null;
			AtomComponent n2 = null;
			sum = 2;			
			while( 3*i < handler.getEdgeparam().size())
			{
				switch(handler.getEdgeparam().get(3*i+2))
				{
				case"Single":
					n1 = doc.nodes.get(Integer.parseInt(handler.getEdgeparam().get(3*i)));
					n2 = doc.nodes.get(Integer.parseInt(handler.getEdgeparam().get(3*i+1)));
					ed = new Edge(n1, n2, EdgeKind.Single);
					break;
				case"Double":
					n1 = doc.nodes.get(Integer.parseInt(handler.getEdgeparam().get(3*i)));
					n2 = doc.nodes.get(Integer.parseInt(handler.getEdgeparam().get(3*i+1)));
					ed = new Edge(n1, n2, EdgeKind.Double);
					break;
				case"Triple":
					n1 = doc.nodes.get(Integer.parseInt(handler.getEdgeparam().get(3*i)));
					n2 = doc.nodes.get(Integer.parseInt(handler.getEdgeparam().get(3*i+1)));
					ed = new Edge(n1, n2, EdgeKind.Triple);
					break;
				}
				doc.edges.add(ed);
				i++;
			}			
			
			return doc;
		}		
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return doc;	
	}
	
}

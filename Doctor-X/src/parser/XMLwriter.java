package parser;

import java.io.FileWriter;

import javax.swing.JFrame;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import model.Application;
import model.DocumentModel;
import model.components.AtomComponent;
import model.components.Edge;


public class XMLwriter
{
	private String source;
	private String rootElement;
	
	private String valueForWrite = "";
	
	public String getValueForWrite()
	{
		return valueForWrite;
	}

	public void setValueForWrite(String valueForWrite)
	{
		this.valueForWrite = valueForWrite;
	}

	public XMLwriter()
	{
		source = "";
		rootElement = "";
	}
	
	public XMLwriter(String source)
	{
		this.source = source;
		rootElement = "";
	}
	
	public XMLwriter(String source, String rootElement)
	{
		this.source = source;
		this.rootElement = rootElement;
	}
	
	public void writeWindowParametars(JFrame application, String defaultWorkspace)
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try
		{
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("xmlfiles\\windowProp.xml"));

		     writer.writeStartDocument();
		     writer.writeStartElement("window");
		     
		     writer.writeStartElement("dimension");
		     writer.writeStartElement("value");
		     writer.writeCharacters("" + application.getWidth());
		     writer.writeEndElement();
		     writer.writeEndElement();
		     
		     writer.writeStartElement("dimension");
		     writer.writeStartElement("value");
		     writer.writeCharacters("" + application.getHeight());
		     writer.writeEndElement();
		     writer.writeEndElement();
		     
		     writer.writeStartElement("x-coordinate");
		     writer.writeStartElement("value");
		     writer.writeCharacters("" + application.getX());
		     writer.writeEndElement();
		     writer.writeEndElement();
		     
		     writer.writeStartElement("y-coordinate");
		     writer.writeStartElement("value");
		     writer.writeCharacters("" + application.getY());
		     writer.writeEndElement();
		     writer.writeEndElement();	
		     
		     //pocetak koda za upis lokalizacije
		     writer.writeStartElement("localization");
		     writer.writeStartElement("value");
		     if(application.getTitle().contains("-X"))
		     { writer.writeCharacters("en");}
			 else if(application.getTitle().contains("-H"))
				{writer.writeCharacters("bh");}
			 else
				{writer.writeCharacters("sr");	}
		     writer.writeEndElement();
		     writer.writeEndElement();		     
		     //kraj koda za upis lokalizacije
		     
		     //upis default workspace
		     writer.writeStartElement("workspace");
		     writer.writeStartElement("value");
		     writer.writeCharacters(defaultWorkspace);
		     writer.writeEndElement();
		     writer.writeEndElement();	
		     
		     //upis lookandfeel-a
		     writer.writeStartElement("theme");
		     writer.writeStartElement("value");
		     writer.writeCharacters("" + Application.theme);
		     writer.writeEndElement();
		     writer.writeEndElement();
		     
		     writer.writeEndDocument();

		     writer.flush();
		     writer.close();
		     
		     System.exit(0);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	
	public void writeDocumentParametars(DocumentModel document)
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try
		{
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(document.location));
			
			writer.writeStartDocument();
		    writer.writeStartElement("document");
		     
		    writer.writeStartElement("name");
		    writer.writeStartElement("value");
		    writer.writeCharacters("" + document.name);
		    writer.writeEndElement();
		    writer.writeEndElement();
		    
		    writer.writeStartElement("location");
		    writer.writeStartElement("value");
		    writer.writeCharacters("" + document.location);
		    writer.writeEndElement();
		    writer.writeEndElement();
		    
		    //Ovde ce ici kod za upis elemenata koji se nalaze u dokumentu
		    for (AtomComponent item : document.nodes) 
		    {
		    	writer.writeCharacters("\n");
		    	writer.writeStartElement("element");
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getTypeAtom()); //upis tipa
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getLocation().x); //upis x lokacije
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getLocation().y); //upis y lokacije
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getR()); //upis poluprecnika r
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getColor().getRGB()); //upis boje
			    writer.writeEndElement();
			    
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getOrdinalNumber()); //upis rendog broja
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getSign()); //upis znaka
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getName()); //upis imena
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getWeight()); //upis mase
			    writer.writeEndElement();
			    writer.writeStartElement("value");
			    writer.writeCharacters("" + item.getNumberOfRings().length); //upis broja ljuski
			    writer.writeEndElement();
			    for (int elecring : item.getNumberOfRings())
			    {
			    	writer.writeStartElement("value");
				    writer.writeCharacters("" + elecring); //upis elektrona po prstenu
				    writer.writeEndElement();
				}    
			    
			    writer.writeEndElement();
			}
		    
		    //upis konekcija medju atomima
		    for (Edge item : document.edges) 
		    {
		    	writer.writeCharacters("\n");
		    	writer.writeStartElement("connection");
		    	
			    writer.writeStartElement("conVal");			    
			    writer.writeCharacters("" + document.nodes.lastIndexOf(item.n1)); //upis rednog broja prvog atoma			    
			    writer.writeEndElement();
			    writer.writeStartElement("conVal");			    
			    writer.writeCharacters("" + document.nodes.lastIndexOf(item.n2)); //upis rednog broja drugog atoma			    
			    writer.writeEndElement();
		    	
			    writer.writeStartElement("conVal");			    
			    writer.writeCharacters("" + item.kind); //upis Kind			    
			    writer.writeEndElement();
		    	
			    writer.writeEndElement();
			}
		    
		    writer.writeEndDocument();
		    writer.flush();
		    writer.close();
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	
	
	public void writeElement(String nameOfElement, String valueOfElement, String [] attributes)
	{
		
	}

	
	
	/*
	 * 
	 *   Getters and setters 
	 * 
	 */
	
	
	
	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getRootElement()
	{
		return rootElement;
	}

	public void setRootElement(String rootElement)
	{
		this.rootElement = rootElement;
	}
	
}

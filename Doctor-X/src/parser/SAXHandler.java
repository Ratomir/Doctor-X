package parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXHandler extends DefaultHandler
{
	boolean value = false;
	boolean conVal = false;
	int i = 0;

	private List<String> parameters = new ArrayList<String>();
	private List<String> edgeparam = new ArrayList<String>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if (qName.equalsIgnoreCase("value"))
		{
			/*
			 * Ovde èitamo atribute.
			 */
			
			value = true;
		}
		if (qName.equalsIgnoreCase("conVal"))
		{
			conVal = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if (qName.equalsIgnoreCase("value"))
		{
			value = false;
		}
		if (qName.equalsIgnoreCase("conVal"))
		{
			conVal = false;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		if (value)
		{			
			this.getParameters().add(new String(ch, start, length));
		}
		if (conVal)
		{			
			this.getEdgeparam().add(new String(ch, start, length));
		}
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public List<String> getEdgeparam() {
		return edgeparam;
	}

	public void setEdgeparam(List<String> edgeparam) {
		this.edgeparam = edgeparam;
	}

}

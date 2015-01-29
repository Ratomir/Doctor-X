package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import commands.Command;

import model.components.AtomComponent;
import model.components.Edge;
import view.ptable.Atom;

/**
 * Ova klasa cuva podatke o kanvasu i elementima koji se nalaze na kanvasu.
 * @author Ilija Divljan
 *
 */

public class DocumentModel 
{
	public static final int WIDE = 640;
	public static final int HIGH = 480;
	public static final int RADIUS = 20;
	
	public int radius = RADIUS;
	public String name = null;
	public String location = null;
	public List<AtomComponent> nodes = new ArrayList<AtomComponent>();
	public List<AtomComponent> selected = new ArrayList<AtomComponent>();
	public List<Edge> edges = new ArrayList<Edge>();
	public Point mousePt = new Point(WIDE/2, HIGH/2);
	public Rectangle mouseRect = new Rectangle();
	
	public boolean selecting = false;
	public int IDdocument;
	public double zoomFactor;
	
	public Atom atom = null;
	
	public Stack<Command> undoCommands = new Stack<Command>();
	public Stack<Command> redoCommands = new Stack<Command>();
	
		
	/**
	 * Osnovni konstruktor. Ostavljen za promjene i prilagodjavanja po potrebi.
	 */
	public DocumentModel(String name, String location)
	{
		this.name = name;
		this.location = location;
		IDdocument = Application.IDcounter;
		zoomFactor = 1;
	}
	
	/**
	 * Metoda za pribavljanje elemenata sa kanvasa.
	 */
	public List<AtomComponent> getElements()
	{
		return nodes;
	}

	/**
	 * Postavlja listu elemenata u dokument.
	 * @param elements - lista elemenata
	 */
	public void setElements(List<AtomComponent> elements)
	{
		this.nodes = elements;
	}

	/**
	 * Dohvaca listu veza iz dokumentu.
	 * @return
	 */
	public List<Edge> getListaKonekcija()
	{
		return edges;
	}

	/**
	 * Postavlja listu veza u dokument.
	 * @param listaKonekcija
	 */
	public void setListaKonekcija(List<Edge> listaKonekcija)
	{
		this.edges = listaKonekcija;
	}

	/**
	 * Metoda vraca listu sa selektovanim elementima na kanvasu.
	 * @param list
	 */
	public List<AtomComponent> getSelectedElements(List<AtomComponent> list)
	{
		AtomComponent.getSelected(list, selected);
		return selected;
	}
	
	/**
	 * Metoda provjerava da li je Undo Stek prazan.
	 * @param
	 */
	public boolean UndoStackEmpty()
	{
		return undoCommands.empty();
	}
	/**
	 * Dodavanje komande na vrh steka.
	 * @param command
	 */
	public void PushUndoCommand(Command command)
	{
		undoCommands.push(command);
	}
	
	/**
	 * Uzimanje komande sa vrha steka.
	 * @return
	 */
	public Command PopUndoCommand()
	{
		return undoCommands.pop();
	}
	/**
	 * Metoda provjerava da li je Undo Stek prazan.
	 * @param
	 */
	public boolean RedoStackEmpty()
	{
		return redoCommands.empty();
	}
	/**
	 * Dodavanje komande na vrh steka.
	 * @param command
	 */
	public void PushRedoCommand(Command command)
	{
		redoCommands.push(command);
	}
	
	/**
	 * Uzimanje komande sa vrha steka.
	 * @return
	 */
	public Command PopRedoCommand()
	{
		return redoCommands.pop();
	}
}

package controller;

import commands.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;

import model.Application;
import model.components.AlkalEarth;
import model.components.AlkalMetals;
import model.components.AtomComponent;
import model.components.AtomType;
import model.components.EdgeKind;
import model.components.Halogens;
import model.components.NobleGases;
import model.components.Nonmetals;
import model.components.Metalloids;
import view.Canvas;
import view.View;
import view.popup.CanvasPopup;
import view.ptable.Atom;
import commands.Command;

/**
 * Klasa CanvasController sadrzi akcije i osluskivace koji se koriste za
 * manipulaciju nad elementima iscrtanim na kanvasu.
 * {@link CanvasController#paintAtom} sluzi za iscrtavanje trenutno selektovanog atoma.
 * @author Ilija Divljan
 *
 */
public class CanvasController 
{
	public Application appModel = null;
	public View view = null;
	
	public HashMap<String, Command> commands = null;
	
	private Toolkit toolkit = null;
	
	private Atom paintAtom = null;
	
	CanvasPopup cp = new CanvasPopup();
	
	public CanvasController(Application model, View view, HashMap<String, Command> commands)
	{
		this.appModel = model;
		this.view = view;
		this.commands = commands;
		
		//da dodaje listener za svaki tab koji se otvori
		this.view.centralPart.addChangeListener(new TabListener());		
		
		//da doda listener za svaki vec otvoreni tab
		for (Canvas item : view.centralPart.canvasList)
		{
			item.addMouseListener(new MouseHandler());
			item.addMouseMotionListener(new MouseMotionHandler());
		}	
		
		cp.setElementListeners(al);
		
		toolkit = Toolkit.getDefaultToolkit();  
	}	
	
	//metoda sluzi za odredjivanje id selektovanog canvasa
	public int selectedCanvas(View view)
	{
		//ovde vrsimo odredjivanje koji je canvas selektovan
		JScrollPane selectedTab = (JScrollPane) view.centralPart.getSelectedComponent();		
		JViewport viewport = selectedTab.getViewport();
		return ((Canvas)viewport.getView()).IDcanvas; 
	}
	
	public class MouseHandler extends MouseAdapter
	{
		@Override
		public void mouseReleased(MouseEvent e) 
		{
			int index = selectedCanvas(view);
			appModel.documents.get(index).selecting = true;
			appModel.documents.get(index).mouseRect.setBounds(0, 0, 0, 0);
						
			if(e.isPopupTrigger())
			{
				
				cp.show(e.getComponent(), e.getX(), e.getY());
				
			}
			
			e.getComponent().repaint();
		}
		
		@SuppressWarnings("static-access")
		@Override
		public void mousePressed(MouseEvent e) 
		{
			int index = selectedCanvas(view);
			double zoom = appModel.documents.get(index).zoomFactor;
			appModel.documents.get(index).mousePt = e.getPoint();			
			appModel.documents.get(index).mousePt.x = (int) (appModel.documents.get(index).mousePt.x/appModel.documents.get(index).zoomFactor);
			appModel.documents.get(index).mousePt.y = (int) (appModel.documents.get(index).mousePt.y/appModel.documents.get(index).zoomFactor);
						
			if(e.isShiftDown())
			{
				AtomComponent.selectToogle(appModel.documents.get(index).nodes, appModel.documents.get(index).mousePt);
			}
			else if(e.isPopupTrigger())
			{
				AtomComponent.selectOne(appModel.documents.get(index).nodes, appModel.documents.get(index).mousePt);
			}
			else if(AtomComponent.selectOne(appModel.documents.get(index).nodes, appModel.documents.get(index).mousePt))
			{
				appModel.documents.get(index).selecting = false;
			}
			else
			{
				AtomComponent.selectNone(appModel.documents.get(index).nodes);
				appModel.documents.get(index).selecting = true;
			}
			
			if(appModel.documents.get(index).atom != null)
			{
				Atom atom = appModel.documents.get(index).atom;
				AtomType type = atom.getType();
				Point p = new Point((int)(e.getX()/zoom),(int)(e.getY()/zoom));
				
				if     (type == AtomType.Nonmetals)
				{
					Command x = new AddElementCommand(appModel, view);
					x.execute(new Nonmetals(p, 20 , Color.decode("#22FF22"), atom.getOrdinalNumber(), atom.getSign(), atom.getName(), atom.getNumberOfRings(), atom.getWeight(), type));
				
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(x);
				}
				else if(type == AtomType.Metalloids)
				{
					Command x = new AddElementCommand(appModel, view);
					x.execute(new Metalloids(p, 20 , Color.decode("#77DD88"), atom.getOrdinalNumber(), atom.getSign(), atom.getName(), atom.getNumberOfRings(), atom.getWeight(), type));
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(x);
				}
				else if(type == AtomType.AlkaliEarth)
				{
					Command x = new AddElementCommand(appModel, view);
					x.execute(new AlkalEarth(p, 20 , Color.decode("#FFFF44"), atom.getOrdinalNumber(), atom.getSign(), atom.getName(), atom.getNumberOfRings(), atom.getWeight(), type));
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(x);
				}
				else if(type == AtomType.AlkalMetals)
				{
					Command x = new AddElementCommand(appModel, view);
					x.execute(new AlkalMetals(p, 20, Color.decode("#FFCC33"), atom.getOrdinalNumber(), atom.getSign(), atom.getName(), atom.getNumberOfRings(), atom.getWeight(), type));
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(x);
				}
				else if(type == AtomType.Halogens)
				{
					Command x = new AddElementCommand(appModel, view);
					x.execute(new Halogens(p, 20, Color.decode("#22EECC"), atom.getOrdinalNumber(), atom.getSign(), atom.getName(), atom.getNumberOfRings(), atom.getWeight(), type));
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(x);
				}
				else if(type == AtomType.NobleGases)
				{
					Command x = new AddElementCommand(appModel, view);
					x.execute(new NobleGases(p, 20, Color.decode("#77CCFF"), atom.getOrdinalNumber(), atom.getSign(), atom.getName(), atom.getNumberOfRings(), atom.getWeight(), type));
					appModel.documents.get(selectedCanvas(view)).PushUndoCommand(x);
				}
			}
			
			switch (appModel.state)
			{
			case ZoomIn:
				Command zoomin = new ZoomInCommand(appModel, view);
				zoomin.execute(null);
				view.centralPart.repaintAll();
				break;
				
			case ZoomOut:
				Command zoomout = new ZoomOutCommand(appModel, view);
				zoomout.execute(null);
				view.centralPart.repaintAll();
				break;
				
			default:
				break;
			}
			
			e.getComponent().repaint();
		}
		
		@Override
		public void mouseExited(MouseEvent e)
		{
			view.setCursor(Cursor.getDefaultCursor());
		}
		
		private void showPopup(MouseEvent e)
		{
			//Popup ide ovde.
		}	
	}
	
	public class MouseMotionHandler extends MouseMotionAdapter
	{
		Point delta = new Point();
		
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			int index = selectedCanvas(view);
			double zoom = appModel.documents.get(index).zoomFactor;
			
			if(view.toolbox.getIndexOfElement() == "grabber")
			{
				if(appModel.documents.get(index).selecting)
				{
					appModel.documents.get(index).mouseRect.setBounds((int)Math.min(appModel.documents.get(index).mousePt.x, e.getX()/zoom), (int)Math.min(appModel.documents.get(index).mousePt.y, e.getY()/zoom),
							(int)Math.abs(appModel.documents.get(index).mousePt.x - e.getX()/zoom), (int)Math.abs(appModel.documents.get(index).mousePt.y - e.getY()/zoom));
					AtomComponent.selectRect(appModel.documents.get(index).nodes, appModel.documents.get(index).mouseRect);				
				}
				else
				{
					delta.setLocation(e.getX()/zoom - appModel.documents.get(index).mousePt.x, e.getY()/zoom - appModel.documents.get(index).mousePt.y);
					AtomComponent.getSelected(appModel.documents.get(index).nodes, appModel.documents.get(index).selected);
					AtomComponent.updatePosition(appModel.documents.get(index).selected, delta);
				
					appModel.documents.get(index).mousePt.x = (int) (e.getX()/zoom);
					appModel.documents.get(index).mousePt.y = (int) (e.getY()/zoom);
				}
			}
			e.getComponent().repaint();
		}
		
		@Override
		public void mouseMoved(MouseEvent e)
		{
			Image img = null;
			switch (view.toolbox.getIndexOfElement())
			{
				case "zoomIn":
					img = toolkit.getImage("image/slidepane/standard/64x64/zoomin64.png");
				break;
				
				case "zoomOut":
					img = toolkit.getImage("image/slidepane/standard/64x64/zoomout64.png");
				break;
				
				case "grabber":
					img = toolkit.getImage("image/slidepane/standard/64x64/grabber64.png");
				break;

				default:
					break;
			}
			
			if(img != null)
			{
				Cursor cursor = toolkit.createCustomCursor(img, new Point(0, 0), "img"); 
				view.setCursor(cursor);
			}
			else
			{
				if(getPaintAtom() != null)
				{
					
				}
				else
				{
					view.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			
			view.statusBar.setCursorState("X: " + e.getX() + " Y: " + e.getY());
		}
	}
	
	public class TabListener implements javax.swing.event.ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{	
			try
			{
			view.centralPart.getSelectedComponent().addMouseListener(new MouseHandler());
			view.centralPart.getSelectedComponent().addMouseMotionListener(new MouseMotionHandler());
			}
			catch(Exception e1)
			{
				
			}
		}

	}

	/**
	 * @return the paintAtom
	 */
	public Atom getPaintAtom()
	{
		return paintAtom;
	}

	/**
	 * @param paintAtom the paintAtom to set
	 */
	public void setPaintAtom(Atom paintAtom)
	{
		this.paintAtom = paintAtom;
	}
	
	/**
	 * Action Listener koji prati korisnicke interakcije sa CanvasPopupm.
	 */
	ActionListener al = new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand();
			
			switch (actionCommand) {
			case "Cut":
				Command cut = new CutCommand(appModel, view);
				cut.execute(null);
				view.centralPart.repaintAll();
				break;
			case "Copy":
				Command copy = new CopyCommand(appModel, view);
				copy.execute(null);
				break;
			case "Paste":
				Command paste = new PasteCommand(appModel, view);
				paste.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(paste);
				view.centralPart.repaintAll();
				break;
			case "SelectAll":
				AtomComponent.selectAll(appModel.getDocuments().get(view.centralPart.getSelectedIndex()).nodes);
				break;
			case "Delete":
				Command delte = new DeleteElementCommand(appModel, view);
				delte.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(delte);
				view.centralPart.repaintAll();
				break;
			case "RotateLeft":
				Command rotateL = new RotateLeftCommand(appModel, view);
				rotateL.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(rotateL);
				view.centralPart.repaintAll();
				break;
			case "RotateRight":
				Command rotateR = new RotateRightCommand(appModel, view);
				rotateR.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(rotateR);
				view.centralPart.repaintAll();
				break;
			case "Single":
				Command single = new ConnectCommand(appModel, view, EdgeKind.Single);
				single.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(single);
				view.centralPart.repaintAll();
				break;
			case "Double":
				Command doub = new ConnectCommand(appModel, view, EdgeKind.Double);
				doub.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(doub);
				view.centralPart.repaintAll();
				break;
			case "Triple":
				Command trip = new ConnectCommand(appModel, view, EdgeKind.Triple);
				trip.execute(null);
				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(trip);
				view.centralPart.repaintAll();
				break;
			case "Color":
				Command paint = new PaintCommand(appModel, view);

				appModel.documents.get(selectedCanvas(view)).PushUndoCommand(paint);
				paint.execute(null);
				view.centralPart.repaintAll();
				break;
			default:
				break;
			}
			
		}
	};
	
	
}

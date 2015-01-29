package view.ptable.jtable;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.components.AtomType;
import view.ptable.Atom;

public class CustomTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	Atom atom = new Atom(0, "", "", new int[0], 0.0f, AtomType.AlkaliEarth);
	Vector<String> columnNames = new Vector<>();
	
	Object [][] rows = null;
	
	public CustomTableModel()
	{
		columnNames.addElement("<html><body><p style=\"font-size: 1.25em; \">Atributi</p></body></html>");
		columnNames.addElement("<html><body><p style=\"font-size: 1.25em; \">Vrijednosti</p></body></html>");
		
		rows = new Object[][]
			{
				{ "Order number", atom.getOrdinalNumber()},
				{ "Sign", atom.getSign()},
				{ "Name", atom.getName()},
				{ "Wight", atom.getWeight()},
				{ "Rings", atom.getNumberOfRings().length},
				{ "Number atoms \n\rper rings", atom.atomsPerRingToString()}
			};
	}
	
	@Override
	public Class<?> getColumnClass(int col)
	{
		switch (col)
		{
		case 0:
		case 1:
			return String.class;
		default:
			return Object.class;
		}
	}
	
	@Override
	public int getColumnCount()
	{
		return 2;
	}
	
	@Override
	public String getColumnName(int column)
	{
		return columnNames.elementAt(column);
	}
	
	@Override
	public int getRowCount()
	{
		return 6;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		return rows[row][column];
	}
	
	@Override
	public void setValueAt(Object value, int row, int column)
	{
		rows[row][column] = value;
	}

}

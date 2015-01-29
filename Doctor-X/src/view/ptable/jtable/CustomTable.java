package view.ptable.jtable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class CustomTable extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	
	private JTable table = null;
	private CustomTableModel model = null;
	
	public CustomTable()
	{
		model = new CustomTableModel();
		
		table = new JTable(model);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		
		table.setDefaultRenderer(String.class, new CustomCellRender());
		table.setDefaultRenderer(Integer.class, new CustomCellRender());
		table.setDefaultRenderer(Float.class, new CustomCellRender());
		
		setViewportView(table);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	public void updateTable()
	{
		for(int i=0; i<6; i++)
		{
			((AbstractTableModel) table.getModel()).fireTableCellUpdated(i, 1);
		}
	}

	/**
	 * @return the table
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table)
	{
		this.table = table;
	}

	/**
	 * @return the model
	 */
	public CustomTableModel getModel()
	{
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(CustomTableModel model)
	{
		this.model = model;
	}
}

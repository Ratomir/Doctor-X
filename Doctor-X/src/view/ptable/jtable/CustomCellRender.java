package view.ptable.jtable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRender extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col)
	{
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
		
		setOpaque(true);
		setForeground(Color.black);
		removeAll();
		setFont(null);
		setText("");
		setHorizontalAlignment(SwingConstants.LEFT);
		
		if (value instanceof String) 
		{
			if(col == 0)
			{
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			else
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			Font font = new Font(value.toString(), Font.PLAIN, 12);
			setText(value.toString());
			setFont(font);
		}
		else if (value instanceof Integer) 
		{
			if(col == 0)
			{
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			else
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			Font font = new Font(value.toString(), Font.PLAIN, 12);
			setText(value.toString());
			setFont(font);
		}
		else if (value instanceof Float) 
		{
			if(col == 0)
			{
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			else
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			Font font = new Font(value.toString(), Font.PLAIN, 12);
			setText(value.toString());
			setFont(font);
		}
		
		return this;
	}
}

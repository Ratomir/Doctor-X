package view;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class TreeRenderer extends DefaultTreeCellRenderer
{
	private static final long serialVersionUID = 1L;

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
			boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		setText(value.toString());		
		this.setBackgroundNonSelectionColor(new Color(240, 240, 240));		
		this.setTextNonSelectionColor(Color.black);
		this.setTextSelectionColor(Color.white);
		this.setBackground(new Color(240, 240, 240));
		this.setBackgroundSelectionColor(Color.blue);
		this.setOpaque(true);	
		setFont(new Font("monospaces", Font.PLAIN, 14));
				
		if(selected)
		{		
			this.setBackground(new Color(153, 204, 255));				
		}
				
		if (value.toString().contains(".xml"))
		{
			setIcon(new ImageIcon("image/xmlicon.png"));
		}		
		else
		{
			setIcon(new ImageIcon("image/foldericon.png"));
		}
		return this;
	}

}

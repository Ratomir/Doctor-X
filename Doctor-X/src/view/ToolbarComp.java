package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Klasa za Toolbar koji sadrzi opcije za rad sa elementima.
 * 
 * @author Ilija Divljan
 *
 */
public class ToolbarComp extends JToolBar 
{
	private JButton defaultButton = new JButton();
	private JComboBox kindCombo = new JComboBox();
	private ColorIcon hueIcon = new ColorIcon(Color.blue);
	private JPopupMenu popup = new JPopupMenu();
	private JButton btnClearAll = new JButton();
	private JButton	btnColor = new JButton();
	
	private int radius;
	
	
	public ToolbarComp()
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.lightGray);
		
		this.add(defaultButton);
		this.add(btnClearAll);
		this.add(kindCombo);
		this.add(btnColor);
		this.add(new JLabel(hueIcon));
		JSpinner js = new JSpinner();
		
		js.setModel(new SpinnerNumberModel(35, 5, 100, 5));
		js.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.add(new JLabel("Size: "));
		this.add(js);
		
		
		
	}
	
	
	private static class ColorIcon implements Icon {

        private static final int WIDE = 20;
        private static final int HIGH = 20;
        private Color color;

        public ColorIcon(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, WIDE, HIGH);
        }

        public int getIconWidth() {
            return WIDE;
        }

        public int getIconHeight() {
            return HIGH;
        }
    }
}

package model.components.interfaces;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Interfejs sa deklaracijom metode za iscrtavanje objekata.
 * 
 * @author Grupa 1
 *
 */
public interface IDrawable 
{
	/** Interfejs funkcija za crtanje objekta.
	    * 
	    * 
	    * @param g Graphics objekat na kojem ce crtanje biti izvrseno. */
	   void draw(Graphics g);
}

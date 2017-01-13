package net.tompy.ast.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import net.tompy.ast.component.EntityDisplay;
import net.tompy.ast.component.EntityList;
import net.tompy.ast.component.EntityObject;
import net.tompy.ast.main.AsteroidConstants;

@SuppressWarnings("serial")
public class PlayPanel extends JPanel 
{
	private BufferedImage bi;
	private List< EntityObject > entities = new ArrayList< EntityObject >();
	private double drawTicks;
	private double updateTicks;

	private List< EntityDisplay > displayList = new ArrayList< EntityDisplay >();
	
	public PlayPanel()
	{
	}

	public void updateStats( double drawTicks, double updateTicks )
	{
		this.drawTicks = drawTicks;
		this.updateTicks = updateTicks;
	}

	public void init()
	{
		try
		{
			bi = ImageIO.read( getClass().getClassLoader().getResourceAsStream( AsteroidConstants.GALAXY ) );
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}		
	}
	
	public void render()
	{
		repaint();
	}
	
	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform oldXForm = g2d.getTransform();
		
		// Background
		g2d.drawImage( bi, 0, 0, null );
		
		//g2d.drawString(String.format( "UPS:%.2f - FPS:%.2f", updateTicks, drawTicks ), 350, 20 );
		
		// Draw entities
		for ( EntityObject ea : entities )
		{
			
			g2d.setTransform(oldXForm);
			ea.draw( g2d );
		}
		
		for ( EntityDisplay ed : displayList )
		{
			g2d.setTransform(oldXForm);
			ed.draw( g2d );
		}
	}
	
	public void addEntity( EntityObject eo )
	{
		entities.add( eo );
	}
	
	public void removeEntity( EntityObject eo )
	{
		entities.remove( eo );
	}
	
	public void removeEntities( List< EntityObject > removeList )
	{
		entities.removeAll( removeList );
	}
	
	public List< EntityObject > getEntities()
	{
		return entities;
	}

	public void setEntities(EntityList entityList) 
	{
		entities.addAll( entityList.getEntityAsteroids() );
		entities.addAll( entityList.getEntityShots() );
	}

	public void setDisplayList(List<EntityDisplay> displayList) {
		this.displayList = displayList;
	}
}

package net.tompy.ast.component;

import java.awt.Graphics2D;

import net.tompy.ggt.component.EntityAbstractImpl;

public class EntityDisplayGameOverImpl extends EntityAbstractImpl implements EntityDisplay 
{

	@Override
	public void draw(Graphics2D g2d) 
	{
		g2d.drawString( "GAME OVER", 380, 280 );
	}

}

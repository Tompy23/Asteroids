package net.tompy.ast.component;

import java.awt.Graphics2D;

import net.tompy.ast.game.Asteroids;
import net.tompy.ggt.component.EntityAbstractImpl;

public class EntityDisplayPlayerImpl extends EntityAbstractImpl implements EntityDisplay 
{
	private Asteroids game;
	
	@Override
	public void draw(Graphics2D g2d) 
	{
		String scoreString = String.format( "Score: %08d", game.getScore() );
		g2d.drawString( String.format( "Score: %08d", game.getScore() ), game.getPaneWidth() - scoreString.length()*10, 20 );
		g2d.drawString( String.format( "Level: %03d", game.getGameLevel() ), 20, 20 ); 
		String nameString = String.format( "%s", game.getPlayerName() );
		g2d.drawString( nameString, game.getPaneWidth()/2 - nameString.length()/2, 20);
		
	}

	public void setGame(Asteroids game) {
		this.game = game;
	}
}

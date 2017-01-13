package net.tompy.ast.game;

import net.tompy.ast.command.Command;
import net.tompy.ast.component.EntityObject;
import net.tompy.gameai.Game;

public interface Asteroids extends Game 
{
	public void showGame();
	public int getPaneWidth();
	public int getPaneHeight();
	public void initEntities();
	public void removeShot( EntityObject shot );
	public void panelRepaint();
	
	public void setShipThrust( boolean st );
	public void setShipRotateRight( boolean srr );
	public void setShipRotateLeft( boolean srl );
	public void setShipFired();
	public void updateEntities();
	
	public void incrementGameLevel();
	public int getGameLevel();
	public void setPlayerName( String playerName );
	public String getPlayerName();
	public void decrementShipCount();
	public int getShipCount();
	public void resetShip();
	
	public void createAsteroids( int count, String type, EntityObject spawn );
	
	public void reloadShot();
	
	public void addScore( long add );
	public long getScore();
	
	public void sendCommand( Command command );
	public void updateStats( long drawTicks, long updateTicks );
	
}


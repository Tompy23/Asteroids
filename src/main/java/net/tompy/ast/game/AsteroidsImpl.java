package net.tompy.ast.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.tompy.ast.command.Command;
import net.tompy.ast.component.EntityFactory;
import net.tompy.ast.component.EntityList;
import net.tompy.ast.component.EntityObject;
import net.tompy.ast.component.EntityShip;
import net.tompy.ast.gui.MainFrame;
import net.tompy.ast.gui.PlayPanel;
import net.tompy.ast.main.AsteroidConstants;
import net.tompy.common.CommonException;
import net.tompy.gameai.GameAbstractImpl;
import net.tompy.gameai.fsm.FSMCreatorGameImpl;
import net.tompy.gameai.fsm.FiniteStateMachine;
import net.tompy.gameai.fsm.State;

public class AsteroidsImpl extends GameAbstractImpl implements Asteroids 
{
	// Game flow state machine
	private FiniteStateMachine< Asteroids > fsm = null;
	//private FSMCreator< Asteroids > fsmCreator = null;
	
	// Game view components
	private MainFrame mainFrame;
	private PlayPanel gamePane;
	private String playerName;
	
	// Game model components
	private EntityShip ship;
	private EntityFactory factory;
	private EntityList entities;
	
	// Command structure
	List< Command > commandList = new ArrayList< Command >();
	
	// Game properties
	private int gameLevel = 0;
	private double gameLevelVelocityFactor = 0.2;
	public long score = 0;
	public int shipCount = 3;
	
	private long startTime = System.currentTimeMillis();
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public void update() throws CommonException 
	{
		if ( fsm.isInState( null ) )
		{
			fsm.changeState( (State< Asteroids >)stateFactory.createState( AsteroidConstants.GAME_INIT ) );
		}
		fsm.update();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void changeState( String newState ) 
	{
		fsm.changeState( (State< Asteroids >)stateFactory.createState( newState ) );
	}

	@Override
	public void updateStats( long drawTicks, long updateTicks )
	{
		long now = System.currentTimeMillis();
		
		double dDTicks = drawTicks/( now - startTime + 1 )*1000;
		double dUTicks = updateTicks/( now - startTime + 1 )*1000;
		
		gamePane.updateStats( dDTicks, dUTicks );
	}
	
	public void init()
	{
		fsm = new FSMCreatorGameImpl< Asteroids >().create( this );
		
		ship = factory.getShip();

		gamePane.init();		
		mainFrame.init();
	}
	
	@Override
	public void showGame()
	{
		mainFrame.setVisible( true );
	}
	
	@Override
	public int getPaneWidth()
	{
		return gamePane.getWidth();
	}
	
	@Override
	public int getPaneHeight()
	{
		return gamePane.getHeight();
	}
	
	@Override
	public void panelRepaint()
	{
		gamePane.render();
	}
	
	@Override
	public void initEntities()
	{
		createAsteroids( gameLevel/2 + 4, AsteroidConstants.ENTITY_ASTEROID_LARGE, null );
		gamePane.setEntities( entities );
		gamePane.getEntities().add( ship );
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public void sendCommand( Command command )
	{
		commandList.add( command );
	}
	
	@Override
	public void createAsteroids( int count, String type, EntityObject spawn )
	{
		for( int i = 0; i < count; i++ )
		{
			EntityObject newAsteroid = createNewAsteroid( type, spawn );
			entities.addAsteroid( newAsteroid );
			gamePane.addEntity( newAsteroid );
		}
	}
	
	private EntityObject createNewAsteroid( String type, EntityObject spawn )
	{
		Random rand = new Random( System.currentTimeMillis() );
		EntityObject newAsteroid = factory.getAsteroid( type );
		
		if ( null != spawn )
		{
			newAsteroid.setX( Math.abs( rand.nextGaussian() * 10 ) + spawn.getX() );
			newAsteroid.setY( Math.abs( rand.nextGaussian() * 10 ) + spawn.getY() );
			newAsteroid.setDirection( rand.nextGaussian() * 2 * Math.PI );
		}
		else
		{
			if ( rand.nextGaussian() >= 0.0 )
			{
				newAsteroid.setX( Math.abs( rand.nextGaussian() * gamePane.getWidth() ) );
				newAsteroid.setY( -1 * newAsteroid.getHeight() );
			}
			else
			{
				newAsteroid.setY( Math.abs( rand.nextGaussian() * gamePane.getHeight() ) );
				newAsteroid.setX( -1 * newAsteroid.getWidth() );
			}
			newAsteroid.setDirection( rand.nextGaussian() * 2 * Math.PI );
		}
		newAsteroid.setRotation( rand.nextGaussian() * 2 * Math.PI );
		newAsteroid.setRotationVelocity( newAsteroid.getRotationVelocity() * Math.abs( rand.nextGaussian() ) );
		newAsteroid.setVelocity( newAsteroid.getVelocity() * gameLevel * (1+gameLevelVelocityFactor) );
		
		return newAsteroid;
	}
	
	@Override
	public void incrementGameLevel()
	{
		gameLevel++;
	}
	
	@Override
	public void decrementShipCount()
	{
		shipCount--;
		if ( 0 == shipCount )
		{
			this.changeState( AsteroidConstants.GAME_OVER );
		}
	}
	
	@Override
	public void resetShip()
	{
		Random rand = new Random( System.currentTimeMillis() );
		boolean placed = false;
		int newX = 400;
		int newY = 300;
		
		while ( ! placed )
		{
			boolean canPlace = true;
			for ( EntityObject asteroid : entities.getEntityAsteroids() )
			{
				if ( asteroid.getX() >= newX + 100 
					&& asteroid.getX() <= newX - 100 
					&& asteroid.getY() >= newY + 100 
					&& asteroid.getY() <= newY - 100 )
				{
					canPlace = false;
					break;
				}
			}
			placed = canPlace;
			if ( ! placed )
			{
				do 
				{
					newX += 50 * rand.nextGaussian();
				} 	while ( newX >= 800 || newX <=0 );
				
				do
				{
					newY += 50 * rand.nextGaussian();
				} while ( newY >=600 || newY <=0 );
			}
		}
		ship.setX( newX );
		ship.setY( newY );
		ship.setVelocity( 0.0 );
		ship.setRotation( 0.0 );
	}
	
	@Override
	public void addScore( long add )
	{
		score += add;
	}
	
	@Override
	public void setShipThrust(boolean st) 
	{
		ship.setThrust( st );
	}

	@Override
	public void setShipRotateRight(boolean srr)
	{
		ship.setRotateRight( srr );
	}

	@Override
	public void setShipRotateLeft(boolean srl)
	{
		ship.setRotateLeft( srl );
	}
	
	@Override
	public void updateEntities()
	{
		List< EntityObject > removeShots = new ArrayList< EntityObject >();
		
		try
		{
			// First take care of any commands
			for ( Command c : commandList )
			{
				c.execute();
			}
			commandList.clear();
			
			// Update the attributes
			ship.update();
			
			for ( EntityObject e : entities.getEntityShots() )
			{
				e.update();
			}
			entities.removeAllShots( removeShots );
			gamePane.removeEntities( removeShots );
			
			if ( ! entities.getEntityAsteroids().isEmpty() )
			{
				for ( EntityObject e : entities.getEntityAsteroids() )
				{
					e.update();
				}
			}
			else
			{
				this.changeState( AsteroidConstants.GAME_INCREMENT_LEVEL );
			}
			
			// Check for collisions
			collisions();
			
			// Check for entities that wish to be removed
			handleEntityObjectRemoval();
		}
		catch( CommonException ce )
		{
			ce.printStackTrace();
		}
	}
	
	private void collisions()
	{
		Rectangle shipRect = new Rectangle( (int)ship.getX(), (int)ship.getY(), ship.getWidth(), ship.getHeight() );
		for ( EntityObject asteroid : entities.getEntityAsteroids() )
		{
			Rectangle asteroidRect = new Rectangle( (int)asteroid.getX(), (int)asteroid.getY(), asteroid.getWidth(), asteroid.getHeight() );
			if ( asteroidRect.intersects( shipRect ) )
			{
				//System.exit( 5 );
				this.changeState( AsteroidConstants.GAME_RESET );
			}
			for ( EntityObject shot : entities.getEntityShots() )
			{
				Rectangle shotRect = new Rectangle( (int)shot.getX(), (int)shot.getY(), shot.getWidth(), shot.getHeight() );
				if ( shotRect.intersects( asteroidRect ) )
				{
					shot.setRemove( true );
					ship.reloadShot();
					asteroid.changeState( AsteroidConstants.ASTEROID_EXPLODE);
				}
			}
		}
	}
	
//	public boolean collide( Rectangle collidable, BufferedImage bi, Rectangle collidable2, BufferedImage bi2 ) 
//	{
//	    // get the overlapping box
//	    int startX = Math.max( (int)collidable.getX(), (int)collidable2.getX() );
//	    int endX = Math.min( (int)collidable.getX() + collidable.width, (int)collidable2.getX() + collidable2.width );
//	
//	    int startY = Math.max( (int)collidable.getY(), (int)collidable2.getY() );
//	    int endY = Math.min( (int)collidable.getY() + collidable.height, (int)collidable2.getY() + collidable2.height );
//	
//	    for( int y = startY ; y < endY ; y++ ) 
//	    {
//	        for( int x = startX ; x < endX ; x++ ) 
//	        {
//	            // compute offsets for surface
//	            if( ( ! isTransparent( bi2, x - (int)collidable2.getX(), y - (int)collidable2.getY() ) )
//	                    && (!isTransparent(bi, x - (int)collidable.getX(), y - (int)collidable.getY() ) ) ) 
//	            {
//	                return true;
//	            }
//	        }
//	    }
//	    return false;
//	}
//	
//	private boolean isTransparent(BufferedImage bufferedImage, int x, int y) {
//	    int pixel = bufferedImage.getRGB(x, y);
//	    if((pixel & 0xFF000000) == 0x00000000) {
//	        return true;
//	    }
//	    return false;
//	}
//	
	
	private void handleEntityObjectRemoval()
	{
		List< EntityObject > removeList = new ArrayList< EntityObject >();
		for ( EntityObject eo : entities.getEntityAll() )
		{
			if ( eo.isRemove() )
			{
				removeList.add( eo );
			}
		}	
		entities.removeAll( removeList );
		gamePane.removeEntities( removeList );
	}
	
	@Override
	public void setShipFired()
	{
		EntityObject shot = factory.getShot();
		if ( null != ( shot = ship.fire( shot ) ) )
		{
			entities.addShot( shot );
			gamePane.addEntity( shot );
		}
	}
	
	@Override
	public void reloadShot()
	{
		ship.reloadShot();
	}
	
	@Override
	public void removeShot( EntityObject shot )
	{
		entities.removeShot( shot );
		gamePane.removeEntity( shot );
	}
	
	public FiniteStateMachine<Asteroids> getFsm() {
		return fsm;
	}

	public void setFsm(FiniteStateMachine<Asteroids> fsm) {
		this.fsm = fsm;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public PlayPanel getGamePane() {
		return gamePane;
	}

	public void setGamePane(PlayPanel gamePane) {
		this.gamePane = gamePane;
	}

	public EntityShip getShip() {
		return ship;
	}

	public void setShip(EntityShip ship) {
		this.ship = ship;
	}

	public EntityFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityFactory factory) {
		this.factory = factory;
	}

	public EntityList getEntities() {
		return entities;
	}

	public void setEntities(EntityList entities) {
		this.entities = entities;
	}

	public int getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}

	public double getGameLevelVelocityFactor() {
		return gameLevelVelocityFactor;
	}

	public void setGameLevelVelocityFactor(double gameLevelVelocityFactor) {
		this.gameLevelVelocityFactor = gameLevelVelocityFactor;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getShipCount() {
		return shipCount;
	}

	public void setShipCount(int shipCount) {
		this.shipCount = shipCount;
	}



}

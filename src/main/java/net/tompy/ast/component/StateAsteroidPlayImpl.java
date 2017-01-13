package net.tompy.ast.component;

import net.tompy.ast.game.Asteroids;
import net.tompy.gameai.fsm.State;

public class StateAsteroidPlayImpl implements State<EntityObject> 
{
	private Asteroids game;
	
	@Override
	public void execute(EntityObject me) 
	{
		me.setRotation( me.getRotation() + me.getRotationVelocity() );
		me.move();
		if ( me.getX() < -1 * me.getWidth() )
		{
			me.setX( game.getPaneWidth() );
		}
		if ( me.getX() > game.getPaneWidth() )
		{
			me.setX( -1 * me.getWidth() );
		}
		if ( me.getY() < -1 * me.getHeight() )
		{
			me.setY( game.getPaneHeight() );
		}
		if ( me.getY() > game.getPaneHeight() )
		{
			me.setY( -1 * me.getHeight() );
		}			
	}

	@Override
	public void enter(EntityObject me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(EntityObject me) {
		// TODO Auto-generated method stub

	}

	public void setGame(Asteroids game) {
		this.game = game;
	}

}

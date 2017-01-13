package net.tompy.ast.game;

import net.tompy.ast.main.AsteroidConstants;
import net.tompy.gameai.fsm.State;

public class StateAsteroidsResetImpl implements State<Asteroids> 
{
	private long start;
	private long gameResetDuration = 3;
	
	@Override
	public void execute(Asteroids me) 
	{
		long now = System.currentTimeMillis();
		if ( now - start >= gameResetDuration * 1000 )
		{
			me.resetShip();
			me.changeState( AsteroidConstants.GAME_PLAY );
		}
	}

	@Override
	public void enter(Asteroids me) 
	{
		start = System.currentTimeMillis();
		me.decrementShipCount();
	}

	@Override
	public void exit(Asteroids me) {
		// TODO Auto-generated method stub

	}

	public void setGameResetDuration(long gameResetDuration) {
		this.gameResetDuration = gameResetDuration;
	}

}

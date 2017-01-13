package net.tompy.ast.game;

import net.tompy.gameai.fsm.State;

// This state is the start of the game.  Resets values.  And in network mode establishes
// the players and waits for the host to click start.

public class StateAsteroidsStartImpl implements State<Asteroids> 
{
	private long accumulatedTime = 0;
	private int updateDuration = 0;

	@Override
	public synchronized void execute(Asteroids me) 
	{
		long startTime = System.currentTimeMillis();
		
		me.panelRepaint();

		if ( accumulatedTime >= updateDuration )
		{
			me.updateEntities();
			accumulatedTime -= updateDuration;
		}
		
		accumulatedTime += System.currentTimeMillis() - startTime;
		
			// Change the state to Play
//		if ( 0 == countdown )
//		{
//			me.changeState( AsteroidConstants.GAME_PLAY );
//		}
	}

	@Override
	public void enter(Asteroids me) 
	{
		// Start countdown
		//me.initEntities();
	}

	@Override
	public void exit(Asteroids me) 
	{
		// Remove countdown entity
		//me.removeCountdownEntity();
		me.initEntities();		

		// Start the keyboard listener
	}

	public int getUpdateDuration() {
		return updateDuration;
	}

	public void setUpdateDuration(int updateDuration) {
		this.updateDuration = updateDuration;
	}
}

package net.tompy.ast.game;

import net.tompy.ast.main.AsteroidConstants;
import net.tompy.gameai.fsm.State;

public class StateAsteroidsGameOverImpl implements State<Asteroids> 
{
	private long start = 0;
	
	@Override
	public void execute(Asteroids me) 
	{
		long now = System.currentTimeMillis();
		
		if ( now - start > 5000 )
		{
			me.changeState( AsteroidConstants.GAME_START );
		}
	}

	@Override
	public void enter(Asteroids me) 
	{	
		start = System.currentTimeMillis();
		// Add a "Add Display Entity" command
	}

	@Override
	public void exit(Asteroids me) {
		// TODO Auto-generated method stub

	}

}

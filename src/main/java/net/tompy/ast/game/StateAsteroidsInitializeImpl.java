package net.tompy.ast.game;

import net.tompy.gameai.fsm.State;

// This class executes only 1 time per execution.  It initializes the game and the graphics.
// To start a new game we go to the Start state.
// 
// 0. Create the UI components
// 1. Get player's name
// 2. Get other stats.
// 3. Go to Start Game State
public class StateAsteroidsInitializeImpl implements State<Asteroids> 
{
	@Override
	public synchronized void execute(Asteroids me) 
	{
		me.panelRepaint();
		try
		{
			this.wait( 30 );
		}
		catch ( InterruptedException ie )
		{
			// NOP
		}
		//me.changeState( AsteroidConstants.GAME_START );
	}

	@Override
	public void enter(Asteroids me) 
	{
		me.showGame();
	}

	@Override
	public void exit(Asteroids me) {
		// TODO Auto-generated method stub
		me.initEntities();
		
	}
}

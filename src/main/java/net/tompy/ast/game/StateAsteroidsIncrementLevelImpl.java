package net.tompy.ast.game;

import net.tompy.ast.main.AsteroidConstants;
import net.tompy.gameai.fsm.State;

public class StateAsteroidsIncrementLevelImpl implements State<Asteroids> {

	@Override
	public void execute(Asteroids me) {
		// TODO Auto-generated method stub

		//me.changeState( AsteroidConstants.GAME_PLAY );
	}

	@Override
	public void enter(Asteroids me) 
	{
		me.incrementGameLevel();
		me.initEntities();
		me.changeState( AsteroidConstants.GAME_PLAY );
	}

	@Override
	public void exit(Asteroids me) {
		// TODO Auto-generated method stub

	}

}

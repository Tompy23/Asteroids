package net.tompy.ast.component;

import net.tompy.ast.command.CreateAsteroidsCommand;
import net.tompy.ast.game.Asteroids;
import net.tompy.gameai.fsm.State;

public class StateAsteroidExplodeImpl implements State<EntityObject>
{
	private Asteroids game;

	@Override
	public void execute(EntityObject me) 
	{
	}

	@Override
	public void enter(EntityObject me) 
	{
		game.addScore( me.getPoints() );
		me.setRemove( true );
		if ( me.getNextObjectName().length() > 0 )
		{
			game.sendCommand( new CreateAsteroidsCommand( game, 2, me.getNextObjectName(), me ) );
		}
	}

	@Override
	public void exit(EntityObject me) {
		// TODO Auto-generated method stub

	}

	public void setGame(Asteroids game) {
		this.game = game;
	}

}

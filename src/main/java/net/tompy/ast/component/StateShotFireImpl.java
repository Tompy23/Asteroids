package net.tompy.ast.component;

import net.tompy.ast.game.Asteroids;
import net.tompy.gameai.fsm.State;

public class StateShotFireImpl implements State<EntityObject> 
{
	private Asteroids game;
	
	@Override
	public void execute(EntityObject me) 
	{
		me.move();
		if ( me.getX() > game.getPaneWidth() 
				|| me.getX() < 0.0 
				|| me.getY() > game.getPaneHeight()
				|| me.getY() < 0.0 )
		{
			me.setRemove( true );
			game.reloadShot();
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

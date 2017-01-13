package net.tompy.ast.game;

import net.tompy.gameai.fsm.State;

// This state is the entire game.  Once we are in this state, we stay until ALL players are dead
// or quit.

public class StateAsteroidsPlayImpl implements State<Asteroids> 
{
	private long accumulatedTime = 0;
	private long updateDuration = 40;
	private long updateTicks = 0;
	private long drawTicks = 0;
	
	@Override
	public synchronized void execute(Asteroids me) 
	{
		long startTime = System.currentTimeMillis();
		
		me.panelRepaint();
		drawTicks++;

		if ( accumulatedTime >= updateDuration )
		{
			me.updateEntities();
			accumulatedTime -= updateDuration;
			updateTicks++;
		}
		
		accumulatedTime += System.currentTimeMillis() - startTime;
		me.updateStats( drawTicks, updateTicks );
	}

	@Override
	public void enter(Asteroids me) 
	{
		//me.initEntities();		
	}

	@Override
	public void exit(Asteroids me) {
		// TODO Auto-generated method stub
		
	}

	public long getUpdateDuration() {
		return updateDuration;
	}

	public void setUpdateDuration(long updateDuration) {
		this.updateDuration = updateDuration;
	}
}

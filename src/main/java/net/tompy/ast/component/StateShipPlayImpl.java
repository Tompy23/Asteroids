package net.tompy.ast.component;

import net.tompy.gameai.fsm.State;

public class StateShipPlayImpl implements State<EntityShip> 
{

	@Override
	public void execute(EntityShip me) 
	{
		me.move();
	}

	@Override
	public void enter(EntityShip me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(EntityShip me) {
		// TODO Auto-generated method stub

	}

}

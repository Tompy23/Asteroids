package net.tompy.ast.component;

import net.tompy.gameai.fsm.FSMCreator;
import net.tompy.gameai.fsm.FiniteStateMachine;
import net.tompy.gameai.fsm.FiniteStateMachineBasicImpl;

public class FSMCreatorShipImpl implements FSMCreator<EntityShip> 
{

	@Override
	public FiniteStateMachine<EntityShip> create(EntityShip owner) 
	{
		FiniteStateMachine< EntityShip > returnValue = new FiniteStateMachineBasicImpl< EntityShip >();
		
		returnValue.setOwner( owner );
		
		return returnValue;
	}

}

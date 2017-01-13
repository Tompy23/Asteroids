package net.tompy.ast.game;

import net.tompy.gameai.fsm.FSMCreator;
import net.tompy.gameai.fsm.FiniteStateMachine;
import net.tompy.gameai.fsm.FiniteStateMachineBasicImpl;

public class FSMCreatorGameImpl implements FSMCreator<Asteroids> 
{
	@Override
	public FiniteStateMachine<Asteroids> create( Asteroids owner ) 
	{
		FiniteStateMachine< Asteroids > returnValue = new FiniteStateMachineBasicImpl< Asteroids >();
		
		returnValue.setOwner( owner );
		
		return returnValue;
	}

}

package net.tompy.ast.component;

import net.tompy.gameai.fsm.FSMCreator;
import net.tompy.gameai.fsm.FiniteStateMachine;
import net.tompy.gameai.fsm.FiniteStateMachineBasicImpl;

public class FSMCreatorEntityObjectImpl implements FSMCreator<EntityObject> {

	@Override
	public FiniteStateMachine<EntityObject> create(EntityObject owner) 
	{
		FiniteStateMachine< EntityObject > returnValue = new FiniteStateMachineBasicImpl< EntityObject >();
		
		returnValue.setOwner( owner );
		
		return returnValue;
	}

}

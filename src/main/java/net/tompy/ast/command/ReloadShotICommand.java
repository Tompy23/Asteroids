package net.tompy.ast.command;

import net.tompy.ast.component.EntityShip;

public class ReloadShotICommand implements Command 
{
	private EntityShip ship;
	
	public ReloadShotICommand( EntityShip ship )
	{
		this.ship = ship;
	}
	@Override
	public void execute() 
	{
		ship.reloadShot();
	}

}

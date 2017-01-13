package net.tompy.ast.command;

import net.tompy.ast.component.EntityObject;
import net.tompy.ast.game.Asteroids;

public class CreateAsteroidsCommand implements Command 
{
	private Asteroids game;
	private int count;
	private String type;
	private EntityObject spawn;
	
	public CreateAsteroidsCommand( Asteroids game, int count, String type, EntityObject spawn )
	{
		this.game = game;
		this.count = count;
		this.type = type;
		this.spawn = spawn;
	}
	
	@Override
	public void execute() 
	{
		game.createAsteroids( count, type, spawn );
	}
}

package net.tompy.ast.component;

import java.awt.Graphics2D;

import net.tompy.ast.game.Asteroids;
import net.tompy.ggt.component.EntityAbstractImpl;

public class EntityDisplayGameStatsImpl extends EntityAbstractImpl implements EntityDisplay 
{
	private Asteroids game;
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	public void setGame(Asteroids game) {
		this.game = game;
	}

}

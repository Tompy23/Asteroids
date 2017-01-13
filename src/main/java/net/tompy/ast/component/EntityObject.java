package net.tompy.ast.component;

import net.tompy.gameai.fsm.StateUpdateable;
import net.tompy.ggt.component.Drawable;
import net.tompy.ggt.component.Entity;
import net.tompy.ggt.component.Movable;
import net.tompy.ggt.component.Sprite;

public interface EntityObject extends Entity, StateUpdateable, Movable, Drawable
{
	public void setSprite( Sprite s );
	public Sprite getSprite();
	public void setX( double x );
	public double getX();
	public void setY( double y );
	public double getY();
	public int getWidth();
	public int getHeight();
	public void setVelocity( double velocity );
	public double getVelocity();
	public void setRotation( double rotation );
	public double getRotation();
	public void setRotationVelocity( double rotationVelocity );
	public double getRotationVelocity();
	public void setDirection( double direction );
	public double getDirection();
	public void setRemove( boolean remove );
	public boolean isRemove();
	public String getNextObjectName();
	public int getPoints();
}

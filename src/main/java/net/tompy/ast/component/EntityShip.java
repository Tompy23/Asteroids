package net.tompy.ast.component;


public interface EntityShip extends EntityObject
{
	public void setThrust( boolean t );
	public void setRotateRight( boolean rr );
	public void setRotateLeft( boolean rl );
	
	public EntityObject fire( EntityObject shot );
	public void reloadShot();
	
}

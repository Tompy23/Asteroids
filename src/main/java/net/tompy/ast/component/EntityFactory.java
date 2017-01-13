package net.tompy.ast.component;

import net.tompy.ast.main.AsteroidConstants;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class EntityFactory implements ApplicationContextAware
{
	private ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException 
	{
		ctx = arg0;
	}
	
	public EntityShip getShip()
	{
		return (EntityShip)ctx.getBean( AsteroidConstants.ENTITY_SHIP );
	}
	
	public EntityObject getShot()
	{
		return (EntityObject)ctx.getBean( AsteroidConstants.ENTITY_SHOT );
	}
	
	public EntityObject getAsteroid( String type )
	{
		return (EntityObject)ctx.getBean( type );
	}
}

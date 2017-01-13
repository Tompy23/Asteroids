package net.tompy.ast.component;

import java.util.ArrayList;
import java.util.List;

public class EntityList 
{
	private List< EntityObject > entityShots = new ArrayList< EntityObject >();
	private List< EntityObject > entityAsteroids = new ArrayList< EntityObject >();
	private List< EntityObject > entityAll = new ArrayList< EntityObject >();
	
	public void addShot( EntityObject shot )
	{
		entityShots.add( shot );
		entityAll.add( shot );
	}
	
	public void addAsteroid( EntityObject asteroid )
	{
		entityAsteroids.add( asteroid );
		entityAll.add( asteroid );
	}
	
	public void removeShot( EntityObject shot )
	{
		entityShots.remove( shot );
		entityAll.remove( shot );
	}
	
	public void removeAllShots( List< EntityObject > list )
	{
		entityShots.removeAll( list );
		entityAll.removeAll( list );
	}
	
	public void removeAsteroid( EntityObject asteroid )
	{
		entityAsteroids.remove( asteroid );
		entityAll.remove( asteroid );
	}
	
	public void removeAll( List< EntityObject> list )
	{
		entityShots.removeAll( list );
		entityAsteroids.removeAll( list );
		entityAll.removeAll( list );
	}

	public List<EntityObject> getEntityShots() 
	{
		return entityShots;
	}

	public List<EntityObject> getEntityAsteroids() 
	{
		return entityAsteroids;
	}

	public List<EntityObject> getEntityAll() {
		return entityAll;
	}

}

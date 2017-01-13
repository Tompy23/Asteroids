package net.tompy.ast.main;

public class AsteroidConstants
{
	// States
	public static final String GAME_INIT = "gameInit";
	public static final String GAME_START = "gameStart";
	public static final String GAME_PLAY = "gamePlay";
	public static final String GAME_RESET = "gameReset";
	public static final String GAME_OVER = "gameOver";
	public static final String GAME_INCREMENT_LEVEL = "gameIncrementLevel";
	public static final String SHIP_PLAY = "shipPlay";
	public static final String SHIP_EXPLODE = "shipExplode";
	public static final String SHOT_FIRE = "shotFire";
	public static final String ASTEROID_PLAY = "asteroidPlay";
	public static final String ASTEROID_EXPLODE = "asteroidExplode";
	
	// Sprites
	public static final int SHIP_THRUST_ROW = 2;
	public static final int SHIP_NOTHRUST_ROW = 1;
	
	// Resources
	public static final String GALAXY = "images/Galaxy.png";
	public static final String SHOT = "images/shot.png";
	
	// Entities
	public static final String ENTITY_COUNTDOWN = "countdown";
	public static final String ENTITY_SHIP = "ship";
	public static final String ENTITY_SHOT = "shot";
	public static final String ENTITY_ASTEROID_LARGE = "asteroidLarge";
	public static final String ENTITY_ASTEROID_MEDIUM = "asteroidMedium";
	public static final String ENTITY_ASTEROID_SMALL = "asteroidSmall";
}

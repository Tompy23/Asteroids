package net.tompy.ast.component;

import java.awt.Graphics2D;

import net.tompy.ast.main.AsteroidConstants;
import net.tompy.common.CommonException;
import net.tompy.gameai.fsm.FSMCreator;
import net.tompy.gameai.fsm.FiniteStateMachine;
import net.tompy.gameai.fsm.State;
import net.tompy.ggt.component.Sprite;

public class EntityShipImpl extends EntityObjectImpl implements EntityShip 
{
	private FiniteStateMachine< EntityShip > fsm = null;
	private FSMCreator< EntityShip > fsmCreator = null;

	private boolean thrust;
	private boolean rotateRight;
	private boolean rotateLeft;
	private double power = 0.0;
	private long timerstart = System.currentTimeMillis();
	
	private int shots = 0;
	
	private int maxShots = 3;
	private int shotCellWidth = 5;
	private int shotCellHeight = 5;
	private int spriteChangeDuration = 70;
	private double powerFactor = .1;
	private double powerMax = .3;
	private double velocityMax = 12;
	private double velocitySlowdown = .002;
	private int frameWidth = 800;
	private int frameHeight = 600;
	
	public void init()
	{
		fsm = fsmCreator.create( this );
	}
	
	@Override
	public void draw( Graphics2D g2d )
	{

		sprite.setRow( thrust ? AsteroidConstants.SHIP_THRUST_ROW : AsteroidConstants.SHIP_NOTHRUST_ROW );
		sprite.rotate( rotation );
		
		sprite.draw( g2d, (int)x, (int)y );		
	}
	
	@Override
	public EntityObject fire( EntityObject shot )
	{
		if ( shots < maxShots )
		{
			shots++;
			shot.setRotation( rotation );
			shot.setDirection( rotation );
			shot.setX( x + shotCellWidth + Math.cos( rotation )*shotCellWidth );
			shot.setY( y + shotCellHeight + Math.sin( rotation )*shotCellHeight );
		}
		else
		{
			shot = null;
		}
		
		return shot;
	}
	
	@Override
	public void reloadShot()
	{
		shots--;
		if ( shots < 0 )
		{
			shots = 0;
		}
	}
	
	@Override
	public void move()
	{
		if ( System.currentTimeMillis() - timerstart > spriteChangeDuration )
		{
			sprite.incrementColumn();
			timerstart = System.currentTimeMillis();
		}
		
		// If rotation is happening, we change it here.
		rotation += rotateRight ? rotationVelocity : 0;
		rotation += rotateLeft ? -1*rotationVelocity : 0;
		
		if ( rotation > Math.PI )
		{
			rotation -= Math.PI * 2;
		}
		if ( rotation < -1 * Math.PI )
		{
			rotation += Math.PI * 2;
		}
		
		// If there is thrust then velocity and direction can change, and thus the deltas
		// A is the current direction
		// B is the new rotation (if any)
		if ( thrust )
		{
			// First, calculate current power
			power += powerFactor;
			if ( power > powerMax )
			{
				power = powerMax;
			}
		}
		else
		{
			// No longer thrusting, so cut power
			power = 0.0;
		}
			
		// Calculate vectors
		double Ax = Math.cos( direction ) * velocity;
		double Ay = Math.sin( direction ) * velocity;
		double Bx = Math.cos( rotation ) * power;
		double By = Math.sin( rotation ) * power;
		double Rx = Ax + Bx;
		double Ry = Ay + By;
		
		direction = Math.atan2( Ry, Rx );
		velocity = Math.sqrt( Rx*Rx + Ry*Ry );
		if ( velocity > velocityMax )
		{
			velocity = velocityMax;
		}
		
		deltax = Math.cos( direction ) * velocity;
		deltay = Math.sin( direction ) * velocity;
		
		x += deltax;
		y += deltay;
		
		if ( x < 0 )
		{
			x = frameWidth;
		}
		if ( x > frameWidth )
		{
			x = 0;
		}
		if ( y < 0 )
		{
			y = frameHeight;
		}
		if ( y > frameHeight )
		{
			y = 0;
		}
		
		// Here we have a slowdown effect
		velocity -= velocitySlowdown;
		if ( velocity < 0.0 )
		{
			velocity = 0.0;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update() throws CommonException 
	{
		if ( fsm.isInState( null ) )
		{
			fsm.changeState( (State< EntityShip >)stateFactory.createState( AsteroidConstants.SHIP_PLAY ) );
		}
		fsm.update();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void changeState(String newState) 
	{
		fsm.changeState( (State< EntityShip >)stateFactory.createState( newState ) );
	}
	
	public boolean isThrust() {
		return thrust;
	}
	public void setThrust(boolean thrust) {
		this.thrust = thrust;
	}
	public boolean isRotateRight() {
		return rotateRight;
	}
	public void setRotateRight(boolean rotateRight) {
		this.rotateRight = rotateRight;
	}
	public boolean isRotateLeft() {
		return rotateLeft;
	}
	public void setRotateLeft(boolean rotateLeft) {
		this.rotateLeft = rotateLeft;
	}
	


	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public double getDeltax() {
		return deltax;
	}

	public void setDeltax(double deltax) {
		this.deltax = deltax;
	}

	public double getDeltay() {
		return deltay;
	}

	public void setDeltay(double deltay) {
		this.deltay = deltay;
	}

	public double getRotation() {
		return rotation;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public int getMaxShots() {
		return maxShots;
	}

	public void setMaxShots(int maxShots) {
		this.maxShots = maxShots;
	}

	public int getShotCellWidth() {
		return shotCellWidth;
	}

	public void setShotCellWidth(int shotCellWidth) {
		this.shotCellWidth = shotCellWidth;
	}

	public int getShotCellHeight() {
		return shotCellHeight;
	}

	public void setShotCellHeight(int shotCellHeight) {
		this.shotCellHeight = shotCellHeight;
	}

	public int getSpriteChangeDuration() {
		return spriteChangeDuration;
	}

	public void setSpriteChangeDuration(int spriteChangeDuration) {
		this.spriteChangeDuration = spriteChangeDuration;
	}

	public double getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}

	public double getPowerMax() {
		return powerMax;
	}

	public void setPowerMax(double powerMax) {
		this.powerMax = powerMax;
	}

	public double getVelocityMax() {
		return velocityMax;
	}

	public void setVelocityMax(double velocityMax) {
		this.velocityMax = velocityMax;
	}

	public double getVelocitySlowdown() {
		return velocitySlowdown;
	}

	public void setVelocitySlowdown(double velocitySlowdown) {
		this.velocitySlowdown = velocitySlowdown;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public void setFsmCreator(FSMCreator<EntityShip> fsmCreator) {
		this.fsmCreator = fsmCreator;
	}
}

package net.tompy.ast.component;

import java.awt.Graphics2D;

import net.tompy.common.CommonException;
import net.tompy.gameai.fsm.FSMCreator;
import net.tompy.gameai.fsm.FiniteStateMachine;
import net.tompy.gameai.fsm.State;
import net.tompy.gameai.fsm.StateFactory;
import net.tompy.ggt.component.EntityAbstractImpl;
import net.tompy.ggt.component.Sprite;

public class EntityObjectImpl extends EntityAbstractImpl implements EntityObject 
{
	protected FiniteStateMachine< EntityObject > fsmEntity = null;
	protected FSMCreator< EntityObject > fsmCreatorEntity = null;
	protected String initialState = "";
	protected StateFactory stateFactory = null;

	protected Sprite sprite = null;
	protected double deltax = 0.0;
	protected double deltay = 0.0;
	protected double rotation = 0.0;
	protected double rotationVelocity = .07;
	protected double direction = 0.0;
	protected double x = 0.0;
	protected double y = 0.0;
	protected double velocity = 0.0;
	protected int width = 0;
	protected int height = 0;
	
	protected boolean remove = false;
	protected String nextObjectName = "";
	protected int points = 0;


	public void init()
	{
		fsmEntity = fsmCreatorEntity.create( this );
		width = sprite.getCellWidth();
		height = sprite.getCellHeight();
	}
	
	@Override
	public void move() 
	{
		deltax = Math.cos( direction ) * velocity;
		deltay = Math.sin( direction ) * velocity;
		
		x += deltax;
		y += deltay;
	}

	@Override
	public void draw(Graphics2D g2d) 
	{
		sprite.rotate( rotation );
		sprite.draw( g2d, (int)x, (int)y );
	}

	@SuppressWarnings("unchecked")
	@Override
	public void changeState(String newState) 
	{
		fsmEntity.changeState( (State< EntityObject >)stateFactory.createState( newState ) );
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update() throws CommonException
	{
		if ( fsmEntity.isInState( null ) )
		{
			fsmEntity.changeState( (State< EntityObject >)stateFactory.createState( initialState ) );
		}
		fsmEntity.update();
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

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public int getWidth()
	{
		return sprite.getCellWidth();
	}
	
	public int getHeight()
	{
		return sprite.getCellHeight();
	}

	public void setStateFactory(StateFactory stateFactory) {
		this.stateFactory = stateFactory;
	}

	public void setFsmCreatorEntity(FSMCreator<EntityObject> fsmCreatorEntity) {
		this.fsmCreatorEntity = fsmCreatorEntity;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public String getNextObjectName() {
		return nextObjectName;
	}

	public void setNextObjectName(String nextObjectName) {
		this.nextObjectName = nextObjectName;
	}

	public double getRotationVelocity() {
		return rotationVelocity;
	}

	public void setRotationVelocity(double rotationVelocity) {
		this.rotationVelocity = rotationVelocity;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Sprite getSprite() {
		return sprite;
	}
}

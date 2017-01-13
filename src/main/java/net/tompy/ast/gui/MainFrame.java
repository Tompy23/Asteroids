package net.tompy.ast.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

import net.tompy.ast.game.Asteroids;
import net.tompy.ast.main.AsteroidConstants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
{
	private Asteroids game;
	private PlayPanel gamePane;
	private JMenuItem mntmEnterParams = new JMenuItem("Enter Parameters...");
	private JMenuItem mntmBeginGame = new JMenuItem("Begin");
	
	/**
	 * Create the frame.
	 */
	public MainFrame() 
	{
	}
	
	public void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable( false );
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Game");
		menuBar.add(mnNewMenu);
		
		mntmEnterParams.setActionCommand( Actions.SHOW_PARAM_DIALOG );
		mntmEnterParams.addActionListener( this );
		mnNewMenu.add(mntmEnterParams);
		
		mntmBeginGame.setEnabled(false);
		mntmBeginGame.setActionCommand( Actions.BEGIN_GAME );
		mntmBeginGame.addActionListener( this );
		mnNewMenu.add(mntmBeginGame);
		
		gamePane.setBorder(new EmptyBorder(1, 1, 1, 1));
		gamePane.setLayout(new BorderLayout(0, 0));
		setContentPane(gamePane);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch ( e.getKeyCode() )
				{
				case ( KeyEvent.VK_LEFT ) :
					game.setShipRotateLeft( true );
					break;
				case ( KeyEvent.VK_RIGHT ) :
					game.setShipRotateRight( true );
					break;
				case( KeyEvent.VK_UP ) :
					game.setShipThrust( true );
					break;
				case ( KeyEvent.VK_SPACE ) :
					game.setShipFired();
					break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch ( e.getKeyCode() )
				{
				case ( KeyEvent.VK_LEFT ) :
					game.setShipRotateLeft( false );
					break;
				case ( KeyEvent.VK_RIGHT ) :
					game.setShipRotateRight( false );
					break;
				case( KeyEvent.VK_UP ) :
					game.setShipThrust( false );
					break;
				}
			}
		});
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch ( e.getActionCommand() )
		{
		case Actions.SHOW_PARAM_DIALOG :
			ParamDialog param = new ParamDialog();
			param.setModal( true );
			param.setVisible( true );
			mntmBeginGame.setEnabled( true );	
			game.setPlayerName( param.getTxtName() );
			break;
			
		case Actions.BEGIN_GAME :
			game.incrementGameLevel();
			game.changeState( AsteroidConstants.GAME_PLAY );
			break;
		}
	}

	public PlayPanel getGamePane() {
		return gamePane;
	}

	public void setGamePane(PlayPanel gamePane) {
		this.gamePane = gamePane;
	}

	public Asteroids getGame() {
		return game;
	}

	public void setGame(Asteroids game) {
		this.game = game;
	}

}

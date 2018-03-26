import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ControlBoard implements ActionListener {
	private JFrame frame = new JFrame();
	private JButton addPlayer = new JButton("Add player");
	private JButton randLeague = new JButton("Randomize league");
	private JButton start = new JButton("Start");
	
	private DefaultListModel<Player> allPlayers = new DefaultListModel<Player>();
	private League<Player> league_1 = new League<Player>(1);
	private League<Player> league_2 = new League<Player>(2);
	
	private JList<Player> playerList = new JList<Player>(allPlayers);
	private JList<Player> leageuOneJList = new JList<Player>(league_1.league);
	private JList<Player> leageuTwoJList = new JList<Player>(league_2.league);
	private JList<Player> serieOneJList = new JList<Player>(league_1.league);
	private JList<Player> serieTwoJList = new JList<Player>(league_2.league);
	
	private JLabel leageuOneLabel = new JLabel("League one");
	private JLabel leageuTwoLabel = new JLabel("League two");
	private JLabel playersLabel = new JLabel("Players");
	
	private JTextField homePlayer = new JTextField(5);
	private JTextField awayPlayer = new JTextField(5);
	
	// Panels
	
	// initial view
	private JPanel settings = new JPanel();
	
	// Game
	private JPanel game = new JPanel();
	
	// Main panels
	private JPanel right = new JPanel();
	private JPanel left = new JPanel();
	
	// Game panels
	private JPanel bracket = new JPanel();
	private JPanel bracketControlPanel = new JPanel();
	
	private JPanel seriePanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel versusFields = new JPanel();
	private JPanel versusFieldsControlBoard = new JPanel();
	
	//JButtons
	
	private JButton winHome = new JButton("W");
	private JButton winAway = new JButton("W");
	private JButton draw = new JButton("D");
	
	private Match currentGame;
	
	private DefaultListModel<Match> allMatches = new DefaultListModel<Match>();
	
	public ControlBoard() {
		
		// Right panel
		right.setLayout(new GridLayout(6, 1));
		right.add(playersLabel);
		right.add(playerList);
		right.add(leageuOneLabel);
		right.add(leageuOneJList);
		right.add(leageuTwoLabel);
		right.add(leageuTwoJList);
		
		// Left panel
		left.setLayout(new GridLayout(3,1));
		left.add(addPlayer);
		left.add(randLeague);
		left.add(start);
		
		// Settings panel configuration
		settings.setLayout(new BorderLayout());
		settings.add(right, BorderLayout.EAST);
		settings.add(left, BorderLayout.WEST);
		
		// JPanel customization
		playerList.setPreferredSize(new Dimension(250, 100));
		playerList.setBorder(new EmptyBorder(10,10, 10, 10));
		leageuOneJList.setPreferredSize(new Dimension(250, 100));
		leageuOneJList.setBorder(new EmptyBorder(10,10, 10, 10));
		leageuTwoJList.setPreferredSize(new Dimension(250, 100));
		leageuTwoJList.setBorder(new EmptyBorder(10,10, 10, 10));
		
		// Buttons actionListeners
		addPlayer.addActionListener(this);
		randLeague.addActionListener(this);
		start.addActionListener(this);
		start.setEnabled(false);
		
		// Brackets panel configuration
		game.setLayout(new BorderLayout());
		game.add(bracketControlPanel, BorderLayout.WEST);
		game.add(bracket, BorderLayout.EAST);
		
		// BracketControlPanel panel
		bracketControlPanel.setPreferredSize(new Dimension(400, 500));
		bracketControlPanel.setLayout(new GridLayout(2,1));
			
			// Serie listorna
			bracketControlPanel.add(seriePanel);
			
				// Serie allPlayers
				serieOneJList.setPreferredSize(new Dimension(190, 160));
				serieOneJList.setBorder(new EmptyBorder(10,10, 10, 10));
				serieTwoJList.setPreferredSize(new Dimension(190, 160));
				serieTwoJList.setBorder(new EmptyBorder(10,10, 10, 10));
				seriePanel.add(serieOneJList);
				seriePanel.add(serieTwoJList);
		
			// Game panel
			bracketControlPanel.add(gamePanel);
			
				// Versus board
				gamePanel.setLayout(new GridLayout(2,1));
				
				versusFields.setLayout(new GridBagLayout());
				versusFields.setSize(400, 125);
				gamePanel.add(versusFields);
				
				// Versus fields components
				homePlayer.setEditable(false);
				awayPlayer.setEditable(false);
				JLabel versusLabel = new JLabel("vs");
				versusFields.add(homePlayer);
				versusFields.add(versusLabel);
				versusFields.add(awayPlayer);
				
				// Versus fields controlBoard
				versusFieldsControlBoard.setSize(400, 125);
				gamePanel.add(versusFieldsControlBoard);

				// Control buttons
				versusFieldsControlBoard.add(winHome);
				versusFieldsControlBoard.add(draw);
				versusFieldsControlBoard.add(winAway);
				
				winHome.addActionListener(this);
				draw.addActionListener(this);
				winAway.addActionListener(this);
		
		// Bracket panel
		bracket.setPreferredSize(new Dimension(600, 500));
		bracket.setBackground(Color.GREEN);
		
		// JFrame settings
		frame.add(settings);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e ) {
		boolean leaguesSet = false;
		
		if(e.getSource().equals(addPlayer)) {
			String name = JOptionPane.showInputDialog(addPlayer, this);
			Player p = new Player(name);
			allPlayers.addElement(p);
		}
		
		if(e.getSource().equals(winAway)) {
			Player winner = null, loser = null;
			
			for(int i = 0; i < allPlayers.size(); i++) {
				if(allPlayers.getElementAt(i).getName() == awayPlayer.getText()) {
					winner = allPlayers.getElementAt(i);
				}
			}
			
			for(int i = 0; i < allPlayers.size(); i++) {
				if(allPlayers.getElementAt(i).getName() == homePlayer.getText()) {
					loser = allPlayers.getElementAt(i);
				}
			}
			
			currentGame.setWinner(winner);
			currentGame.setLoser(loser);
			
			winner.setSerieString();
			loser.setSerieString();
			
			startNewMatch(currentGame);
		}
		
		if(e.getSource().equals(winHome)) {
			Player winner = null, loser = null;
			
			for(int i = 0; i < allPlayers.size(); i++) {
				if(allPlayers.getElementAt(i).getName().equals(homePlayer.getText())) {
					winner = allPlayers.getElementAt(i);
				}
				
				if(allPlayers.getElementAt(i).getName().equals(awayPlayer.getText())) {
					loser = allPlayers.getElementAt(i);
				}
			}
			
			currentGame.setWinner(winner);
			currentGame.setLoser(loser);
			
			winner.setSerieString();
			loser.setSerieString();
			
			startNewMatch(currentGame);
		}
		
		if(e.getSource().equals(randLeague)) {
			leaguesSet = true;
			
			league_1.emptyList();
			league_2.emptyList();
			
			double maxPlayers = Math.floor(allPlayers.size() / 2); 
			
			for(int i = 0; i < allPlayers.size(); i++) {
				int league = ThreadLocalRandom.current().nextInt(1, 3);
				
				Player player = allPlayers.getElementAt(i); 
				
				if(league == 1 && league_1.getPlayers().size() != maxPlayers || league_2.getPlayers().size() == maxPlayers) {
					league_1.addPlayer(player);
				} else {
					league_2.addPlayer(player);
				}
			}
		}
		
		// Start bracket
		if(e.getSource().equals(start)) {
			frame.getContentPane().remove(settings);
			frame.add(game);
			frame.getContentPane().invalidate();
			frame.getContentPane().validate();
			
			frame.setSize(1000, 500);
			
			currentGame = new Match();
			currentGame.start(league_1, league_2);

			allMatches.addElement(currentGame);
			
			homePlayer.setText(currentGame.getHomePlayer().getName());
			awayPlayer.setText(currentGame.getAwayPlayer().getName());
			
			// Construct serie text
			for(int i = 0; i < league_1.getPlayers().size(); i++) {
				league_1.getPlayers().elementAt(i).setSerieString();
			}
			
			for(int i = 0; i < league_2.getPlayers().size(); i++) {
				league_2.getPlayers().elementAt(i).setSerieString();
			}
		}
		
		if(allPlayers.size() >= 4 && leaguesSet) {
			start.setEnabled(true);
		}
	}
	
	private void startNewMatch(Match cg) {
		
		// Add current game to record
		allMatches.addElement(cg);
		
		if(cg.getLeague() == 1) {
			// Create game from league 2
			currentGame = new Match();
			
			currentGame.newMatch(league_2);
			homePlayer.setText(currentGame.getHomePlayer().getName());
			awayPlayer.setText(currentGame.getAwayPlayer().getName());
		} else {
			// Create game from league 1
			currentGame = new Match();
			
			currentGame.newMatch(league_1);
			homePlayer.setText(currentGame.getHomePlayer().getName());
			awayPlayer.setText(currentGame.getAwayPlayer().getName());
		}
	}
}

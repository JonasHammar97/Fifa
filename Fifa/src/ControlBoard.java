import java.awt.BorderLayout;
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
	private JPanel bracketPanel = new JPanel();
	private JPanel bracketControlPanel = new JPanel();
	
	private JPanel seriePanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel versusFields = new JPanel();
	private JPanel versusFieldsControlBoard = new JPanel();
	
	//JButtons
	
	private JButton winHome = new JButton("W");
	private JButton winAway = new JButton("W");
	private JButton draw = new JButton("D");
	
	// Bracket
	private Bracket bracket = new Bracket();
	
	private Match currentGame;
	
	private DefaultListModel<Match> allMatches = new DefaultListModel<Match>();
	
	// Bracket label
	JLabel semiOneHome = new JLabel("test home");
	JLabel semiOneAway = new JLabel("test away");
	JLabel semiTwoHome = new JLabel("test home");
	JLabel semiTwoAway = new JLabel("test away");
	
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
		game.add(bracketPanel, BorderLayout.EAST);
		
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
			bracketPanel.setPreferredSize(new Dimension(600, 500));
			JPanel left = new JPanel();
			JPanel middle = new JPanel();
			JPanel right = new JPanel();
			left.setPreferredSize(new Dimension(180, 500));
			left.setLayout(new GridLayout(2,1));
			middle.setPreferredSize(new Dimension(180, 500));
			middle.setLayout(new GridBagLayout());
			right.setPreferredSize(new Dimension(180, 500));
			right.setLayout(new GridBagLayout());
			
			// Left
			JPanel semiOnePanel = new JPanel();
			JPanel semiTwoPanel = new JPanel();
			semiOnePanel.setLayout(new GridBagLayout());
			semiOnePanel.add(semiOneHome);
			semiOnePanel.add(semiOneAway);
			
			left.add(semiOnePanel);
			
			semiTwoPanel.setLayout(new GridBagLayout());
			semiTwoPanel.add(semiTwoHome);
			semiTwoPanel.add(semiTwoAway);
			
			left.add(semiTwoPanel);
			//
			
			// middle
			JLabel finalHome = new JLabel("test home");
			JLabel finalAway = new JLabel("test away");
			JPanel finalPanel = new JPanel();
			finalPanel.setLayout(new GridBagLayout());
			finalPanel.add(finalHome);
			finalPanel.add(finalAway);
			
			middle.add(finalPanel);
			//
		
			// right
			JLabel winnerBracket = new JLabel("winner");
			JPanel winnerPanel = new JPanel();
			winnerPanel.setLayout(new GridBagLayout());
			winnerPanel.add(winnerBracket);
			
			right.add(winnerPanel);
			//
			
			bracketPanel.add(left);
			bracketPanel.add(middle);
			bracketPanel.add(right);
		// 
		
		// JFrame settings
		frame.add(settings);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	private void startNewMatch(Match lastGame) {
		
		// Add current game to record
		allMatches.addElement(lastGame);
		
		// Bracket with less than 5 players
		if(league_1.getPlayers().size() + league_2.getPlayers().size() < 6) {
			// start bracket
			
			// Check what league has 3 players and start serie for that league only
			if(league_1.getPlayers().size() > 2) {
				// League 1 got 3 players
				
				System.out.println("League 1 games:" + league_1.matchesPlayed());
				
				// Check games played
				if(league_1.matchesPlayed() == league_1.getPlayers().size() * (league_1.getPlayers().size() - 1)) {
					// start bracket
					System.out.println("Start bracket 1");
				}
				
				// Create game from league 2
				// global variable
				currentGame = new Match();
				currentGame.newMatch(league_1);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
				
				// Set match as played
				league_1.matchPlayed();
			} else if(league_2.getPlayers().size() > 2) {
				// League 2 got 3 players
				
				System.out.println("League 2 games:" + league_2.matchesPlayed());
				// Check games played
				if(league_2.matchesPlayed() == league_2.getPlayers().size() * (league_2.getPlayers().size() - 1)) {
					// start bracket
					System.out.println("Start bracket 2");
				}
				
				// Create game from league 2
				// global variable
				currentGame = new Match();
				currentGame.newMatch(league_2);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
				
				// Set match as played
				league_2.matchPlayed();
			} else {
				System.out.println("Start bracket 3");
			}
		} else {
			// More than or equal to 6 players
			
			if(lastGame.getLeague() == 1) {
				// Create game from league 2
				
				// global variable
				currentGame = new Match();
				
				currentGame.newMatch(league_2);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
				
				league_2.matchPlayed();
			} else {
				// Create game from league 1
				
				// global variable
				currentGame = new Match();
				
				currentGame.newMatch(league_1);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
				
				league_1.matchPlayed();
			}
			
			// Brackets with more than 5 players
			if(league_1.matchesPlayed() == league_1.getPlayers().size() * (league_1.getPlayers().size() - 1) 
					&& league_2.matchesPlayed() == league_2.getPlayers().size() * (league_2.getPlayers().size() - 1)) {
				System.out.println("Start bracket 4");
				Player[] topTwo1 = league_1.getTopTwo();
				Player[] topTwo2 = league_2.getTopTwo();
				
				// start bracket
				bracket.setSemi(topTwo1, topTwo2);
				semiOneHome.setText(bracket.getSemi1().getHomePlayer().getName());
				semiOneAway.setText(bracket.getSemi1().getAwayPlayer().getName());
				semiTwoHome.setText(bracket.getSemi2().getHomePlayer().getName());
				semiTwoAway.setText(bracket.getSemi2().getAwayPlayer().getName());
			}
		}
	}
}

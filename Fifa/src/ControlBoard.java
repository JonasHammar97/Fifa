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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ControlBoard implements ActionListener {
	private JFrame frame = new JFrame();
	private JButton addPlayer = new JButton("Add player");
	private JButton randLeague = new JButton("Randomize league");
	private JButton start = new JButton("Start");
	
	private DefaultListModel<Player> temp1 = new DefaultListModel<Player>();
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
	
	// Bracket labels
	
	// Semi-final
	JLabel semiOneHome = new JLabel("Home");
	JLabel semiOneAway = new JLabel("Away");
	JLabel semiTwoHome = new JLabel("Home");
	JLabel semiTwoAway = new JLabel("Away");
	
	// Final
	JLabel finalHome = new JLabel("Home");
	JLabel finalAway = new JLabel("Away");
	
	// Winner
	JLabel winnerLabel = new JLabel("winner");
	
	//

	public ControlBoard() {

		// Right panel
		right.setLayout(new GridLayout(3, 1, 0, 20));
		right.add(playerList);
		right.add(leageuOneJList);
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
		playerList.setPreferredSize(new Dimension(300, 100));
		playerList.setBorder(new EmptyBorder(10,10, 10, 10));
		leageuOneJList.setPreferredSize(new Dimension(300, 100));
		leageuOneJList.setBorder(new EmptyBorder(10,10, 10, 10));
		leageuTwoJList.setPreferredSize(new Dimension(300, 100));
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
		versusFields.add(homePlayer);
		versusFields.add(new JLabel(" vs "));
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
			semiOnePanel.add(new JLabel(" vs "));
			semiOnePanel.add(semiOneAway);
			
			left.add(semiOnePanel);
			
			semiTwoPanel.setLayout(new GridBagLayout());
			semiTwoPanel.add(semiTwoHome);
			semiTwoPanel.add(new JLabel(" vs "));
			semiTwoPanel.add(semiTwoAway);
			
			left.add(semiTwoPanel);
			//
			
			// middle
			JPanel finalPanel = new JPanel();
			finalPanel.setLayout(new GridBagLayout());
			finalPanel.add(finalHome);
			finalPanel.add(new JLabel(" vs "));
			finalPanel.add(finalAway);
			
			middle.add(finalPanel);
			//
		
			// right
			JPanel winnerPanel = new JPanel();
			winnerPanel.setLayout(new GridBagLayout());
			winnerPanel.add(winnerLabel);
			
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
				if(allPlayers.getElementAt(i).getName().equals(awayPlayer.getText())) {
					winner = allPlayers.getElementAt(i);
				}

				if(allPlayers.getElementAt(i).getName().equals(homePlayer.getText())) {
					loser = allPlayers.getElementAt(i);

				}
			}
			
			// If bracket has started
			if(bracket.checkStart()) {
				
				if(bracket.checkSemi()) {
					bracket.getFinal().setWinner(winner);
					
					winnerLabel.setText(winner.getName());
				} else {
					if(winner.getName() == bracket.getSemi1().getAwayPlayer().getName() || winner.getName() == bracket.getSemi1().getHomePlayer().getName()) {
						bracket.getSemi1().setWinner(winner);
						
						// Start semi 2
						homePlayer.setText(bracket.getSemi2().getHomePlayer().getName());
						awayPlayer.setText(bracket.getSemi2().getAwayPlayer().getName());
					} else {
						bracket.getSemi2().setWinner(winner);
					}
					
					// Start final if both semis are played
					if(bracket.getSemi1().getWinner() != null && bracket.getSemi2().getWinner() != null ) {
						bracket.setSemiPlayed(true);
						
						// Set label texts
						finalHome.setText(bracket.getSemi1().getWinner().getName());
						finalAway.setText(bracket.getSemi2().getWinner().getName());
						
						// Set game label boxes
						homePlayer.setText(bracket.getSemi1().getWinner().getName());
						awayPlayer.setText(bracket.getSemi2().getWinner().getName());
						
						// Setup final game
						bracket.setFinal(bracket.getSemi1().getWinner(), bracket.getSemi2().getWinner());
					}
				}
			} else {
				currentGame.setWinner(winner);
				currentGame.setLoser(loser);
				winner.setSerieString();
				loser.setSerieString();

				
				if(currentGame.getLeague() == 1) {
					updateTable(league_1);
				}else {
					updateTable(league_2);
				}
				
				checkGames();
			}
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

			// If bracket has started
			if(bracket.checkStart()) {
				
				// Disable draw button
				draw.setEnabled(false);
				
				if(bracket.checkSemi()) {
					bracket.getFinal().setWinner(winner);
					
					winnerLabel.setText(winner.getName());
				} else {
					if(winner.getName() == bracket.getSemi1().getAwayPlayer().getName() || winner.getName() == bracket.getSemi1().getHomePlayer().getName()) {
						bracket.getSemi1().setWinner(winner);
						
						// Start semi 2
						homePlayer.setText(bracket.getSemi2().getHomePlayer().getName());
						awayPlayer.setText(bracket.getSemi2().getAwayPlayer().getName());
					} else {
						bracket.getSemi2().setWinner(winner);
					}
					
					// Start final if both semis are played
					if(bracket.getSemi1().getWinner() != null && bracket.getSemi2().getWinner() != null ) {
						bracket.setSemiPlayed(true);
						
						// Set label texts
						finalHome.setText(bracket.getSemi1().getWinner().getName());
						finalAway.setText(bracket.getSemi2().getWinner().getName());
						
						// Set game label boxes
						homePlayer.setText(bracket.getSemi1().getWinner().getName());
						awayPlayer.setText(bracket.getSemi2().getWinner().getName());
						
						// Setup final game
						bracket.setFinal(bracket.getSemi1().getWinner(), bracket.getSemi2().getWinner());
					}
				}
			} else {
				currentGame.setWinner(winner);
				currentGame.setLoser(loser);
				winner.setSerieString();
				loser.setSerieString();

				if(currentGame.getLeague() == 1) {
					updateTable(league_1);
				}else {
					updateTable(league_2);
				}
				
				checkGames();
			}
		}
		
		if(e.getSource().equals(draw)) {
			Player one = null, two = null;
			
			for(int i = 0; i < allPlayers.size(); i++) {
				if(allPlayers.getElementAt(i).getName().equals(homePlayer.getText())) {
					one = allPlayers.getElementAt(i);
				}

				if(allPlayers.getElementAt(i).getName().equals(awayPlayer.getText())) {
					two = allPlayers.getElementAt(i);

				}
			}
			currentGame.setDraw(one, two);
			
			one.setSerieString();
			two.setSerieString();
			
			if(currentGame.getLeague() == 1) {
				updateTable(league_1);
			}else {
				updateTable(league_2);
			}
			
			checkGames();
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
	
	private void startBracket() {
		
		// Get top 2 players in each league
		Player[] topTwo1 = league_1.getTopTwo();
		Player[] topTwo2 = league_2.getTopTwo();
		
		// Start semi-final
		bracket.setSemi(topTwo1, topTwo2);
		bracket.setStart(true);
		
		// Disable draw button
		draw.setEnabled(false);
		
		semiOneHome.setText(bracket.getSemi1().getHomePlayer().getName());
		semiOneAway.setText(bracket.getSemi1().getAwayPlayer().getName());
		semiTwoHome.setText(bracket.getSemi2().getHomePlayer().getName());
		semiTwoAway.setText(bracket.getSemi2().getAwayPlayer().getName());
		
		homePlayer.setText(bracket.getSemi1().getHomePlayer().getName());
		awayPlayer.setText(bracket.getSemi1().getAwayPlayer().getName());
	}
	
	private void checkGames() {
		// Brackets with more than 5 players
		if(league_1.matchesPlayed() == league_1.getPlayers().size() && league_2.matchesPlayed() == league_2.getPlayers().size()) {
			startBracket();
		} else {
			startNewMatch(currentGame);
		}
	}
	
	private void updateTable(League <Player> l) {
		
		for(int i = 0; i < l.league.size(); i++) {
			temp1.addElement(l.league.getElementAt(i));
		}
		
		l.league.removeAllElements();
		
		for(int i = 0; i < temp1.size(); i++) {
			l.league.addElement(temp1.getElementAt(i));
		}
		
		temp1.removeAllElements();
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
				if(league_1.matchesPlayed() == league_1.getPlayers().size()) {
					// start bracket
					System.out.println("Start bracket 1");
				}
				
				// Create game from league 2
				// global variable
				currentGame = new Match();
				currentGame.newMatch(league_1);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
			} else if(league_2.getPlayers().size() > 2) {
				// League 2 got 3 players
				
				// Check games played
				if(league_2.matchesPlayed() == league_2.getPlayers().size()) {
					// start bracket
					System.out.println("Start bracket entry 2");
				}
				
				// Create game from league 2
				// global variable
				currentGame = new Match();
				currentGame.newMatch(league_2);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
			} else {
				System.out.println("Start bracket entry 3");
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
			} else {
				// Create game from league 1
				
				// global variable
				currentGame = new Match();
				
				currentGame.newMatch(league_1);
				homePlayer.setText(currentGame.getHomePlayer().getName());
				awayPlayer.setText(currentGame.getAwayPlayer().getName());
			}
		}
	}
}

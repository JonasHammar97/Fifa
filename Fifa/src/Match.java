import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.DefaultListModel;

public class Match {
	private Player homePlayer;
	private Player awayPlayer;
	private Player winner, loser;
	private boolean draw = true;
	private int league;
	
	public Match() {}
	
	public void start(League<Player> l1, League<Player> l2) {
		league = ThreadLocalRandom.current().nextInt(1, 3);
		
		if(league == 1) {
			newMatch(l1);
		} else {
			newMatch(l2);
		}
	}
	
	private void randomizeVersus(DefaultListModel<Player> players) {
		Random rand = new Random();
		
		int h = rand.nextInt(players.size()) + 0;
		int a;
		do {
			a = rand.nextInt(players.size()) + 0;
		} while(h == a);
		
		setHomePlayer(players.getElementAt(h));
		setAwayPlayer(players.getElementAt(a));
	}
	
	// Borde vara konstruktor
	public void newMatch(League<Player> l) {
		// Set league
		setLeague(l.getNumber());
		
		DefaultListModel<Player> l_players = l.getPlayers();

		// Check if they've met before
		boolean metBefore = false;
		
		// get versus
		randomizeVersus(l_players);
		
		// Check in one players list if the other player is in it
		DefaultListModel<Player> homeVersuses = homePlayer.getVersus();
		for(int i = 0; i < homeVersuses.size(); i++) {
			if(homeVersuses.getElementAt(i).getName() == awayPlayer.getName()) {
				metBefore = true;
			}
		}
		
		if(!metBefore) {
			// Add versus in each list
			homePlayer.addVersus(awayPlayer);
			awayPlayer.addVersus(homePlayer);
			
			l.matchPlayed();
		} else {
			newMatch(l);
		}
	}
	
	public Player getAwayPlayer() {
		return awayPlayer;
	}
	
	public Player getHomePlayer() {
		return homePlayer;
	}
	
	public void setAwayPlayer(Player p) {
		awayPlayer = p;
	}
	
	public void setHomePlayer(Player p) {
		homePlayer = p;
	}
	
	public void setWinner(Player p) {
		p.addWin();
		
		winner = p;
	}
	
	public void setLoser(Player p) {
		p.addLoss();
		
		loser = p;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public void setDraw(Player p1, Player p2) {
		p1.addDraw();
		p2.addDraw();
		draw = true;
	}
	
	public void setLeague(int l) {
		league = l;
	}
	
	public int getLeague() {
		return league;
	}
}

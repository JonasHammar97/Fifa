import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.DefaultListModel;

public class Match {
	private Player home;
	private Player away;
	private Player winner, loser;
	private boolean draw = true;
	private int league;
	
	public Match() {}
	
	public void start(League<Player> l1, League<Player> l2) {
		league = ThreadLocalRandom.current().nextInt(1, 3);
		
		Random rand = new Random();
		
		if(league == 1) {
			DefaultListModel<Player> l1_players = l1.getPlayers();

			int  h_number = rand.nextInt(l1_players.size()) + 0;
			int a_number;
			do {
				a_number = rand.nextInt(l1_players.size()) + 0;
			} while(h_number == a_number);
			
			home = l1_players.getElementAt(h_number);
			away = l1_players.getElementAt(a_number);
		} else {
			DefaultListModel<Player> l2_players = l2.getPlayers();
			
			int  h_number = rand.nextInt(l2_players.size()) + 0;
			int a_number;
			do {
				a_number = rand.nextInt(l2_players.size()) + 0;
			} while(h_number == a_number);
			
			home = l2_players.getElementAt(h_number);
			away = l2_players.getElementAt(a_number);
		}
	}
	
	public void newMatch(League<Player> l) {
		Random rand = new Random();
		
		league = l.getNumber();
		
		DefaultListModel<Player> l_players = l.getPlayers();

		int h_number = rand.nextInt(l_players.size()) + 0;
		int a_number;
		do {
			a_number = rand.nextInt(l_players.size()) + 0;
		} while(h_number == a_number);
		
		home = l_players.getElementAt(h_number);
		away = l_players.getElementAt(a_number);
	}
	
	public Player getAwayPlayer() {
		return away;
	}
	
	public Player getHomePlayer() {
		return home;
	}
	
	public void setAwayPlayer(Player p) {
		away = p;
	}
	
	public void setHomePlayer(Player p) {
		home = p;
	}
	
	public void setWinner(Player p) {
		p.addWin();
		
		winner = p;
	}
	
	public void setLoser(Player p) {
		p.addLoss();
		
		loser = p;
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

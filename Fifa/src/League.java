import javax.swing.DefaultListModel;

public class League<E> {
	DefaultListModel<Player> league = new DefaultListModel<Player>(); 
	private int number;
	private int matchesPlayed = 0;
	
	public League(int n) {
		number = n;
	}
	
	public void addPlayer(Player p) {
		league.addElement(p);
	}
	
	public void emptyList() {
		league.removeAllElements();
	}
	
	public DefaultListModel<Player> getPlayers() {
		return league;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void matchPlayed() {
		matchesPlayed += 1;
	}
	
	public Player[] getTopTwo() {
		Player first = new Player("");
		Player second = new Player("");
		
		for(int i = 0; i < league.size(); i++) {
			Player p = league.getElementAt(i);
			
			if(first.getScore() < p.getScore()) {
				first = p;
			}
		}
		
		for(int i = 0; i < league.size(); i++) {
			Player p = league.getElementAt(i);
			
			if(p != first) {
				if(second.getScore() < p.getScore()) {
					second = p;
				}
			}
		}
		
		Player[] topTwo = {first, second}; 
		
		return topTwo;
	}
	
	public int matchesPlayed() {
		return matchesPlayed;
	}
}

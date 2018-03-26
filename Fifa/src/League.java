import javax.swing.DefaultListModel;

public class League<E> {
	DefaultListModel<E> league = new DefaultListModel<E>(); 
	private int number;
	
	public League(int n) {
		number = n;
	}
	
	public void addPlayer(E p) {
		league.addElement(p);
	}
	
	public void emptyList() {
		league.removeAllElements();
	}
	
	public DefaultListModel<E> getPlayers() {
		return league;
	}
	
	public int getNumber() {
		return number;
	}
}

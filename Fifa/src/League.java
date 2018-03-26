import javax.swing.DefaultListModel;

public class League<Person> {
	DefaultListModel<Person> league = new DefaultListModel<Person>(); 
	private int number;
	
	public League(int n) {
		number = n;
	}
	
	public void addPlayer(Person p) {
		league.addElement(p);
	}
	
	public void emptyList() {
		league.removeAllElements();
	}
	
	public DefaultListModel<Person> getPlayers() {
		return league;
	}
	
	public int getNumber() {
		return number;
	}
}

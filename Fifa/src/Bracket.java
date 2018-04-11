
public class Bracket {
	
	public Match semi1 = new Match();
	public Match semi2 = new Match();

	public Bracket() {}
	
	public void setSemi(Player[] semi_1, Player[] semi_2) {
		semi1.setAwayPlayer(semi_1[0]);
		semi1.setHomePlayer(semi_1[1]);
		
		semi2.setAwayPlayer(semi_1[0]);
		semi2.setHomePlayer(semi_1[1]);
	}
	
	public Match getSemi1() {
		return semi1;
	}
	
	public Match getSemi2() {
		return semi1;
	}
}

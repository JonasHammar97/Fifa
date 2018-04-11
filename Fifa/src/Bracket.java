
public class Bracket {
	
	private Match semi1 = new Match();
	private Match semi2 = new Match();
	private Match finalGame = new Match();
	private boolean started = false, semiPlayed = false, finalPlayed = false;

	public Bracket() {}
	
	public void setSemi(Player[] semi_1, Player[] semi_2) {
		semi1.setAwayPlayer(semi_1[0]);
		semi1.setHomePlayer(semi_1[1]);
		
		semi2.setAwayPlayer(semi_2[0]);
		semi2.setHomePlayer(semi_2[1]);
	}
	
	public void setFinal(Player p1, Player p2) {
		finalGame.setAwayPlayer(p1);
		finalGame.setHomePlayer(p2);
	}
	
	public void setStart(boolean b) {
		started = b;
	}
	
	public void setSemiPlayed(boolean b) {
		semiPlayed = b;
	}
	
	public boolean checkFinal() {
		return finalPlayed;
	}
	
	public void setFinalPlayed(boolean b) {
		finalPlayed = b;
	}
	
	public boolean checkSemi() {
		return semiPlayed;
	}
	
	public boolean checkStart() {
		return started;
	}
	
	public Match getSemi1() {
		return semi1;
	}
	
	public Match getSemi2() {
		return semi2;
	}
	
	public Match getFinal() {
		return finalGame;
	}
}

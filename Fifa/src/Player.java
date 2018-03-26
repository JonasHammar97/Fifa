public class Player {
	private League<?> league;
	private int wins = 0, draws = 0, losses = 0, score;
	private String printedText, name;
	
	public Player(String n) {
		setToString(n);
		name = n;
	}
	
	public void changeName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLeague(League<?> l) {
		league = l;
	}
	
	public League<?> getLeague() {
		return league;
	}
	
	public void addWin() {
		wins += 1;
	}
	
	public void addDraw() {
		draws += 1;
	}
	
	public int getDraws() {
		return draws;
	}
	
	public void addLoss() {
		losses += 1;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setToString(String n) {
		printedText = n;
	}
	
	public void setSerieString() {
		setToString(name + " W:" + wins + " D:" + draws + " L:" + losses);
	}
	
	@Override
	public String toString() {
		//String n = name + " W:" + wins + " D:" + draws + " L:" + losses; 
		return printedText;
	}
}

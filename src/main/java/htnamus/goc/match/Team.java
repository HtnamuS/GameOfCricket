package htnamus.goc.match;

public class Team {
	private Player[] roster;
	private int score, noOfWickets, noOfOversPlayed;
	public Team(){
		roster = new Player[11];
		for (int i = 0; i < 4; i++) {
			roster[i] = new Player(PlayerType.BATSMAN);
		}
		for (int i = 0; i < 3; i++) {
			roster[i] = new Player(PlayerType.ALLROUNDER);
		}
		for (int i = 0; i < 4; i++) {
			roster[i] = new Player(PlayerType.BOWLER);
		}
	}
}

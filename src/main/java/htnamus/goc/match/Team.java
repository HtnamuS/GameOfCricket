package htnamus.goc.match;

import java.util.ArrayList;

public class Team {
	public Player[] Playing11;
	private int nextBatsmanIndex = 0;
	ArrayList<Player> Bowlers;
	public String name;
	
	public Team(){
		Playing11 = new Player[11];
		Bowlers = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Playing11[i] = new Player(PlayerType.BATSMAN, i+1);
		}
		for (int i = 4; i < 7; i++) {
			Playing11[i] = new Player(PlayerType.ALLROUNDER, i+1);
			Bowlers.add(Playing11[i]);
		}
		for (int i = 7; i < 11; i++) {
			Playing11[i] = new Player(PlayerType.BOWLER, i+1);
			Bowlers.add(Playing11[i]);
		}
	}
	public Player nextBatsman(){
		Player next = Playing11[nextBatsmanIndex];
		nextBatsmanIndex++;
		return next;
	}
	public Player nextBowler(Player prevBowler){
		Player nextBowler = Bowlers.get((int) (Math.random() * Bowlers.size()));
		while(nextBowler == prevBowler || nextBowler.getNoOfOversBowled() >= 10){
			nextBowler = Bowlers.get((int) (Math.random() * Bowlers.size()));
		}
		return nextBowler;
	}
}

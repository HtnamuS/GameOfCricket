package htnamus.goc.match;

import java.util.ArrayList;
import java.util.List;

public class Team {
	Player[] PlayingTeam;
	private int nextBatsmanIndex = 0;
	List<Player> Bowlers;
	public String name;
	private MatchType matchType;
	
	public Team(MatchType m){
		PlayingTeam = new Player[MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM];
		Bowlers = new ArrayList<>(MATCH_CONSTANTS.NO_OF_BOWLERS + MATCH_CONSTANTS.NO_OF_ALLROUNDERS);
		for (int i = 0; i < MATCH_CONSTANTS.NO_OF_BATSMEN; i++) {
			PlayingTeam[i] = new Player(PlayerType.BATSMAN, i+1);
		}
		for (int i = 0; i < MATCH_CONSTANTS.NO_OF_ALLROUNDERS; i++) {
			PlayingTeam[i + MATCH_CONSTANTS.NO_OF_BATSMEN] = new Player(PlayerType.ALLROUNDER, i+ MATCH_CONSTANTS.NO_OF_BATSMEN + 1);
			Bowlers.add(PlayingTeam[i + MATCH_CONSTANTS.NO_OF_BATSMEN]);
		}
		for (int i = MATCH_CONSTANTS.NO_OF_BOWLERS; i >= 0; i--) {
			PlayingTeam[MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM - i] = new Player(PlayerType.BOWLER,MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM - i + 1);
			Bowlers.add(PlayingTeam[MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM - i]);
		}
		this.matchType = m;
	}
	public Player nextBatsman(){
		Player next = PlayingTeam[nextBatsmanIndex];
		nextBatsmanIndex++;
		return next;
	}
	public Player nextBowler(Player prevBowler){
		Player nextBowler = Bowlers.get((int) (Math.random() * Bowlers.size()));
		while(nextBowler == prevBowler || nextBowler.getNoOfOversBowled() >= matchType.noOfOversPerBowler){
			nextBowler = Bowlers.get((int) (Math.random() * Bowlers.size()));
		}
		return nextBowler;
	}
}

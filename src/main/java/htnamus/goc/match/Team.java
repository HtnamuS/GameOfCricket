package htnamus.goc.match;

import java.util.ArrayList;
import java.util.List;

public class Team {
	Player[] PlayingTeam;
	private int nextBatsmanIndex = 0;
	List<Player> Bowlers;
	public String name;
	private Match.MatchType matchType;
	
	public Team(Match.MatchType matchType){
		PlayingTeam = new Player[MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM];
		Bowlers = new ArrayList<>(MATCH_CONSTANTS.NO_OF_BOWLERS + MATCH_CONSTANTS.NO_OF_ALLROUNDERS);
		for (int batsmanIndex = 0; batsmanIndex < MATCH_CONSTANTS.NO_OF_BATSMEN; batsmanIndex++) {
			PlayingTeam[batsmanIndex] = new Player(Player.PlayerType.BATTING, "Player " + (batsmanIndex+1),batsmanIndex+1,
					MATCH_CONSTANTS.BatsmanBattingStrikeRate,
					MATCH_CONSTANTS.BatsmanBowlingEconomy, MATCH_CONSTANTS.BatsmanBallsPlayedPerInnings, MATCH_CONSTANTS.BatsmanWicketsPer100Balls);
		}
		for (int allRounderIndex =  MATCH_CONSTANTS.NO_OF_BATSMEN; allRounderIndex < MATCH_CONSTANTS.NO_OF_BATSMEN + MATCH_CONSTANTS.NO_OF_ALLROUNDERS; allRounderIndex++) {
			PlayingTeam[allRounderIndex] = new Player(Player.PlayerType.ALLROUNDER, "Player " + (allRounderIndex+1),allRounderIndex+1,
					MATCH_CONSTANTS.AllRounderBattingStrikeRate,
					MATCH_CONSTANTS.AllRounderBowlingEconomy, MATCH_CONSTANTS.AllRounderBallsPlayedPerInnings, MATCH_CONSTANTS.AllRounderWicketsPer100Balls);
			Bowlers.add(PlayingTeam[allRounderIndex]);
		}
		for (int bowlerIndex =  MATCH_CONSTANTS.NO_OF_BATSMEN + MATCH_CONSTANTS.NO_OF_ALLROUNDERS; bowlerIndex < MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM; bowlerIndex++) {
			PlayingTeam[bowlerIndex] = new Player(Player.PlayerType.BOWLING, "Player " + (bowlerIndex+1), bowlerIndex + 1,
					MATCH_CONSTANTS.BowlerBattingStrikeRate, MATCH_CONSTANTS.BowlerBowlingEconomy, MATCH_CONSTANTS.BowlerBallsPlayedPerInnings, MATCH_CONSTANTS.BowlerWicketsPer100Balls);
			Bowlers.add(PlayingTeam[bowlerIndex]);
		}
		this.matchType = matchType;
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

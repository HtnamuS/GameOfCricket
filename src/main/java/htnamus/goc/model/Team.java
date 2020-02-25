package htnamus.goc.model;

import htnamus.goc.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
	private List<Player> PlayingRoster;
	private int nextBatsmanIndex = 0;
	private List<Player> Bowlers;
	private String name;
	private Match.MatchType matchType;
	
	private Team(Match.MatchType matchType){
		PlayingRoster = new ArrayList<>(Constants.NO_OF_PLAYERS_PER_TEAM);
		Bowlers = new ArrayList<>(Constants.NO_OF_BOWLERS + Constants.NO_OF_ALLROUNDERS);
		this.matchType = matchType;
	}
	
	public static Team getInstance(Match.MatchType matchType){
		Team teamInstance = new Team(matchType);
		addBatsmenToRoster(teamInstance);
		addAllRoundersToRoster(teamInstance);
		addBowlersToRoster(teamInstance);
		return teamInstance;
	}
	
	private static void addBatsmenToRoster(Team team){
		for (int batsmanIndex = 0; batsmanIndex < Constants.NO_OF_BATSMEN; batsmanIndex++) {
			PlayerStats batsmanStats = new PlayerStats(Constants.BatsmanBattingStrikeRate,
				Constants.BatsmanBowlingEconomy, Constants.BatsmanBallsPlayedPerInnings, Constants.BatsmanWicketsPer100Balls);
			String playerName = "Player " + (batsmanIndex+1);
			Player batsman = new Player(Player.PlayerType.BATTING, playerName,batsmanIndex+1, batsmanStats);
			team.PlayingRoster.add(batsman);
		}
	}
	private static void addAllRoundersToRoster(Team team){
		for (int allRounderIndex = Constants.NO_OF_BATSMEN; allRounderIndex < Constants.NO_OF_BATSMEN + Constants.NO_OF_ALLROUNDERS; allRounderIndex++) {
			PlayerStats allRounderStats = new PlayerStats(Constants.AllRounderBattingStrikeRate,
				Constants.AllRounderBowlingEconomy, Constants.AllRounderBallsPlayedPerInnings, Constants.AllRounderWicketsPer100Balls);
			String playerName = "Player " + (allRounderIndex+1);
			Player allRounder = new Player(Player.PlayerType.ALLROUNDER, playerName,allRounderIndex+1, allRounderStats);
			team.PlayingRoster.add( allRounder);
			team.Bowlers.add(allRounder);
		}
	}
	private static void addBowlersToRoster(Team team) {
		for (int bowlerIndex = Constants.NO_OF_BATSMEN + Constants.NO_OF_ALLROUNDERS; bowlerIndex < Constants.NO_OF_PLAYERS_PER_TEAM; bowlerIndex++) {
			PlayerStats bowlerStats = new PlayerStats(Constants.BowlerBattingStrikeRate, Constants.BowlerBowlingEconomy, Constants.BowlerBallsPlayedPerInnings, Constants.BowlerWicketsPer100Balls);
			String playerName = "Player " + (bowlerIndex + 1);
			team.PlayingRoster.add( new Player(Player.PlayerType.BOWLING, playerName, bowlerIndex + 1, bowlerStats));
			team.Bowlers.add(team.PlayingRoster.get(bowlerIndex));
		}
	}
	
	public List<Player> getPlayingRoster() {
		return Collections.unmodifiableList(PlayingRoster);
	}
	
	public int getNextBatsmanIndex() {
		return nextBatsmanIndex;
	}
	
	public List<Player> getBowlers() {
		return Collections.unmodifiableList(Bowlers);
	}
	
	public String getName() {
		return name;
	}
	
	public Match.MatchType getMatchType() {
		return matchType;
	}
	
	public void incrementNextBatsmanIndex() {
		this.nextBatsmanIndex++;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

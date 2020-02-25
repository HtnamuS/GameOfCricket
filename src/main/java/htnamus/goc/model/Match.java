package htnamus.goc.model;

import htnamus.goc.util.Constants;

public class Match {
	public enum MatchType {
		ODI(50, 10),
		T20(20, 4);
		
		public final int totalNumberOfOversPerInnings;
		public final int totalNumberOfOversPerBowlerPerInnings;
		MatchType(int numOfOvers, int numOversPerBowler){
			totalNumberOfOversPerInnings = numOfOvers;
			totalNumberOfOversPerBowlerPerInnings = numOversPerBowler;
		}
	}
	
	private final Innings firstInnings, secondInnings;
	
	public Match(MatchType matchType){
		Team team1 = Team.getInstance(matchType);
		Team team2 = Team.getInstance(matchType);
		team1.setName(Constants.TEAM_1_NAME);
		team2.setName(Constants.TEAM_2_NAME);
		int totalNumberOfOvers = matchType.totalNumberOfOversPerInnings;
		firstInnings = new Innings(team1, team2, totalNumberOfOvers);
		secondInnings = new Innings(team2, team1, totalNumberOfOvers);
	}
	
	public Innings getFirstInnings() {
		return firstInnings;
	}
	public Innings getSecondInnings() {
		return secondInnings;
	}
}

package htnamus.goc.match;

import java.util.List;

public class Match {
	public enum MatchType {
		ODI(50, 10),T20(20, 4);
		public final int noOfOvers;
		public final int noOfOversPerBowler;
		MatchType(int numOfOvers, int numOversPerBowler){
			noOfOvers = numOfOvers;
			noOfOversPerBowler = numOversPerBowler;
		}
	}
	
	private Team team1, team2;
	private int noOfOvers;
	private Innings firstInnings, secondInnings;
	
	private class Result{
		public  final InningsResult firstInningsResult, SecondInningsResult;
		public final String statement;
		public final int winMargin;
		public final String marginUnits;
		
		private class InningsResult{
			public final String battingTeam;
			public final String bowlingTeam;
			public final int score;
			public final int wickets;
			public final float numberOfOvers;
			public InningsResult(String battingTeam, String bowlingTeam, int score, int wickets, float numberOfOvers) {
				this.battingTeam = battingTeam;
				this.bowlingTeam = bowlingTeam;
				this.score = score;
				this.wickets = wickets;
				this.numberOfOvers = numberOfOvers;
			}
		}
		
		public Result(){
			firstInningsResult = new InningsResult(firstInnings.getBattingTeamName(), secondInnings.getBattingTeamName(),
				firstInnings.getScore(),
				firstInnings.getWickets(),(float)firstInnings.getnOvers()+firstInnings.getDecimalBalls()/10.0f);
			SecondInningsResult = new InningsResult(secondInnings.getBattingTeamName(), firstInnings.getBattingTeamName(),
				secondInnings.getScore(), secondInnings.getWickets(), secondInnings.getnOvers());
			if(firstInnings.getScore() > secondInnings.getScore()){
				statement = firstInnings.getBattingTeamName() + " Wins!";
				winMargin = firstInnings.getScore() - secondInnings.getScore();
				marginUnits = "Runs";
			}
			else if (firstInnings.getScore() < secondInnings.getScore()){
				statement = secondInnings.getBattingTeamName() + " Wins!";
				winMargin = 10 - secondInnings.getWickets();
				marginUnits = "Wickets";
			}
			else{
				statement = "Match Tied";
				winMargin = 0;
				marginUnits = "NA";
			}
		}
		
		
	}
	
	private class Scorecard {
		public final Innings.InningsScoreCard firstInningsScorecard, secondInningsScorecard;
		public Scorecard() {
			firstInningsScorecard = firstInnings.getScoreCard();
			secondInningsScorecard = secondInnings.getScoreCard();
		}
	}
	
	private class OversScores {
		public final Innings.OversScores firstInningsOversScores, secondInningsOversScores;
		
		public OversScores(){
			this.firstInningsOversScores = firstInnings.getOverScores();
			this.secondInningsOversScores = secondInnings.getOverScores();
		}
	}
	
	private class PartnershipsReport {
		public final List<Innings.Partnership.PartnershipReport> firstInningsPartnershipsReport, secondInningsPartnershipsReport;
		
		public PartnershipsReport() {
			this.firstInningsPartnershipsReport = firstInnings.getPartnernships();
			this.secondInningsPartnershipsReport = secondInnings.getPartnernships();
		}
	}
	
	public class MatchReport {
		public final Result result;
		public final Scorecard scorecard;
		public final OversScores oversScores;
		public final PartnershipsReport partnershipReport;
		
		public MatchReport() {
			result = getResult();
			scorecard = getScoreCard();
			oversScores = getOverScores();
			partnershipReport = getPartnershipsReport();
		}
	}
	
	public Match(MatchType matchType){
		team1 = new Team(matchType);
		team2 = new Team(matchType);
		team1.name = MATCH_CONSTANTS.TEAM_1_NAME;
		team2.name = MATCH_CONSTANTS.TEAM_2_NAME;
		this.noOfOvers = matchType.noOfOvers;
	}
	
	public void play(){
		firstInnings = Innings.getFirstInnings(team1, team2, noOfOvers);
		int target = firstInnings.play()+1;
		secondInnings = Innings.getSecondInnings(team2, team1, noOfOvers, target);
		secondInnings.play();
	}
	
	private Result getResult(){
		return new Result();
	}
	
	private Scorecard getScoreCard(){
		return new Scorecard() ;
	}
	
	private OversScores getOverScores(){
		return new OversScores();
	}
	
	private PartnershipsReport getPartnershipsReport() {
		return new PartnershipsReport();
	}
	
	public MatchReport getMatchReport(){
		return new MatchReport();
	}
	
}

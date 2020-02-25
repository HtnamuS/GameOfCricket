package htnamus.goc.DTO;

import htnamus.goc.model.Innings;

public class MatchResult {
	public final InningsReport firstInningsReport,
				secondInningsReport;
	public final String statement;
	public final int winMargin;
	public final String marginUnits;
	
	private static class InningsReport {
		public final String battingTeam,
				bowlingTeam;
		public final int score,
				wickets;
		public final float numberOfOvers;
		public InningsReport(Innings innings) {
			this.battingTeam = innings.getBattingTeam().getName();
			this.bowlingTeam = innings.getBowlingTeam().getName();
			this.score = innings.getCurrentScore();
			this.wickets = innings.getNumberOfWicketsDown();
			this.numberOfOvers = innings.getNumberOfOversPlayed() + (0.1f *innings.getDecimalBalls());
		}
	}
	public MatchResult(Innings firstInnings, Innings secondInnings) {
		firstInningsReport = new InningsReport(firstInnings);
		secondInningsReport = new InningsReport(secondInnings);
		if (firstInnings.getCurrentScore() > secondInnings.getCurrentScore()) {
			statement = firstInnings.getBattingTeam().getName() + " Wins!";
			winMargin = firstInnings.getCurrentScore() - secondInnings.getCurrentScore();
			marginUnits = "Runs";
		} else if (firstInnings.getCurrentScore() < secondInnings.getCurrentScore()) {
			statement = secondInnings.getBattingTeam().getName() + " Wins!";
			winMargin = 10 - secondInnings.getNumberOfWicketsDown();
			marginUnits = "Wickets";
		} else {
			statement = "Match Tied";
			winMargin = 0;
			marginUnits = "NA";
		}
	}
}

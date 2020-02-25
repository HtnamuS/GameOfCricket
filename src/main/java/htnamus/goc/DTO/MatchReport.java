package htnamus.goc.DTO;

import htnamus.goc.model.Innings;

public class MatchReport {
	public final MatchResult matchResult;
	public final MatchScorecard matchScorecard;
	public final MatchOversScores matchOversScores;
	public final PartnershipsReport partnershipReport;
	
	public MatchReport(Innings firstInnings, Innings secondInnings) {
		matchResult = new MatchResult(firstInnings, secondInnings);
		matchScorecard = new MatchScorecard(firstInnings, secondInnings);
		matchOversScores = new MatchOversScores(firstInnings, secondInnings);
		partnershipReport = new PartnershipsReport(firstInnings, secondInnings);
	}
}
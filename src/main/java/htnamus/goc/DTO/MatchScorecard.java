package htnamus.goc.DTO;

import htnamus.goc.model.Innings;

public class MatchScorecard {
	
	public final InningsScoreCard firstInningsScorecard,
				secondInningsScorecard;
	public MatchScorecard(Innings firstInnings, Innings secondInnings) {
		firstInningsScorecard = new InningsScoreCard(firstInnings);
		secondInningsScorecard = new InningsScoreCard(secondInnings);
	}
}

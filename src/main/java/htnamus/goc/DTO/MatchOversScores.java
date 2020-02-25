package htnamus.goc.DTO;

import htnamus.goc.model.Innings;

public class MatchOversScores {
	public final InningsOversScores firstInningsOversScores,
					secondInningsOversScores;
	
	public MatchOversScores(Innings firstInnings, Innings secondInnings) {
		this.firstInningsOversScores = new InningsOversScores(firstInnings);
		this.secondInningsOversScores = new InningsOversScores(secondInnings);
	}
}


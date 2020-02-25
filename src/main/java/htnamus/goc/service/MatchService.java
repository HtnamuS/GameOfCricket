package htnamus.goc.service;

import htnamus.goc.DTO.MatchReport;
import htnamus.goc.model.Innings;
import htnamus.goc.model.Match;

public class MatchService {
	
	InningsService inningsService;
	
	public MatchService() {
		inningsService = new InningsService();
	}
	
	public void playMatch(Match match) {
		Innings firstInnings = match.getFirstInnings(),
			secondInnings = match.getSecondInnings();
		inningsService.playInnings(firstInnings);
		int target = firstInnings.getCurrentScore() + 1;
		secondInnings.setTarget(target);
		inningsService.playInnings(match.getSecondInnings());
	}
	
	public MatchReport getMatchReport(Match match) {
		return new MatchReport(match.getFirstInnings(), match.getSecondInnings());
	}
}

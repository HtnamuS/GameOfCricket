package htnamus.goc.controller;

import htnamus.goc.DTO.MatchReport;
import htnamus.goc.model.Match;
import htnamus.goc.service.MatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
	Match match;
	MatchService matchService;
	
	@PostMapping("/start")
	public MatchReport startMatch() {
		match = new Match(Match.MatchType.ODI);
		matchService = new MatchService();
		matchService.playMatch(match);
		return matchService.getMatchReport(match);
	}
}

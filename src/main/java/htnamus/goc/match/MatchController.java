package htnamus.goc.match;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
	Match m;
	
	@PostMapping("/start")
	public Match.MatchReport startMatch() {
		m = new Match(Match.MatchType.ODI);
		m.play();
		return m.getMatchReport();
	}
}

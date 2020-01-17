package htnamus.goc.match;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
	@GetMapping("/start")
	public String startMatch(){
		Match m = new Match();
		m.start();
		return "hey";
	}
}

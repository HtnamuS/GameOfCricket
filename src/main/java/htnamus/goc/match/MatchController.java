package htnamus.goc.match;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
	Match m;
	@RequestMapping("/start")
	public String startMatch(){
		m = new Match(MatchType.ODI);
		return "<!DOCTYPE html>\n" +
			       "<html>\n" +
			       "<head>\n" +
			       "<style>\n" +
			       "table, th, td {\n" +
			       "  border: 1px solid black;\n" +
			       "}\n" +
			       "</style>\n" +
			       "</head>\n" +
			       "<body>\n" +
			       m.play() +
			       "\n <a href=\"/scorecard\">Scorecard</a>\n" +
			       "</body>\n" +
			       "</html>\n" +
			       "\n";
	}
	
	@RequestMapping("/scorecard")
	public String ScoreCard(){
		if(m == null){
			m = new Match(MatchType.ODI);
			m.play();
		}
		String result = "<!DOCTYPE html>\n" +
					
			                "<html>\n" +
			                "<head>\n" +
			                "<style>\n" +
			                "table, th, td {\n" +
			                "  border: 1px solid black;\n" +
			                "}\n" +
			                "</style>\n" +
			                "</head>\n" +
			                "<body>";
		
		result += m.getScoreCard();
		result+= "\n <a href=\"/start\">New match</a>\n" + "</body>\n" +
			         "</html>\n" +
			         "\n";
		return result;
	}
}

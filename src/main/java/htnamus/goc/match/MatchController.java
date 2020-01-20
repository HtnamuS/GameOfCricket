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
			       "\n <a href=\"/scorecard\">Scorecard</a></br>\n" +
			       "<a href=\"/overScores\">Over Scores</a></br>\n" +
			       "</body>\n" +
			       "</html>\n" +
			       "\n";
	}
	
	@RequestMapping("/scorecard")
	public String ScoreCard(){
		if(m == null){
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
				       "<h1> Match Not Played Yet</h1>\n" +
				       "<a href = \"/start\"> Start Match</a>\n" +
				       "</body>\n" +
				       "</html>\n" +
				       "\n";
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
		result+= "\n" +
			         "<a href=\"/overScores\">Over Scores</a></br>\n" +
			         " <a href=\"/start\">New match</a></br>\n" +
			         "</body>\n" +
			         "</html>\n" +
			         "\n";
		return result;
	}
	
	@RequestMapping("/overScores")
	public String overScores() {
		if(m == null){
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
				       "<h1> Match Not Played Yet</h1>\n" +
				       "<a href = \"/start\"> Start Match</a>\n" +
				       "</body>\n" +
				       "</html>\n" +
				       "\n";
				       
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
		result += m.getOverScores();
		result+= "\n" +
			         "<a href=\"/scorecard\">Scorecard</a></br>\n" +
			         " <a href=\"/start\">New match</a></br>\n" +
			         "</body>\n" +
			         "</html>\n" +
			         "\n";
		return result;
	}
	
}

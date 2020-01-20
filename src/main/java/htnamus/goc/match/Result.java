package htnamus.goc.match;


public class Result {
	public static String generate(Innings first, Innings second){
		String result = String.format("%s scored %d runs at a loss of %d wicket(s) in %d.%d overs<br />",first.battingTeam.name,first.score, first.wickets,
			first.nOvers,
			first.decimalBalls);
		result += String.format("%s scored %d runs at a loss of %d wicket(s) in %d.%d overs<br />",second.battingTeam.name,second.score, second.wickets,
			second.nOvers,
			second.decimalBalls);
		if(second.score>first.score){
			result+= String.format("%s wins the match with %d wicket(s) and ", second.battingTeam.name,(10-second.wickets));
			if(second.decimalBalls == 0)
				result+= String.format( "%d overs remaining", (second.totalOvers-second.nOvers));
			else
				result+= String.format( "%d.%d overs remaining", (second.totalOvers-second.nOvers-1),(6-second.decimalBalls));
		}
		else if(second.score < first.score){
			result+= String.format("%s wins the match with %d runs",first.battingTeam.name, (first.score-second.score));
		}
		else{
			result+= "Match tied";
		}
		return result + "<br />";
	}
	
}

package htnamus.goc.match;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Innings {
	protected Team battingTeam, bowlingTeam;
	protected int score, wickets, nOvers, totalOvers, decimalBalls;
	protected ArrayList<Over> overs;
	protected Player battingEnd, bowlingEnd, bowler;
	protected String InningsNumber;
	
	public String getScoreCard() {
		StringBuilder scoreCardTableBuilder = new StringBuilder("<h2>" + InningsNumber + " Innings:</h2>\n" +
			                                                        "<h3>" + battingTeam.name + " Batting:</h3>\n" +
			                                                        "<table>\n" +
			                                                        "  <tr>\n" +
			                                                        "    <th>Player Name</th>\n" +
			                                                        "    <th>Player Type</th>\n" +
			                                                        "    <th>Runs</th>\n" +
			                                                        "    <th>No. Of Balls Played</th>\n" +
			                                                        "    <th>No. Of Fours</th>\n" +
			                                                        "    <th>No. Of Sixes</th>\n" +
			                                                        "    <th>Strike Rate</th>\n" +
			                                                        "  </tr>\n");
		for (Player p :
			battingTeam.Playing11) {
			if (p.getNoOfBallsPlayed() == 0)
				scoreCardTableBuilder.append(String.format("  <tr>\n" +
					                                           "    <td>Player %d</td>\n" +
					                                           "    <td>%s</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "  </tr>\n", p.JerseyNo, p.type));
			else
				scoreCardTableBuilder.append(String.format("  <tr>\n" +
					                                           "    <td>Player %d</td>\n" +
					                                           "    <td>%s</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%.1f</td>\n" +
					                                           "  </tr>\n", p.JerseyNo, p.type, p.getScore(), p.getNoOfBallsPlayed(),
					p.getNoOfFours(), p.getNoOfSixes(),
					((float) p.getScore() * 100 / p.getNoOfBallsPlayed())));
		}
		
		scoreCardTableBuilder.append("</table> </br>\n" + "<h3>").append(bowlingTeam.name).append(" Bowling:</h3>\n").append("<table>\n").append("  <tr>\n").append("    <th>Player Name</th>\n").append("    <th>Player Type</th>\n").append("    <th>No. Of Overs Bowled</th>\n").append("    <th>No. Of Maidens</th>\n").append("    <th>No. Of Wickets Taken</th>\n").append("    <th>No. Of Runs Given</th>\n").append("    <th>Economy</th>\n").append("  </tr>\n");
		for (Player p :
			bowlingTeam.Bowlers) {
			if (p.getNoOfOversBowled() == 0 && p.getDecimalBallsBowled() == 0)
				scoreCardTableBuilder.append(String.format("  <tr>\n" +
					                                           "    <td>Player %d</td>\n" +
					                                           "    <td>%s</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "    <td>-</td>\n" +
					                                           "  </tr>\n", p.JerseyNo, p.type));
			else
				scoreCardTableBuilder.append(String.format("  <tr>\n" +
					                                           "    <td>Player %d</td>\n" +
					                                           "    <td>%s</td>\n" +
					                                           "    <td>%d.%d</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%d</td>\n" +
					                                           "    <td>%.1f</td>\n" +
					                                           "  </tr>\n", p.JerseyNo, p.type, p.getNoOfOversBowled(), p.getNoOfMaidens(),p.getDecimalBallsBowled(),
					p.getNoOfWicketsTaken(), p.getRunsGiven(),
					((float) 6 * p.getRunsGiven() / (float) (6 * p.getNoOfOversBowled() + p.getDecimalBallsBowled()))));
		}
		scoreCardTableBuilder.append("</table></br>");
		return scoreCardTableBuilder.toString();
	}
	
	public String getOverScores(){
		StringBuilder overScoresBuilder = new StringBuilder("<h2>" + InningsNumber + " Innings:</h2>\n" +
			                                                        "<h3>" + battingTeam.name + " Batting:</h3>\n" +
			                                                        "<table>\n" +
			                                                        "  <tr>\n" +
			                                                    "    <th>Over No.</th>\n" +
			                                                    "    <th>Over Total</th>\n" +
			                                                    "    <th>1st Ball</th>\n" +
			                                                    "    <th>2nd Ball</th>\n" +
			                                                    "    <th>3rd Ball</th>\n" +
			                                                    "    <th>4th Ball</th>\n" +
			                                                    "    <th>5th Ball</th>\n" +
			                                                    "    <th>6th Ball</th>\n" +
			                                                    "  </tr>\n");
		int i = 1;
		for(Over over: overs){
			overScoresBuilder.append(String.format("  <tr>\n" +
				                                       "    <th>Over %d</td>\n" +
				                                       " <th> %d </th>",
				i,
				Arrays.stream(over.balls).map(a -> {
					if(a == null || a.equals("W")){
						return 0;
					}
					return Integer.parseInt(a);
				} ).reduce(0, Integer::sum)));
			for (int j = 0; j < 6; j++) {
				if(over.balls[j] != null)
					overScoresBuilder.append(String.format("    <td>%s</td>\n", over.balls[j]));
				else
					overScoresBuilder.append("<td> - </td>\n");
			}
			i++;
		}
		overScoresBuilder.append("  <tr>\n" +
			                                       "<tr>");
		for (int j = 0; j < 8; j++) {
			if(j == 1)
				overScoresBuilder.append(String.format("    <th>%d</th>\n", score));
			else
				overScoresBuilder.append("    <td> - </td>\n");
		}
		overScoresBuilder.append("  <tr>\n");
		overScoresBuilder.append("</table></br>");
		return overScoresBuilder.toString();
	}
}
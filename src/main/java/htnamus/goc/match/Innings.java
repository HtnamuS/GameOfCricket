package htnamus.goc.match;

public abstract class Innings {
	protected Team battingTeam, bowlingTeam;
	protected int score, wickets, nOvers, totalOvers, decimalBalls;
	protected Over[] overs;
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
}
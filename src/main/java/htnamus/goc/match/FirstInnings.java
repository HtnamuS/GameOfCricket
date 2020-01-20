package htnamus.goc.match;

public class FirstInnings extends Innings {
	public FirstInnings(Team batting, Team bowling, int no) {
		this.battingTeam = batting;
		this.bowlingTeam = bowling;
		this.totalOvers = no;
		this.overs = new Over[totalOvers];
		this.bowler = null;
		this.InningsNumber = "First";
	}
	public int start(){
		battingEnd = battingTeam.nextBatsman();
		bowlingEnd = battingTeam.nextBatsman();
		Over curOver;
		for (int i = 0; i < this.totalOvers; i++) {
			this.bowler = bowlingTeam.nextBowler(this.bowler);
			curOver = this.overs[i] = new Over();
			curOver.play(battingTeam, battingEnd, bowlingEnd, bowler, this.wickets, score);
			int noOfBalls = curOver.noOfBalls;
			this.wickets = curOver.noOfWicketsDown;
			this.score  = curOver.curScore;
			battingEnd = curOver.battingEnd;
			bowlingEnd = curOver.bowlingEnd;
			this.decimalBalls = noOfBalls%6;
			nOvers += noOfBalls/6;
			if(wickets == 10){
				break;
			}
			try {
				bowler.incrementOvers();
			} catch (Player.WrongMethodOnPlayer wrongMethodOnPlayer) {
				wrongMethodOnPlayer.printStackTrace();
			}
			Player temp = bowlingEnd;
			bowlingEnd = battingEnd;
			battingEnd = temp;
		}
		return this.score;
	}
}

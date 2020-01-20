package htnamus.goc.match;

import java.util.ArrayList;

public class SecondInnings extends Innings {
	int target;
	public SecondInnings(Team batting, Team bowling, int noOfOvers, int target) {
		this.battingTeam = batting;
		this.bowlingTeam = bowling;
		this.target = target;
		totalOvers = noOfOvers;
		overs = new ArrayList<>();
		this.bowler = null;
		this.InningsNumber = "Second";
	}
	public void start(){
		battingEnd = battingTeam.nextBatsman();
		bowlingEnd = battingTeam.nextBatsman();
		for (int i = 0; i < this.totalOvers; i++) {
			this.bowler = bowlingTeam.nextBowler(this.bowler);
			Over curOver = new Over(target);
			overs.add(curOver);
			curOver.play(battingTeam, battingEnd, bowlingEnd, bowler, this.wickets, score);
			int noOfBalls = curOver.noOfBalls;
			this.wickets = curOver.noOfWicketsDown;
			this.score  = curOver.curScore;
			battingEnd = curOver.battingEnd;
			bowlingEnd = curOver.bowlingEnd;
			this.decimalBalls = noOfBalls%6;
			this.nOvers += noOfBalls/6;
			if(wickets == 10 || score>=target){
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
		System.out.println();
	}
}

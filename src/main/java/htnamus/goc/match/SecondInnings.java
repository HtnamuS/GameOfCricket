package htnamus.goc.match;

public class SecondInnings extends Innings {
	int target;
	public SecondInnings(Team batting, Team bowling, int noOfOvers, int target) {
		this.battingTeam = batting;
		this.bowlingTeam = bowling;
		this.target = target;
		totalOvers = noOfOvers;
		overs = new Over[totalOvers];
		this.bowler = null;
		this.InningsNumber = "Second";
	}
	public void start(){
		battingEnd = battingTeam.nextBatsman();
		bowlingEnd = battingTeam.nextBatsman();
		for (int i = 0; i < this.totalOvers; i++) {
			this.bowler = bowlingTeam.nextBowler(this.bowler);
			Over curOver = this.overs[i] = new Over();
			curOver.play(battingTeam, battingEnd, bowlingEnd, bowler, this.wickets, score);
			int noOfBalls = curOver.noOfBalls;
			this.wickets = curOver.noOfWicketsDown;
			this.score  = curOver.curScore;
			battingEnd = curOver.battingEnd;
			bowlingEnd = curOver.bowlingEnd;
			this.decimalBalls = noOfBalls%6;
			this.nOvers += noOfBalls/6;
			if(wickets == 10 || score>=target){
				System.out.println("score = " + score);
				System.out.println("target = " + target);
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

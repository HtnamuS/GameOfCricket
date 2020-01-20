package htnamus.goc.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Over {
	String[] balls;
	int curScore;
	int noOfBalls;
	int noOfWicketsDown;
	int target;
	Player battingEnd, bowlingEnd;
	public Over(){ // Constructor for first Innings
		target = Integer.MAX_VALUE;
	}
	public Over(int target){ // Constructor for Second Innings
		this.target =target;
	}
	
	public void play(Team battingTeam, Player BattingEnd, Player BowlingEnd, Player bowler, int wickets, int score) {
		balls = new String[6];
		curScore = score;
		this.noOfWicketsDown = wickets;
		this.battingEnd = BattingEnd;
		this.bowlingEnd = BowlingEnd;
		int runsThisOver = 0;
		for (int i = 0; i < 6; i++) {
			int runs = runsScored();
			boolean wicket = ((int)(Math.random()*40) == 0);
			battingEnd.incrementBallsPlayed();
			if(wicket){ //WICKET
				balls[i] = "W";
				noOfWicketsDown++;
				try {
					bowler.incrementWickets();
				} catch (Player.WrongMethodOnPlayer wrongMethodOnPlayer) {
					wrongMethodOnPlayer.printStackTrace();
				}
				if(noOfWicketsDown == 10){ //ALL out
					bowler.setDecimalBallsBowled(i+1);
					noOfBalls = i+1;
					return;
				}
				battingEnd = battingTeam.nextBatsman();
				continue;
			}
			balls[i] = String.valueOf(runs);
			curScore += runs;
			try {
				bowler.incrementRunsGiven(runs);
			} catch (Player.WrongMethodOnPlayer wrongMethodOnPlayer) {
				wrongMethodOnPlayer.printStackTrace();
			}
			
			battingEnd.incrementBallsPlayed();
			battingEnd.incrementScore(runs);
			runsThisOver+= runs;
			if(runs == 4){
				battingEnd.incrementFours();
			}
			if (runs == 6) {
				battingEnd.incrementSixes();
			}
			if(curScore >= target){
				bowler.setDecimalBallsBowled(i+1);
				noOfBalls = i+1;
				return;
			}
			if(runs % 2 != 0){
				Player temp = bowlingEnd;
				bowlingEnd = battingEnd;
				battingEnd = temp;
			}
		}
		noOfBalls = 6;
		if(runsThisOver == 0){
			
			try {
				bowler.incrementMaidens();
			} catch (Player.WrongMethodOnPlayer wrongMethodOnPlayer) {
				wrongMethodOnPlayer.printStackTrace();
			}
		}
	}
	private int runsScored(){
		Integer[] arr = {
			30, // 0
			32, // 1
			8, // 2
			6, // 3
			6, // 4
			1, //5
			2 //6
		};
		List<Integer> probs = Arrays.asList(arr);
		int rand = (int)(Math.random()*(probs.stream().mapToInt(a->a).sum()));
		
		if(rand<probs.get(0))
			return 0;
		if(rand- probs.subList(0,1).stream().mapToInt(a->a).sum() < probs.get(1) )
			return 1;
		if(rand - probs.subList(0,2).stream().mapToInt(a->a).sum() < probs.get(2))
			return 2;
		if(rand- probs.subList(0,3).stream().mapToInt(a->a).sum() < probs.get(3))
			return 3;
		if (rand - probs.subList(0,4).stream().mapToInt(a->a).sum() < probs.get(4)) {
			return 4;
		}
		if (rand - probs.subList(0,5).stream().mapToInt(a->a).sum() < probs.get(5)) {
			return 5;
		}
		else {
			return 6;
		}
	}
}

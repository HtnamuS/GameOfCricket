package htnamus.goc.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Over {
	public enum BallType{
		ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), WICKET;
		// Order of these values is extremely IMPORTANT. DO NOT CHANGE ORDER. IF NECESSARY ADD VALUES AT THE END
		int value;
		BallType(int val){ value = val; }
		BallType(){ }
		public String toString(){
			if(this == WICKET){
				return "W";
			}
			else{
				return String.valueOf(this.value);
			}
		}
	}
	
	private List<BallType> balls;
	private int curScore;
	private int noOfBalls;
	private int noOfWicketsDown;
	private Player battingEnd, bowlingEnd;
	private List<Innings.Partnership> newPartnershipsThisOver;
	private List<Player> batsmenThisOver;
	private Player bowler;
	private int runsThisOver;
	
	public void play(int noPrevOvers, Team battingTeam, Player BattingEnd, Player BowlingEnd, Player bowler, Innings.Partnership partnership, int wickets, int score,
	                 int target) {
		balls = new ArrayList<>(MATCH_CONSTANTS.NO_BALLS_PER_OVER);
		newPartnershipsThisOver = new ArrayList<>(2);
		batsmenThisOver = new ArrayList<>(List.of(BattingEnd,BowlingEnd));
		curScore = score;
		this.noOfWicketsDown = wickets;
		this.battingEnd = BattingEnd;
		this.bowlingEnd = BowlingEnd;
		this.bowler = bowler;
		runsThisOver = 0;
		
		for (int i = 0; i < MATCH_CONSTANTS.NO_BALLS_PER_OVER; i++) {
			int ball = playBall(battingEnd, bowler);
			boolean wicket = ball == -1;
			battingEnd.incrementBallsPlayed();
			if(wicket){ //WICKET
				balls.add( BallType.WICKET);
				noOfWicketsDown++;
				bowler.incrementWickets();
				partnership.generatePartnershipReport(curScore,noPrevOvers, i+1);
				if(noOfWicketsDown == MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM -1){ //ALL out
					bowler.setDecimalBallsBowled(i+1);
					noOfBalls = i+1;
					return;
				}
				battingEnd = battingTeam.nextBatsman();
				partnership = new Innings.Partnership(this.bowlingEnd, this.battingEnd, this.curScore, noPrevOvers, i + 1);
				newPartnershipsThisOver.add(partnership);
				continue;
			}
			balls.add(BallType.values()[ball]); // DEPENDS ON THE ORDER OF THE VALUES IN BallType. DO NOT CHANGE OR ELSE THIS FUNCTIONALITY WILL BE BROKEN
			curScore += ball;
			bowler.incrementRunsGiven(ball);
			// TODO: Ordinals to be removed, shorter functions
			battingEnd.incrementBallsPlayed();
			battingEnd.incrementScore(ball);
			runsThisOver+= ball;
			if(ball == 4){
				battingEnd.incrementFours();
			}
			else if (ball == 6) {
				battingEnd.incrementSixes();
			}
			
			if(curScore >= target){
				bowler.setDecimalBallsBowled(i+1);
				noOfBalls = i+1;
				partnership.generatePartnershipReport(curScore,noPrevOvers, i+1);
				return;
			}
			if(ball % 2 != 0){
				Player temp = bowlingEnd;
				bowlingEnd = battingEnd;
				battingEnd = temp;
			}
		}
		partnership.generatePartnershipReport(curScore,noPrevOvers, 6);
		noOfBalls = MATCH_CONSTANTS.NO_BALLS_PER_OVER;
		if(runsThisOver == 0){
			
			bowler.incrementMaidens();
		}
	}
	
	private int playBall(Player batsman, Player bowler){
		//WICKET
		float batWeight = 2000, bowlWeight = 10;
		float wicketThresh = 110;
		float probWicket =
			(batWeight/batsman.averageBallsPlayedPerInnings) + bowlWeight*(bowler.averageWicketsPer100Balls);
		if(Math.random()*probWicket > wicketThresh)
			return -1;
		
		//RUNS
		int SRWeight = 1;
		float variableWeight = 2000.0f;
		int probRun = (((SRWeight*batsman.averageStrikeRate) / 100) + (bowler.averageBowlingEconomy / 6)) / (1+SRWeight);
		Float[] arr = {
			40.0f + variableWeight/(0.5f + Math.abs(probRun)), // 0
			15.0f + variableWeight/(0.5f + Math.abs(probRun - 1)), // 1
			7.0f + variableWeight/(0.5f + Math.abs(probRun - 2)), // 2
			4.0f + variableWeight/(0.5f + Math.abs(probRun - 3)), // 3
			3.0f + variableWeight/(0.5f + Math.abs(probRun - 4)), // 4
			0.25f + 0.1f* variableWeight/(0.5f + Math.abs(probRun - 5)), //5
			1.5f + variableWeight/(0.5f + Math.abs(probRun - 6)) //6
		};
		List<Float> probs = Arrays.asList(arr);
		float rand = (float) (Math.random()*(probs.stream().reduce(0f,(sum, a)->sum+= a)));
		
		if(rand<probs.get(0))
			return 0;
		if(rand- probs.subList(0,1).stream().reduce(0f,(sum, a)->sum+= a) < probs.get(1) )
			return 1;
		if(rand - probs.subList(0,2).stream().reduce(0f,(sum, a)->sum+= a) < probs.get(2))
			return 2;
		if(rand- probs.subList(0,3).stream().reduce(0f,(sum, a)->sum+= a) < probs.get(3))
			return 3;
		if (rand - probs.subList(0,4).stream().reduce(0f,(sum, a)->sum+= a) < probs.get(4)) {
			return 4;
		}
		if (rand - probs.subList(0,5).stream().reduce(0f,(sum, a)->sum+= a) < probs.get(5)) {
			return 5;
		}
		else {
			return 6;
		}
		
	}
	
	public List<BallType> getBalls() {
		return balls;
	}
	
	public int getCurScore() {
		return curScore;
	}
	
	public int getNoOfBalls() {
		return noOfBalls;
	}
	
	public int getNoOfWicketsDown() {
		return noOfWicketsDown;
	}
	
	public Player getBattingEnd() {
		return battingEnd;
	}
	
	public Player getBowlingEnd() {
		return bowlingEnd;
	}
	
	public List<Innings.Partnership> getNewPartnershipsThisOver() {
		return newPartnershipsThisOver;
	}
	
	public List<Player> getBatsmenThisOver() {
		return batsmenThisOver;
	}
	
	public Player getBowler() {
		return bowler;
	}
	
	public int getRunsThisOver() {
		return runsThisOver;
	}
}

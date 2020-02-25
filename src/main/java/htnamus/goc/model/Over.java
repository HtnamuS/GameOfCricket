package htnamus.goc.model;

import htnamus.goc.DTO.TransientInningsDetails;
import htnamus.goc.util.Constants;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Over {
	
	private int currentScore,
		numberOfBallsBowled,
		numberOfWicketsDown,
		numberOfWicketThisOver,
		runsThisOver,
		numberOfPrevOvers,
		target;
	private Team battingTeam;
	private TransientInningsDetails transientInningsDetails;
	private List<BallResult> balls;
	private List<Partnership> newPartnershipsThisOver;
	private List<Player> batsmenThisOver;
	private static List<Integer> validRunsInBall = Stream.of(BallResult.values())
		                                        .filter(a -> a.value != Constants.NOT_A_RUN)
		                                        .map(a -> a.value)
		                                        .collect(Collectors.toList());
	public Over(Innings innings, TransientInningsDetails transientInningsDetails, int noPrevOvers) {
		this.currentScore = innings.getCurrentScore();
		this.numberOfWicketsDown = innings.getNumberOfWicketsDown();
		this.battingTeam = innings.getBattingTeam();
		this.transientInningsDetails = transientInningsDetails;
		this.numberOfPrevOvers = noPrevOvers;
		this.target = innings.getTarget();
		balls = new ArrayList<>(Constants.NO_BALLS_PER_OVER);
		newPartnershipsThisOver = new ArrayList<>();
		batsmenThisOver = new ArrayList<>(List.of(transientInningsDetails.getBattingEnd(),transientInningsDetails.getBowlingEnd()));
	}
	//GETTERS
	public List<BallResult> getBalls() {
		return Collections.unmodifiableList(balls);
	}
	public int getCurrentScore() {
		return currentScore;
	}
	public int getNumberOfBallsBowled() {
		return numberOfBallsBowled;
	}
	public int getNumberOfWicketsDown() {
		return numberOfWicketsDown;
	}
	public Player getBattingEnd() {
		return transientInningsDetails.getBattingEnd();
	}
	public Player getBowlingEnd() {
		return transientInningsDetails.getBowlingEnd();
	}
	public List<Partnership> getNewPartnershipsThisOver() {
		return Collections.unmodifiableList(newPartnershipsThisOver);
	}
	public List<Player> getBatsmenThisOver() {
		return Collections.unmodifiableList(batsmenThisOver);
	}
	public Player getBowler() {
		return transientInningsDetails.getBowler();
	}
	public int getRunsThisOver() {
		return runsThisOver;
	}
	public int getNumberOfPrevOvers() {
		return numberOfPrevOvers;
	}
	public int getTarget() {
		return target;
	}
	public Team getBattingTeam() {
		return battingTeam;
	}
	public Partnership getPartnership() {
		return transientInningsDetails.getCurrentPartnership();
	}
	public int getNumberOfWicketThisOver() {
		return numberOfWicketThisOver;
	}
	
	//MUTATORS
	public void incrementNumberOfWickets() {
		this.numberOfWicketsDown++;
		this.numberOfWicketThisOver++;
	}
	public void incrementNumberOfBallsBowled(){
		this.numberOfBallsBowled++;
	}
	public void newPartnership(int ballIndex){
		int noBallsPlayed = numberOfPrevOvers * 6 + ballIndex;
		Partnership newPartnership = Partnership.getMidPlayPartnership(this.transientInningsDetails, noBallsPlayed, currentScore);
		transientInningsDetails.setCurrentPartnership(newPartnership);
		this.newPartnershipsThisOver.add(newPartnership);
	}
	public void setBattingEnd(Player newBattingEnd){
		transientInningsDetails.setBattingEnd(newBattingEnd);
	}
	public void setBowlingEnd(Player newBowlingEnd) {
		transientInningsDetails.setBattingEnd(newBowlingEnd);
	}
	public void incrementScoreAndRuns(int runsThisBall){
		if(validRunsInBall.contains(runsThisBall)){
			this.currentScore += runsThisBall;
			this.runsThisOver += runsThisBall;
			transientInningsDetails.getBattingEnd().incrementScore(runsThisBall);
			transientInningsDetails.getBowler().incrementRunsGiven(runsThisBall);
		}
		else{
			throw new ArithmeticException();
		}
	}
	public void addBall(BallResult ballResult){
		balls.add(Objects.requireNonNull(ballResult, "No Matching Ball Result Found"));
	}

	public void setNumberOfBallsBowled(int numberOfBallsBowled) {
		this.numberOfBallsBowled = numberOfBallsBowled;
	}
}

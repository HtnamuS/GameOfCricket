package htnamus.goc.model;

import htnamus.goc.util.Constants;

import java.util.*;
import java.util.stream.Collectors;


public class Innings {
	public enum InningsStatus{
		AllOut, OversCompleted, TargetReached, OnGoing;
		public boolean isCompleted(){
			return this == AllOut || this == OversCompleted || this == TargetReached;
		}
	}
	private final Team battingTeam, bowlingTeam;
	private int currentScore,
		numberOfWicketsDown,
		numberOfOversPlayed,
		decimalBalls,
		target;
	private final int totalNumberOfOvers;
	private List<Over> overs;
	
	private List<Partnership> partnerships;
	
	public Innings(Team batting, Team bowling, int totalNumberOfOvers) {
		this.battingTeam = batting;
		this.bowlingTeam = bowling;
		this.totalNumberOfOvers = totalNumberOfOvers;
		this.overs = new ArrayList<>();
		this.partnerships = new ArrayList<>();
		this.target = Constants.NO_TARGET;
	}
	
	//GETTERS
	public int getCurrentScore() {
		return currentScore;
	}
	public int getNumberOfWicketsDown() {
		return numberOfWicketsDown;
	}
	public int getNumberOfOversPlayed() {
		return numberOfOversPlayed;
	}
	public int getDecimalBalls() {
		return decimalBalls;
	}
	public Team getBattingTeam() {
		return battingTeam;
	}
	public Team getBowlingTeam(){
		return bowlingTeam;
	}
	public List<Partnership.PartnershipReport> getPartnershipsReport() {
		return Collections.unmodifiableList(partnerships.stream().map(a -> a.partnershipReport).collect(Collectors.toList()));
	}
	public int getTotalNumberOfOvers() {
		return totalNumberOfOvers;
	}
	public int getTarget() {
		return target;
	}
	public List<Over> getOvers(){
		return Collections.unmodifiableList(overs);
	}
	//MUTATORS
	public void addOverRecord(Over curOver){
		this.overs.add(curOver);
	}
	public void setNumberOfWicketsDown(int numberOfWicketsDown) {
		this.numberOfWicketsDown = numberOfWicketsDown;
	}
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	public void addNumberOfOversPlayed(int numberOfOversPlayed) {
		this.numberOfOversPlayed += numberOfOversPlayed;
	}
	public void setDecimalBalls(int decimalBalls) {
		this.decimalBalls = decimalBalls;
	}
	public void addPartnerships(List<Partnership> partnerships) {
		this.partnerships.addAll(partnerships);
	}
	public void setTarget(int target) {
		this.target = target;
	}
	
	//MISC FUNCTIONS
	public boolean checkInningsCompleted(){
		return numberOfWicketsDown == Constants.NO_OF_PLAYERS_PER_TEAM - 1 || currentScore >= target;
	}
}
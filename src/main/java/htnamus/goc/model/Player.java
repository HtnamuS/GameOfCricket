package htnamus.goc.model;

public class Player {
	public enum PlayerType{
		BATTING, BOWLING, ALLROUNDER
	}
	private int score,
		noOfBallsPlayed,
		noOfOversBowled,
		noOfWicketsTaken,
		runsGiven,
		decimalBallsBowled,
		noOfFours,
		noOfSixes,
		noOfMaidens;
	private final int JerseyNo;
	private final String name;
	private final PlayerStats playerStats;
	private final PlayerType playerType;
	
	
	public Player(PlayerType playerType, String name,int jerseyNo, PlayerStats playerStats) {
		this.playerType = playerType;
		this.name = name;
		this.JerseyNo = jerseyNo;
		this.playerStats = playerStats;
	}
	
	public void incrementBallsPlayed(){
		noOfBallsPlayed++;
	}
	public void incrementScore(int runs) {
		score+= runs;
	}
	public void incrementOversBowled() {
		this.noOfOversBowled++;
	}
	public void setDecimalBallsBowled(int decimalBallsBowled) {
		this.decimalBallsBowled = decimalBallsBowled;
	}
	public void incrementWickets(){
		noOfWicketsTaken++;
	}
	public void incrementRunsGiven(int runs) {
		runsGiven += runs;
	}
	public int getScore() {
		return score;
	}
	public int getNoOfBallsPlayed() {
		return noOfBallsPlayed;
	}
	public int getNoOfOversBowled() {
		return noOfOversBowled;
	}
	public int getNoOfWicketsTaken() {
		return noOfWicketsTaken;
	}
	public int getRunsGiven() {
		return runsGiven;
	}
	public int getDecimalBallsBowled() {
		return decimalBallsBowled;
	}
	public PlayerType getPlayerType() {
		return playerType;
	}
	public void incrementFours() {
		this.noOfFours ++;
	}
	public void incrementSixes() {
		this.noOfSixes ++;
	}
	public int getNoOfFours() {
		return noOfFours;
	}
	public int getNoOfSixes() {
		return noOfSixes;
	}
	public void incrementMaidens() {
		this.noOfMaidens++;
	}
	public int getNoOfMaidens(){
		return noOfMaidens;
	}
	public int getJerseyNo() {
		return JerseyNo;
	}
	public String getName() {
		return name;
	}
	public PlayerStats getPlayerStats() {
		return playerStats;
	}
}

package htnamus.goc.match;

public class Player {
	private int score, noOfBallsPlayed, noOfOversBowled, noOfWicketsTaken, runsGiven, decimalBallsBowled, noOfFours, noOfSixes, noOfMaidens;
	public int JerseyNo;
	PlayerType type;
	public Player(PlayerType type, int i ){
		this.type = type;
		noOfBallsPlayed = 0;
		JerseyNo = i;
	}
	public void incrementBallsPlayed(){
		noOfBallsPlayed++;
	}
	public void incrementScore(int runs) {
		score+= runs;
	}
	public void incrementOvers() throws WrongMethodOnPlayer {
		if(type == PlayerType.BATSMAN){
			throw new WrongMethodOnPlayer("Player is Batsman only");
		}
		this.noOfOversBowled++;
	}
	
	public Player setDecimalBallsBowled(int decimalBallsBowled) {
		this.decimalBallsBowled = decimalBallsBowled;
		return this;
	}
	
	public void incrementWickets() throws WrongMethodOnPlayer{
		if(type == PlayerType.BATSMAN){
			throw new WrongMethodOnPlayer("Player is Batsman only");
		}
		noOfWicketsTaken++;
	}
	public void incrementRunsGiven(int runs) throws WrongMethodOnPlayer {
		if(type == PlayerType.BATSMAN){
			throw new WrongMethodOnPlayer("Player is Batsman only");
		}
		runsGiven += runs;
	}
	public class WrongMethodOnPlayer extends Exception{
		public WrongMethodOnPlayer(String s) {
			super(s);
		}
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
	
	public PlayerType getType() {
		return type;
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
	public void incrementMaidens() throws WrongMethodOnPlayer {
		if(type == PlayerType.BATSMAN){
			throw new WrongMethodOnPlayer("Player is Batsman only");
		}
		this.noOfMaidens++;
	}
	
	public int getNoOfMaidens(){
		return noOfMaidens;
	}
}

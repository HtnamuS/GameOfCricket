package htnamus.goc.match;

public class Player {
	private int score, noOfBalls;
	PlayerType type;
	private Player(){ }
	public Player(PlayerType type){
		this.type = type;
	}
}

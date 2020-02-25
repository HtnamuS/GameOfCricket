package htnamus.goc.DTO;

import htnamus.goc.model.Over;
import htnamus.goc.model.Partnership;
import htnamus.goc.model.Player;
import htnamus.goc.model.Team;


public class TransientInningsDetails {
	private Player battingEnd,
			bowlingEnd,
			bowler;
	private Over currentOver;
	private Partnership currentPartnership;
	
	
	public Player getBattingEnd() {
		return battingEnd;
	}
	
	public void setBattingEnd(Player battingEnd) {
		this.battingEnd = battingEnd;
	}
	
	public Player getBowlingEnd() {
		return bowlingEnd;
	}
	
	public void setBowlingEnd(Player bowlingEnd) {
		this.bowlingEnd = bowlingEnd;
	}
	
	public Player getBowler() {
		return bowler;
	}
	
	public void setBowler(Player bowler) {
		this.bowler = bowler;
	}
	
	public Over getCurrentOver() {
		return currentOver;
	}
	
	public void setCurrentOver(Over currentOver) {
		this.currentOver = currentOver;
	}
	
	public Partnership getCurrentPartnership() {
		return currentPartnership;
	}
	
	public void setCurrentPartnership(Partnership currentPartnership) {
		this.currentPartnership = currentPartnership;
	}
}
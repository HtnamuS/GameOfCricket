package htnamus.goc.DTO;

import htnamus.goc.model.Innings;

public class InningsScoreCard {
	
	public BattingScoreCard battingScoreCard;
	public BowlingScoreCard bowlingScoreCard;
	
	public InningsScoreCard(Innings innings) {
		this.battingScoreCard = new BattingScoreCard(innings);
		this.bowlingScoreCard = new BowlingScoreCard(innings);
	}
}
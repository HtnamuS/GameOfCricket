package htnamus.goc.DTO;

import htnamus.goc.model.Innings;
import htnamus.goc.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingScoreCard {
	public List<BowlerScoreCard> bowlersScoreCard;
	private static class BowlerScoreCard {
		final public String playerName;
		final public Player.PlayerType playerType;
		final public int jerseyNo,
				noOfMaidens,
				noOfWickets,
				noOfRunsGiven;
		final public float noOfOvers;
		
		public BowlerScoreCard(Player bowler) {
			this.jerseyNo = bowler.getJerseyNo();
			this.playerName = bowler.getName();
			this.playerType = bowler.getPlayerType();
			this.noOfOvers = bowler.getNoOfOversBowled() + 0.1f * bowler.getDecimalBallsBowled();
			this.noOfMaidens = bowler.getNoOfMaidens();
			this.noOfWickets = bowler.getNoOfWicketsTaken();
			this.noOfRunsGiven = bowler.getRunsGiven();
		}
	}
	
	public BowlingScoreCard(Innings innings) {
		List<BowlerScoreCard> mutableBowlersScoreCard = new ArrayList<>(innings.getBowlingTeam().getBowlers().size());
		bowlersScoreCard = Collections.unmodifiableList(mutableBowlersScoreCard);
		for (Player bowler : innings.getBowlingTeam().getBowlers()) {
			if (bowler.getNoOfOversBowled() == 0 && bowler.getDecimalBallsBowled() == 0) continue;
			BowlerScoreCard bowlerScoreCard =  new BowlerScoreCard(bowler);
			mutableBowlersScoreCard.add(bowlerScoreCard);
		}
	}
}
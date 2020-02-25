package htnamus.goc.DTO;

import htnamus.goc.model.Innings;
import htnamus.goc.model.Player;
import htnamus.goc.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattingScoreCard {
	public List<BatsmanScoreCard> battingScorecard;
	
	private static class BatsmanScoreCard {
		final public String playerName;
		final public Player.PlayerType playerType;
		final public int jerseyNo,
				runs,
				noOfBalls,
				noOfFours,
				noOfSixes;
		
		public BatsmanScoreCard(Player batsman) {
			this.jerseyNo = batsman.getJerseyNo();
			this.playerName = batsman.getName();
			this.playerType = batsman.getPlayerType();
			this.runs = batsman.getScore();
			this.noOfBalls = batsman.getNoOfBallsPlayed();
			this.noOfFours = batsman.getNoOfFours();
			this.noOfSixes = batsman.getNoOfSixes();
		}
	}
	public BattingScoreCard(Innings innings) {
		List<BatsmanScoreCard> mutableBattingScorecard = new ArrayList<>(Constants.NO_OF_PLAYERS_PER_TEAM);
		battingScorecard = Collections.unmodifiableList(mutableBattingScorecard);
		for (Player batsman : innings.getBattingTeam().getPlayingRoster()) {
			if (batsman.getNoOfBallsPlayed() == 0) continue;
			BatsmanScoreCard batsmanScoreCard = new BatsmanScoreCard(batsman);
			mutableBattingScorecard.add(batsmanScoreCard);
		}
	}
}
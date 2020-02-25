package htnamus.goc.DTO;

import htnamus.goc.model.BallResult;
import htnamus.goc.model.Innings;
import htnamus.goc.model.Over;
import htnamus.goc.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InningsOversScores {
	public List<OverScores> oversScores;
	
	private static class OverScores {
		final public int runs,
				wickets;
		final public String bowler;
		final public List<String> balls,
					batmenThisOver;
		
		public OverScores(Over over) {
			this.runs = over.getRunsThisOver();
			this.wickets = over.getNumberOfWicketThisOver();
			this.bowler = over.getBowler().getName();
			this.balls = over.getBalls().stream().map(BallResult::toString).collect(Collectors.toList());
			this.batmenThisOver = over.getBatsmenThisOver().stream().map(Player::getName).collect(Collectors.toList());
		}
	}
	public InningsOversScores(Innings innings) {
		List<OverScores> mutableOverScores = new ArrayList<>(innings.getNumberOfOversPlayed());
		oversScores = Collections.unmodifiableList(mutableOverScores);
		for (Over over : innings.getOvers()) {
			OverScores overScores = new OverScores(over);
			mutableOverScores.add(overScores);
		}
	}
}
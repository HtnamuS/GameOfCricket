package htnamus.goc.service;

import htnamus.goc.model.Player;
import htnamus.goc.model.Team;

import java.util.List;

public class TeamService {
	
	public Player nextBatsman(Team BattingTeam){
		Player next = BattingTeam.getPlayingRoster().get(BattingTeam.getNextBatsmanIndex());
		BattingTeam.incrementNextBatsmanIndex();
		return next;
	}
	
	public Player nextBowler(Team BowlingTeam, Player prevBowler){
		List<Player> Bowlers = BowlingTeam.getBowlers();
		Player nextBowler = Bowlers.get((int) (Math.random() * Bowlers.size()));
		while(nextBowler == prevBowler || nextBowler.getNoOfOversBowled() >= BowlingTeam.getMatchType().totalNumberOfOversPerBowlerPerInnings){
			nextBowler = Bowlers.get((int) (Math.random() * Bowlers.size()));
		}
		return nextBowler;
	}
}

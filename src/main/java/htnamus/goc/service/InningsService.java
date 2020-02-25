package htnamus.goc.service;

import htnamus.goc.DTO.TransientInningsDetails;
import htnamus.goc.model.*;
import htnamus.goc.util.Constants;

import java.util.List;

public class InningsService {
	OverService overService;
	TeamService teamService;
	
	public InningsService() {
		this.overService = new OverService();
		this.teamService = new TeamService();
	}
	
	public void playInnings(Innings innings) {
		TransientInningsDetails transientInningsDetails = new TransientInningsDetails();
		setInitialTransientInningsDetails(transientInningsDetails,innings);
		
		for (int overIndex = 0; overIndex < innings.getTotalNumberOfOvers(); overIndex++) {
			createNextOver(transientInningsDetails, innings, overIndex);
			overService.playOver(transientInningsDetails.getCurrentOver());
			updateInningsDetailsAfterOver(innings, transientInningsDetails.getCurrentOver());
			if (innings.checkInningsCompleted()) { break; }
		}
		transientInningsDetails.getCurrentPartnership().generatePartnershipReport(innings.getCurrentScore(), innings.getNumberOfOversPlayed(), innings.getDecimalBalls());
	}
	
	private void updateInningsDetailsAfterOver(Innings innings, Over curOver){
		int noOfBalls = curOver.getNumberOfBallsBowled();
		innings.addOverRecord(curOver);
		innings.setNumberOfWicketsDown(curOver.getNumberOfWicketsDown());
		innings.setCurrentScore(curOver.getCurrentScore());
		innings.setDecimalBalls(noOfBalls % Constants.NO_BALLS_PER_OVER);
		innings.addNumberOfOversPlayed(noOfBalls / Constants.NO_BALLS_PER_OVER);
		innings.addPartnerships(curOver.getNewPartnershipsThisOver());
	}

	private void setInitialTransientInningsDetails(TransientInningsDetails transientInningsDetails, Innings innings){
		transientInningsDetails.setBattingEnd(teamService.nextBatsman(innings.getBattingTeam()));
		transientInningsDetails.setBowlingEnd(teamService.nextBatsman(innings.getBattingTeam()));
		Partnership newPartnership = Partnership.getInitialPartnership(transientInningsDetails);
		transientInningsDetails.setCurrentPartnership(newPartnership);
		innings.addPartnerships(List.of(transientInningsDetails.getCurrentPartnership()));
	}
	private void createNextOver(TransientInningsDetails transientInningsDetails, Innings innings, int overIndex){
		transientInningsDetails.setBowler(teamService.nextBowler(innings.getBowlingTeam(), transientInningsDetails.getBowler()));
		Over currentOver = new Over(innings, transientInningsDetails,overIndex);
		transientInningsDetails.setCurrentOver(currentOver);
	}
}

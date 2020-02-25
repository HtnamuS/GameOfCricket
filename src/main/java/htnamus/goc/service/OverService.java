package htnamus.goc.service;

import htnamus.goc.model.*;
import htnamus.goc.util.Constants;

import java.util.List;

public class OverService {
	TeamService teamService;
	
	public OverService() {
		this.teamService = new TeamService();
	}
	
	public void playOver(Over over) {
		for (int ballIndex = 0; ballIndex < Constants.NO_BALLS_PER_OVER; ballIndex++) {
			BallResult ballResult = playBall(over.getBattingEnd(), over.getBowler());
			Innings.InningsStatus inningsStatus = afterBallHandler(over, ballResult, ballIndex);
			if(inningsStatus.isCompleted()){ break; }
		}
		over.setNumberOfBallsBowled(Constants.NO_BALLS_PER_OVER);
		if(over.getRunsThisOver() == 0){
			over.getBowler().incrementMaidens();
		}
		over.getBowler().incrementOversBowled();
	}
	
	private BallResult playBall(Player batsman, Player bowler){
		PlayerStats batsmanStats = batsman.getPlayerStats(),
			bowlerStats = bowler.getPlayerStats();
		
		float wicketFallChanceThreshold = 110;
		float chanceOfWicket = getChanceOfWicket(batsmanStats, bowlerStats);
		if(chanceOfWicket > wicketFallChanceThreshold)
			return BallResult.getBallResult("W");
		
		List<Float> runChances =  getAllRunsChances(batsmanStats, bowlerStats);
		return getRun(runChances);
	}
	
	private float getChanceOfWicket(PlayerStats batsmanStats, PlayerStats bowlerStats){
		int batWeight = 2000, bowlWeight = 10;
		float batsmanWicketFallStat = ((float) batWeight / batsmanStats.getAverageBallsPlayedPerInnings());
		float bowlerWicketTakingStat = (float)bowlWeight*(bowlerStats.getAverageWicketsPer100Balls());
		float wicketFallStat = batsmanWicketFallStat + bowlerWicketTakingStat;
		return (float)(Math.random()*wicketFallStat);
	}
	
	private List<Float> getAllRunsChances(PlayerStats batsmanStats, PlayerStats bowlerStats){
		float variableWeight = 2000.0f;
		int batsmanRunsStat = batsmanStats.getAverageStrikeRate()/100;
		int bowlerRunsStat = bowlerStats.getAverageBowlingEconomy()/6;
		int probRun = (batsmanRunsStat + bowlerRunsStat)/2;
		@SuppressWarnings("UnnecessaryLocalVariable")
		List<Float> runChances = List.of(
			40.0f + variableWeight/(0.5f + Math.abs(probRun)), // 0
			15.0f + variableWeight/(0.5f + Math.abs(probRun - 1)), // 1
			7.0f + variableWeight/(0.5f + Math.abs(probRun - 2)), // 2
			4.0f + variableWeight/(0.5f + Math.abs(probRun - 3)), // 3
			1.5f + variableWeight/(0.5f + Math.abs(probRun - 4)), // 4
			0.05f + 0.1f* variableWeight/(0.5f + Math.abs(probRun - 5)), //5
			0.5f + variableWeight/(0.5f + Math.abs(probRun - 6)) //6
		);
		return runChances;
	}
	
	private BallResult getRun(List<Float> runChances){
		@SuppressWarnings("UnnecessaryBoxing")
		float runChancesSum = runChances.stream().reduce(Float.valueOf(0f),(sum, a)->sum+= a);
		float random = ((float)Math.random()*(runChancesSum));
		if((random -= runChances.get(0)) <0) return BallResult.getBallResult(0);
		if((random -= runChances.get(1)) <0 ) return BallResult.getBallResult(1);
		if((random -= runChances.get(2)) <0) return BallResult.getBallResult(2);
		if((random -= runChances.get(3)) <0) return BallResult.getBallResult(3);
		if ((random -= runChances.get(4)) <0) return BallResult.getBallResult(4);
		if ((random - runChances.get(5)) <0) return BallResult.getBallResult(5);
		else return BallResult.getBallResult(6);
	}
	
	private Innings.InningsStatus wicketHandler(Over over, int ballIndex){
		over.incrementNumberOfWickets();
		over.getBowler().incrementWickets();
		Partnership completedPartnership = over.getPartnership();
		completedPartnership.generatePartnershipReport(over.getCurrentScore(),over.getNumberOfPrevOvers(), ballIndex+1);
		if(over.getNumberOfWicketsDown() == Constants.NO_OF_PLAYERS_PER_TEAM -1){
			over.getBowler().setDecimalBallsBowled(ballIndex+1);
			over.incrementNumberOfBallsBowled();
			return Innings.InningsStatus.AllOut;
		}
		Player nextBatsman = teamService.nextBatsman(over.getBattingTeam());
		over.setBattingEnd(nextBatsman);
		over.newPartnership(ballIndex);
		return Innings.InningsStatus.OnGoing;
	}
	
	private Innings.InningsStatus afterBallHandler(Over over, BallResult ballResult, int ballIndex){
		over.getBattingEnd().incrementBallsPlayed();
		over.addBall(ballResult);
		if(ballResult == BallResult.WICKET){
			return wicketHandler(over, ballIndex);
		}
		updateScores(over, ballResult);
		if (over.getCurrentScore() >= over.getTarget()) {
			targetReachedHandler(over, ballIndex);
			return Innings.InningsStatus.TargetReached;
		}
		if (ballResult.didStrikeChange()) {
			over.swapBatsmen();
		}
		return Innings.InningsStatus.OnGoing;
	}
	private void updateScores(Over over, BallResult ballResult){
		over.incrementScoreAndRuns(ballResult.getValue());
		over.getBattingEnd().incrementBallsPlayed();
		if (ballResult == BallResult.FOUR) {
			over.getBattingEnd().incrementFours();
		} else if (ballResult == BallResult.SIX) {
			over.getBattingEnd().incrementSixes();
		}
	}
	private void targetReachedHandler(Over over, int ballIndex){
		over.getBowler().setDecimalBallsBowled(ballIndex + 1);
		over.setNumberOfBallsBowled(ballIndex + 1);
	}
}


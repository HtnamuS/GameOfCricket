package htnamus.goc.model;

import htnamus.goc.DTO.TransientInningsDetails;

public class Partnership {
	private final Player batsman1, batsman2;
	private final int initScore, initBallsPlayed, batsman1InitScore, batsman2InitScore;
	public Partnership.PartnershipReport partnershipReport;
	
	private Partnership(TransientInningsDetails transientInningsDetails, int initBallsPlayed, int initScore) {
		this.batsman1 = transientInningsDetails.getBattingEnd();
		this.batsman2 = transientInningsDetails.getBowlingEnd();
		this.batsman1InitScore = batsman1.getScore();
		this.batsman2InitScore = batsman2.getScore();
		this.initBallsPlayed = initBallsPlayed;
		this.initScore = initScore;
	}
	
	public static Partnership getInitialPartnership(TransientInningsDetails transientInningsDetails){
		return new Partnership(transientInningsDetails, 0, 0);
	}
	
	public static Partnership getMidPlayPartnership(TransientInningsDetails transientInningsDetails, int initBallsPlayed, int initScore){
		return new Partnership(transientInningsDetails, initBallsPlayed, initScore);
	}
	public class PartnershipReport {
		public final String batsman1, batsman2;
		public final int score, noOfBalls, batsman1Contribution, batsman2Contribution;
		
		private PartnershipReport(Player batsman1, Player batsman2, int score, int noOfBalls) {
			this.batsman1 = batsman1.getName();
			this.batsman2 = batsman2.getName();
			this.score = score;
			this.noOfBalls = noOfBalls;
			this.batsman1Contribution = batsman1.getScore() - batsman1InitScore;
			this.batsman2Contribution = batsman2.getScore() - batsman2InitScore;
		}
		
	}
	
	public void generatePartnershipReport(int finalScore, int finalNOvers, int finalNDecBalls) {
		partnershipReport = new Partnership.PartnershipReport(batsman1, batsman2, finalScore - initScore, finalNOvers * 6 + finalNDecBalls - initBallsPlayed);
	}
}
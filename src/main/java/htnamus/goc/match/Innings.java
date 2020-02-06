package htnamus.goc.match;

import java.util.*;
import java.util.stream.Collectors;


public class Innings {
	private Team battingTeam, bowlingTeam;
	private int score, wickets, nOvers, totalOvers, decimalBalls, target;
	private ArrayList<Over> overs;
	private Player bowler;
	
	private List<Partnership> partnerships;
	
	public class InningsScoreCard {
		
		public BattingScoreCard battingScoreCard;
		public BowlingScoreCard bowlingScoreCard;
		
		private class BattingScoreCard {
			public List<BatsmanScoreCard> battingScorecard;
			
			private class BatsmanScoreCard {
				final public int jerseyNo;
				final public String playerName;
				final public Player.PlayerType playerType;
				final public int runs, noOfBalls, noOfFours, noOfSixes;
				
				public BatsmanScoreCard(int jerseyNo, String name, Player.PlayerType playerType, int runs, int noOfBalls, int noOfFours, int noOfSixes) {
					this.jerseyNo = jerseyNo;
					this.playerName = name;
					this.playerType = playerType;
					this.runs = runs;
					this.noOfBalls = noOfBalls;
					this.noOfFours = noOfFours;
					this.noOfSixes = noOfSixes;
				}
			}
			
			public BattingScoreCard() {
				List<BatsmanScoreCard> mutableBattingScorecard = new ArrayList<>(MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM);
				battingScorecard = Collections.unmodifiableList(mutableBattingScorecard);
				for (Player player : battingTeam.PlayingTeam) {
					if (player.getNoOfBallsPlayed() == 0)
						continue;
					mutableBattingScorecard.add(new BatsmanScoreCard(player.getJerseyNo(), player.getName(), player.getPlayerType(), player.getScore(),
						player.getNoOfBallsPlayed(), player.getNoOfFours(), player.getNoOfSixes()));
				}
			}
		}
		
		private class BowlingScoreCard {
			public List<BowlerScoreCard> bowlerScoreCard;
			private class BowlerScoreCard {
				final public int jerseyNo;
				final public String playerName;
				final public Player.PlayerType playerType;
				final public int noOfMaidens, noOfWickets, noOfRunsGiven;
				final public float noOfOvers;
				
				public BowlerScoreCard(int jerseyNo, String name, Player.PlayerType playerType, float noOfOvers, int noOfMaidens, int noOfWickets,
				                       int noOfRunsGiven) {
					this.jerseyNo = jerseyNo;
					this.playerName = name;
					this.playerType = playerType;
					this.noOfOvers = noOfOvers;
					this.noOfMaidens = noOfMaidens;
					this.noOfWickets = noOfWickets;
					this.noOfRunsGiven = noOfRunsGiven;
				}
			}
			
			
			public BowlingScoreCard() {
				List<BowlerScoreCard> mutableBowlerScoreCard = new ArrayList<>(bowlingTeam.Bowlers.size());
				bowlerScoreCard = Collections.unmodifiableList(mutableBowlerScoreCard);
				for (Player player : bowlingTeam.Bowlers) {
					if (player.getNoOfOversBowled() == 0 && player.getDecimalBallsBowled() == 0)
						continue;
					mutableBowlerScoreCard.add(new BowlerScoreCard(player.getJerseyNo(), player.getName(), player.getPlayerType(),
						player.getNoOfOversBowled() + 0.1f * player.getDecimalBallsBowled(),
						player.getNoOfMaidens(), player.getNoOfWicketsTaken(), player.getRunsGiven()));
				}
			}
		}
		
		private InningsScoreCard() {
			this.battingScoreCard = new BattingScoreCard();
			this.bowlingScoreCard = new BowlingScoreCard();
		}
	}
	
	public class OversScores {
		public List<OverScores> oversScores;
		
		private class OverScores {
			final public int runs, wickets;
			final public String bowler;
			final public List<String> balls;
			final public List<String> batmenThisOver;
			
			public OverScores(int runs, int wickets, Player bowler, List<Over.BallType> balls, List<Player> batmenThisOver) {
				this.runs = runs;
				this.wickets = wickets;
				this.bowler = bowler.getName();
				this.balls = balls.stream().map(Over.BallType::toString).collect(Collectors.toList());
				this.batmenThisOver = batmenThisOver.stream().map(Player::getName).collect(Collectors.toList());
			}
		}
		
		public OversScores() {
			List<OverScores> mutableOverScores = new ArrayList<>(nOvers);
			oversScores = Collections.unmodifiableList(mutableOverScores);
			for (Over over : overs) {
				mutableOverScores.add(new OverScores(over.getRunsThisOver(), over.getNoOfWicketsDown(), over.getBowler(), over.getBalls(),
					over.getBatsmenThisOver()));
			}
		}
	}
	
	public static class Partnership {
		private final Player batsman1, batsman2;
		private final int initScore, initBallsPlayed, batsman1InitScore, batsman2InitScore;
		public Partnership.PartnershipReport partnershipReport;
		
		public Partnership(Player batsman1, Player batsman2, int initScore, int oversPlayed, int decimalBallsPlayed) {
			this.batsman1 = batsman1;
			this.batsman2 = batsman2;
			this.batsman1InitScore = batsman1.getScore();
			this.batsman2InitScore = batsman2.getScore();
			this.initBallsPlayed = oversPlayed * MATCH_CONSTANTS.NO_BALLS_PER_OVER + decimalBallsPlayed;
			this.initScore = initScore;
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
	
	private Innings(Team batting, Team bowling, int no) {
		this.battingTeam = batting;
		this.bowlingTeam = bowling;
		this.totalOvers = no;
		this.overs = new ArrayList<>();
		this.bowler = null;
		this.partnerships = new ArrayList<>();
	}
	
	public static Innings getFirstInnings(Team batting, Team bowling, int nOvers) {
		Innings first = new Innings(batting, bowling, nOvers);
		first.target = Integer.MAX_VALUE;
		return first;
	}
	
	public static Innings getSecondInnings(Team batting, Team bowling, int nOvers, int target) {
		Innings second = new Innings(batting, bowling, nOvers);
		second.target = target;
		return second;
	}
	
	public int play() {
		Player battingEnd = battingTeam.nextBatsman();
		Player bowlingEnd = battingTeam.nextBatsman();
		Over curOver;
		Partnership partnership = new Partnership(battingEnd, bowlingEnd, score, nOvers, 0);
		partnerships.add(partnership);
		for (int i = 0; i < this.totalOvers; i++) {
			this.bowler = bowlingTeam.nextBowler(this.bowler);
			curOver = new Over();
			overs.add(curOver);
			
			curOver.play(i, battingTeam, battingEnd, bowlingEnd, bowler, partnership, this.wickets, score, target);
			
			int noOfBalls = curOver.getNoOfBalls();
			this.wickets = curOver.getNoOfWicketsDown();
			this.score = curOver.getCurScore();
			battingEnd = curOver.getBattingEnd();
			bowlingEnd = curOver.getBowlingEnd();
			this.decimalBalls = noOfBalls % MATCH_CONSTANTS.NO_BALLS_PER_OVER;
			this.nOvers += noOfBalls / MATCH_CONSTANTS.NO_BALLS_PER_OVER;
			partnerships.addAll(curOver.getNewPartnershipsThisOver());
			
			if (wickets == MATCH_CONSTANTS.NO_OF_PLAYERS_PER_TEAM - 1 || score >= target)
				break;
			partnership = partnerships.get(partnerships.size() - 1);
			bowler.incrementOvers();
			
			Player temp = bowlingEnd;
			bowlingEnd = battingEnd;
			battingEnd = temp;
		}
		return this.score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getWickets() {
		return wickets;
	}
	
	public int getnOvers() {
		return nOvers;
	}
	
	public int getDecimalBalls() {
		return decimalBalls;
	}
	
	public String getBattingTeamName() {
		return battingTeam.name;
	}
	
	public InningsScoreCard getScoreCard() {
		return new InningsScoreCard();
	}
	
	public OversScores getOverScores() {
		return new OversScores();
	}
	
	public List<Partnership.PartnershipReport> getPartnerships() {
		return Collections.unmodifiableList(partnerships.stream().map(a -> a.partnershipReport).collect(Collectors.toList()));
	}
	
}
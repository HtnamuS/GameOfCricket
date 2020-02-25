package htnamus.goc.model;

public class PlayerStats {
	private final int averageStrikeRate,
		averageBowlingEconomy,
		averageBallsPlayedPerInnings,
		averageWicketsPer100Balls;
	
	public PlayerStats(int averageStrikeRate, int averageBowlingEconomy, int averageBallsPlayedPerInnings, int averageWicketsPer100Balls) {
		this.averageStrikeRate = averageStrikeRate;
		this.averageBowlingEconomy = averageBowlingEconomy;
		this.averageBallsPlayedPerInnings = averageBallsPlayedPerInnings;
		this.averageWicketsPer100Balls = averageWicketsPer100Balls;
	}
	
	public int getAverageStrikeRate() {
		return averageStrikeRate;
	}
	
	public int getAverageBowlingEconomy() {
		return averageBowlingEconomy;
	}
	
	public int getAverageBallsPlayedPerInnings() {
		return averageBallsPlayedPerInnings;
	}
	
	public int getAverageWicketsPer100Balls() {
		return averageWicketsPer100Balls;
	}
}

package htnamus.goc.match;

public enum MatchType {
	ODI(50, 10),T20(20, 4);
	public final int noOfOvers;
	public final int noOfOversPerBowler;
	MatchType(int numOfOvers, int numOversPerBowler){
		noOfOvers = numOfOvers;
		noOfOversPerBowler = numOversPerBowler;
	}
}

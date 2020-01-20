package htnamus.goc.match;

public enum MatchType {
	ODI(50),T20(20);
	int noOfOvers;
	MatchType(int num){
		noOfOvers = num;
	}
}

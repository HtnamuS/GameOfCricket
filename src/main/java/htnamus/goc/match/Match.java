package htnamus.goc.match;

public class Match {
	Team T1, T2;
	int noOfOvers;
	FirstInnings first;
	SecondInnings second;
	public Match(MatchType m){
		T1 = new Team();
		T2 = new Team();
		T1.name = "India";
		T2.name = "England";
		this.noOfOvers = m.noOfOvers;
	}
	public String play(){
		first = new FirstInnings(T1, T2, noOfOvers);
		int target = first.start()+1;
		second = new SecondInnings(T2, T1, noOfOvers, target);
		second.start();
		return Result.generate(first,second);
	}
	public String getScoreCard(){
		return "<h1> Scorecard </h1>\n" + Result.generate(first,second) + first.getScoreCard() + "\n" + second.getScoreCard() ;
	}
}

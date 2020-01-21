package htnamus.goc.match;

public class Match {
	Team T1, T2;
	int noOfOvers;
	FirstInnings first;
	SecondInnings second;
	public Match(MatchType m){
		T1 = new Team(m);
		T2 = new Team(m);
		T1.name = "India";
		T2.name = "England";
		this.noOfOvers = m.noOfOvers;
	}
	public String play(){
		first = new FirstInnings(T1, T2, noOfOvers);
		int target = first.start()+1;
		second = new SecondInnings(T2, T1, noOfOvers, target);
		second.start();
		System.out.println("first = " + first.score);
		System.out.println("second = " + second.score);
		return Result.generate(first,second);
	}
	public String getScoreCard(){
		return "<h1> Scorecard </h1>\n" + Result.generate(first,second) + first.getScoreCard() + "\n" + second.getScoreCard() ;
	}
	public String getOverScores(){
		return "<h1> Over Scores</h1>" + Result.generate(first, second) + first.getOverScores() + "\n" + second.getOverScores();
	}
}

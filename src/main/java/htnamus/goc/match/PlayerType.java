package htnamus.goc.match;

public enum PlayerType {
	BATSMAN,
	BOWLER{
	
	},
	ALLROUNDER{
		private int noOfOvers = 0;
		private int noOfWickets = 0;
		public void incrementOvers(){ noOfOvers++;}
		public void incrementWickets(){ noOfWickets++;}
	}
}

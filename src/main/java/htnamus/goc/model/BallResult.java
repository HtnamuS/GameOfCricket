package htnamus.goc.model;

import htnamus.goc.util.Constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BallResult {
	ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), WICKET;
	int value;
	private static Map<Integer, BallResult> intToBallResult =
		Stream.of(values()).filter(a -> a.value != Constants.NOT_A_RUN).collect(Collectors.toMap(a ->a.value, a->a));
	private static Map<String, BallResult> stringToBallResult =
		Stream.of(values()).filter(a -> a.value == Constants.NOT_A_RUN).collect(Collectors.toMap(BallResult::toString, a -> a));
	BallResult(int val){ this.value = val; }
	BallResult(){
		this.value = Constants.NOT_A_RUN;
	}
	
	public String toString(){
		if(this == WICKET){
			return "W";
		}
		else{
			return String.valueOf(this.value);
		}
	}
	public static BallResult getBallResult(int value){
		return intToBallResult.get(value);
	}
	public static BallResult getBallResult(String ballResult){
		return stringToBallResult.get(ballResult);
	}
	public int getValue(){
		if(this.value == Constants.NOT_A_RUN){
			throw new RuntimeException(this + "Ball Result has no value");
		}
		return this.value;
	}
	public boolean didStrikeChange(){
		return this.value%2 !=0;
	}
}
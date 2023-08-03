package dicePoker;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Computer {
	
	// Initialise variables
	public int diceOne;
	public int diceTwo;
	
	// Method for the computer to randomly 'roll' two dice so two numbers can be generated for that round
	//public ArrayList<Integer> RollTheDice(){
	public void RollTheDice(){
		//ArrayList<Integer> diceNumbers = new ArrayList<Integer>();
		int min = 1;
		int max = 6;
		
		diceOne = ThreadLocalRandom.current().nextInt(min, max + 1);
		diceTwo = ThreadLocalRandom.current().nextInt(min, max + 1);
		//diceNumbers.add(diceOne);
		//diceNumbers.add(diceTwo);
		
		//return diceNumbers;
	}
}
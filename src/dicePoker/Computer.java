package dicePoker;

import java.util.concurrent.ThreadLocalRandom;

public class Computer {
	
	// Initialise variables
	public int diceOne;
	public int diceTwo;
	
	// Method for the computer to randomly 'roll' two dice so two numbers can be generated for that round
	public void RollTheDice(){
		int min = 1;
		int max = 6;
		
		diceOne = ThreadLocalRandom.current().nextInt(min, max + 1);
		diceTwo = ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
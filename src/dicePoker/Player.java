package dicePoker;

import javax.swing.JOptionPane;

public class Player {
	
	// Initialise variables 
	public String name;
	public int bankBalance;
	public int bettingTotal;
	
	// Constructor 
	public Player(String name, int bankBalance) {
		this.name = name;
		this.bankBalance = bankBalance;
	}
	
	// Method to retrieve name in a certain format so the user sees a 'neat' message
	public String GetName() { 
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); 
	}
    
    // Method to assign points to the player
    public int pointsCalculator(int bankBalance, int betAmount, int diceOne, int diceTwo) {
    	String output = "Dice 1 has rolled: " + diceOne + "\nDice 2 has rolled: " + diceTwo;
    	DisplayMessage(output);
    	int points = 0;
    	
    	output = "You bank balance is: £";
    	
    	if (diceOne == diceTwo + 1 || diceOne == diceTwo - 1) {
        	points = bankBalance = bankBalance + (betAmount * 2);
        	DisplayMessage(output + bankBalance);
        } else if (diceOne == diceTwo) {
        	points = bankBalance = bankBalance + (betAmount * 3);
        	DisplayMessage(output + bankBalance);
        } else {
        	points = bankBalance = bankBalance - betAmount;
        	DisplayMessage(output + bankBalance);
        }
    	return points;
    }   

    // Method for player to place their bet. Must be between £1 - £4 only
    public int placeBet() {
    	String output = "";
    	String betRequested = JOptionPane.showInputDialog("How much do you wish to bet? \n It's £1 per bet and you can bet up to £4");
		int betRequestedToInt = Integer.parseInt(betRequested);
		if (betRequestedToInt >= 1 && betRequestedToInt <= 4) {
			bettingTotal = bettingTotal + betRequestedToInt;
			output = "You have chosen to bet £" + betRequestedToInt;
			DisplayMessage(output);
			return betRequestedToInt;
		}
		return 0;
    }
    
    // Method to calculate through-out the whole game how much the player has lost or made
    public int CalculateProfitsOrLoss(int bankBalance) {
    	printResult(bankBalance);
		int profits = 0;
		if (bankBalance < 0) {
			profits = 6 - bankBalance;
		} else {
			profits = bankBalance - 6;
		}
		return profits;
    }
    
    // Method to display end game message
    public void printResult(int bankBalance) {
    	String output = "";
    	
    	if (bankBalance > 0) {
			output = "All rounds have been played! \nYour bank balance at the end of the game is: £" + bankBalance;
			DisplayMessage(output);
		} else {
			output = "Game over! \nYou have lost all your money!";
			DisplayMessage(output);
		}
    }
    
 // Method to display message to the user. This message is passed a string to allow multiple messages to use this method
    public void DisplayMessage(String output) {
		JOptionPane.showMessageDialog(null, output, "", JOptionPane.INFORMATION_MESSAGE);
	}
    
    public Boolean PlayAgain() {
    	int result = JOptionPane.showConfirmDialog(null, "Do you wish to play again?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.NO_OPTION) {
			return false;
		} else {
			return true;
		}
    }
}
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

	// Method to assign points to the player
	public int pointsCalculator(int bankBalance, int betAmount, int diceOne, int diceTwo) {
		// Message to make player aware what dice numbers the computer has rolled
		String output = "Dice 1 has rolled: " + diceOne + "\nDice 2 has rolled: " + diceTwo;
		DisplayMessage(output);

		int points = 0;

		output = "Your bank balance is: £";

		// Rules put in place for players to either double, triple or lose their money
		if (diceOne == diceTwo + 1 || diceTwo == diceOne + 1 || diceOne == diceTwo - 1 || diceTwo == diceOne - 1) {
			points = bankBalance + (betAmount * 2);
			DisplayMessage(output + points);
		} else if (diceOne == diceTwo) {
			points = bankBalance + (betAmount * 3);
			DisplayMessage(output + points);
		} else {
			points = bankBalance - betAmount;
			DisplayMessage(output + points);
		}
		return points;
	}   

	// Method for player to place their bet. Must be between £1 - £4 only
	public int placeBet() {
		String output = "";
		String betRequested = "";

		while (betRequested == "" || !betRequested.matches("[0-9]+")) {
			betRequested = JOptionPane.showInputDialog("How much do you wish to bet? \n It's £1 per bet and you can bet up to £4");
			CancelGame(betRequested);
		}

		int betRequestedToInt = Integer.parseInt(betRequested);

		if (betRequestedToInt >= 1 && betRequestedToInt <= 4) {
			// bettingTotal updated which this attribute is used for the high score table
			bettingTotal = bettingTotal + betRequestedToInt;
			output = "You have chosen to bet £" + betRequestedToInt;
			DisplayMessage(output);
			return betRequestedToInt;
		}
		return 0;
	}

	// Method to calculate through-out the whole game how much the player has lost or made
	public int CalculateProfitsOrLoss(int bankBalance) {
		// Firstly, check if the game needs to end 
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
	private void printResult(int bankBalance) {
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

	// Method to determine if the player wants to play again or not
	public Boolean PlayAgain() {
		int result = JOptionPane.showConfirmDialog(null, "Do you wish to play again?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.NO_OPTION) {
			return false;
		} else {
			return true;
		}
	}

	// Method to catch the instances a player cancels on an input
	public void CancelGame(String cancelInput) {
		if (cancelInput == null) {
			System.out.println("Game cancelled");
			System.exit(0);
		} 
	}
}
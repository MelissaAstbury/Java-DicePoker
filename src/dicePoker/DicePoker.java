package dicePoker;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.ArrayList;

public class DicePoker {

	public static void main(String[] args) {
		// Initialise variables
		String output = "";
		int betAmount = 0;
		int playedBets = 0;
		Computer computer = new Computer();
		boolean playAgain = true;
		
		// Play the game until the player specifies not to play anymore
		while(playAgain) {
		// Player input required to specify their name. This value is then used to create a new Player instance
		String playerName = JOptionPane.showInputDialog("Welcome to Dice Poker! \n Please enter your name");
		Player player = new Player(playerName, 6);
		
		// Display to the player their starting balance for the game
		output = player.GetName() + ", your starting balance for this game is £" + player.bankBalance;
		player.DisplayMessage(output);
		
			// Bets can only be placed if the player has money to bet or if no more than 5 bets have already been used. Restriction for this has been put in place
			while(player.bankBalance > 0 && playedBets < 5) {
				// Keep asking the player for a betting amount if the bet is not between £1 - £4
				while (betAmount == 0) {
					betAmount = player.placeBet();
				}
				// Call method to generate two numbers
				computer.RollTheDice();
					
				// Call method to calculate the players balance after the bet has been played
				player.bankBalance = player.pointsCalculator(player.bankBalance, betAmount, computer.diceOne, computer.diceTwo);
				
				// To be able to place another bet the betAmount needs to be reset to £0 and we need to increase the count for the bets played (restriction in place for a maximum of 5)
				betAmount = 0;
				playedBets ++;
			}
				
			// Method called once all bets have been played to display to the player their profits/loss
			int profits = player.CalculateProfitsOrLoss(player.bankBalance);
			
			// Display a table back to the user to show a breakdown of their game statistics 
			Object[][] rows = {{playerName, player.bettingTotal,playedBets,profits, player.bankBalance}};
			Object[] cols = {"Name", "Betting Total (£)","Rounds","Profits (£)", "Balance (£)"};
			JTable table = new JTable(rows, cols);
			JOptionPane.showMessageDialog(null, new JScrollPane(table));
			
			// HIGHSCORE TABLE REQUIRED
			
			// Once all rounds are complete and the winner is revealed ask player if they wish to play again
			playAgain = player.PlayAgain();
			if (playAgain) {
				// If the player wishes to play again the bet count needs to be set back to 0 and the betAmount is to be set back to 0
				// This is required so no other values from the previous games effect the new game
				betAmount = 0;
				playedBets = 0;
			}
		}
    }
}


// -- SUDO CODE --
// - RULES
// If the numbers are in sequence - double your bet - DONE
// If the numbers are identical - triple your bet - DONE
// Otherwise, lose your bet money - DONE

// - CRITERIA
// Ask user for their names - DONE
// Show balance before betting - DONE
// Start with balance of £6 - DONE
// £1 minimum per bet (can bet between £1-£4) - DONE
// The game will end for maximum 5 bets OR you run out of money - DONE

// - INFO TO DISPLAY
// | bet amount | the numbers rolled | Money earned or lost, - DONE
// | total in your bank at the end of the game | - DONE

// - ADDITIONAL FEATURES
// High score table based on balance - 

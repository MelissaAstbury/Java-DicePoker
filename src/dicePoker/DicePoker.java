package dicePoker;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DicePoker {

	public static void main(String[] args) {
		// Initialise variables
		String output = "";
		String playerName = "";
		int betAmount = 0;
		int playedBets = 0;
		int startingBankBalance = 6;
		boolean playAgain = true;
		Computer computer = new Computer();
		// Map is an interface and HashMap is the class that uses this interface. You can not use a interface directly and you must obey by the interface 'contract'
		Map<String, Integer> leaderBoardScores = new HashMap<>();

		// Play the game (application) until the player specifies not to play anymore
		while(playAgain) {
			// Player input required to specify their name. This value is then used to create a new Player instance
			playerName = JOptionPane.showInputDialog("Welcome to Dice Poker! \n Please enter your name");
			// Added restriction to not allow names that are less than 3 characters
			if(playerName.length() > 2) {
				playerName = playerName.substring(0, 1).toUpperCase() + playerName.substring(1).toLowerCase();
			} else {
				JOptionPane.showMessageDialog(null, "Game cancelled due to invalid name", "", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}

			Player player = new Player(playerName, startingBankBalance);
			// Player has the option to cancel inputting their name therefore, this method is called to exit the game if no name is given
			player.CancelGame(player.name);

			// Display to the player their starting balance for the game (currently players get £6)
			output = player.name + ", your starting balance for this game is £" + player.bankBalance;
			player.DisplayMessage(output);

			// Bets can only be placed if the player has money to bet or if no more than 5 bets have already been used. Restriction for this has been put in place with the while loop
			while(player.bankBalance > 0 && playedBets < 5) {
				// Keep asking the player for a betting amount if the bet is not between £1 - £4
				while (betAmount == 0) {
					betAmount = player.placeBet();
				}
				// Call method to generate two random dice numbers
				computer.RollTheDice();

				// Call method to calculate the players balance after the bet has been played so the player knows how much is left
				player.bankBalance = player.pointsCalculator(player.bankBalance, betAmount, computer.diceOne, computer.diceTwo);

				// To be able to place another bet the betAmount needs to be reset to £0 and we need to increase the count for the bets played (restriction of 5 bets per game)
				betAmount = 0;
				playedBets ++;
			}

			// Method called once all bets have been played or there is no money left, to display the profit or loss
			int profits = player.CalculateProfitsOrLoss(player.bankBalance);

			// Display a table back to the user to show a breakdown of their game statistics
			// 2 dimensional for 'rows' so more than one row of data can be inserted
			Object[][] rows = {{player.name, player.bettingTotal,playedBets, profits, player.bankBalance}};
			Object[] cols = {"Name", "Betting Total (£)","Rounds","Profits (£)", "Balance (£)"};
			JTable table = new JTable(rows, cols);
			JOptionPane.showMessageDialog(null, new JScrollPane(table));

			// Push players final values into HashMap so we can retrieve this information for the score table
			leaderBoardScores.put(player.name, player.bankBalance);

			// Method to retrieve the high score table
			computer.CreateScoresTable(leaderBoardScores);

			// Once all rounds are complete and the winner is revealed ask player if they wish to play again
			playAgain = player.PlayAgain();
			if (playAgain) {
				// If the player wishes to play again 'playedBets' needs to be set back to 0 and the 'betAmount' is to be set back to 0 also
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
// High score table based on balance - DONE

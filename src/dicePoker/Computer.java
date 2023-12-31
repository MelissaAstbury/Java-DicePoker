package dicePoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Computer {

	// Initialise variables
	private int diceOne;
	private int diceTwo;
	
	// Getters and Setters
	public int getDiceOne() {
		return diceOne;
	}

	public void setDiceOne(int diceOne) {
		this.diceOne = diceOne;
	}

	public int getDiceTwo() {
		return diceTwo;
	}

	public void setDiceTwo(int diceTwo) {
		this.diceTwo = diceTwo;
	}

	// Method for the computer to randomly 'roll' two dice so two numbers can be generated for that round
	public void RollTheDice(){
		int min = 1;
		int max = 6;

		setDiceOne(ThreadLocalRandom.current().nextInt(min, max + 1));
		setDiceTwo(ThreadLocalRandom.current().nextInt(min, max + 1));
	}

	// Method to display high score table
	public void CreateScoresTable(Map<String, Integer> leaderBoardScores) {
		// Create table for display
		DefaultTableModel model = new DefaultTableModel();
		// Define columns
		model.addColumn("Name");
		model.addColumn("High Score - Balance (£)");
		// Pass the DefaultTableModel to the JTable created
		JTable table2 = new JTable(model);

		// Convert to a list of entries
		List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(leaderBoardScores.entrySet());
		// Sort by Integer in descending order
		sortedScores.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

		// Loop through and add to table for display
		for (Map.Entry<String, Integer> score : sortedScores) {
			model.addRow(new Object[] {score.getKey(), score.getValue()});
		}

		// Display the table
		JOptionPane.showMessageDialog(null, new JScrollPane(table2));
	}
}
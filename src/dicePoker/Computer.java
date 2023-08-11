package dicePoker;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;


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
/**
 * This class represents a driver program to simulate a game between two AI players.
 * It creates two AI players, initializes a new game, runs 1000 games, and counts the number
 * of wins for each player.
 * 
 * Results:
 * Win counts when the more intelligent player plays first!!
 * More intelligent player win count: 744
 * Less intelligent player win count: 256
 * 
 * Win counts when the more intelligent player plays second!!
 * More intelligent player win count: 961
 * Less intelligent player win count: 39
 * 
 * The results are as per our expectation. The intelligent player got more wins in both cases. The difference
 * in win count is different. In the second case, when the more intelligent player plays second, the first player 
 * would have already made the move, which would mean that the second player would have more depth to look into. Also, 
 * since the first player's intelligent level is 1, its children have scores 0 in the very first move of the game. It 
 * means that the first move is randomly selected by the first player. As a result, the second player has more advantage.
 * That's why the win count for the more intelligent player is higher when it plays second than when it plays first.
 * 
 * @author Varun Rayamajhi
 * @author John Paul Atehortua
 */
public class Driver {
	/**
	 * This is the method that is executed when the program is run.
	 * 
	 * @param args an array of strings input from the command line
	 */
	public static void main(String[] args) {
		// Create two AI players with depth limit of 10 and 1
		Player player10 = new AIPlayer("DepthLimit10", HexBoard.WHITE, 10);
		Player player1 = new AIPlayer("DepthLimit1", HexBoard.BLACK, 1);
		
		// Initialize variables to count the number of wins for each players in two different cases
		int player10winsCase1 = 0;
		int player1winsCase1 = 0;
		
		int player10winsCase2 = 0;
		int player1winsCase2 = 0;
		
		// Case 1: More intelligent player plays first
		for(int i = 0; i < 1000; i++) {
			Player winner = player10.play(new HexBoard(), player1);
			
			// Increment player win counts
			if(winner.toString().equals(player10.toString()))
				player10winsCase1++;
			else
				player1winsCase1++;
		}
		
		// Case 2: More intelligent player plays second
		for(int i = 0; i < 1000; i++) {
			Player winner = player1.play(new HexBoard(), player10);
			
			// Increment player win counts
			if(winner.toString().equals(player10.toString()))
				player10winsCase2++;
			else
				player1winsCase2++;
		}
		
		// Print the win count for the players in each case
		System.out.println("Win counts when the more intelligent player plays first!!");
		System.out.println("More intelligent player win count: " + player10winsCase1);
		System.out.println("Less intelligent player win count: " + player1winsCase1);
		System.out.println();
		System.out.println("Win counts when the more intelligent player plays second!!");
		System.out.println("More intelligent player win count: " + player10winsCase2);
		System.out.println("Less intelligent player win count: " + player1winsCase2);
	}
}
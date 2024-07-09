import java.util.Vector;
import java.util.Scanner;

/**
 * This class extends AbstractPlayer class and creates a Human player. It implements the method selectMove
 * which is used to play the game with a human player.
 * 
 * @author Varun Rayamajhi
 * @author John Paul Atehortua
 */
public class HumanPlayer extends AbstractPlayer{
	/**
	 * Declare a Scanner variable to scan for the input by the user
	 */
	private Scanner scan;
	
	/**
	 * This constructor creates a Human Player with a name and a color and initializes the Scanner object.
	 * 
	 * @param name the name for the human player
	 * @param color the color for the human player
	 */
	public HumanPlayer(String name, char color) {
		// Initialize the name and color for the human player
		this.name = name;
		this.color = color;
		
		// Create a Scanner object to scan for the user input
		scan = new Scanner(System.in);
	}
	
	/**
	 * This move creates a vector of possible moves for the current player from the current board states,
	 * displays the possible moves, and prompts the player to select the move.
	 * 
	 * @param board the current state of the board in the game
	 * @return a HexMove selected by the player from a vector of possible moves
	 */
	public HexMove selectMove(HexBoard board) {
		// Get the possible moves for the current player from the current state of the game
		Vector<HexMove> possibleMoves = board.moves(color);
		
		// Display possible moves for the player
		for(int i = 0; i < possibleMoves.size(); i++)
			System.out.println("Option " + i + ": " + possibleMoves.get(i).toString());
		
		// Declare a variable to store the option selected by the player
		int move;
		
		// Prrompt the user to select an option until a vaild option is selected
		while (true){
			System.out.print("Please select a move: ");
			if(scan.hasNextInt()) {
				// Get the option entered by the player
				move = scan.nextInt();
				
				// If the move is valid, exit out of the loop
				if(move < possibleMoves.size() && move >= 0)
					break;
				
				// If the move is invalid, display the error message
				System.out.println("Invalid Move! Please enter a valid move!");
			}else {
				// If the input type is invalid, prompt the player again
				System.out.println("Invalid input! Please select a valid input!");
				scan.next();
			}
		}
		
		// Return the move selected by the player
		return possibleMoves.get(move);	
	}
}
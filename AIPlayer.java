import java.util.Vector;
import java.util.Random;

/**
 * This class extends AbstractPlayer class and creates an AI player with certain depthLimit.
 * It implements the method selectMove that returns the bestMove the AI player can make from 
 * its current position in the game.
 * 
 * @author Varun Rayamajhi
 * @author John Paul Atehortua
 */
public class AIPlayer extends AbstractPlayer{
	/**
	 * This variable is the depth of an AI player.
	 */
	private int depthLimit;
	
	/**
	 * This is a Random variable to retrun a random move if there are multiple best moves.
	 */
	private Random rand;
	
	/**
	 * This constructor takes three parameters for the name, color, and depthLimit of the AI player
	 * and creates an AI player.
	 * 
	 * @param name the name of the AI player
	 * @param color the color of the AI player
	 * @param depthLimit the depth of the AI player
	 */
	public AIPlayer(String name, char color, int depthLimit) {
		// Initialize the name, current player color, and depthLimit of the AI player
		this.name = name;
		this.color = color;
		this.depthLimit = depthLimit;
		
		// Create a Random object
		rand = new Random();
	}
	
	/**
	 * This method creates a game tree and the best moves from the game tree and returns 
	 * a HexMove randomly from the best moves available.
	 * 
	 *  @param board the current state of the board in the game
	 *  @return HexMove object if there are any bestMoves available for the AI player, otherwise null
	 */
	public HexMove selectMove(HexBoard board){
		// Create a game tree for the AI player
		GameTree gameTree = new GameTree(board, color, depthLimit);
		
		// Get the bestMoves for the AI player from the current game tree
		Vector<HexMove> bestMoves = gameTree.bestMoves();
		
		/*
		 * Return null if there are not any moves available for the AI player, otherwise return a randomly selected move
		 * from the possible best move(s)
		 */
		if(bestMoves.isEmpty())
			return null;
		else
			return bestMoves.get(rand.nextInt(bestMoves.size()));
	}
}
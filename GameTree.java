import java.util.Vector;

/**
 * This class represents a game tree for the Hex-a-pawn game, using recursion to build possible game states up 
 * to a specified depth. It includes methods to calculate the scores for the children of the game tree such
 * that the current player has the highest chances of winning.
 * 
 * @author Varun Rayamajhi
 * @author John Paul Atehortua
 */
public class GameTree{
	/**
	 * The game board at the current state of the tree
	 */
	private HexBoard board;
	
	/**
	 * The color of the player who is next to move
	 */
    private char currentPlayerColor; 
    
	/**
	 * The color of the player who moved last
	 */
    private char lastPlayerColor; 
    
	/**
	 * Vector of child trees representing future game states
	 */
    private Vector<GameTree> children; 
	
    /**
     * Constructs a game tree node with a specific game board state, the current player's color, 
     * and a limit to tree depth.
     *
     * @param board the current state of the Hex board
     * @param currentPlayerColor the color of the current player 
     * @param depthLimit the maximum depth of the game tree
     */
	public GameTree(HexBoard board, char currentPlayerColor, int depthLimit) {
		// Initialize the board and the current player color
		this.board = board;
		this.currentPlayerColor = currentPlayerColor;
		
		// Get the opponent player's color and initialize the last player color
		lastPlayerColor = HexBoard.opponent(currentPlayerColor);
		
		// Create an empty vector for the children of the game tree
		children = new Vector<GameTree>();
		
        // Base case: if depth limit is reached or the last player has won, the children vector is an empty vector
        if (depthLimit == 0 || board.win(lastPlayerColor)) 
            return;

        // Generate all possible moves for the current player
        Vector<HexMove> moves = board.moves(currentPlayerColor);
		
		// Recursive case: explore each possible move and add the resulting game trees children of the current game tree
        for (HexMove move : moves) {
        	// Create a new game tree for the children and add it to the children vector
            GameTree newTree = new GameTree(new HexBoard(board, move), lastPlayerColor, depthLimit - 1);
            children.add(newTree);
        }
	}
	
    /**
     * This method calculates the score for this current player based on a given tree.
     * The score is determined by the end game states, i.e, when the tree is a leaf.
     *
     * @param tree the game tree to evaluate
     * @return A double representing the score of a tree
     */
	private double evaluate(GameTree tree) {
		// Base Case: When the tree is a leaf, return the scores based on the state of the game
		if(tree.children.isEmpty()) {
			// If the game has been won, check who won the game and return the associated value
			if(tree.board.win(tree.lastPlayerColor)){
				// If the player we're evaluating for won the game, return 1.0 else -1.0
				if(this.currentPlayerColor == tree.lastPlayerColor)
					return 1.0;
				else
					return -1.0;
			}
			
			// If the game has not yet ended, return 0.0
			return 0.0;
		}
		
		// Recursive case: Recurse through each children, calculate their scores, and return the average
		// Initialize a variable to store the total scores for the children
		double totalScores = 0.0;
		
		// Loop through the children of the tree and evaluate the score for the children and sum them up
		for(GameTree child: tree.children) 
			totalScores += evaluate(child);
		
		// Return the average score of the tree's children
		return totalScores/tree.children.size();	
	}
	
	/**
	 * This method computes the best moves from currently possible moves for the current player and 
	 * returns a vector of HexMoves with the highest score.
	 * 
	 * @return A vector containing the best moves if there are possible moves, otherwise an empty vector
	 */
	public Vector<HexMove> bestMoves(){
		// Get the possible moves for the current player
		Vector<HexMove> moves = board.moves(currentPlayerColor);
		
		// If there are no moves available for the current player, return an empty vector
		if(moves.isEmpty())
			return new Vector<HexMove>();
		
		// Initialize variables to store the mazimum score and the score for each children of the game tree
		double max = evaluate(children.get(0));
		double childScore;
		
		// Loop through each children to get the highest score among the children
		for(int i = 0; i < children.size(); i++) {
			childScore = evaluate(children.get(i));
			if(childScore > max)
				max = childScore;
		}
		
		// Initialize a vector to store the best move(s)
		Vector<HexMove> bestMoves = new Vector<HexMove>();
		
		// Loop through each children and add them to the vector bestMoves if their score is the highest
		for(int i = 0; i < children.size(); i++) {
			if(evaluate(children.get(i)) == max) 
				bestMoves.add(moves.get(i));
		}
		
		// Return the vector containing moves that result into children with the highest score
		return bestMoves;
	}
}
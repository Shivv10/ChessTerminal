# ChessTerminal
This is a Game of Chess which you can play on a terminal. 

A Simplified Game of Chess

Main: Defines the board and initializes the gameplay.

TextGameDisplay Class: serves as an implementation of the GameDisplay interface and provides the text-based user
interface for the chess game.

GameLogic Class: This class extends TextGameDisplay and implements ChessController. It contains the core logic for
executing moves and managing the game flow. The winner variable stores the result of the game, indicating which
player won or if the game ended in a draw.

Board class: The Board class represents the chessboard for the game. It encapsulates the logic for managing the
board state, including the arrangement of pieces and resetting the board. Object orientation is employed in this
class through the use of objects to represent the board and its components. Each piece on the board is represented
by an object of a subclass of the Piece class, such as King, Queen, Knight, etc. This allows for modular design,
encapsulating the behavior and properties of each piece type within its respective class.

Piece Class: The Piece class serves as a base class for representing individual chess pieces on the board. It
encapsulates common properties and behaviors shared by all chess pieces, such as position, ownership, name, and symbol.
Object orientation is utilized in this class through the concept of inheritance and method overriding. The Piece class
serves as a superclass, providing common attributes and behaviors shared by all types of chess pieces. Subclasses
such as King, Queen, Knight, etc., can extend the Piece class to inherit these attributes and behaviors while also
implementing specific rules for movement and collision detection tailored to each piece type. This approach promotes
code reusability and modularity, allowing for easy extension and customization of the chess piece hierarchy.
Additionally, by defining common behavior in the superclass, the code becomes more maintainable and scalable,
facilitating future enhancements or modifications to the game logic.

Move Class: The Move class represents a move made by a player or the AI in the chess game. It encapsulates information
about the old and new positions of a piece, as well as details about any captured piece during the move.

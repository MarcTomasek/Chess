package Pieces;

public class King extends Piece {

    public King (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♚' : '♔';
        this.position = position;
        this.pieceType = PIECE.KING;
    }

}

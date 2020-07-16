package Pieces;

public class Queen extends Piece {

    public Queen (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♛' : '♕';
        this.position = position;
        this.pieceType = PIECE.QUEEN;
    }
}

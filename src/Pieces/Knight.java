package Pieces;

public class Knight extends Piece {

    public Knight (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♞' : '♘';
        this.position = position;
        this.pieceType = PIECE.KNIGHT;
    }
}

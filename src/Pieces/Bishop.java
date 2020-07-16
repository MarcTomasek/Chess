package Pieces;

public class Bishop extends Piece {

    public Bishop (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♝' : '♗';
        this.position = position;
        this.pieceType = PIECE.BISHOP;
    }
}

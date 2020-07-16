package Pieces;

public class Pawn extends Piece {

    public Pawn (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♟' : '♙';
        this.position = position;
        this.pieceType = PIECE.PAWN;
    }
}

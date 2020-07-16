package Pieces;

public class Rook extends Piece {

    public Rook (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♜' : '♖';
        this.position = position;
        this.pieceType = PIECE.ROOK;
    }
}

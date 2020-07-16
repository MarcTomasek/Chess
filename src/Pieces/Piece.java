package Pieces;

import java.util.ArrayList;

public abstract class Piece{
    public enum PIECE {PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING}
    protected int[] position;
    protected char symbol;
    protected boolean black;
    protected boolean firstMove = true;
    protected PIECE pieceType;

    public int[] getPosition() {
        return this.position;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public boolean isBlack() {
        return this.black;
    }

    public boolean isFirstMove(){
        return firstMove;
    }

    public PIECE getPieceType() {return this.pieceType;}

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setFirstMove(boolean bool) {
        this.firstMove = bool;
    }
}

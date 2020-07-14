package Pieces;

import java.net.NoRouteToHostException;
import java.util.ArrayList;

public abstract class Piece{
    protected int[] position;
    protected char symbol;
    protected boolean black;
    protected PIECE pieceType;
    protected ArrayList<Piece> otherPieces = new ArrayList<>(31);
    protected ArrayList<int[]> legalMoves = new ArrayList<>(28);
    protected enum PIECE {PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING}

    public int[] getPosition() {
        return this.position;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public boolean isBlack() {
        return this.black;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setOtherPieces(ArrayList<Piece> otherPieces) {
        this.otherPieces.clear();
        this.otherPieces.addAll(otherPieces);
    }

    public ArrayList<int[]> getLegalMoves(){
        return legalMoves;
    }

    public static boolean isInBounds(int[] position) {
        for (int i = 0; i < 2; i++) {
            if (position[i] < 0 || position[i] > 7) {
                return false;
            }
        }
        return true;
    }

    public boolean isClear(int[] position) {
        for (Piece piece: this.otherPieces
             ) {
            if (position == piece.getPosition()) {
                return false;
            }
        }
        return true;
    }

    public boolean isTakeable(int[] position) {
        for (Piece piece: this.otherPieces) {
            if (position == piece.getPosition()) {
                if (this.isBlack() == piece.isBlack() && piece.pieceType != PIECE.KING) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract void setLegalMoves();
}

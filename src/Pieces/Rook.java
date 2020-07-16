package Pieces;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♜' : '♖';
        this.position = position;
        this.pieceType = PIECE.ROOK;
    }

    @Override
    public void setMovablePositions() {
        this.legalMoves.clear();

        int[] position = this.getPosition();
        int yPos = position[0];
        int xPos = position[1];
        int[] newPos;

        //Left Moves
        for (int i = xPos - 1; i >= 0; i--) {
            newPos = new int[]{yPos, i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Right Moves
        for (int i = xPos + 1; i < 8; i++) {
            newPos = new int[]{yPos, i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Up Moves
        for (int i = yPos - 1; i >= 0; i--) {
            newPos = new int[]{i, xPos};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Down Moves
        for (int i = yPos + 1; i < 8; i++) {
            newPos = new int[]{yPos, i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }
    }
}

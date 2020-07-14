package Pieces;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♝' : '♗';
        this.position = position;
        this.pieceType = PIECE.BISHOP;
    }

    @Override
    public void setLegalMoves() {
        this.legalMoves.clear();

        int[] position = this.getPosition();
        int yPos = position[0];
        int xPos = position[1];
        int[] newPos;

        int up_right_distance = Math.min(7 - xPos, yPos);
        int down_right_distance = 7 - Math.max(xPos, yPos);
        int down_left_distance = Math.min(xPos, 7 - yPos);
        int up_left_distance = Math.min(xPos,yPos);

        //Up Right
        for (int i = 1; i < up_right_distance; i++) {
            newPos = new int[]{yPos - i, xPos + i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Down Right
        for (int i = 1; i < down_right_distance; i++) {
            newPos = new int[]{yPos + i, xPos + i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Down Left
        for (int i = 1; i < down_left_distance; i++) {
            newPos = new int[]{yPos + i, xPos - i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Up Left
        for (int i = 1; i < up_left_distance; i++) {
            newPos = new int[]{yPos - i, xPos - i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }
    }
}

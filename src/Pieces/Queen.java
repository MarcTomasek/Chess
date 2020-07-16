package Pieces;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♛' : '♕';
        this.position = position;
        this.pieceType = PIECE.QUEEN;
    }

    @Override
    public void setMovablePositions() {
        this.legalMoves.clear();

        int[] position = this.getPosition();
        int yPos = position[0];
        int xPos = position[1];
        int[] newPos;

        int up_right_distance = Math.min(7 - xPos, yPos);
        int down_right_distance = 7 - Math.max(xPos, yPos);
        int down_left_distance = Math.min(xPos, 7 - yPos);
        int up_left_distance = Math.min(xPos,yPos);

        //Bishop Moves
        //Up Right Moves
        for (int i = 1; i < up_right_distance; i++) {
            newPos = new int[]{yPos - i, xPos + i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Down Right Moves
        for (int i = 1; i < down_right_distance; i++) {
            newPos = new int[]{yPos + i, xPos + i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Down Left Moves
        for (int i = 1; i < down_left_distance; i++) {
            newPos = new int[]{yPos + i, xPos - i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Up Left Moves
        for (int i = 1; i < up_left_distance; i++) {
            newPos = new int[]{yPos - i, xPos - i};
            if (isInBounds(newPos) && (isClear(newPos))) {
                this.legalMoves.add(newPos);
            } else if (isInBounds(newPos) && isTakeable(newPos)) {
                this.legalMoves.add(newPos);
                break;
            }
        }

        //Rook Moves
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

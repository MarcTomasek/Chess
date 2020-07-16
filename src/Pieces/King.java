package Pieces;

import java.util.ArrayList;

public class King extends Piece{
    private boolean hasMoved = false;

    public King (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♚' : '♔';
        this.position = position;
        this.pieceType = PIECE.KING;
    }

    @Override
    public void setMovablePositions() {
        //TODO Add Castling
        //TODO Don't allow moves which put in check
        //TODO Don't allow discovery checks from moving other pieces
        this.legalMoves.clear();

        int[] position = this.getPosition();
        int yPos = position[0];
        int xPos = position[1];
        int[] newPos;

        for (int i = xPos - 1; i <= xPos + 1; i++) {
            for (int j = yPos - 1; j <= yPos + 1; j++) {
                newPos = new int[]{j, i};
                if (newPos == position) {
                    continue;
                } else {
                    if (isClear(newPos) || isTakeable(newPos)) {
                        this.legalMoves.add(newPos);
                    }
                }
            }
        }

    }
}

package Pieces;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♞' : '♘';
        this.position = position;
        this.pieceType = PIECE.KNIGHT;
    }

    @Override
    public void setLegalMoves() {
        this.legalMoves.clear();

        int[] position = this.getPosition();
        int yPos = position[0];
        int xPos = position[1];

        int[][] moveOptions = new int[][]{
                {yPos - 2, xPos - 1}, {yPos - 2, xPos + 1},  //UP
                {yPos - 1, xPos + 2}, {yPos + 1, xPos + 2},  //RIGHT
                {yPos + 2, xPos + 1}, {yPos + 2, xPos - 1},  //DOWN
                {yPos - 1, xPos - 2}, {yPos + 1, xPos - 2}}; //LEFT

        for (int[] move: moveOptions) {
            if (isClear(move) || isTakeable(move)) {
                this.legalMoves.add(move);
            }
        }
    }
}

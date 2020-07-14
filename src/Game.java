import Pieces.*;

import java.time.Duration;
import java.util.ArrayList;

public class Game {
    //TODO Add Time Mechanic
    public Piece[][] boardState;
    public ArrayList<Piece> piecesTakenByBlack = new ArrayList<>(15);
    public ArrayList<Piece> piecesTakenByWhite = new ArrayList<>(15);
    public Duration timeLeftBlack = Duration.ofMinutes(5);
    public Duration timeLeftWhite = Duration.ofMinutes(5);

    public Game(){
        resetGame();
    }

    public void clearBoard() {
        boardState = new Piece[8][8];
    }

    public void resetPiecesTaken(){
        piecesTakenByBlack.clear();
        piecesTakenByWhite.clear();
    }

    public void resetTime(){
        timeLeftBlack = Duration.ofMinutes(5);
        timeLeftWhite = Duration.ofMinutes(5);
    }

    private void initializeBlackPieces(){
        boardState[0][0] = new Rook(true, new int[]{0, 0});
        boardState[0][1] = new Knight(true, new int[]{0, 1});
        boardState[0][2] = new Bishop(true, new int[]{0, 2});
        boardState[0][3] = new Queen(true, new int[]{0, 3});
        boardState[0][4] = new King(true, new int[]{0, 4});
        boardState[0][5] = new Bishop(true, new int[]{0, 5});
        boardState[0][6] = new Knight(true, new int[]{0, 6});
        boardState[0][7] = new Rook(true, new int[]{0, 7});
        for (int i = 0; i < 8; i++) {
            boardState[1][i] = new Pawn(true, new int[]{1, i});
        }
    }

    private void initializeWhitePieces(){
        boardState[7][0] = new Rook(false, new int[]{7, 0});
        boardState[7][1] = new Knight(false, new int[]{7, 1});
        boardState[7][2] = new Bishop(false, new int[]{7, 2});
        boardState[7][3] = new Queen(false, new int[]{7, 3});
        boardState[7][4] = new King(false, new int[]{7, 4});
        boardState[7][5] = new Bishop(false, new int[]{7 ,5});
        boardState[7][6] = new Knight(false, new int[]{7, 6});
        boardState[7][7] = new Rook(false, new int[]{7 , 7});
        for (int i = 0; i < 8; i++) {
            boardState[6][i] = new Pawn(false, new int[]{6, i});
        }
    }

    private void initializeEmptySpaces(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardState[i][j] = null;
            }
        }
    }

    public void initializeBoard(){
        initializeEmptySpaces();
        initializeBlackPieces();
        initializeWhitePieces();
    }

    public void resetBoard(){
        clearBoard();
        initializeBoard();
    }

    public void resetGame(){
        resetBoard();
        resetPiecesTaken();
        resetTime();
    }

    public void movePiece(int[] startPosition, int[] targetPosition) {
        Piece pieceToMove = boardState[startPosition[0]][startPosition[1]];
        ArrayList<Piece> allPieces = getPieces();
        allPieces.remove(pieceToMove);
        pieceToMove.setOtherPieces(allPieces);

        if (pieceToMove.getLegalMoves().contains(targetPosition)) {
            Piece toBeTaken = findPieceAtPosition(targetPosition);
            if (toBeTaken != null) {
                if (toBeTaken.isBlack()) {
                    piecesTakenByWhite.add(toBeTaken);
                } else {
                    piecesTakenByBlack.add(toBeTaken);
                }
            }
            boardState[targetPosition[0]][targetPosition[1]] = pieceToMove;
            pieceToMove.setPosition(targetPosition);
            boardState[startPosition[0]][startPosition[1]] = null;
        }
    }

    public Piece findPieceAtPosition(int[] position) {
        Piece pieceAtPosition;
        ArrayList<Piece> allPieces = getPieces();
        for (Piece piece: allPieces) {
            if (piece.getPosition() == position) {
                return piece;
            }
        }
        return null;
    }

    /**
     * Return a ArrayList of all Pieces on the board
     * @return ArrayList<Piece>
     */
    public ArrayList<Piece> getPieces(){
        ArrayList<Piece> pieces = new ArrayList<>(32);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardState[i][j] != null) {
                    pieces.add(boardState[i][j]);
                }
            }
        }
        return pieces;
    }

    public void printBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardState[i][j] != null)
                    System.out.print(boardState[i][j].getSymbol());
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}

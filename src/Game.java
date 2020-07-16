import Pieces.*;

import java.time.Duration;
import java.util.ArrayList;

public class Game {
    //TODO Add Time Mechanic
    //TODO Add Ability to flip board
    //TODO Add Letters to board-files
    //TODO Add Numbers to board-rows
    //TODO Add Clickable GUI Stuff
    public Piece[][] boardState;
    public ArrayList<Piece> piecesTakenByBlack = new ArrayList<>(15);
    public ArrayList<Piece> piecesTakenByWhite = new ArrayList<>(15);
    public Duration timeLeftBlack = Duration.ofMinutes(5);
    public Duration timeLeftWhite = Duration.ofMinutes(5);

    final private ArrayList<int[]> castlingPositions = new ArrayList<>() {{
        add(new int[]{0, 1});   //Black Queen side
        add(new int[]{0, 6});   //Black King side
        add(new int[]{7, 1});   //White Queen side
        add(new int[]{7, 6}); }};   //White King side

    public Game(){
        resetGame();
    }

    public boolean isClear(int[] position) {
        return (boardState[position[0]][position[1]] == null);

    }

    public boolean isTakeable(Piece piece, int[] position) {
        return (boardState[position[0]][position[1]] != null &&
                piece.isBlack() != boardState[position[0]][position[1]].isBlack()
                && piece.getPieceType() != Piece.PIECE.KING);
    }

    public boolean isChecking(int[] position) {
        //TODO Figure out how to detect checks
        //TODO Don't allow moves which put in check
        //TODO Don't allow castling through check
        //TODO Don't allow discovery checks by moving other pieces
        return false;
    }

    public static boolean isInBounds(int[] position) {
        for (int i = 0; i < 2; i++) {
            if (position[i] < 0 || position[i] > 7) {
                return false;
            }
        }
        return true;
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
        boardState = new Piece[8][8];
        initializeEmptySpaces();
        initializeBlackPieces();
        initializeWhitePieces();
    }

    public void resetPiecesTaken(){
        piecesTakenByBlack.clear();
        piecesTakenByWhite.clear();
    }

    public void resetTime(){
        timeLeftBlack = Duration.ofMinutes(5);
        timeLeftWhite = Duration.ofMinutes(5);
    }

    public void resetBoard(){
        initializeBoard();
    }

    public void resetGame(){
        resetBoard();
        resetPiecesTaken();
        resetTime();
    }

    public void movePieceToPosition(int[] startPosition, int[] targetPosition) {
        Piece pieceToMove = boardState[startPosition[0]][startPosition[1]];
        Piece toBeTaken = boardState[targetPosition[0]][targetPosition[1]];

        ArrayList<int[]> moves = generateMoves(startPosition);
        if (moves.contains(targetPosition)) {

            //Check for first move
            if (pieceToMove.isFirstMove()) {
                pieceToMove.setFirstMove(false);

                //Also move Rook when castling
                if (pieceToMove.getPieceType() == Piece.PIECE.KING
                        && castlingPositions.contains(targetPosition)) {
                    int[] rookPos;
                    int row = pieceToMove.isBlack() ? 0 : 7;

                    //Queen side
                    if (targetPosition[1] == 1) {
                        rookPos = new int[]{row, 0};
                        boardState[0][0].setFirstMove(false);
                        movePieceToPosition(rookPos, new int[]{row, 2});
                    }
                    //King side
                    if (targetPosition[1] == 6) {
                        rookPos = new int[]{row, 7};
                        boardState[0][7].setFirstMove(false);
                        movePieceToPosition(rookPos, new int[]{row, 5});
                    }
                }
            }

            //Take
            if (toBeTaken != null) {
                if (pieceToMove.isBlack()) {
                    piecesTakenByBlack.add(toBeTaken);
                } else {
                    piecesTakenByWhite.add(toBeTaken);
                }
            }

            //Move
            boardState[targetPosition[0]][targetPosition[1]] = pieceToMove;
            pieceToMove.setPosition(targetPosition);

            boardState[startPosition[0]][startPosition[1]] = null;
        }
    }

    public ArrayList<int[]> generateMoves(int[] position) {
        ArrayList<int[]> moves = new ArrayList<>(30);
        Piece piece = boardState[position[0]][position[1]];

        if (piece.getPieceType() == Piece.PIECE.PAWN) {
            moves = pawnMoves(piece);
        } else if (piece.getPieceType() == Piece.PIECE.ROOK) {
            moves = rookMoves(piece);
        } else if (piece.getPieceType() == Piece.PIECE.KNIGHT) {
            moves = knightMoves(piece);
        } else if (piece.getPieceType() == Piece.PIECE.BISHOP) {
            moves = bishopMoves(piece);
        } else if (piece.getPieceType() == Piece.PIECE.QUEEN) {
            moves = queenMoves(piece);
        } else if (piece.getPieceType() == Piece.PIECE.KING) {
            moves = kingMoves(piece);
        }

        return moves;
    }

    public ArrayList<int[]> pawnMoves(Piece piece) {
        //TODO Add Promotion
        ArrayList<int[]> moves = new ArrayList<>(5);
        int[] position = piece.getPosition();
        int yPos = position[0];
        int xPos = position[1];

        int[] oneAheadBlack = new int[]{yPos + 1, xPos};
        int[] twoAheadBlack = new int[]{yPos + 2, xPos};
        int[] oneAheadWhite = new int[]{yPos - 1, xPos};
        int[] twoAheadWhite = new int[]{yPos - 2, xPos};

        int[] takeLeftBlack = new int[]{yPos + 1, xPos - 1};
        int[] takeRightBlack = new int[]{yPos + 1, xPos + 1};
        int[] takeLeftWhite = new int[]{yPos - 1, xPos - 1};
        int[] takeRightWhite = new int[]{yPos - 1, xPos + 1};

        if (piece.isBlack()) {

            //Move
            if (isClear(oneAheadBlack) && isInBounds(oneAheadBlack))
                moves.add(oneAheadBlack);
            if (piece.isFirstMove() && isClear(twoAheadBlack) && isInBounds(twoAheadBlack))
                moves.add(twoAheadBlack);
            //Take
            if (isInBounds(takeLeftBlack) && isTakeable(piece, takeLeftBlack))
                moves.add(takeLeftBlack);
            if (isInBounds(takeRightBlack) && isTakeable(piece, takeRightBlack))
                moves.add(takeRightBlack);

        } else {

            //Move
            if (isClear(oneAheadWhite) && isInBounds(oneAheadWhite))
                moves.add(oneAheadWhite);
            if (piece.isFirstMove() && isClear(twoAheadWhite) && isInBounds(twoAheadWhite))
                moves.add(twoAheadWhite);
            //Take
            if (isInBounds(takeLeftWhite) && isTakeable(piece, takeLeftWhite))
                moves.add(takeLeftWhite);
            if (isInBounds(takeRightWhite) && isTakeable(piece, takeRightWhite))
                moves.add(takeRightWhite);
        }
        return moves;
    }

    public ArrayList<int[]> rookMoves(Piece piece) {
        ArrayList<int[]> moves = new ArrayList<>(15);
        int[] position = piece.getPosition();
        int yPos = position[0];
        int xPos = position[1];
        int[] newPos;

        //Left Moves
        for (int i = xPos - 1; i >= 0; i--) {
            newPos = new int[]{yPos, i};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        //Right Moves
        for (int i = xPos + 1; i < 8; i++) {
            newPos = new int[]{yPos, i};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        //Up Moves
        for (int i = yPos - 1; i >= 0; i--) {
            newPos = new int[]{i, xPos};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        //Down Moves
        for (int i = yPos + 1; i < 8; i++) {
            newPos = new int[]{yPos, i};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return moves;
    }

    public ArrayList<int[]> knightMoves(Piece piece) {
        ArrayList<int[]> moves = new ArrayList<>(8);
        int[] position = piece.getPosition();
        int yPos = position[0];
        int xPos = position[1];

        int[][] moveOptions = new int[][]{
                {yPos - 2, xPos - 1}, {yPos - 2, xPos + 1},  //UP
                {yPos - 1, xPos + 2}, {yPos + 1, xPos + 2},  //RIGHT
                {yPos + 2, xPos + 1}, {yPos + 2, xPos - 1},  //DOWN
                {yPos - 1, xPos - 2}, {yPos + 1, xPos - 2}}; //LEFT

        for (int[] move: moveOptions) {
            if (isInBounds(move) && (isClear(move) || isTakeable(piece, move))) {
                moves.add(move);
            }
        }

        return moves;
    }

    public ArrayList<int[]> bishopMoves(Piece piece) {
        ArrayList<int[]> moves = new ArrayList<>(15);
        int[] position = piece.getPosition();
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
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            }
        }

        //Down Right
        for (int i = 1; i < down_right_distance; i++) {
            newPos = new int[]{yPos + i, xPos + i};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            }
        }

        //Down Left
        for (int i = 1; i < down_left_distance; i++) {
            newPos = new int[]{yPos + i, xPos - i};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            }
        }

        //Up Left
        for (int i = 1; i < up_left_distance; i++) {
            newPos = new int[]{yPos - i, xPos - i};
            if (isInBounds(newPos)) {
                if (isClear(newPos)) {
                    moves.add(newPos);
                } else if (isTakeable(piece, newPos)) {
                    moves.add(newPos);
                    break;
                } else {
                    break;
                }
            }
        }

        return moves;
    }

    public ArrayList<int[]> queenMoves(Piece piece) {
        ArrayList<int[]> moves = new ArrayList<>(30);
        moves.addAll(bishopMoves(piece));
        moves.addAll(rookMoves(piece));
        return moves;
    }

    public ArrayList<int[]> kingMoves(Piece piece) {
        ArrayList<int[]> moves = new ArrayList<>(8);

        int[] position = piece.getPosition();
        int yPos = position[0];
        int xPos = position[1];
        int[] newPos;

        //Move
        for (int i = xPos - 1; i <= xPos + 1; i++) {
            for (int j = yPos - 1; j <= yPos + 1; j++) {
                newPos = new int[]{j, i};
                if (newPos != position) {
                    if (isClear(newPos) || isTakeable(piece, newPos)) {
                        moves.add(newPos);
                    }
                }
            }
        }
        
        //Castle
        if (piece.isFirstMove()) {
            int row = piece.isBlack() ? 0 : 7;
            //Queen side
            if (boardState[row][0].isFirstMove()
                    && boardState[row][1] == null
                    && boardState[row][2] == null) {
                moves.add(new int[]{row,1});
            }
            //King side
            if (boardState[row][7].isFirstMove()
                    && boardState[row][6] == null
                    && boardState[row][5] == null) {
                moves.add(new int[]{row,6});
            }
        }

        return moves;
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

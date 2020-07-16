package Pieces;

public class Pawn extends Piece{
    boolean firstMove = true;


    public Pawn (boolean black, int[] position){
        this.black = black;
        this.symbol = black ? '♟' : '♙';
        this.position = position;
        this.pieceType = PIECE.PAWN;
    }

    @Override
    public void setMovablePositions() {
        //TODO Add "En Passant" rule
        this.legalMoves.clear();

        int[] position = this.getPosition();
        int yPos = position[0];
        int xPos = position[1];

        int[] oneAheadBlack = new int[]{yPos + 1, xPos};
        int[] twoAheadBlack = new int[]{yPos + 2, xPos};
        int[] oneAheadWhite = new int[]{yPos - 1, xPos};
        int[] twoAheadWhite = new int[]{yPos - 2, xPos};

        int[] takeLeftBlack = new int[]{yPos - 1, xPos - 1};
        int[] takeRightBlack = new int[]{yPos - 1, xPos + 1};
        int[] takeLeftWhite = new int[]{yPos + 1, xPos - 1};
        int[] takeRightWhite = new int[]{yPos + 1, xPos + 1};

        if (this.black) {
            //Move
            if (isClear(oneAheadBlack) && isInBounds(oneAheadBlack))
                this.legalMoves.add(oneAheadBlack);
            if (this.firstMove && isClear(twoAheadBlack) && isInBounds(twoAheadBlack))
                this.legalMoves.add(twoAheadBlack);
            //Take
            if (isInBounds(takeLeftBlack) && isTakeable(takeLeftBlack))
                this.legalMoves.add(takeLeftBlack);
            if (isInBounds(takeRightBlack) && isTakeable(takeRightBlack))
                this.legalMoves.add(takeRightBlack);

        } else {
            //Move
            if (isClear(oneAheadWhite) && isInBounds(oneAheadWhite))
                this.legalMoves.add(oneAheadWhite);
            else if (this.firstMove && isClear(twoAheadWhite) && isInBounds(twoAheadWhite))
                this.legalMoves.add(twoAheadWhite);
            //Take
            if (isInBounds(takeLeftWhite) && isTakeable(takeLeftWhite))
                this.legalMoves.add(takeLeftWhite);
            if (isInBounds(takeRightWhite) && isTakeable(takeRightWhite))
                this.legalMoves.add(takeRightWhite);
        }
    }
}

package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "♘";
    }

    @Override
    public boolean[][] possibleMoves() {     
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        mat = movePiece(mat, p, -2, -1,this);
        mat = movePiece(mat, p, -2, 1,this);
        mat = movePiece(mat, p, -1, -2,this);
        mat = movePiece(mat, p, 1, -2,this);
        mat = movePiece(mat, p, -1, 2,this);
        mat = movePiece(mat, p, 1, 2,this);
        mat = movePiece(mat, p, 2, -1,this);
        mat = movePiece(mat, p, 2, 1,this);


        return mat;
    }

}

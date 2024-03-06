package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "♖";
    }

    @Override
    public boolean[][] possibleMoves() {     
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        // above
        mat = move(mat, p, -1, 0);

        // left
        mat = move(mat, p, 0, -1);

        // right
        mat = move(mat, p, 0, 1);

        // below
        mat = move(mat, p, 1, 0);


        return mat;
    }
}

package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "♙";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        if(getColor() == Color.WHITE){
            mat = move(mat, -1);
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        }else{
            mat = move(mat, 1);
            if(position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return mat;
    }

    private boolean[][] move(boolean[][]mat, int operator){
        Position p = new Position(0,0);
        mat = this.movePiece(mat, p, (1 * operator), 0, this);
        p.setValues(position.getRow() + (2 * operator), position.getColumn());
        Position p2 = new Position(position.getRow() + (1 * operator), position.getColumn());
        if(canMove(p, this) && canMove(p2, this) && getMoveCount() == 0){
            mat[p.getRow()][p.getColumn()] = true;
        }
        mat = this.movePiece(mat, p, (1 * operator), -1, null);
        mat = this.movePiece(mat, p, (1 * operator), 1, null);
        return mat;
    }
}

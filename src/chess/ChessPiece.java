package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

    protected boolean canMove(Position position, ChessPiece piece) {
        try {
            ChessPiece p = (ChessPiece) getBoard().piece(position);
            try{
                if (piece.toString() == "♙" ){
                    return !getBoard().thereIsAPiece(position) && getBoard().positionExists(position);
                }
            }catch(Exception e){
                return isThereOpponentPiece(position) && getBoard().positionExists(position);
            }
            return (p == null || isThereOpponentPiece(position)) && getBoard().positionExists(position);
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean[][] movePiece(boolean[][] mat, Position p, int row, int column, ChessPiece piece) {
        p.setValues(position.getRow() + row, position.getColumn() + column);
        if (canMove(p,piece)) {
            mat[p.getRow()][p.getColumn()] = true;     
        }
        return mat;
    }

    protected void increaseMoveCount() {
        moveCount++;
    }

    protected void decreaseMoveCount() {
        moveCount--;
    }

    protected boolean[][] move(boolean[][] mat, Position p, int row, int column ) {
        p.setValues(position.getRow() + row, position.getColumn() + column);
        while (true) {  
            if(canMove(p,this)) {
                mat[p.getRow()][p.getColumn()] = true;
                if(getBoard().thereIsAPiece(p)){
                    break;
                }
            }else{
                break;
            }
            p.setValues(p.getRow() + row, p.getColumn() + column);
        }
        return mat;
    }

}

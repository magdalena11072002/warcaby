package org.example.GUI;

import org.example.Data.TypeData;
import org.example.GUI.components.RectangleWithPiece;
import org.example.Logic.pieces.Piece;

public class Board {
    private int size;
    private int[] pieceslines;
    private RectangleWithPiece.colortype piecestand;
    private RectangleWithPiece.colortype firstboard;
    private RectangleWithPiece.colortype secondboard;

    private RectangleWithPiece[][] chessBoard;
    Board(TypeData type) {
        size = type.getSize();
        pieceslines = type.getLinePieces();
        piecestand = type.getPiecesstand();
        firstboard = type.getBoardStart();
        secondboard = firstboard == RectangleWithPiece.colortype.LIGHT ? RectangleWithPiece.colortype.DARK : RectangleWithPiece.colortype.LIGHT;

        Piece.backBites = type.getCaptureBackwards();
        Piece.queenAnywhereAfterCapture = type.getQueenAfterCapture();
    }

    public RectangleWithPiece[][] createBoard() {
        /*if(chessBoard == null){ //singleton??? m
            createBoardd();
        }*/
        createBoardd();
        return chessBoard;

    }

    private void createBoardd() {
        chessBoard = new RectangleWithPiece[size][size];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                RectangleWithPiece square = new RectangleWithPiece(j, i);
                if (((i + j) % 2) == 0) {
                    square.setColortype(firstboard);
                } else {
                    square.setColortype(secondboard);
                }

                if (square.getColortype() == piecestand) {
                    if (pieceslines[j] == -1) {
                        square.createPawn("#FFFFFF", j, i);
                    } else if (pieceslines[j] == 1) {
                        square.createPawn("#000000", j, i);
                    }
                }
                chessBoard[i][j] = square;
            }
        }

    }
}

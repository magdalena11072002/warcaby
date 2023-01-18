package org.example.gui;

import org.example.data.TypeData;
import org.example.gui.components.RectangleWithPiece;
import org.example.logic.pieces.Piece;

public class Board {
    private final int size;
    private final int[] pieceslines;
    private final RectangleWithPiece.colortype piecestand;
    private final RectangleWithPiece.colortype firstboard;
    private final RectangleWithPiece.colortype secondboard;

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

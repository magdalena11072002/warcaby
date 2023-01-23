package org.example.gui;

import org.example.data.TypeData;
import org.example.gui.components.RectangleWithPiece;
import org.example.logic.pieces.Piece;

/**
 * Klasa do tworzenia planszy
 */
public class Board {

    /**
     * @param size - rozmiar planszy
     * @param piecesline - tablica lini w których mają się pojawić pionki
     * @param piecestand - oznacza typ pól na którym stoją pionki
     * @param firstboard - oznaczenie typu pola w lewym górnym rogu planszy
     * @param secondboard - oznaczenie typu pola w prawym dolnym rogu planszy
     * @param chessborad - plansza
     */
    private final int size;
    private final int[] pieceslines;
    private final RectangleWithPiece.Colortype piecestand;
    private final RectangleWithPiece.Colortype firstboard;
    private final RectangleWithPiece.Colortype secondboard;

    private RectangleWithPiece[][] chessBoard;

    /**
     * Konstruktor do stworzenia nowej planszy
     * @param type - typ gry
     */
    public Board(TypeData type) {
        size = type.getSize();
        pieceslines = type.getLinePieces();
        piecestand = type.getPiecesstand();
        firstboard = type.getBoardStart();
        secondboard = firstboard == RectangleWithPiece.Colortype.LIGHT ? RectangleWithPiece.Colortype.DARK : RectangleWithPiece.Colortype.LIGHT;

        Piece.backBites = type.isAbleCaptureBackwards();
        Piece.queenAnywhereAfterCapture = type.isStandingAnywhereQueenAfterCapture();
    }

    /**
     * Funkcja do tworzenia planszy
     * @return - zwraca utworzoną plansze z pionkami
     */
    public RectangleWithPiece[][] createBoard() {
        chessBoard = new RectangleWithPiece[size][size];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                RectangleWithPiece square = new RectangleWithPiece(j, i);
                if ((i + j) % 2 == 0) {
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
        return chessBoard;
    }
}

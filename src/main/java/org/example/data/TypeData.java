package org.example.data;

import org.example.gui.components.RectangleWithPiece;

public interface TypeData {

    int getSize();
    int[] getLinePieces();
    RectangleWithPiece.colortype getPiecesstand();

    RectangleWithPiece.colortype getBoardStart();
    boolean getCaptureBackwards();
    public boolean getQueenAfterCapture();

    int amountOfPawns();
}
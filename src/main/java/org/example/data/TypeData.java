package org.example.data;

import org.example.gui.components.RectangleWithPiece;

public interface TypeData {

    int getSize();
    int[] getLinePieces();
    RectangleWithPiece.Colortype getPiecesstand();

    RectangleWithPiece.Colortype getBoardStart();
    boolean isAbleCaptureBackwards();
    boolean isStandingAnywhereQueenAfterCapture();
}
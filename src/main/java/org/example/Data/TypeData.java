package org.example.Data;

import org.example.GUI.components.RectangleWithPiece;

public interface TypeData {

    int getSize();
    int[] getLinePieces();
    RectangleWithPiece.colortype getPiecesstand();

    RectangleWithPiece.colortype getBoardStart();
    boolean getCaptureBackwards();
    public boolean getQueenAfterCapture();
}
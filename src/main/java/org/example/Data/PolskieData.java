package org.example.Data;

import org.example.GUI.components.RectangleWithPiece;

public class PolskieData implements TypeData {

    private final int size = 10;
    private final int[] pieceslines = {-1, -1, -1, -1, 0, 0, 1, 1, 1, 1};
    private final RectangleWithPiece.colortype piecesstand = RectangleWithPiece.colortype.DARK;

    private final RectangleWithPiece.colortype boardstarts = RectangleWithPiece.colortype.LIGHT;

    private final boolean capturebackward = true;

    //private final boolean obligatorycapture = true;

    private boolean anywherequeenaftercapture = false;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int[] getLinePieces() {
        return pieceslines;
    }

    @Override
    public RectangleWithPiece.colortype getPiecesstand() {
        return piecesstand;
    }

    @Override
    public RectangleWithPiece.colortype getBoardStart() {
        return boardstarts;
    }

    @Override
    public boolean getCaptureBackwards() {
        return capturebackward;
    }

    @Override
    public boolean getQueenAfterCapture() {
        return anywherequeenaftercapture;
    }


}


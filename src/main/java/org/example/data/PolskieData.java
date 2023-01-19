package org.example.data;

import org.example.gui.components.RectangleWithPiece;

public class PolskieData implements TypeData {

    private final int[] pieceslines = {-1, -1, -1, -1, 0, 0, 1, 1, 1, 1};
    private final RectangleWithPiece.Colortype piecesstand = RectangleWithPiece.Colortype.DARK;

    private final RectangleWithPiece.Colortype boardstarts = RectangleWithPiece.Colortype.LIGHT;

    //private final boolean obligatorycapture = true;

    @Override
    public int getSize() {
        int size = 10;
        return size;
    }

    @Override
    public int[] getLinePieces() {
        return pieceslines;
    }

    @Override
    public RectangleWithPiece.Colortype getPiecesstand() {
        return piecesstand;
    }

    @Override
    public RectangleWithPiece.Colortype getBoardStart() {
        return boardstarts;
    }

    @Override
    public boolean isAbleCaptureBackwards() {
        boolean capturebackward = true;
        return capturebackward;
    }

    @Override
    public boolean isStandingAnywhereQueenAfterCapture() {
        boolean anywherequeenaftercapture = false;
        return anywherequeenaftercapture;
    }

}


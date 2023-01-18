package org.example.data;

import org.example.gui.components.RectangleWithPiece;

public class PolskieData implements TypeData {

    private final int[] pieceslines = {-1, -1, -1, -1, 0, 0, 1, 1, 1, 1};
    private final RectangleWithPiece.colortype piecesstand = RectangleWithPiece.colortype.DARK;

    private final RectangleWithPiece.colortype boardstarts = RectangleWithPiece.colortype.LIGHT;

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
    public RectangleWithPiece.colortype getPiecesstand() {
        return piecesstand;
    }

    @Override
    public RectangleWithPiece.colortype getBoardStart() {
        return boardstarts;
    }

    @Override
    public boolean getCaptureBackwards() {
        boolean capturebackward = true;
        return capturebackward;
    }

    @Override
    public boolean getQueenAfterCapture() {
        boolean anywherequeenaftercapture = false;
        return anywherequeenaftercapture;
    }

    @Override
    public int amountOfPawns() {
        return 16;
    }


}


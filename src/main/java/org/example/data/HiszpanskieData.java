package org.example.data;

import org.example.gui.components.RectangleWithPiece;


public class HiszpanskieData implements TypeData {

    private final int[] pieceslines = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final RectangleWithPiece.colortype piecesstand = RectangleWithPiece.colortype.LIGHT;

    private final RectangleWithPiece.colortype boardstarts = RectangleWithPiece.colortype.DARK;

    //boolean obligatorycapture = true; //wszedzie true

    //int amountofpieces = 12*2;

    @Override
    public int getSize() {
        int size = 8;
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
        boolean capturebackward = false;
        return capturebackward;
    }

    @Override
    public boolean getQueenAfterCapture() {
        boolean anywherequeenaftercapture = true;
        return anywherequeenaftercapture;
    }

    @Override
    public int amountOfPawns() {
        return 12;
    }
}

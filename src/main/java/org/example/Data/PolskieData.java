package org.example.Data;

import org.example.GUI.components.RectangleWithPiece;

public class PolskieData implements TypeData {

    private final int size = 10;
    private final int[] pieceslines = {-1,-1,-1,-1,0,0,1,1,1,1};
    private final RectangleWithPiece.colortype piecesstand = RectangleWithPiece.colortype.DARK;

    private final RectangleWithPiece.colortype boardstarts = RectangleWithPiece.colortype.LIGHT;
    private final boolean capturebackward = true;
    private final boolean obligatorycapture = true;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int[] getLinePieces() {
        return pieceslines;
    }

    @Override
    public RectangleWithPiece.colortype getPiecesstand(){
        return piecesstand;
    }



//    boolean anywherequeenaftercapture = false;//zaraz za nia


    //can't move, 15 moves of queen
}


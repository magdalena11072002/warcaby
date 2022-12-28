package org.example.Data;

import org.example.GUI.components.RectangleWithPiece;

public class DwulinioweData implements TypeData{

    int size = 8;

    int[] pieceslines = {-1,-1,0,0,0,0,1,1};
    private final RectangleWithPiece.colortype piecesstand = RectangleWithPiece.colortype.DARK;

    private final RectangleWithPiece.colortype boardstarts = RectangleWithPiece.colortype.LIGHT;
    private final boolean capturebackward = false;
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
    public RectangleWithPiece.colortype getPiecesstand() {
        return piecesstand;
    }

//    boolean anywherequeenaftercapture = true;
//    boolean queenmultiblecapture = true;


}

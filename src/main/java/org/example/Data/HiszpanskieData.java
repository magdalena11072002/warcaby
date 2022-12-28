package org.example.Data;

import org.example.GUI.components.RectangleWithPiece;


public class HiszpanskieData implements TypeData {

    int size = 8;

    int[] pieceslines = {-1,-1,-1,0,0,1,1,1};
    private final RectangleWithPiece.colortype piecesstand = RectangleWithPiece.colortype.LIGHT;

    private final RectangleWithPiece.colortype boardstarts = RectangleWithPiece.colortype.DARK;

    boolean capturebackward;
    boolean obligatorycapture;

    //    boolean anywherequeenaftercapture = true;
//    boolean queenmultiblecapture = true;
    int amountofpieces = 12*2;

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
    //od razu damka i koniec ruchu
}

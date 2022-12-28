package org.example.Data;

import org.example.GUI.components.RectangleWithPiece;

public interface TypeData {

    int getSize();
    int[] getLinePieces();
    RectangleWithPiece.colortype getPiecesstand();

    //RectangleWithPiece.colortype getFirstboard();
}
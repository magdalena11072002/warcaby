package org.example.data;

import org.example.gui.components.RectangleWithPiece;

/**
 * Klasa dla pierwszego trybu warcab.
 */
public class DwulinioweData implements TypeData {

    /**
     * @param pieceslines - linie na których będą mieszczane pionki
     * @param piecesstand - oznacza typ pól na którym stoją pionki
     * @param boardstarts - oznaczenie koloru w lewym górnym rogu planszy
     */
    private final int[] pieceslines = {-1, -1, 0, 0, 0, 0, 1, 1};
    private final RectangleWithPiece.Colortype piecesstand = RectangleWithPiece.Colortype.DARK;
    private final RectangleWithPiece.Colortype boardstarts = RectangleWithPiece.Colortype.LIGHT;


    /**
     * Funkcja która zwraca rozmiar planszy.
     * @return
     */
    @Override
    public int getSize() {
        int size = 8;
        return size;
    }

    /**
     * Funkcja która zwraca listę z rzędami w których będą umieszczane pionki.
     * @return lista zawiera wartości -1 dla białych pionków, 1 dla czarnych, 0 dla pola pustego
     */
    @Override
    public int[] getLinePieces() {
        return pieceslines;
    }

    /**
     * Funkcja która zwraca typ pól na którym stoją pionki.
     * @return Colortype.DARK jesli pionki stoją na ciemnym polu
     */
    @Override
    public RectangleWithPiece.Colortype getPiecesstand() {
        return piecesstand;
    }

    /**
     * Funkcja która zwraca typ pola w lewym górnym rogu planszy.
     * @return Colortype.LIGHT jesli jest to jasne pole
     */
    @Override
    public RectangleWithPiece.Colortype getBoardStart() {
        return boardstarts;
    }


    /**
     * Funkcja która zwraca czy pionki moga bić "do tyłu".
     * @return true jesli może wykonywać "bicia do tyłu"
     */
    @Override
    public boolean isAbleCaptureBackwards() {
        boolean capturebackward = false;
        return capturebackward;
    }


    /**
     * Funkcja która zwraca czy damki mogą stanąć dalej niż zaraz za zbitym pionkiem.
     * @return true jesli może stanąć na dalszym polu
     */
    @Override
    public boolean isStandingAnywhereQueenAfterCapture() {
        boolean anywherequeenaftercapture = true;
        return anywherequeenaftercapture;
    }

}

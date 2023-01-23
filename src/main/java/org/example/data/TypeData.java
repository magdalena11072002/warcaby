package org.example.data;

import org.example.gui.components.RectangleWithPiece;

/**
 * Do tworzenia różnych typów warcab
 */
public interface TypeData {

    /**
     * Funckja która zwraca rozmiar planszy
     * @return
     */
    int getSize();

    /**
     * Funkcja która zwraca rzędy w których będą umieszczane pionki
     * @return
     */
    int[] getLinePieces();

    /**
     * Funkcja która zwraca rzędy w których będą umieszczane pionki
     * @return
     */
    RectangleWithPiece.Colortype getPiecesstand();

    /**
     * Funkcja która zwraca typ pola w lewym górnym rogu planszy
     * @return
     */
    RectangleWithPiece.Colortype getBoardStart();

    /**
     * Funkcja która zwraca czy pionki moga bić "do tyłu"
     * @return
     */
    boolean isAbleCaptureBackwards();

    /**
     * Funkcja która zwraca czy damki mogą stanąć dalej niż zaraz za zbitym pionkiem
     * @return
     */
    boolean isStandingAnywhereQueenAfterCapture();
}

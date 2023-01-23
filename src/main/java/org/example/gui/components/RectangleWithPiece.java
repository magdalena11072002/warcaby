package org.example.gui.components;


import javafx.scene.paint.Color;
import org.example.gui.GameWindow;
import org.example.logic.Pawn;
import org.example.logic.Queen;
import org.example.logic.pieces.Piece;
import javafx.scene.shape.Rectangle;

/**
 * Klasa do tworzenia pionków i królowej, ustawiania ich na planszy oraz nadawania im kolorów
 */
public class RectangleWithPiece extends Rectangle {

    /**
     * @param piece - pionek
     * @param myX - współrzędna X pionka
     * @param myY - współrzędna Y pionka
     * @param type - zawiera kolor pola
     */
    private Piece piece = null;
    private final int myX;
    private final int myY;
    private Colortype type;

    /**
     * enum do ustalania kolorów pionków
     */
    public enum Colortype {
        LIGHT,
        DARK
    }

    /**
     * Funkcja d ustawiania pionków na odpowiednich lokalizacjach pól
     * @param myY
     * @param myX
     */
    public RectangleWithPiece(int myY, int myX) {
        super();
        int rectanglesize = 50;
        setHeight(rectanglesize);
        setWidth(rectanglesize);
        this.myX = myX;
        this.myY = myY;
        setupMouseEvents();
    }

    /**
     * Funkcja nadająca polą kolory
     *
     * @param color
     */
    public void setColortype(Colortype color) {
        type = color;

        if (type.equals(Colortype.LIGHT)) {
            setFill(Color.LIGHTGRAY);
        } else if (type == Colortype.DARK) {
            setFill(Color.DARKGREY);
        }
    }

    /**
     * Funkcja zwracająca kolor danego pola
     * @return
     */
    public Colortype getColortype() {
        return type;
    }

    /**
     * Funkcja aby dostać się bezpośrednio do pionka
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Funkcja ustawiająca pionka
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Funkcja do tworzenia pionka
     * @param color - kolor pionka
     * @param pieceY -współrzędna Y
     * @param pieceX - współrzędna X
     * @return
     */
    public Piece createPawn(String color, int pieceY, int pieceX) {
        this.piece = new Pawn(color, pieceY, pieceX);
        return piece;
    }

    /**
     * Funckja do tworzenia królowej
     * @param color - kolor królowej
     * @param pieceY - wpółrzędna Y
     * @param pieceX - współrzędna X
     * @return
     */
    public Piece createQueen(String color, int pieceY, int pieceX) {
        this.piece = new Queen(color, pieceY, pieceX);
        return piece;
    }

    /**
     * Funkcja do osługi myszy
     */
    private void setupMouseEvents() {
        setOnMouseClicked(e -> {
            if (this.getPiece() != null) {
                System.out.println("nie moge sie tu ruszyc, stoi pionek");
                return;
            }
            System.out.println("wybrałem pole:");
            System.out.println("docelowe X=" + myX);
            System.out.println("docelowe Y=" + myY);
            GameWindow.makeMove(myX, myY);
        });
    }

    /**
     * Funkcja do osługi myszy po zakonczeniu gry
     */
    public void setEndingMouseEvents(){
        setOnMouseClicked(e -> {
        });
    }
}


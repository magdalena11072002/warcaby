package org.example.GUI.components;


import org.example.GUI.GameWindow;
import org.example.Logic.Pawn;
import org.example.Logic.pieces.Piece;
import javafx.scene.shape.Rectangle;

public class RectangleWithPiece extends Rectangle { //zmienialam pomysl na rozszerzenie, wydaje sie czytelniejsze
    private Piece piece = null;
    private final int myX;
    private final int myY;

    public RectangleWithPiece(int v1, int v2, int myY, int myX) {
        super(v1, v2);
        this.myX = myX;
        this.myY = myY;
        setupMouseEvents();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece createPawn(String color, int pieceY, int pieceX) {
        this.piece = new Pawn(color, pieceY, pieceX);
        return piece;
    }

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
}


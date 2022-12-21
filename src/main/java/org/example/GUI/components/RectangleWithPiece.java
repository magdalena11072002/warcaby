package org.example.GUI.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.Logic.Pawn;
import org.example.Logic.pieces.Piece;


public class RectangleWithPiece {
    private Piece piece;
    private Rectangle square; //todo myslalam o referencji do pol lewo i prawo, mozna pomyslec, wtedy ruszanie byloyb latwiejsze do implementacji ale mniej wydajne


    public RectangleWithPiece(int v1, int v2) {
       this.square = new Rectangle(v1,v2);
       this.piece=null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) { // todo do przemyslenia czy potrzebne
        this.piece = piece;
        piece.setHome(this.square);
    }

    public Piece createPawn(String color){    //todo dla krolowej tez
        this.piece = new Pawn(color,this.square);
        return piece;
    }



    public Rectangle getSquare() {
        return square;
    }

    public void setSquare(Rectangle square) {
        this.square = square;
    }
}

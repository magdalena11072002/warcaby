package org.example.GUI.components;


import javafx.scene.paint.Color;
import org.example.GUI.GameWindow;
import org.example.Logic.Pawn;
import org.example.Logic.Queen;
import org.example.Logic.pieces.Piece;
import javafx.scene.shape.Rectangle;

public class RectangleWithPiece extends Rectangle { //zmienialam pomysl na rozszerzenie, wydaje sie czytelniejsze
    private Piece piece = null;
    private final int myX;
    private final int myY;

    private final int rectanglesize = 50;

    public enum colortype {
        LIGHT,
        DARK
    }
    private colortype type;

    public RectangleWithPiece(int myY, int myX) { //ZMIANA wszystkie kwadraty maja byc takie same wiec nie ma sensu podawac wielkosci, wystarczy sama lokalizacja
        super();
        setHeight(rectanglesize);
        setWidth(rectanglesize);
        this.myX = myX;
        this.myY = myY;
        setupMouseEvents();
    }

    /*
    public RectangleWithPiece(int myY, int myX, colortype color){
        super(50, 50);
        this.myX = myX;
        this.myY = myY;
        setColortype(color);
        setupMouseEvents();
    }
     */
    public void setColortype(colortype color) {
        type = color;

        if (type.equals(colortype.LIGHT)) {
            setFill(Color.LIGHTGRAY);
        } else if (type == colortype.DARK) {
            setFill(Color.DARKGREY);
        }
    }
    public colortype getColortype() {
        return type;
    }

    /*public colortype otherColortype(colortype type){
        if( type.equals(colortype.LIGHT)){
            return colortype.DARK;
        }
        else {
            return colortype.LIGHT;
        }
    }*/


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

    public Piece createQueen(String color, int pieceY, int pieceX) {
        this.piece = new Queen(color, pieceY, pieceX);
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

    public void setEndingMouseEvents(){
        setOnMouseClicked(e -> {
        });
    }
}


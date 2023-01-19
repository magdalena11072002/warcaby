package org.example.gui.components;


import javafx.scene.paint.Color;
import org.example.gui.GameWindow;
import org.example.logic.Pawn;
import org.example.logic.Queen;
import org.example.logic.pieces.Piece;
import javafx.scene.shape.Rectangle;

public class RectangleWithPiece extends Rectangle {
    private Piece piece = null;
    private final int myX;
    private final int myY;

    public enum Colortype {
        LIGHT,
        DARK
    }
    private Colortype type;

    public RectangleWithPiece(int myY, int myX) {
        super();
        int rectanglesize = 50;
        setHeight(rectanglesize);
        setWidth(rectanglesize);
        this.myX = myX;
        this.myY = myY;
        setupMouseEvents();
    }

    /*
    public RectangleWithPiece(int myY, int myX, Colortype color){
        super(50, 50);
        this.myX = myX;
        this.myY = myY;
        setColortype(color);
        setupMouseEvents();
    }
     */
    public void setColortype(Colortype color) {
        type = color;

        if (type.equals(Colortype.LIGHT)) {
            setFill(Color.LIGHTGRAY);
        } else if (type == Colortype.DARK) {
            setFill(Color.DARKGREY);
        }
    }
    public Colortype getColortype() {
        return type;
    }

    /*public Colortype otherColortype(Colortype type){
        if( type.equals(Colortype.LIGHT)){
            return Colortype.DARK;
        }
        else {
            return Colortype.LIGHT;
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


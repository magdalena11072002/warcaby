package org.example.Logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.Logic.pieces.Piece;

public class Pawn extends Piece{

    public Pawn(String color, Rectangle home){
        super(color, home);
    }


    @Override
    public boolean move(Rectangle target) {
        return false;
    }

    @Override
    public void showAvailableMoves() {
        //todo powinien pokazac posuniecia mozliwe na boardzie
        System.out.println("pokazuje posuniecia");
    }

    @Override
    public void select() {

    }
}

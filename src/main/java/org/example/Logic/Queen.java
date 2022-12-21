package org.example.Logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.Logic.pieces.Piece;



public class Queen extends Piece {
    public Queen(String color, Rectangle home) {
        super(color, home);
    }

    @Override
    public boolean move(Rectangle target) {
        return false;
    }

    @Override
    public void showAvailableMoves() {

    }

    @Override
    public void select() {

    }
}

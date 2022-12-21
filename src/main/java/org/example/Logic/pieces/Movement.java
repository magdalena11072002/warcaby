package org.example.Logic.pieces;

import javafx.scene.shape.Rectangle;

public interface Movement {
    boolean move(Rectangle target);
    void showAvailableMoves();
    void select(); // do przemyslenia
}

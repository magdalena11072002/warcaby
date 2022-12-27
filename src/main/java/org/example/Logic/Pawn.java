package org.example.Logic;

import org.example.GUI.components.RectangleWithPiece;
import org.example.GUI.GameWindow;
import org.example.Logic.pieces.Piece;


public class Pawn extends Piece {
    public Pawn(String color, int myY, int myX) {
        super(color, myY, myX);
    }

    @Override
    public boolean makeMove() { return killPiece() || simpleMove();}

    private boolean killPiece() {
        if (coordinates.getTargetX() == this.myX + 2 || coordinates.getTargetX() == this.myX - 2) {
            if (color.equals("#FFFFFF")) { //#FFFFFF-bialy
                if (coordinates.getTargetY() == this.myY + 2 || coordinates.getTargetY() == this.myY - 2) {
                    RectangleWithPiece rectangleWithPieceToKill = GameWindow.checkSquare((coordinates.getSelectedX() + coordinates.getTargetX()) / 2, (coordinates.getSelectedY() + coordinates.getTargetY()) / 2);
                    if (rectangleWithPieceToKill.getPiece() == null) return false; //nie ma bicia
                    if (rectangleWithPieceToKill.getPiece().getColor().equals("#FFFFFF")) return false;
                    rectangleWithPieceToKill.setPiece(null);
                    this.myY = coordinates.getTargetY();
                    this.myX = coordinates.getTargetX();
                    coordinates.setLocked(true);
                    return true;
                }
            } else {
                if (coordinates.getTargetY() == this.myY - 2) {
                    RectangleWithPiece rectangleWithPieceToKill = GameWindow.checkSquare((coordinates.getSelectedX() + coordinates.getTargetX()) / 2, (coordinates.getSelectedY() + coordinates.getTargetY()) / 2);
                    if (rectangleWithPieceToKill.getPiece() == null) return false; //nie ma bicia
                    if (rectangleWithPieceToKill.getPiece().getColor().equals("#000000")) return false;
                    rectangleWithPieceToKill.setPiece(null);
                    this.myY = coordinates.getTargetY();
                    this.myX = coordinates.getTargetX();
                    coordinates.setLocked(true);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean simpleMove() {
        if (coordinates.getTargetX() == this.myX + 1 || coordinates.getTargetX() == this.myX - 1) {//czarnych i bialych ta sama zasada
            if (color.equals("#FFFFFF")) { //#FFFFFF-bialy
                if (coordinates.getTargetY() == this.myY + 1) {
                    this.myY = coordinates.getTargetY();
                    this.myX = coordinates.getTargetX();
                    return true;
                }
            } else if (coordinates.getTargetY() == this.myY - 1) {//#000000-czarny
                this.myY = coordinates.getTargetY();
                this.myX = coordinates.getTargetX();
                return true;
            }
        }
        return false;
    }

    private boolean bites() {
        if (coordinates.getTargetX() == this.myX + 2 || coordinates.getTargetX() == this.myX - 2) {
            if (color.equals("#FFFFFF")) { //#FFFFFF-bialy
                if (coordinates.getTargetY() == this.myY + 2 || coordinates.getTargetY() == this.myY - 2) {
                }
            } else {
                if (coordinates.getTargetY() == this.myY - 2) {
                }
            }
            return true;
        }
        return false;
    }
}

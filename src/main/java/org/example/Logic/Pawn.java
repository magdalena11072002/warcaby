package org.example.Logic;

import org.example.GUI.components.RectangleWithPiece;
import org.example.GUI.GameWindow;
import org.example.Logic.pieces.Piece;

import java.util.Objects;


public class Pawn extends Piece {
    public Pawn(String color, int myY, int myX) {
        super(color, myY, myX);
    }

    @Override
    public boolean makeMove() {
        coordinates = GameWindow.getCoordinates();
        boolean moves = killPiece() || simpleMove();
        GameWindow.setCoordinates(coordinates);
        return moves;
    }

    @Override
    public int possibleBitesHere() {

        int amount = 0;
        int border = GameWindow.getBoardSize();

        if( myX > 1 && myY > 1 ){//lewo gora, przynajmniej 2 od krawedzi
            if(GameWindow.checkSquare(myX-2, myY - 2).getPiece() == null){
                RectangleWithPiece tokill = GameWindow.checkSquare(myX-1, myY-1);
                if(tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)){ //jesli obok pionek i innego koloru
                    amount += 1;
                }
            }
        }
        if(myX < border-1 && myY > 1 ){ //prawo gora
            if(GameWindow.checkSquare(myX+2,myY-2).getPiece() == null) {
                RectangleWithPiece tokill = GameWindow.checkSquare(myX + 1, myY - 1);
                if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                    amount += 1;
                }
            }
        }
        if(myX < border-1 && myY < border-1 ){ //prawo dol
            if(GameWindow.checkSquare(myX+2,myY+2).getPiece() == null) {
                RectangleWithPiece tokill = GameWindow.checkSquare(myX + 1, myY + 1);
                if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                    amount += 1;
                }
            }
        }
        if(myX > 1 && myY < border-1 ){ //lewo gora
            if(GameWindow.checkSquare(myX-2,myY+2).getPiece() == null) {
                RectangleWithPiece tokill = GameWindow.checkSquare(myX - 1, myY + 1);
                if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                    amount += 1;
                }
            }
        }
        return amount;
    }

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
                    coordinates.nextMove();
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
                    coordinates.nextMove();

                    return true;
                }
            }
        }

        return false;
    }

    private boolean simpleMove() {
        if(coordinates.getAmountOfMoves() == 0) {

            if (coordinates.getTargetX() == this.myX + 1 || coordinates.getTargetX() == this.myX - 1) {//czarnych i bialych ta sama zasada
                if (color.equals("#FFFFFF")) { //#FFFFFF-bialy
                    if (coordinates.getTargetY() == this.myY + 1) {
                        this.myY = coordinates.getTargetY();
                        this.myX = coordinates.getTargetX();
                        coordinates.endMove();
                        return true;
                    }
                } else if (coordinates.getTargetY() == this.myY - 1) {//#000000-czarny
                    this.myY = coordinates.getTargetY();
                    this.myX = coordinates.getTargetX();
                    coordinates.endMove();
                    return true;
                }
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

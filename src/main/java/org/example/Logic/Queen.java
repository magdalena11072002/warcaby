package org.example.Logic;

import org.example.GUI.GameWindow;
import org.example.GUI.components.RectangleWithPiece;
import org.example.Logic.pieces.Piece;

import static java.lang.Math.abs;


public class Queen extends Piece {

    public Queen(String color, int myY, int myX) {

        super(color, myY, myX);
        //zmiana wygladu
        setStyle(
                getStyle()
                        + "-fx-background-color: red;"
        );
    }

    @Override
    public boolean makeMove() {
        coordinates = GameWindow.getCoordinates();

        int tx = coordinates.getTargetX();
        int ty = coordinates.getTargetY();

        RectangleWithPiece tokill = whatpiecestokill(tx, ty);
        GameWindow.setCoordinates(coordinates);

        if (tokill == null) {
            return false;
        } else if (tokill == GameWindow.checkSquare(myX, myY)) {
            return longMove();
        } else {
            return killPiece(tokill);
        }
    }

    @Override
    public int possibleBitesHere() {

        int targetX;
        int targetY;

        int amount = 0;

        if (myX > 0) {
            targetX = 0;
            targetY = myY - myX; //myY + (-1)* myX;
            while (targetY < 0 && targetY != myY) {
                targetX++;
                targetY++;
            }
            amount += anybites(targetX, targetY, -1, -1);
        }
        if (myY > 0) {
            targetY = 0;
            targetX = myX + myY;
            while (targetX > GameWindow.getBoardSize() && targetX != myX) {
                targetX--;
                targetY++;
            }
            amount += anybites(targetX, targetY, 1, -1);
        }
        if (myX < GameWindow.getBoardSize()) {
            targetX = GameWindow.getBoardSize();
            targetY = myY + (targetX - myX);
            while (targetY > GameWindow.getBoardSize() && targetY != myY) {
                targetX--;
                targetY--;
            }
            amount += anybites(targetX, targetY, 1, 1);
        }
        if (myY < GameWindow.getBoardSize()) {
            targetY = GameWindow.getBoardSize();
            targetX = myX - (targetY - myY);
            while (targetX < 0) {
                targetX++;
                targetY--;
            }
            amount += anybites(targetX, targetY, -1, 1);
        }
        return amount;
    }

    @Override
    public int longestway() {
        return 0;
    }

    private RectangleWithPiece whatpiecestokill(int targetX, int targetY) {
        RectangleWithPiece tokill = null;
        int amount = 0;

        int forX;
        int forY;
        //po lewej = -1; po prawej = 1; ten sam = 0
        forX = Integer.compare(targetX, myX);
        //nizej,wyzej
        forY = Integer.compare(targetY, myY);


        //jesli sa po przekatnej
        if ((forX * (targetX - myX)) == (forY * (targetY - myY))) {


            int i = 1;
            while ((myX != targetX - i * forX) && (myY != targetY - i * forY)) { //teoretycznie wystarczylby jeden warunek
                RectangleWithPiece rwptokill = GameWindow.checkSquare((targetX - i * forX), (targetY - i * forY));
                //nastepny
                if (rwptokill.getPiece() != null) {
                    //ten sam kolor
                    if (rwptokill.getPiece().getColor().equals(color)) {
                        return null;
                    }
                    amount++;
                    if (amount > 1) {
                        break;
                    }
                    if (i == 1 || queenAnywhereAfterCapture) {
                        tokill = rwptokill; //?czy to na pewno to bedzie
                    } else {
                        break;
                    }
                }
                i++;
            }
            //jesli znaleziono 1, to on do usuniecia
            if (amount == 1) {
                return tokill;
            }
            //jesli nic nie znaleziono to tylko ruch
            if (amount == 0) {
                return GameWindow.checkSquare(myX, myY);
            }
        }
        //nic nie ma do zabicia
        return null;
    }

    private boolean killPiece(RectangleWithPiece tokill) {
        if (tokill == null || tokill.getPiece() == this) {
            return false;
        }

        tokill.setPiece(null);
        myY = coordinates.getTargetY();
        myX = coordinates.getTargetX();

        coordinates.nextMove();
        return true;
    }

    private boolean longMove() {
        if (coordinates.getAmountOfMoves() == 0) {
            if (abs(coordinates.getTargetX() - myX) == abs(coordinates.getTargetY() - myY)) {
                this.myY = coordinates.getTargetY();
                this.myX = coordinates.getTargetX();

                coordinates.endMove();
                return true;
            }
        }
        return  false;
    }

    public int anybites(int targetX, int targetY, int forX, int forY) {
        //jesli nie ma pionka to nizej, az bedzie
        while (GameWindow.checkSquare(targetX, targetY).getPiece() != null) {  //najpozniej my sami
            if (GameWindow.checkSquare(targetX, targetY).getPiece() == this) {    //jesli my sami to nie bedzie - najmniejszy
                return 0;
            }
            targetX -= forX; //zbliza sie do nas
            targetY -= forY;
        }

        RectangleWithPiece move = whatpiecestokill(targetX, targetY);

        if (move == null) {
            //do nastepnego
            return anybites(targetX - forX, targetY - forY, forX, forY); //do blizszego pionka - w ostatecznosci wyrowna do siebie)
            //alternatywnie: return 0;  - odgornie zmniejszac
        }
        if (move == GameWindow.checkSquare(myX, myY)) {
            return 0;   //chodzenie zrobione wczesniej
        } else {
            return 1;
        }
        //return 0;
    }

}

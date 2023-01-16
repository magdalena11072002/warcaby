package org.example.Logic;

import java.util.HashSet;
import java.util.Objects;
import org.example.GUI.GameWindow;
import org.example.GUI.components.RectangleWithPiece;
import org.example.Logic.pieces.Piece;

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
        return possibleBitesHere(myX, myY);
    }

    public int possibleBitesHere(int xX, int yY) {

        int amount = 0;
        int border = GameWindow.getBoardSize();

        if (!color.equals("#FFFFFF") || backBites) {
            if (xX > 1 && yY > 1) { //lewo gora, przynajmniej 2 od krawedzi
                if (GameWindow.checkSquare(xX - 2, yY - 2).getPiece() == null) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX - 1, yY - 1);
                    if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) { //jesli obok pionek i innego koloru
                        amount += 1;
                    }
                }
            }
            if (xX < border - 1 && yY > 1) { //prawo gora
                if (GameWindow.checkSquare(xX + 2, yY - 2).getPiece() == null) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX + 1, yY - 1);
                    if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                        amount += 1;
                    }
                }
            }
        }
        if (color.equals("#FFFFFF") || backBites) {
            if (xX < border - 1 && yY < border - 1) { //prawo dol
                if (GameWindow.checkSquare(xX + 2, yY + 2).getPiece() == null) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX + 1, yY + 1);
                    if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                        amount += 1;
                    }
                }
            }
            if (xX > 1 && yY < border - 1) { //lewo gora
                if (GameWindow.checkSquare(xX - 2, yY + 2).getPiece() == null) {                                    // BLAD
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX - 1, yY + 1);
                    if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                        amount += 1;
                    }
                }
            }
        }
        return amount;
    }

    @Override
    public int longestway() {
        //return longestway(myX,myY, new HashSet<RectangleWithPiece>());

        int trasa = longestway(myX, myY, new HashSet<RectangleWithPiece>());
        System.out.println(" ! taki dla tego punktu " + trasa);
        return trasa;
    }

    private boolean killPiece() {
        if (coordinates.getTargetX() == this.myX + 2 || coordinates.getTargetX() == this.myX - 2) {
            if (color.equals("#FFFFFF")) { //#FFFFFF-bialy
                if ((coordinates.getTargetY() == this.myY + 2) || (backBites && coordinates.getTargetY() == this.myY - 2)) {
                    RectangleWithPiece rectangleWithPieceToKill = GameWindow.checkSquare((coordinates.getSelectedX() + coordinates.getTargetX()) / 2, (coordinates.getSelectedY() + coordinates.getTargetY()) / 2);
                    if (rectangleWithPieceToKill.getPiece() == null) { return false; } //nie ma bicia
                    if (rectangleWithPieceToKill.getPiece().getColor().equals("#FFFFFF")) { return false; }
                    rectangleWithPieceToKill.setPiece(null);
                    this.myY = coordinates.getTargetY();
                    this.myX = coordinates.getTargetX();
                    coordinates.setLocked(true);
                    coordinates.nextMove();
                    return true;
                }
            } else {
                if (coordinates.getTargetY() == this.myY - 2 || (backBites && coordinates.getTargetY() == this.myY + 2)) {
                    RectangleWithPiece rectangleWithPieceToKill = GameWindow.checkSquare((coordinates.getSelectedX() + coordinates.getTargetX()) / 2, (coordinates.getSelectedY() + coordinates.getTargetY()) / 2);
                    if (rectangleWithPieceToKill.getPiece() == null) { return false; } //nie ma bicia
                    if (rectangleWithPieceToKill.getPiece().getColor().equals("#000000")) { return false; }
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
        if (coordinates.getAmountOfMoves() == 0) {

            if (coordinates.getTargetX() == this.myX + 1 || coordinates.getTargetX() == this.myX - 1) { //czarnych i bialych ta sama zasada
                if (color.equals("#FFFFFF")) { //#FFFFFF-bialy
                    if (coordinates.getTargetY() == this.myY + 1) {
                        this.myY = coordinates.getTargetY();
                        this.myX = coordinates.getTargetX();
                        coordinates.endMove();
                        return true;
                    }
                } else if (coordinates.getTargetY() == this.myY - 1) { //#000000-czarny
                    this.myY = coordinates.getTargetY();
                    this.myX = coordinates.getTargetX();
                    coordinates.endMove();
                    return true;
                }
            }
        }
        return false;
    }

    /*
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
    }*/

    public int longestway(int xX, int yY, HashSet<RectangleWithPiece> biten) {
        int way = 0; //liczy ogolna
        int newway; //to jest na kawalek do porownania

        int border = GameWindow.getBoardSize();

        if (possibleBitesHere(xX, yY) != 0) {             //sprawdzanie sciezki bic
            if (color.equals("#000000") || backBites) {
                if (xX > 1 && yY > 1) {   //lewo gora
                    if (GameWindow.checkSquare(xX - 2, yY - 2).getPiece() == null) { //jest gdzie isc
                        RectangleWithPiece tokill = GameWindow.checkSquare(xX - 1, yY - 1);   //tam zbic
                        if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {  //jesli cos tam stoi i inny kolor
                            HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);    //kopia juz zbitych
                            if (!alreadybiten.contains(tokill)) {    //jesli nie ma w juz zbitych
                                alreadybiten.add(tokill);   //dodaj
                                newway = longestway(xX - 2, yY - 2, alreadybiten) + 1;

                                if (newway > way) {
                                    way = newway;
                                }
                            }
                        }
                    }
                }
                if (xX < border - 1 && yY > 1) { //prawo gora
                    if (GameWindow.checkSquare(xX + 2, yY - 2).getPiece() == null) {
                        RectangleWithPiece tokill = GameWindow.checkSquare(xX + 1, yY - 1);
                        if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                            HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                            if (!alreadybiten.contains(tokill)) {
                                alreadybiten.add(tokill);
                                newway = longestway(xX + 2, yY - 2, alreadybiten) + 1;
                                if (newway > way) {
                                    way = newway;
                                }
                            }

                        }
                    }
                }
            }
            if (color.equals("#FFFFFF") || backBites) {
                if (xX < border - 1 && yY < border - 1) { //prawo dol
                    if (GameWindow.checkSquare(xX + 2, yY + 2).getPiece() == null) {
                        RectangleWithPiece tokill = GameWindow.checkSquare(xX + 1, yY + 1);
                        if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                            HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                            if (!alreadybiten.contains(tokill)) {
                                alreadybiten.add(tokill);
                                newway = longestway(xX + 2, yY + 2, alreadybiten) + 1;
                                if (newway > way) {
                                    way = newway;
                                }
                            }
                        }
                    }
                }
                if (xX > 1 && yY < border - 1) { //lewo gora
                    if (GameWindow.checkSquare(xX - 2, yY + 2).getPiece() == null) {
                        RectangleWithPiece tokill = GameWindow.checkSquare(xX - 1, yY + 1);
                        if (tokill.getPiece() != null && !Objects.equals(tokill.getPiece().getColor(), color)) {
                            HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                            if (!alreadybiten.contains(tokill)) {
                                alreadybiten.add(tokill);
                                newway = longestway(xX - 2, yY + 2, alreadybiten) + 1;
                                if (newway > way) {
                                    way = newway;
                                }
                            }
                        }
                    }
                }
            }
        } else if (biten.isEmpty()) {  //puste - nic nie zbito = pierwsze powtorzenie
            newway = -1;
            if (color.equals("#FFFFFF") && yY < border) {
                if (xX > 0) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX - 1, yY + 1);   //to co zbic
                    if (tokill.getPiece() == null) {
                        newway = 0;
                    }
                }
                if (xX < border) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX + 1, yY + 1);   //to co zbic
                    if (tokill.getPiece() == null) {
                        newway = 0;
                    }
                }
            } else if (color.equals("#000000") && yY > 0) {
                if (xX > 0) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX - 1, yY - 1);   //to co zbic
                    if (tokill.getPiece() == null) {
                        newway = 0;
                    }
                }
                if (xX < border) {
                    RectangleWithPiece tokill = GameWindow.checkSquare(xX + 1, yY - 1);   //to co zbic
                    if (tokill.getPiece() == null) {
                        newway = 0;
                    }
                }
            }
            if (newway != 0) {
                way = newway; //= -1
            }
        }
        return way;
    }


}

package org.example.logic;

import javafx.scene.paint.Color;
import org.example.gui.GameWindow;
import org.example.gui.components.RectangleWithPiece;
import org.example.logic.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;


/**
 * Klasa ado obsługi królowej
 */
public class Queen extends Piece {

    /**
     * Konstruktor do tworzenia królowej
     * @param color
     * @param myY
     * @param myX
     */
    public Queen(String color, int myY, int myX) {

        super(color, myY, myX);
        setFill(Color.RED);
    }

    /**
     * Funkcja do wykonywania uchów królową
     * @return
     */
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

    /**
     * Funkcja do sprawdzania ilości możliwych bić
     * @return
     */
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

    /**
     * Funkca zwracająca najdłuższą drogę dla damki
     * @return
     */
    @Override
    public int longestway() {
        int trasa = longestway(myX, myY, new HashSet<>());
        //System.out.println("   !! damka, najdluższy: " + trasa);
        return trasa;
    }

    /**
     * Funkcja zwracająca który pionek można zbić
     * @param targetX
     * @param targetY
     * @return
     */
    private RectangleWithPiece whatpiecestokill(int targetX, int targetY) {
        return  whatpiecestokill(myX, myY, targetX, targetY, new HashSet<>());
    }

    /**
     * Funkca sprawdzająca czy można, a jesli tak to który pionek zbić
     * @param startedX
     * @param startedY
     * @param targetX
     * @param targetY
     * @param biten
     * @return
     */
    private RectangleWithPiece whatpiecestokill(int startedX, int startedY, int targetX, int targetY, Set<RectangleWithPiece> biten) {
        RectangleWithPiece tokill = null;
        int amount = 0;

        int forX = Integer.compare(targetX, startedX); //po lewej = -1; po prawej = 1; ten sam = 0
        int forY = Integer.compare(targetY, startedY); //nizej,wyzej

        //jesli sa po przekatnej
        if (forX * (targetX - startedX) == forY * (targetY - startedY)) {
            int i = 1;
            while ((startedX != targetX - i * forX) && (startedY != targetY - i * forY)) { //teoretycznie wystarczylby jeden warunek
                RectangleWithPiece rwptokill = GameWindow.checkSquare((targetX - i * forX), (targetY - i * forY));
                //nastepny
                if (rwptokill.getPiece() != null) {
                    //ten sam kolor
                    if (rwptokill.getPiece().getColor().equals(color)) {
                        return null;
                    }
                    if (!biten.contains(rwptokill)) {   //inaczej traktuj jak puste
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
                }
                i++;
            }
            //jesli znaleziono 1, to on do usuniecia
            if (amount == 1) {
                return tokill;
            }
            //jesli nic nie znaleziono to tylko ruch
            if (amount == 0) {
                return GameWindow.checkSquare(startedX, startedY);
            }
        }
        //nic nie ma do zabicia
        return null;
    }

    /**
     * Funkcja do zbijania
     * @param tokill
     * @return
     */
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

    /**
     * Funkca do sprawdzania najdłuższego możliwego ruchu
     * @return
     */
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

    /**
     * Funkcja zwracająca ilośc możliwych ruchów
     * @param tX
     * @param tY
     * @param forX
     * @param forY
     * @return
     */
    public int anybites(int tX, int tY, int forX, int forY) {
        int targetX = tX;
        int targetY = tY;
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

    /**
     * Funkca do sprawdzania i wykonywania najdłuższego możliwego ruchu
     * @param xX
     * @param yY
     * @param biten
     * @return
     */
    public int longestway(int xX, int yY, Set<RectangleWithPiece> biten) {

        int targetX;
        int targetY;

        int way = -1; //to sie returnuje
        //int newway;
        int border = GameWindow.getBoardSize();

        if (xX > 0) {
            targetX = 0;
            targetY = yY - xX; //myY + (-1)* myX;
            while (targetY < 0 && targetY != yY) {
                targetX++;
                targetY++;
            }   //^znaleziono punkt ktory bedziemy sprawdzac

            int newway = 0;

            while (newway != -1) {
                //System.out.println("przed while: i nie -1");
                while (GameWindow.checkSquare(targetX, targetY).getPiece() != null) {    //nie ma gdzie isc
                    if (targetX == xX && targetY == yY) { //jesli my sami to nie bedzie - najmniejszy
                        newway = -1;    //nie da sie isc tam bo ciagiem zajete
                        break;
                    }
                    if (biten.contains(GameWindow.checkSquare(targetX, targetY))) { //jesli na liscie to jakby nie bylo
                        System.out.println("udawanie ze puste");
                        break;
                    }
                    targetX++;
                    targetY++;
                }
                if (newway == -1 || (targetX == xX && targetY == yY)) {
                    break;
                }
                RectangleWithPiece move = whatpiecestokill(xX, yY, targetX, targetY, biten);
                if (move == null) {
                    targetX++;
                    targetY++;
                } else if (move == GameWindow.checkSquare(xX, yY)) {
                    //newway = 0;
                    if (0 > way) {
                        way = newway;
                    }
                    newway = -1;
                } else {
                    HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                    if (!alreadybiten.contains(move)) {
                        alreadybiten.add(move);
                        newway = longestway(targetX, targetY, alreadybiten) + 1; //jesli tu jestesmy to musi min ==1;
                        if (newway <= 0) {
                            newway = 1;
                        }
                        if (newway > way) {
                            way = newway;
                        }
                    }
                    targetX++;
                    targetY++;
                }
            }
        }
        if (yY > 0) {
            targetY = 0;
            targetX = xX + yY;
            while (targetX > border && targetX != xX) {
                targetX--;
                targetY++;
            }
            int newway = 0;
            while (newway != -1) {
                while (GameWindow.checkSquare(targetX, targetY).getPiece() != null) {
                    if (targetX == xX && targetY == yY) { //jesli my sami to nie bedzie - najmniejszy
                        newway = -1;
                        break;
                    }
                    if (biten.contains(GameWindow.checkSquare(targetX, targetY))) {
                        break;
                    }
                    targetX--;
                    targetY++;
                }
                if (newway == -1 || (targetX == xX && targetY == yY)) {
                    break;
                }
                RectangleWithPiece move = whatpiecestokill(xX, yY, targetX, targetY, biten);
                if (move == null) {
                    targetX--;
                    targetY++;
                } else if (move == GameWindow.checkSquare(xX, yY)) {
                    if (0 > way) {
                        way = newway;
                    }
                    newway = -1;
                } else {
                    HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                    if (!alreadybiten.contains(move)) {
                        alreadybiten.add(move);
                        newway = longestway(targetX, targetY, alreadybiten) + 1;
                        if (newway <= 0) {
                            newway = 1;
                        }
                        if (newway > way) {
                            way = newway;
                        }
                    }
                    targetX--;
                    targetY++;
                }
            }
        }
        if (xX < border) {
            targetX = border;
            targetY = yY + (targetX - xX);
            while (targetY > border && targetY != yY) {
                targetX--;
                targetY--;
            }
            int newway = 0;
            while (newway != -1) {
                while (GameWindow.checkSquare(targetX, targetY).getPiece() != null) {
                    if (targetX == xX && targetY == yY) {
                        newway = -1;
                        break;
                    }
                    if (biten.contains(GameWindow.checkSquare(targetX, targetY))) { //jesli na liscie to jakby nie bylo
                        break;
                    }
                    targetX--;
                    targetY--;
                }
                if (newway == -1 || (targetX == xX && targetY == yY)) {
                    break;
                }
                RectangleWithPiece move = whatpiecestokill(xX, yY, targetX, targetY, biten);
                if (move == null) {
                    targetX--;
                    targetY--;
                } else if (move == GameWindow.checkSquare(xX, yY)) {
                    if (0 > way) {
                        way = newway;
                    }
                    newway = -1;
                } else {

                    HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                    if (!alreadybiten.contains(move)) {
                        alreadybiten.add(move);
                        newway = longestway(targetX, targetY, alreadybiten) + 1;
                        if (newway <= 0) {
                            newway = 1;
                        }
                        if (newway > way) {
                            way = newway;
                        }
                    }
                    targetX--;
                    targetY--;
                }
            }
        }
        if (yY < border) {
            targetY = border;
            targetX = xX - (targetY - yY);
            while (targetX < 0 && targetX != xX) {
                targetX++;
                targetY--;
            }
            int newway = 0;
            while (newway != -1) {
                while (GameWindow.checkSquare(targetX, targetY).getPiece() != null) {    //nie ma gdzie isc
                    if (targetX == xX && targetY == yY) {
                        newway = -1;
                        break;
                    }
                    if (biten.contains(GameWindow.checkSquare(targetX, targetY))) {
                        break;
                    }
                    targetX++;
                    targetY--;
                }
                if (newway == -1 || (targetX == xX && targetY == yY)) {
                    break;
                }
                RectangleWithPiece move = whatpiecestokill(xX, yY, targetX, targetY, biten);
                if (move == null) {
                    targetX++;
                    targetY--;
                } else if (move == GameWindow.checkSquare(xX, yY)) {
                    if (0 > way) {
                        way = newway;
                    }
                    newway = -1;
                } else {
                    HashSet<RectangleWithPiece> alreadybiten = new HashSet<>(biten);
                    if (!alreadybiten.contains(move)) {
                        alreadybiten.add(move);
                        newway = longestway(targetX, targetY, alreadybiten) + 1;
                        if (newway <= 0) {
                            newway = 1;
                        }
                        if (newway > way) {
                            way = newway;
                        }
                    }
                    targetX++;
                    targetY--;
                }
            }
        }
        return way;
    }

}

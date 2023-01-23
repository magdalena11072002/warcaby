package org.example.logic.pieces;

import javafx.scene.control.Button;
import org.example.data.Date;
import org.example.gui.GameWindow;
import org.example.logic.Pawn;

/**
 * Klasa pionek która jest ogólna dla pionka i królowej
 */
public abstract class Piece extends Button implements Movement {

    /**
     * @param color - kolor
     * @param myX - współrzędna X figury
     * @param myY - współrzędna Y figury
     * @param backBites - parametr który informuje czy są bicia do tyłu
     * @param queenAnywhereAfterCapture - parametr informuje czy damki mogą stanąć dalej niż zaraz za zbitym pionkiem
     * @param coordinates - instancja klasy Date umożliwiająca korzystanie ze znajdującyhc się w niej funkcji
     * @param gameWindow - instancja klasy GameWindow umożliwiająca korzystanie z funkcji tej klasy które nie są statyczne
     */
    protected String color; //dwie wartosci, podana przez hex : #FFFFFF-bialy , #000000-czarny.
    protected int myX;
    protected int myY;

    public static boolean backBites;
    public static boolean queenAnywhereAfterCapture;

    protected Date coordinates;

    /**
     * Funkcja do ustawienia koloru oraz współrzędnych danej figury
     * @param color
     * @param myY
     * @param myX
     */
    public Piece(String color, int myY, int myX) {
        //konfiguracja przycisku
        super();
        setStyle(
                "-fx-background-radius: 20; "
                        + "-fx-min-width: 40; "
                        + "-fx-min-height: 40; "
                        + "-fx-background-color: " + color + ";"
                        //+ "-fx-translate-y: 5; "
                        + "-fx-translate-x: 5;"
                        + "-fx-border-color: " + color + ";"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-width: 8 ;"
        );

        setupMouseEvents();
        this.color = color;
        this.myX = myX;
        this.myY = myY;
    }

    GameWindow gameWindow=new GameWindow(); //

    /**
     * Funkcja zwracająca kolor
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     * Funkcja pozwalajaca nadac pionkowi kolor
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Funkcja do obsługi myszki
     */
    private void setupMouseEvents() {
        setOnMouseClicked(e -> {
            if (gameWindow.getPlayer().equals(this.color)) {
                coordinates = GameWindow.getCoordinates();

                //jesli nic nie wybrane
                if ((coordinates.getSelectedY() == -1 && coordinates.getSelectedX() == -1) || coordinates.getAmountOfMoves() == 0) {

                    System.out.print("wybrałem przycisk");
                    System.out.print(" myX =" + this.myX);
                    System.out.println(" myY =" + this.myY);
                    coordinates.setSelectedX(this.myX);
                    coordinates.setSelectedY(this.myY);

                    if(this.getClass() == Pawn.class){
                        System.out.println(" ! pawn ");
                        longestway();
                    }

                    GameWindow.setCoordinates(coordinates);
                } else if (coordinates.getAmountOfMoves() == 0 && coordinates.getSelectedX() == myX && coordinates.getSelectedY() == myY) {

                    System.out.println("odznaczam przycisk x: " + myX + " y: " + myY);
                    coordinates.setSelectedX(-1);
                    coordinates.setSelectedY(-1);

                    GameWindow.setCoordinates(coordinates);
                } else {
                    System.out.println("nie mozna wybrac");
                }

            } else {
                System.out.println("zly gracz");
            }
        });
    }
    public void setEndingMouseEvents(){
        setOnMouseClicked(e -> {
        });
    }
}

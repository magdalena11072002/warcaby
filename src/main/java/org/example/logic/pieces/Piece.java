package org.example.logic.pieces;

import javafx.scene.control.Button;
import org.example.data.Date;
import org.example.gui.GameWindow;
import org.example.logic.Pawn;

public abstract class Piece extends Button implements Movement {

    protected String color; //dwie wartosci, podana przez hex : #FFFFFF-bialy , #000000-czarny.
    protected int myX;
    protected int myY;

    public static boolean backBites;
    public static boolean queenAnywhereAfterCapture;

    protected Date coordinates;

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

    GameWindow gameWindow=new GameWindow();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

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

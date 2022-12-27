package org.example.Logic.pieces;

import javafx.scene.control.Button;
import org.example.Data.Date;
import org.example.GUI.GameWindow;

public abstract class Piece extends Button implements Movement{

    protected String color; //dwie wartosci, podana przez hex : #FFFFFF-bialy , #000000-czarny. // todo sprawdzic czy mozna przez klase color
    protected int myX;
    protected int myY;
    protected final Date coordinates = new Date(); //todo zastanowic sie czy tego mozna sie pozbyc

    public Piece(String color, int myY, int myX) {
        //konfiguracja przycisku
        super();
        setStyle("-fx-background-color: " + color + ";");
        setMaxSize(20, 20);
        //todo wycentrowac + zmienic na kolka
        setupMouseEvents();
        this.color = color;
        this.myX = myX;
        this.myY = myY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private void setupMouseEvents() {
        setOnMouseClicked(e -> {
            if(GameWindow.getPlayer().equals(this.color)){
                System.out.println("wybrałem przycisk");
                System.out.println("myX =" + this.myX);
                System.out.println("myY =" + this.myY);
                coordinates.setSelectedX(this.myX);
                coordinates.setSelectedY(this.myY);
            }
            else{
                System.out.println("zly gracz");
            }
        });
    }
}

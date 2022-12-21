package org.example.Logic.pieces;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.GUI.GameWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Piece extends Button implements Movement {
    protected String color; //dwie wartosci, podana przez hex : #FFFFFF-bialy , #000000-czarny. //todo sprawdzic czy mozna prze zklase color
    protected Rectangle home;

    public Piece(String color, Rectangle home) {
        //konfiguracja przycisku
        super();
        setStyle("-fx-background-color: "+color+";");
        setMaxSize(20,20);
        //todo wycentrowac
        setupMouseEvents();

        this.color = color;
        this.home = home;
    }

    public Rectangle getHome() {
        return home;
    }

    public void setHome(Rectangle home) {
        this.home = home;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private void setupMouseEvents(){
        setOnMouseClicked(e->{
            showAvailableMoves();
        });

        setOnMouseEntered(e->{
            this.home.setFill(Color.YELLOW);
        });

        setOnMouseExited(e->{
            this.home.setFill(Color.GREY);
        });
    }
}

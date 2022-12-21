package org.example.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.Data.Date;
import org.example.GUI.components.RectangleWithPiece;
import org.example.Logic.Pawn;

import java.util.ArrayList;

public class GameWindow extends Application {
    private static Date date;

         //usunac/zmienic/nadawac na biezaco
        static ArrayList<Rectangle> rectangles=new ArrayList<>();

        public static void display() {

           // int size =date.getSize() ; todo naprawic blad NPE
            int size =10;

            Stage stage = new Stage();

            //layout
            BorderPane root = new BorderPane(); //zeby mozna bylo pozniej cos dodac innego jeszcze np z boku
            root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
            root.setPadding(new Insets(25)); //marginesy

            GridPane board = new GridPane();//do samej planszy
            //wypelnianie root
            root.setCenter(board);
            root.setLeft(null);
            root.setRight(null);
            root.setBottom(null);

            //mozna zrobic zeby zmieniać jakieś polecenia itd
            Label communication = new Label("Rozpoczęto rozgrywke");
            root.setTop(communication);

            //zeby byla jakas zawartosc
            //dodaje kwadraty udajace plansze - prawdopdobnie dodac klasy i pokombinować z StackPane
            for(int i = 0; i < size; i++) {
                for(int j = 0; j< size; j++){
                    RectangleWithPiece square = new RectangleWithPiece(50,50); // klasa rozszerzona o bierke
                    Rectangle rectangle = square.getSquare();
                    rectangles.add(rectangle);
                    if(((i+j)%2) ==0){
                        rectangle.setFill(Color.GREY);//byle jaki kolor dalam, byle rozne byly
                    }
                    board.add(rectangle, i+1,j+1);

                    //todo dodac do roznych trybow gry pionki przyklad dla standardu, powiazac z sizem i trybem gry - jeszcze nie dodany cyba
                    if((j<2) && (rectangle.getFill()==Color.GREY) ){
                        board.add(square.createPawn("#000000"), i+1,j+1);
                    }
                    else if ((j>7) && (rectangle.getFill()==Color.GREY))
                    board.add(square.createPawn("#FFFFFF"), i+1,j+1);
                }

            }

            //dzialanie okna
            Scene scene = new Scene(root);
            stage.setTitle("Warcaby - rozgrywka");
            stage.setScene(scene);
            stage.sizeToScene(); //dopasowuje okno do zawartosci
            stage.show();
        }

        public ArrayList<Rectangle> getRectangles(){
            return rectangles;
        }
        //musi byc ponoc
        @Override
        public void start(Stage stage){}
    }


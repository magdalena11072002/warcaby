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
    private static int size=10;



    private static Stage stage = new Stage();
    private static BorderPane root = new BorderPane();
         //usunac/zmienic/nadawac na biezaco
        static ArrayList<Rectangle> rectangles=new ArrayList<>();

        private static void setup(){
             //zeby mozna bylo pozniej cos dodac innego jeszcze np z boku
            root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
            root.setPadding(new Insets(25)); //marginesy
            Label communication = new Label("Rozpoczęto rozgrywke");
            root.setTop(communication);
            stage.setTitle("Warcaby - rozgrywka");
        }

        public static void display() {
            setup();

            //w logice
            for(int i = 0; i < size; i++) {
                for(int j = 0; j< size; j++){
                    if((j<2) && (rectangle.getFill()==Color.GREY) ){
                        board.add(square.createPawn("#000000"), i+1,j+1);
                    }
                    else if ((j>7) && (rectangle.getFill()==Color.GREY))
                    board.add(square.createPawn("#FFFFFF"), i+1,j+1);
                }
            }
        }

        public static void refreshDisplay(RectangleWithPiece chessBoard[][]){
            GridPane board = new GridPane();//do samej planszy
            root.setCenter(board);

            for(int i=0; i<chessBoard.length;i++){
                for(int j=0;j<chessBoard[i].length;j++){
                    RectangleWithPiece square = new RectangleWithPiece(50,50); // klasa rozszerzona o bierke
                    Rectangle rectangle = square.getSquare();
                    if(((i+j)%2) ==0){
                        rectangle.setFill(Color.GREY);//byle jaki kolor dalam, byle rozne byly
                    }
                    board.add(rectangle, i+1,j+1);
                     if(square.getPiece()!=null){
                         board.add(square.getPiece(),i+1,j+1);
                     }
                }
            }

            stage.setScene(new Scene(root));
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


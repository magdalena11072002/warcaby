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
import org.example.Logic.MainApplication;
import org.example.Logic.Pawn;

import java.util.ArrayList;

public class GameWindow extends Application {
    private static Stage stage = new Stage();
         //usunac/zmienic/nadawac na biezaco
        static ArrayList<Rectangle> rectangles=new ArrayList<>();

        private static void setup(BorderPane root){
             //zeby mozna bylo pozniej cos dodac innego jeszcze np z boku
            root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
            root.setPadding(new Insets(25)); //marginesy
            Label communication = new Label("Rozpoczęto rozgrywke");
            root.setTop(communication);
            stage.setTitle("Warcaby - rozgrywka");
        }

        public static void display() {
            MainApplication mainApplication = new MainApplication();
            mainApplication.run();
        }

        public static void refreshDisplay(RectangleWithPiece[][] chessBoard){
            GridPane board = new GridPane();//do samej planszy


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

            BorderPane root = new BorderPane();
            setup(root);
            root.setCenter(board);
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


package org.example.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.Data.Date;
import org.example.Data.TypeData;
import org.example.GUI.components.RectangleWithPiece;


public class GameWindow extends Application {
    private static RectangleWithPiece[][] chessBoard; // to raczej git
    //private static int size = 10;  // to ma byc programowalne, tzn z klasy Hello Application po wybranej opcji powinno to byc nastawione, moze cos display(int size) itd;
    private static Date coordinates = new Date(); //mega wazne!!! warto sie zastanowic czy nie idzie tego usunac i przekazywac wartosci przez parametry
    private static String player = "#FFFFFF";
    private static Stage stage = new Stage();  // chyba tak ale nie wiem xd
    //private static String option1;
    //usunac/zmienic/nadawac na biezaco


    private static void setup(BorderPane root) {
        //zeby mozna bylo pozniej cos dodac innego jeszcze np z boku
        root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
        root.setPadding(new Insets(25)); //marginesy
        Label communication = new Label("Rozpoczęto rozgrywke");
        root.setTop(communication);
        stage.setTitle("Warcaby - rozgrywka");
    }

    public static void display(TypeData type) {
        Board board = new Board(type);
        chessBoard = board.createBoard();
        refreshDisplay();

    }

    public static void refreshDisplay() {  //nie wiem czy to jest najoptymalniejszy sposob odswierzenia, tutaj usuwam wszystko i pobieram z chessboard
        GridPane board = new GridPane();//do samej planszy

        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                RectangleWithPiece square = chessBoard[i][j]; // klasa rozszerzona o bierke
                /*if (((i + j) % 2) == 0) {
                    square.setFill(Color.GREY);//byle jaki kolor dalam, byle rozne byly
                }*/
                board.add(square, i + 1, j + 1);
                if (square.getPiece() != null) {
                    board.add(square.getPiece(), i + 1, j + 1);
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

    //musi byc ponoc
    @Override
    public void start(Stage stage) {
    }

    public static void makeMove(int x, int y) {
        coordinates.setTargetX(x);
        coordinates.setTargetY(y);
        if (coordinates.getSelectedX() != -1 && coordinates.getSelectedY() != -1) { // w sumie mozna uzyc zaleznosci - wydaje sie byc rozsadniejsze aby odpowiedzialnosc za posuniecie lezala na figurze
            if (chessBoard[coordinates.getSelectedX()][coordinates.getSelectedY()].getPiece().makeMove()) {// dodatkowo to jest tylko dla prostych posuniec bez bicia

                RectangleWithPiece selectSquare = chessBoard[coordinates.getSelectedX()][coordinates.getSelectedY()];
                RectangleWithPiece targetSquare = chessBoard[coordinates.getTargetX()][coordinates.getTargetY()];

                targetSquare.setPiece(selectSquare.getPiece());
                selectSquare.setPiece(null);

                if (player.equals("#FFFFFF")) player = "#000000";
                else player = "#FFFFFF";

                refreshDisplay();

                System.out.println("ruszyłem");
                coordinates.setSelectedX(-1);
                coordinates.setSelectedY(-1);
                coordinates.setTargetY(-1);
                coordinates.setTargetX(-1);
            }

        }
    }

    public static String getPlayer() {
        return player;
    }

    public static RectangleWithPiece checkSquare(int x, int y) {
        return chessBoard[x][y];
    }

}


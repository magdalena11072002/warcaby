package org.example.GUI;

import java.util.Objects;
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
import org.example.Logic.pieces.Piece;


public class GameWindow extends Application {
    private static RectangleWithPiece[][] chessBoard; // to raczej git
    //private static int size = 10;  // to ma byc programowalne, tzn z klasy Hello Application po wybranej opcji powinno to byc nastawione, moze cos display(int size) itd;
    private static Date coordinates = new Date(); //mega wazne!!! warto sie zastanowic czy nie idzie tego usunac i przekazywac wartosci przez parametry
    private static String player = "#FFFFFF";
    private static Stage stage = new Stage();  // chyba tak ale nie wiem xd
    //private static String option1;
    //usunac/zmienic/nadawac na biezaco

    private static int maxWay = 0;


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
        GridPane board = new GridPane(); //do samej planszy

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

        if (coordinates.getSelectedX() != -1 && coordinates.getSelectedY() != -1) {
            coordinates.setTargetX(x);
            coordinates.setTargetY(y);

            RectangleWithPiece selectSquare;
            RectangleWithPiece targetSquare;

            if ( coordinates.getAmountOfMoves() == 0 ) { //na poczatku rundy - pobierz maxymalna bicie
                maxWay = getMaxWay();
                System.out.println("POCZATEK");
                /*if (maxWay == -1){
                    endOfGame(player,"brak mozliwosci wykonania ruchu przez przeciwnika");
                    return;
                }*/
            }

            if (chessBoard[coordinates.getSelectedX()][coordinates.getSelectedY()].getPiece().makeMove()) {

                selectSquare = chessBoard[coordinates.getSelectedX()][coordinates.getSelectedY()];
                targetSquare = chessBoard[coordinates.getTargetX()][coordinates.getTargetY()];

                if (coordinates.getAmountOfMoves() == 0) { //jesli tylko skoczyl
                    //zwalnia select
                    coordinates.setSelectedX(-1);
                    coordinates.setSelectedY(-1);
                } else { //jesli zbil
                    //przekazuje do select
                    coordinates.setSelectedX(coordinates.getTargetX());
                    coordinates.setSelectedY(coordinates.getTargetY());
                }

                targetSquare.setPiece(selectSquare.getPiece());
                selectSquare.setPiece(null);

                System.out.println(targetSquare.getPiece().possibleBitesHere() + " bites");

                if (targetSquare.getPiece().possibleBitesHere() == 0 || coordinates.getAmountOfMoves() == 0) { //jesli ostatnie bicie lub nie bicie

                    if ((Objects.equals(player, "#000000") && coordinates.getTargetY() == 0) || (Objects.equals(player, "#FFFFFF") && coordinates.getTargetY() == chessBoard.length - 1)) {
                        System.out.println("tworzy damke");
                        //zrob damke
                        targetSquare.setPiece(targetSquare.createQueen(targetSquare.getPiece().getColor(), coordinates.getTargetY(), coordinates.getTargetX()));
                    }

                    System.out.println("zrobione kroki" + coordinates.getAmountOfMoves());

                    /*if( maxWay != coordinates.getAmountOfMoves()) {
                        System.out.println("To nie była najdluzsza mozliwosc bicia, tracisz pionka");
                        System.out.println(maxWay+"najdluzsza?");   //
                        targetSquare.setPiece(null); //usuwa tego pionka
                        maxWay = 0;
                    }*/

                    if (Objects.equals(player, "#FFFFFF")) {
                        player = "#000000";
                    } else {
                        player = "#FFFFFF";
                    }
                    System.out.println("zmiana gracza");

                    coordinates.setSelectedX(-1);
                    coordinates.setSelectedY(-1);

                    coordinates.endMove();
                }

                coordinates.setTargetY(-1);
                coordinates.setTargetX(-1);
                refreshDisplay();

                if (amountOfPieces("#FFFFFF") == 0) { //trzeba sprawdzic oba bo moze stracic ostatniego pionka przez niezbicie
                    endOfGame("#FFFFFF", "Zbito wszystkie pionki przeciwnika");
                    //return;
                } else if (amountOfPieces("#000000") == 0) {
                    endOfGame("#000000", "Zbito wszystkie pionki przeciwnika");
                    System.out.println("WYGRANA BIALYCH!!!!\n Zbito wszystkie czarne pionki\n\n Mozesz wylaczyć okno gry");
                    //return;
                }

            }

        }
    }

    public static String getPlayer() {
        return player;
    }

    public static RectangleWithPiece checkSquare(int x, int y) {
        return chessBoard[x][y];
    }

    public static int getBoardSize() {
        return chessBoard.length - 1;
    }

    public static Date getCoordinates() {
        return coordinates;
    }

    public static void setCoordinates(Date coords) {
        coordinates = coords;
    }

    public static int amountOfPieces(String playercolor){
        int amount = 0;
        if (!Objects.equals(playercolor, "#FFFFFF") && !Objects.equals(playercolor, "#000000")) {
            return -1;
        }
        for (RectangleWithPiece[] rectangleWithPieces : chessBoard) {   //zmiana handlerow by nie reagowaly
            for (RectangleWithPiece rectangleWithPiece : rectangleWithPieces) {
                Piece pionek = rectangleWithPiece.getPiece();
                if (pionek != null && Objects.equals(pionek.getColor(), playercolor)) {
                    amount++;
                }
            }
        }
        return amount;
    }

    private static void endOfGame(String loser, String info){
        String winner = Objects.equals(loser, "#FFFFFF") ? "WYGRANA CZARNYCH" : "WYGRANA BIALYCH";

        System.out.println(winner + "!!!!\n" + info + "\n\n Mozesz wylaczyć okno gry");
        player = "#868686"; //jakikolwiek
        for (RectangleWithPiece[] rectangleWithPieces : chessBoard) {
            for (RectangleWithPiece rectangleWithPiece : rectangleWithPieces) {
                rectangleWithPiece.setEndingMouseEvents();
                if(rectangleWithPiece.getPiece() != null){
                    rectangleWithPiece.getPiece().setEndingMouseEvents();
                }
            }
        }
    }

    static int getMaxWay(){

        int amount = -1; //-1 gdy nie mozna sie ruszyc wcale; 0 gdy krok; wiecej == ilosc zbic
        /*  //zakomentowane poki nie ma dla queen
        for (int i=0; i<chessBoard.length; i++) {
            for (int j=0; j<chessBoard[i].length; j++) {
                Piece pionek = chessBoard[i][j].getPiece();
                if ( pionek != null && Objects.equals(pionek.getColor(), player)) {
                //szukaj max jump
                    if (amount < chessBoard[i][j].getPiece().longestway()) {
                        amount = chessBoard[i][j].getPiece().longestway();
                    }
                }
            }
        }*/
        amount = 0;   //
        return amount;
    }

}


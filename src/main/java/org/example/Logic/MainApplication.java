package org.example.Logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.GUI.GameWindow;
import org.example.GUI.components.RectangleWithPiece;

public class MainApplication {
    private int size=10;
    private boolean player; //mozna pomyslec aby to byl kolor
    private RectangleWithPiece[][] chessBoard;


    public void run() {
        createBoard();
    }

    private void createBoard() {
        chessBoard = new RectangleWithPiece[size][size];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                RectangleWithPiece square = new RectangleWithPiece(50, 50);
                Rectangle rectangle = square.getSquare();
                if (((i + j) % 2) == 0) {
                    rectangle.setFill(Color.GREY);
                }
                //itutaj powinna byc implementaja do pionkow moze jakis switch case do trybow gry
                //standard
                if ((j < 2) && ((i + j) % 2) == 0) {
                    square.createPawn("#000000");
                } else if ((j > 7) && ((i + j) % 2) == 0) {
                    square.createPawn("#FFFFFF");
                    //
                    chessBoard[i][j] = square;
                }

            }
        }
        GameWindow.refreshDisplay(chessBoard);


    }
}
